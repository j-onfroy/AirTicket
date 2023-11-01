package com.company.verification;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailMailing {
    private static final Properties properties = new Properties();

    public void sendEmail(String email, Integer code) throws MessagingException {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        String mail = "davlataliyevdoniyorjon@gmail.com";
        String password = "oiaw tlmj ltkf dvoo";
        Session session = getSession(mail, password);
        MimeMessage message = new MimeMessage(session);
        message.setContent("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Email Verification Code</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f5f5f5;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                            background-color: #ffffff;
                        }
                        .header {
                            background-color: #3498db;
                            color: #fff;
                            text-align: center;
                            padding: 20px;
                        }
                        .content {
                            padding: 20px;
                        }
                        .verification-code {
                            background-color: #3498db;
                            color: #fff;
                            padding: 10px;
                            text-align: center;
                            font-size: 24px;
                            border-radius: 5px;
                        }
                        .instructions {
                            margin-top: 20px;
                        }
                        .button {
                            background-color: #3498db;
                            color: #fff;
                            text-align: center;
                            padding: 10px;
                            border-radius: 5px;
                            text-decoration: none;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>Email Verification</h1>
                        </div>
                        <div class="content">
                            <p>Hello,</p>
                            <p>Your verification code is:</p>
                            <div class="verification-code">%d</div>
                            <p class="instructions">Please use this code to verify your email address.</p>
                            <a class="button" href="#">Verify Email</a>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(code), "text/html");
        message.setFrom(new InternetAddress(mail));
        message.setSubject("Your verification code ");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        Transport.send(message);
    }

    private static Session getSession(String mail, String password) {
        return Session.getInstance(EmailMailing.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });
    }
}
