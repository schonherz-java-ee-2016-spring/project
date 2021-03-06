package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.mail.Session;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name="registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;

	private String username;
	private String fullname;
	private String email;
	private String message;

	private String password;

	private String passwordConfirm;
	
	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
    
	@ManagedProperty(value="#{mailSenderBean}")
    private MailSenderBean mailSenderBean;
	
	@Resource(mappedName="java:jboss/mail/Default")
	private Session mailSessionSeznam;

	public void registration() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		UserVo userVo = new UserVo();
		userVo.setUserName(username);
		userVo.setFullName(fullname);
		userVo.setEmail(email);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String uuid = UUID.randomUUID().toString();
		userVo.setPassword(bCryptPasswordEncoder.encode(uuid));
		userVo.setHashCode(bCryptPasswordEncoder.encode(uuid));

		try {
			// itt kell majd az userService regisztrációs szolgáltatását
			// meghívni, majd ha lesz.
			userService.registrationUser(userVo);
			message = "http://" + currentInstance.getExternalContext().getRequestServerName()
					+ ":" + currentInstance.getExternalContext().getRequestServerPort() 
					+ currentInstance.getExternalContext().getRequestContextPath()
					+ "/public/setupPassword.xhtml?code=" + userVo.getHashCode();
			mailSenderBean.sendMail(mailSessionSeznam, "noreply@javatraining.hu", email, "password", message);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("errorreg"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
			return;
		}

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
				bundle.getString("sucreg"));
		currentInstance.addMessage("growl", facesMessage);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MailSenderBean getMailSenderBean() {
		return mailSenderBean;
	}

	public void setMailSenderBean(MailSenderBean mailSenderBean) {
		this.mailSenderBean = mailSenderBean;
	}

	public Session getMailSessionSeznam() {
		return mailSessionSeznam;
	}

	public void setMailSessionSeznam(Session mailSessionSeznam) {
		this.mailSessionSeznam = mailSessionSeznam;
	}
	
}
