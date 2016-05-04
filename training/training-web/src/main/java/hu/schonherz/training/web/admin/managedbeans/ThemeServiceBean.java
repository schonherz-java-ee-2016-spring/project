package hu.schonherz.training.web.admin.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.vo.ThemeVo;

@ManagedBean(name = "themeServiceBean")
@ApplicationScoped
public class ThemeServiceBean {

	@EJB
	private ThemeService themeService;
	
	private String name;
	private String type;
	private String themeCode;
	private String description;
	private Integer hours;
	private Integer code = 0;

	private TreeNode root;
	
    @PostConstruct
    public void init() {
    	setRoot(createThemes());
    }
	
	public TreeNode createThemes() {
		//get all the "main" themes
		List<ThemeVo> maintypes = themeService.getThemesByType("main");
		//get all the "item" themes
		List<ThemeVo> itemtypes = themeService.getThemesByType("item");
		//rootVo for the root TreeNode - this will not show anything
		ThemeVo rootVo = new ThemeVo();
		TreeNode root = new DefaultTreeNode(rootVo, null);
		//adding all the main types for the node TreeNode
		for (ThemeVo main : maintypes) {
			TreeNode node = new DefaultTreeNode(main, root);
			//where themeCode is equals - creates a TreeNode item for the main TreeNode
			for (ThemeVo item : itemtypes) {
				if (item.getThemeCode().equals(main.getThemeCode())) {
					TreeNode itemNode = new DefaultTreeNode(item, node);
				}
			}
		}

		return root;
	}
	
	public void createMainTheme(){
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		ThemeVo newTheme = new ThemeVo();
		newTheme.setName(name);
		newTheme.setDescription(description);
		newTheme.setType("main");
		System.out.println(code);
		newTheme.setThemeCode(Integer.toString(code++));
		System.out.println(code);
		themeService.createTheme(newTheme);
		setRoot(createThemes());
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Theme created!");
		currentInstance.addMessage(null, msgs);
		
	}

	public ThemeService getThemeService() {
		return themeService;
	}

	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getThemeCode() {
		return themeCode;
	}

	public void setThemeCode(String themeCode) {
		this.themeCode = themeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}