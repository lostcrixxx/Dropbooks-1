package controller;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class JavaMailApp
{
      public static void main(String[] args) {
          
         
         
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
 
            
            Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("contatodropbooks@gmail.com", "Mudar123");
                             }
                        });
 
            /** Ativa Debug para sessão */
            session.setDebug(true);
 
            try {
 
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("contato@dropbooks.com.br")); //Remetente
 
                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("traceurcristiano@gmail.com");  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Dropbooks");//Assunto
                  message.setText("Obrigado por entrar em contato!");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
 

 
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
      
    
}
