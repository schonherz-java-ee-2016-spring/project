package hu.schonherz.training.web.admin.managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;
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
	private Double hours;
	private Integer code = 0;
	private boolean disabled = true;
	private boolean mainSelected = true;
	private ThemeVo testVo;
	private Double lastHours;
	private boolean fileUploaded = true;
	
	private String usableImageLink;
	private Part file;

	private TreeNode root;
	private TreeNode selectedNode = null;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	@PostConstruct
	public void init() {
		selectedNode = null;
		testVo = null;
		lastHours = null;
		root = createThemes();
		mainSelected = true;
		disabled = true;
	}
	
	public void saveFile() {
		try (InputStream input = getFile().getInputStream()) {
			String folder = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/themefiles/"
					+ selectedNode.getData().toString() + "/";
			String filename = file.getSubmittedFileName();
			if (!Files.exists(Paths.get(folder))) {
				Files.createDirectories(Paths.get(folder));
			}
			Files.copy(input, new File(folder, filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
			testVo = themeService.getThemeByName(selectedNode.getData().toString());
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			setUsableImageLink(ec.getRequestScheme() + "://" + ec.getRequestServerName() + ":"
					+ ec.getRequestServerPort() + "/training-web/themefiles/" + selectedNode.getData().toString() + "/" + filename);
			RequestContext.getCurrentInstance().update("usableImageLinkForm");
			testVo.setFileLink(usableImageLink);
			themeService.createTheme(testVo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void downloadFile() {
		testVo = themeService.getThemeByName(selectedNode.getData().toString());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(testVo.getFileLink());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		resetFields();
		selectedNode = null;
		return root;
	}

	public void createMainTheme() {
		ThemeVo newTheme = new ThemeVo();
		newTheme.setName(name);
		newTheme.setDescription(description);
		newTheme.setType("main");
		newTheme.setThemeCode(Integer.toString(code++));		
		themeService.createTheme(newTheme);	
		root = createThemes();
		resetFields();
		mainSelected = true;
		disabled = true;
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Theme created!");
		FacesContext.getCurrentInstance().addMessage(null, msgs);
	}
	
	public void createItemTheme(){
		ThemeVo newTheme = new ThemeVo();
		newTheme.setName(name);
		newTheme.setDescription(description);
		newTheme.setType("item");
		newTheme.setHours(hours);
		if (selectedNode != null) {
			testVo = themeService.getThemeByName(selectedNode.getData().toString());
			newTheme.setThemeCode(testVo.getThemeCode());
			if (testVo.getHours() == null) {
				testVo.setHours(hours);
			} else {
				testVo.setHours(testVo.getHours() + hours);
			}
		}
		themeService.createTheme(testVo);
		themeService.createTheme(newTheme);
		root = createThemes();
		resetFields();
		mainSelected = true;
		disabled = true;
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Item theme created!");
		FacesContext.getCurrentInstance().addMessage(null, msgs);
	}

	public void deleteTheme() {
		if (!(testVo.getType().equals("main"))) {
			ThemeVo parent = themeService.getThemeByName(selectedNode.getParent().getData().toString());
			if (parent.getHours() != null) {
				parent.setHours(parent.getHours() - testVo.getHours());
			}
			themeService.createTheme(parent);
		}
		if (testVo.getType().equals("main")) {
			List<TreeNode> children = selectedNode.getChildren();
			List<ThemeVo> voChildren = new ArrayList<>();
			for (TreeNode child : children) {
				voChildren.add(themeService.getThemeByName(child.getData().toString()));
			}
			for (ThemeVo themeVo : voChildren) {
				themeService.deleteTheme(themeVo.getId());
			}
		}
		themeService.deleteTheme(testVo.getId());
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Theme deleted!");
		FacesContext.getCurrentInstance().addMessage(null, msgs);
		root = createThemes();
		disabled = true;
		mainSelected = true;
	}

	public void editTheme() {
		themeService.createTheme(testVo);
		ThemeVo testVo2 = themeService.getThemeByName(testVo.getName());
		ThemeVo parent = themeService.getThemeByName(selectedNode.getParent().getData().toString());
		if (parent != null) {
			parent.setHours(parent.getHours() + (testVo2.getHours() - lastHours));
			themeService.createTheme(parent);
		}
		FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Theme edited!");
		FacesContext.getCurrentInstance().addMessage(null, msgs);
		root = createThemes();
		disabled = true;
		mainSelected = true;
	}

	public void onRowSelect(NodeSelectEvent event) {
		disabled = false;
		testVo = themeService.getThemeByName(event.getTreeNode().getData().toString());
		lastHours = testVo.getHours();
		if (testVo.getType().equals("main"))
			mainSelected = false;
		else
			mainSelected = true;
		if(testVo.getFileLink() != null)
			fileUploaded = false;
		else
			fileUploaded = true;

	}

	public void resetFields() {
		name = null;
		type = null;
		description = null;
		hours = null;
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

	public ThemeVo getTestVo() {
		return testVo;
	}

	public void setTestVo(ThemeVo testVo) {
		this.testVo = testVo;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public Double getLastHours() {
		return lastHours;
	}

	public void setLastHours(Double lastHours) {
		this.lastHours = lastHours;
	}

	/**
	 * @return the file
	 */
	public Part getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(Part file) {
		this.file = file;
	}

	/**
	 * @return the usableImageLink
	 */
	public String getUsableImageLink() {
		return usableImageLink;
	}

	/**
	 * @param usableImageLink the usableImageLink to set
	 */
	public void setUsableImageLink(String usableImageLink) {
		this.usableImageLink = usableImageLink;
	}

	/**
	 * @return the fileUploaded
	 */
	public boolean isFileUploaded() {
		return fileUploaded;
	}

	/**
	 * @param fileUploaded the fileUploaded to set
	 */
	public void setFileUploaded(boolean fileUploaded) {
		this.fileUploaded = fileUploaded;
	}

}