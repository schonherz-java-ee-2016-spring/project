package hu.schonherz.training.web.admin.managedbeans;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;

 
@ManagedBean(name="ttBasicView")
@ViewScoped
public class BasicView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TreeNode roots;
          
    @ManagedProperty("#{themesService}")
    private themesService service;
     
    @PostConstruct
    public void init() {
    	roots = service.createThemes();
    }

	public TreeNode getRoots() {
		return roots;
	}

	public void setRoots(TreeNode roots) {
		this.roots = roots;
	}

	public themesService getService() {
		return service;
	}

	public void setService(themesService service) {
		this.service = service;
	}
 
}