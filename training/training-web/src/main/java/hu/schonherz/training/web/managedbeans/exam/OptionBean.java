package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.exam.service.OptionService;
import hu.schonherz.training.exam.vo.OptionVo;

@ManagedBean(name = "OptionBean")
@SessionScoped
public class OptionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private OptionService optionService;
	/*
	 * 
	 * Todo
	 * 
	 */
	private String newOptionText;	
	
	public void createOption(){
		
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		
		OptionVo option = new OptionVo();
		option.setOptionText(newOptionText);
		
		if (newOptionText.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Name field is empty!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		
	try {
		getOptionService().createOption(option);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}



	public OptionService getOptionService() {
		return optionService;
	}



	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}
	
}
