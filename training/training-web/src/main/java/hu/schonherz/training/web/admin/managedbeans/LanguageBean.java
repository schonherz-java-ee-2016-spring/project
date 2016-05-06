package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="languageBean")
@SessionScoped
public class LanguageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String locale = "hu";
	
	private boolean disabledHU;
	
	private boolean disabledEN;
	
	public String setToHun(){
		locale = "hu";
		disabledHU = true;
		disabledEN = false;
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		return path + "index.xhtml";
	}
	
	public String setToEn(){
		locale = "en";
		disabledHU = false;
		disabledEN = true;
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		return path + "index.xhtml";

	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean getDisabledHU() {
		return disabledHU;
	}

	public void setDisabledHU(boolean disabledHU) {
		this.disabledHU = disabledHU;
	}

	public boolean getDisabledEN() {
		return disabledEN;
	}

	public void setDisabledEN(boolean disabledEN) {
		this.disabledEN = disabledEN;
	}

}
