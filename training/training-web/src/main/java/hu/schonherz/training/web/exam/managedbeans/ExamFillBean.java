package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean(name = "examFillBean")
@ViewScoped
public class ExamFillBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String onFlowProcess(FlowEvent event) {
		
		return event.getNewStep();
		

	}
}
