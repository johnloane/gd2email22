package com.dkit.gd2.johnloane;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailTLS
{
    public static void main(String[] args)
    {
        final String username = System.getenv("MAIL_DEFAULT_SENDER");
        final String password = System.getenv("MAIL_PASSWORD");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gd2@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse("john.loane@dkit.ie, D00236203@student.dkit.ie")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear student," + "\n\n This is a test");

            Transport.send(message);
            System.out.println("Done");
        }
        catch(MessagingException me)
        {
            me.printStackTrace();
        }
    }
}
