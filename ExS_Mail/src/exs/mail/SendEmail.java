package exs.mail;

/**
 *
 * @author Kevin Villalobos A.
 */
import exs.logs.err.Log;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    //Set mail user and pass
    private static String user = "exito@una.cr";
    private static String password = "Exitos2009";
    private static String from = "exito@una.cr";
    private static String host = "smtp.gmail.com";

    public static boolean SendMail(String to, String title, String body) {
        return SendMail_Att(to, title, body, null);
    }

    public static boolean SendMail_Att(String to, String title, String body, String path) {
        Session session = Session.getDefaultInstance(getPropros());
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(title);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            if (path != null) {
                messageBodyPart = new MimeBodyPart();                
                DataSource source = new FileDataSource(path);
                messageBodyPart.setDataHandler(new DataHandler(source));                
                String [] ruta = path.split("/") ;
                String name = ruta[ruta.length -1];
                messageBodyPart.setFileName(name);
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            try {
                transport.connect(host, user, password);
                transport.sendMessage(message, message.getAllRecipients());
            } finally {
                transport.close();
            }

        } catch (MessagingException mex) {
            Log.SendLog(mex.getMessage());
            return false;
        }
        return true;
    }
    
   private static Properties getPropros() {
        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.user", "username");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.EnableSSL.enable", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.user", user);
        properties.put("mail.smtp.password", password);
        properties.setProperty("mail.user", user);
        properties.setProperty("mail.password", password);
        return properties;
    }
}
