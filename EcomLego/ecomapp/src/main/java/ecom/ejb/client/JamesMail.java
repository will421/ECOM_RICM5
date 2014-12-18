package ecom.ejb.client;  
  
import java.util.Properties;  
  

import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  

import org.apache.commons.codec.digest.DigestUtils;
  
public class JamesMail {  
      
    public JamesMail(String fn, String ln, String mail){  
        /***** CHANGE THESE FOUR VARIABLE VALUES TO REFLECT YOUR ENVIRONMENT ******/  
        String user = "user";  // Newly created user on JAMES  
        String password = "mpduser"; // user password  
          
        String fromAddress = "no-reply@byl.com"; 
        String toAddress = mail;  
           
          
        // Create a mail session  
        Properties properties = new Properties();  
        properties.put("mail.smtp.host", "localhost");  
        properties.put("mail.smtp.port", "6667");  
        properties.put("mail.smtp.username", user);  
        properties.put("mail.smtp.password", password);  
        Session session = Session.getDefaultInstance(properties, null);  
          
        try   
        {  
            Message message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(fromAddress));  
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));  
              
            message.setSubject("Thank you for register on our website");  
            message.setText("Hi,\n\n\n"
            		+ "Thank you for register on ByYourLego.com"
            		+ "\n\n"
            		+ "Any usefull information :\n"
            		+ "login : "+mail
            		+ "\n password : keep it secret"
            		+ "\n Please click on this link to active your account : https://54.149.169.177:8181/ecom/"+mail+"/"+DigestUtils.sha1Hex(password)
            		+ "\n Have fun on our website \n\n"
            		+ "---------- BYL team");  
            Transport.send(message);  
              
            System.out.println("Email sent successfully to : "+mail);  
        }  
        catch (MessagingException e)   
        {  
            e.printStackTrace();  
        }  
    }  
}  