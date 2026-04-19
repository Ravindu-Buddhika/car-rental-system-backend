package org.example.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.model.entity.Rental;
import org.example.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendBookingConfirmation(Rental rental) {
        try {
            Context context = new Context();
            context.setVariable("customerName", rental.getCustomer().getFullName());
            context.setVariable("carModel", rental.getCar().getCarModel());
            context.setVariable("plateNumber", rental.getCar().getPlateNumber());
            context.setVariable("pickupInfo", rental.getStartDate().toString());
            context.setVariable("dropoffInfo", rental.getEndDate().toString());
            context.setVariable("amountPaid", String.format("%,.2f", rental.getTotalAmount()));

            String htmlContent = templateEngine.process("booking-email", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String recipientEmail = rental.getCustomer().getEmail();

            if (recipientEmail == null || recipientEmail.isEmpty()) {
                System.err.println("❌ Email Sending Failed: Customer email not found for Rental ID: " + rental.getRentalId());
                return;
            }

            helper.setTo(recipientEmail);
            helper.setSubject("DriveME - Booking Confirmed! (" + rental.getCar().getCarModel() + ")");
            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to: " + recipientEmail);

        } catch (Exception e) {
            System.err.println("❌ Critical Error in EmailService: " + e.getMessage());
            e.printStackTrace();
        }
    }
}