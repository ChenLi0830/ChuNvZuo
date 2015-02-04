package com.springapp.bean;




import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

class SendHTMLImagesInEmail {
    public static void main(String[] args) throws Exception{

        final String username = "lulugeo.li@gmail.com";//change accordingly
        final String password = "Wangwei19820510";//change accordingly
        System.out.println("Sending mail...");
        Properties props = new Properties();
        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("HTML  mail with images");
        message.setFrom(new InternetAddress("lulugeo.li@gmail.com"));
        message.setContent
                ("<h1>This is a test</h1>"
                                + "<img src=\"http://www.rgagnon.com/images/jht.gif\">",
                        "text/html");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress("lulugeo.li@gmail.com"));

        transport.connect();
        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}