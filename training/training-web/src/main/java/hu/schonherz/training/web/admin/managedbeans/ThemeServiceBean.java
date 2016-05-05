package hu.schonherz.training.web.admin.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeSelectEvent;
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
	private boolean disabled;
	private boolean mainSelected;

	private TreeNode root;
	private TreeNode selectedNode;

	@PostConstruct
	public void init() {
		root = createThemes();
		mainSelected = true;
		disabled = true;
	}

	public TreeNode createThemes() {
		// get all the "main" themes
		List<ThemeVo> maintypes = themeService.getThemesByType("main");
		// get all the "item" themes
		List<ThemeVo> itemtypes = themeService.getThemesByType("item");
		// rootVo for the root TreeNode - this will not show anything
		ThemeVo rootVo = new ThemeVo();
		TreeNode root = new DefaultTreeNode(rootVo, null);
		// adding all the main types for the node TreeNode
		for (ThemeVo main : maintypes) {
			TreeNode node = new DefaultTreeNode(main, root);
			// where themeCode is equals - creates a TreeNode item for the main
			// TreeNode
			for (ThemeVo item : itemtypes) {
				if (item.getThemeCode().equals(main.getThemeCode())) {
					TreeNode itemNode = new DefaultTreeNode(item, node);
				}
			}
		}

		return root;
	}

	public void createTheme() {
		ThemeVo newTheme = new ThemeVo();
		newTheme.setName(name);
		newTheme.setDescription(description);
		if (selectedNode != null) {
			ThemeVo testVo = themeService.getThemeByName(selectedNode.getData().toString());
			if (testVo.getType().equals("main")) {
				if (testVo.getHours() == null) {
					testVo.setHours(hours);
				} else {
					testVo.setHours(testVo.getHours() + hours);
				}
				themeService.createTheme(testVo);
				newTheme.setType("item");
				newTheme.setHours(hours);
				newTheme.setThemeCode(testVo.getThemeCode());
			} else {
				newTheme.setType("main");
				newTheme.setThemeCode(Integer.toString(code++));
			}
		} else {
			newTheme.setType("main");
			newTheme.setThemeCode(Integer.toString(code++));
		}
		themeService.createTheme(newTheme);
		root = createThemes();
		name = null;
		description = null;
		hours = null;
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Theme created!");
		FacesContext.getCurrentInstance().addMessage(null, msgs);
	}

	public void deleteTheme() {
		ThemeVo testVo = themeService.getThemeByName(selectedNode.getData().toString());
		if (!(testVo.getType().equals("main"))) {
			ThemeVo parent = themeService.getThemeByName(selectedNode.getParent().getData().toString());
			System.out.println("ASDASDASD" + parent.toString());
			if (parent.getHours() != null) {
				parent.setHours(parent.getHours() - testVo.getHours());
			}
			themeService.createTheme(parent);
		}
		themeService.deleteTheme(testVo.getId());
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Theme deleted!");
		FacesContext.getCurrentInstance().addMessage(null, msgs);
		root = createThemes();
	}

	public void onRowSelect(NodeSelectEvent event) {
		disabled = false;
		System.out.println(event.getTreeNode().getData().toString());
		ThemeVo testVo = themeService.getThemeByName(event.getTreeNode().getData().toString());
		if (testVo.getType().equals("main"))
			mainSelected = false;
		else
			mainSelected = true;
	}

	public void displaySelectedSingle() {
		if (selectedNode != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",
					selectedNode.getData().toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isMainSelected() {
		return mainSelected;
	}

	public void setMainSelected(boolean mainSelected) {
		this.mainSelected = mainSelected;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

}