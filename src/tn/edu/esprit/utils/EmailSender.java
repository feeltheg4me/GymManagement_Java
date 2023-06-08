/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author agn
 */
public class EmailSender {
    
    private static String SMTP_SERVER = "smtp.gmail.com";
    private static int SMTP_PORT = 465; // SMTP server port
    private static String PASSWORD = "trzvkhagluedohob";
    private static String SENDER_EMAIL = "aziz.guizani@esprit.tn";
    public static void sendPasswordResetEmail(String newPassword, String reciever) {

        // Recipient's email ID needs to be mentioned.
        String to = reciever;

        // Sender's email ID needs to be mentioned
        String from = SENDER_EMAIL;

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Password reset!");

            // Now set the actual message
            message.setText("Dear User,\n\nYour password has been reset. Please use the following password for login:\n\n"
                    + "New Password: " + newPassword + "\n\n"
                    + "Make sure to change your password after logging in.");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
