package util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class JavaMailApp
{

    public static void send(String destinatario, String subject, String texto, String atachFile, final String remetente, final String pass)  {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //final String
        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication(remetente,pass);
                         }
                    });
        session.setDebug(true);
        try {

        	  MimeMessage message = new MimeMessage(session);
              message.setFrom(new InternetAddress(remetente)); //Remetente

              Address[] toUser = InternetAddress.parse(destinatario);  
              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject(subject);//Assunto
              message.setText("");
               MimeBodyPart messageBody =  new MimeBodyPart();
              messageBody.setText(texto);
              MimeBodyPart  anexoBody = new MimeBodyPart();
              FileDataSource fds = new FileDataSource(atachFile);  
              anexoBody.setDataHandler(new DataHandler(fds));
              anexoBody.setFileName(fds.getName());
              Multipart multipart = new MimeMultipart();
              multipart.addBodyPart(messageBody);
              multipart.addBodyPart(anexoBody);
              message.setContent(multipart);
              Transport.send(message);
              System.out.println("Feito!!!");
         } catch (MessagingException e) {
              e.printStackTrace();
        }
        

  }
}

