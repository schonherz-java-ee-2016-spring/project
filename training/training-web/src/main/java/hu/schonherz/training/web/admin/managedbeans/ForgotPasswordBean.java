package hu.schonherz.training.web.admin.managedbeans;

import java.io.IOException;
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
	private String forgotPasswordUName;
	private String message;
	private String code;
	private String newPassword;
	private String newPasswordConfirm;

	@ManagedProperty(value = "#{mailSenderBean}")
	private MailSenderBean mailSenderBean;

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSessionSeznam;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	public void forgotPasswordSendMail() {
		UserVo testUser = new UserVo();
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		try {
			testUser = userService.findUserByEmail(forgotPasswordEmail);
			if (testUser == null) {
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email doesn't exists!");
				currentInstance.addMessage(null, msgs);
				return;
			}
		} catch (Exception e1) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email doesn't exists!");
			currentInstance.addMessage(null, msgs);
			e1.printStackTrace();
			return;
		}
		try {
			// Random password generation
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String uuid = (UUID.randomUUID().toString());
			uuid = uuid.substring(0, 8);
			testUser.setHashCode(bCryptPasswordEncoder.encode(uuid));
			userService.registrationUser(testUser);
			message = "http://" + currentInstance.getExternalContext().getRequestServerName()
					+ ":" + currentInstance.getExternalContext().getRequestServerPort() 
					+ currentInstance.getExternalContext().getRequestContextPath()
					+ "/public/setupPassword.xhtml?code=" + testUser.getHashCode();
			mailSenderBean.sendMail(mailSessionSeznam, "noreply@javatraining.hu", forgotPasswordEmail, "password", message);
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Setting up hashCode!");
			currentInstance.addMessage(null, msgs);
			e.printStackTrace();
			return;
		}
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Succes mail!");
		currentInstance.addMessage(null, msgs);
		forgotPasswordEmail = null;
		forgotPasswordUName = null;
		return;
	}

	public void hashConfirm() {
		UserVo testVo = null;
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		testVo = userService.findUserByHashCode(code);
		if (testVo == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("hashError.xhtml");
			} catch (IOException e) {
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!",
						"User with that token doesn't exists!");
				currentInstance.addMessage(null, msgs);
				e.printStackTrace();
				return;
			}
		}

	}

	public void updatePassword() {
		UserVo testVo = null;
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		testVo = userService.findUserByHashCode(code);
		System.out.println(testVo.getFullName());
		if (newPassword == null || newPasswordConfirm == null) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Passwords must filled!");
			currentInstance.addMessage(null, msgs);
			return;
		} else if (!newPassword.equals(newPasswordConfirm)) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords not match!");
			currentInstance.addMessage(null, msgs);
			return;
		}
		try {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			testVo.setPassword(bCryptPasswordEncoder.encode(newPassword));
			userService.updateUser(testVo);
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Update password error!");
			currentInstance.addMessage(null, msgs);
			e.printStackTrace();
			return;
		}
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Password updated for" + testVo.getFullName());
		currentInstance.addMessage(null, msgs);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			FacesMessage msgs2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "Redirect error!");
			currentInstance.addMessage(null, msgs2);
			e.printStackTrace();
		}
		newPassword = null;
		newPasswordConfirm = null;
		return;
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
