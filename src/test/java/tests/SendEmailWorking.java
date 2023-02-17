package tests;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

import org.testng.annotations.Test;

public class SendEmailWorking {

	//public static void main(String[] args)
	@Test
	public void testmail()
	{
		Properties props= new Properties();
		props.put("mail.smtp.starttls.enable", "true"); 
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");  
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");  

		Session session= Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("user@gmail.com", "password");

			}
		});

		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress("singaraomaddineni@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("maddinenilatha522@gmail.com"));
			message.setSubject("Testing subject");

			BodyPart messageBodyPart1=new MimeBodyPart();
			messageBodyPart1.setText("This is message body");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = "C:\\Users\\singaraomaddineni\\git\\Test_Shruthi\\src\\testcases\\Testdata.txt";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart1);

			// add body part 2
		multipart.addBodyPart(messageBodyPart2);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}
	}

}
