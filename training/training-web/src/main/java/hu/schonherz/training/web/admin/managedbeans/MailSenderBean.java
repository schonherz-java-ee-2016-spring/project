package hu.schonherz.training.web.admin.managedbeans;

import java.util.Properties;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean(name = "mailSenderBean")
@ViewScoped
public class MailSenderBean {
	
	
	@Resource(mappedName="java:jboss/mail/Default")
	private Session mailSessionSeznam;
	
	  public void sendMail( Session mailSessionSeznam, String mailFrom, String sMailTo, String sSubject, String sMailText) throws MessagingException {
	       
		  Properties props = System.getProperties();
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.port", "465");
		  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		  props.put("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.socketFactory.fallback", "false");	  
		  Session session = Session.getDefaultInstance(props, null);
		  
		  MimeMessage message2 = new MimeMessage( session );
		  message2.setFrom( new InternetAddress( mailFrom ) );
		  message2.addRecipient( Message.RecipientType.TO, new InternetAddress( sMailTo ) );
		  message2.setSubject( sSubject );
		  message2.setContent(sMailText, "text/html; charset=utf-8");	  
		  
		  Transport transport = session.getTransport("smtp");
		  transport.connect("smtp.gmail.com", "tesztu70", "dummypassword" ); 
		  transport.sendMessage( message2, message2.getAllRecipients() ); 
		  
//		  MimeMessage message = new MimeMessage( mailSessionSeznam );     
//	       message.setFrom( new InternetAddress( mailFrom ) );
//	       message.addRecipient( Message.RecipientType.TO, new InternetAddress( sMailTo ) );
//	       message.setContent(sMailText, "text/html; charset=utf-8");
//	       message.setSubject( sSubject );
//	       Transport.send( message ); 
	       }

	public Session getMailSessionSeznam() {
		return mailSessionSeznam;
	}

	public void setMailSessionSeznam(Session mailSessionSeznam) {
		this.mailSessionSeznam = mailSessionSeznam;
	}
	
}
