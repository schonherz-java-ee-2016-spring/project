package hu.schonherz.training.web.admin.managedbeans;

import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Session;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "forgotPasswordBean")
@ViewScoped
public class ForgotPasswordBean {

	@EJB
	UserService userService;
	
	private String forgotPasswordEmail;
	private String forgotPasswordFName;
	private String forgotPasswordUName;

	
    @ManagedProperty(value="#{mailSenderBean}")
    private MailSenderBean mailSenderBean;
	
	@Resource(mappedName="java:jboss/mail/Default")
	private Session mailSessionSeznam;
	
	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
	
	
	public void forgotPasswordSendMail() {
		UserVo testUser = null;
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		// Confirm email
		if (forgotPasswordEmail == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("emailReq"));
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		try {
			testUser = userService.findUserByEmail(forgotPasswordEmail);
			// Random password generation
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String uuid = (UUID.randomUUID().toString());
			uuid = uuid.substring(0, 8);
			testUser.setPassword(bCryptPasswordEncoder.encode(uuid));
			testUser.setFullName(forgotPasswordFName);
			testUser.setUserName(forgotPasswordUName);
			testUser.setEmail(forgotPasswordEmail);
			userService.registrationUser(testUser);
			mailSenderBean.sendMail(mailSessionSeznam, "SCHTraining", forgotPasswordEmail, "password", uuid);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("Error"),
					bundle.getString("Email doesn't exists"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
	}

	public String getForgotPasswordEmail() {
		return forgotPasswordEmail;
	}

	public void setForgotPasswordEmail(String forgotPasswordEmail) {
		this.forgotPasswordEmail = forgotPasswordEmail;
	}


	public MailSenderBean getMailSenderBean() {
		return mailSenderBean;
	}

	public void setMailSenderBean(MailSenderBean mailSenderBean) {
		this.mailSenderBean = mailSenderBean;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public String getForgotPasswordFName() {
		return forgotPasswordFName;
	}

	public void setForgotPasswordFName(String forgotPasswordFName) {
		this.forgotPasswordFName = forgotPasswordFName;
	}

	public String getForgotPasswordUName() {
		return forgotPasswordUName;
	}

	public void setForgotPasswordUName(String forgotPasswordUName) {
		this.forgotPasswordUName = forgotPasswordUName;
	}
	
}
