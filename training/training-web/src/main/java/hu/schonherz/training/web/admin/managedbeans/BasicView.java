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

	private TreeNode root;
          
    @ManagedProperty(value="#{themesService}")
    private ThemeServiceBean service;
    
     
    @PostConstruct
    public void init() {
    	root = service.createThemes();
    }

	public TreeNode getRoot() {
		return root;
	}

	public void setRoots(TreeNode root) {
		this.root = root;
	}

	public ThemeServiceBean getService() {
		return service;
	}

	public void setService(ThemeServiceBean service) {
		this.service = service;
	}
 
}