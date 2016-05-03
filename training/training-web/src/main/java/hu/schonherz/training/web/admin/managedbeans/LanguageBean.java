package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="languageBean")
@SessionScoped
public class LanguageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String locale = "hu";
	
	public String setToHun(){
		locale = "hu";
		return "";
	}
	
	public String setToEn(){
		locale = "en";
		return "";
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
