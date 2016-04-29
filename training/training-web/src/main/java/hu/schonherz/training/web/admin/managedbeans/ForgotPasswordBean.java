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
	private String message;
	private String code;
	private String newPassword;
	private String newPasswordConfirm;
	
	
    @ManagedProperty(value="#{mailSenderBean}")
    private MailSenderBean mailSenderBean;
	
	@Resource(mappedName="java:jboss/mail/Default")
	private Session mailSessionSeznam;
	
	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
	
	
	public void forgotPasswordSendMail() {
		
		UserVo testUser = new UserVo();
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
			//testUser.setPassword(bCryptPasswordEncoder.encode(uuid));
			testUser.setHashCode(bCryptPasswordEncoder.encode(uuid));
			//testUser.setFullName(forgotPasswordFName);
			//testUser.setUserName(forgotPasswordUName);
			//testUser.setEmail(forgotPasswordEmail);
			userService.registrationUser(testUser);
			message = "http://localhost:8080/training-web/public/setupPassword.xhtml?code=" + testUser.getHashCode();
			mailSenderBean.sendMail(mailSessionSeznam, "SCHTraining", forgotPasswordEmail, "password", message);
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("Error"),
					bundle.getString("Email doesn't exists"));
			currentInstance.addMessage(null, msgs);
			e.printStackTrace();
		}
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Succes mail!");
		currentInstance.addMessage(null, msgs);
	}
	
	public void resetPassword() {
		UserVo vo = null;
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		vo = userService.findUserByHashCode(code);
		if (vo.getHashCode().equals(code)) {
			System.out.println(vo.getFullName());
			if (newPassword == null || newPasswordConfirm == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Passwords must filled!");
				currentInstance.addMessage(null, facesMessage);
				return;
			} else if (!newPassword.equals(newPasswordConfirm)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Passwords not match!");
				currentInstance.addMessage(null, facesMessage);
				return;
			}
			try {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				vo.setPassword(bCryptPasswordEncoder.encode(newPassword));
				userService.updateUser(vo);
			} catch (Exception e) {
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("Error"),
						bundle.getString("Update password error!"));
				currentInstance.addMessage(null, msgs);
				e.printStackTrace();
			}
		} else {
			System.out.println("NULL VO");
			System.out.println(vo.getHashCode());
			System.out.println(code);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!",
					"User with that token doesn't exists!");
			currentInstance.addMessage(null, msgs);
		}
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", vo.getFullName());
		currentInstance.addMessage(null, msgs);

	}
	public void updatePassword(){
		
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	
}
