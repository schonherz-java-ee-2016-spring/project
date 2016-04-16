package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.exam.service.OptionService;

@ManagedBean(name = "OptionBean")
@SessionScoped
public class OptionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private OptionService questionService;
	
	
	
	
	
}
