import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void sendEmail(String recipient, String otp) {
        final String senderEmail = "imranhasanshuvo5@gmail.com";
        final String senderPassword = "feef qibi npff qidc";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("OTP FRIENDS FOR EVER FARMA");
            message.setText("Your OTP code is: " + otp);

            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipient);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
