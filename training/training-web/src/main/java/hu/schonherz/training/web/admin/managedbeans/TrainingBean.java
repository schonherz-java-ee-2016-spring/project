package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.ThemeVo;
import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "trainingBean")
@ViewScoped
public class TrainingBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UserGroupsBean.class);

	@EJB
	private TrainingService trainingService;

	@EJB
	private ThemeService themeService;

	@EJB
	private UserService userService;

	@EJB
	private UserGroupService userGroupService;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	private Boolean isDisabled = true;

	private List<TrainingVo> trainings;
	private TrainingVo selected;

	private DualListModel<UserVo> users;
	private List<UserVo> usersSource;
	private List<UserVo> usersTarget;

	private DualListModel<UserGroupVo> uGroups;
	private List<UserGroupVo> uGroupsSource;
	private List<UserGroupVo> uGroupsTarget;

	private TreeNode root1;
	private TreeNode root2;
	private TreeNode selectedNode1;
	private TreeNode selectedNode2;

	@PostConstruct
	public void init() {
		users = new DualListModel<>();
		uGroups = new DualListModel<>();
		try {
			trainings = trainingService.getAllTrainings();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void selectTrainingListener(SelectEvent event) {
		isDisabled = false;
	}

	public void createAction() {
		selected = new TrainingVo();
	}

	public void treeAction() {
		root1 = new DefaultTreeNode("Root", null);
		root2 = new DefaultTreeNode("Root2", null);

		List<TreeNode> child = new ArrayList<TreeNode>();
		List<TreeNode> child2 = new ArrayList<TreeNode>();
		TreeNode t1 = new DefaultTreeNode();
		TreeNode t2 = new DefaultTreeNode();
		t1.setSelectable(false);
		t2.setSelectable(false);
		child.add(t1);
		child2.add(t2);

		List<ThemeVo> tvos = themeService.getThemesByType("main");
		List<ThemeVo> selectedThemes = trainingService.getTrainingById(selected.getId()).getThemes();
		for (ThemeVo themeVo : tvos) {
			TreeNode tp = new DefaultTreeNode(themeVo.getName());
			List<TreeNode> tchild = new ArrayList<TreeNode>();
			List<ThemeVo> childThemes = themeService.getItemThemesByThemeCode(themeVo.getThemeCode());
			for (ThemeVo themeVo2 : childThemes) {
				tchild.add(new DefaultTreeNode(themeVo2.getName()));
			}
			((DefaultTreeNode) tp).setChildren(tchild);
			int o = 0;
			for (ThemeVo themeVo2 : selectedThemes) {
				if (themeVo.getId().equals(themeVo2.getId())) {
					child.remove(t1);
					child.add(tp);
					o = 1;
					break;
				}
			}

			if (o == 0) {
				child2.remove(t2);
				child2.add(tp);
			}
		}
		((DefaultTreeNode) root1).setChildren(child);
		((DefaultTreeNode) root2).setChildren(child2);
	}

	public void userManageAction() {
		usersSource = new ArrayList<>();
		usersTarget = new ArrayList<>();
		usersTarget = trainingService.getAllUsers(selected.getId());
		try {
			for (UserVo userVo : userService.findAllUser()) {
				int o = 0;
				for (UserVo user : usersTarget) {
					if (user.getId().equals(userVo.getId())) {
						o = 1;
						break;
					}
				}
				if (o == 0) {
					usersSource.add(userVo);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		users = new DualListModel<UserVo>(usersSource, usersTarget);
	}

	public void groupManageAction() {
		uGroupsSource = new ArrayList<>();
		uGroupsTarget = new ArrayList<>();
		uGroupsTarget = trainingService.getAllUserGroups(selected.getId());
		try {
			for (UserGroupVo uGroupVo : userGroupService.getUserGroups()) {
				int o = 0;
				for (UserGroupVo ugroup : uGroupsTarget) {
					if (ugroup.getId().equals(uGroupVo.getId())) {
						o = 1;
						break;
					}
				}
				if (o == 0) {
					uGroupsSource.add(uGroupVo);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		uGroups = new DualListModel<UserGroupVo>(uGroupsSource, uGroupsTarget);
	}

	public void save() {
		Long isCreateAction = selected.getId();
		try {
			TrainingVo trainingVo = trainingService.getTrainingByName(selected.getName());
			if ((trainingVo != null) && !trainingVo.getId().equals(selected.getId())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING",
						"Training name is already used!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				trainingService.saveTraining(selected);
				trainings.remove(selected);
				selected = trainingService.getTrainingByName(selected.getName());
				trainings.add(selected);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "Training saved!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		if (isCreateAction == null) {
			selected = new TrainingVo();
		}
	}

	public void saveThemes() {
		List<TreeNode> nodes = root1.getChildren();
		List<ThemeVo> vos = new ArrayList<>();
		for (TreeNode treeNode : nodes) {
			if (treeNode.getData() != null) {
				ThemeVo pTheme = themeService.getThemeByName(treeNode.getData().toString());
				if (pTheme.getType().equals("main")) {
					for (TreeNode tc : treeNode.getChildren()) {
						ThemeVo theme = themeService.getThemeByName(tc.getData().toString());
						if (theme.getType().equals("item")) {
							theme.setThemeCode(pTheme.getThemeCode());
							themeService.createTheme(theme);
							vos.add(theme);
						}
					}
					vos.add(pTheme);
				} else if (pTheme.getType().equals("item")) {
					for (TreeNode tnd : root1.getChildren()) {
						ThemeVo theme = themeService.getThemeByName(tnd.getData().toString());
						if (theme.getType().equals("main") && theme.getThemeCode().equals(pTheme.getThemeCode())) {
							vos.add(pTheme);
						}
					}
				}
			}
		}
		selected.setThemes(vos);
		trainingService.saveTraining(selected);
		nodes = root2.getChildren();
		for (TreeNode treeNode : nodes) {
			if (treeNode.getData() != null) {
				ThemeVo pTheme = themeService.getThemeByName(treeNode.getData().toString());
				if (pTheme.getType().equals("main")) {
					for (TreeNode tc : treeNode.getChildren()) {
						ThemeVo theme = themeService.getThemeByName(tc.getData().toString());
						if (theme.getType().equals("item")) {
							theme.setThemeCode(pTheme.getThemeCode());
							themeService.createTheme(theme);
						}
					}
				} else if (pTheme.getType().equals("item")) {
					for (TreeNode tnd : root1.getChildren()) {
						ThemeVo theme = themeService.getThemeByName(tnd.getData().toString());
						if (theme.getType().equals("main") && theme.getThemeCode().equals(pTheme.getThemeCode())) {
							vos.add(pTheme);
							selected.setThemes(vos);
							trainingService.saveTraining(selected);
						}
					}
				}
			}
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "Training saved!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveUsers() {
		List<UserVo> trainingUsers = selected.getUsers();
		for (UserVo userVo : users.getSource()) {
			for (UserVo tu : trainingUsers) {
				if (userVo.getId().equals(tu.getId())) {
					trainingUsers.remove(tu);
					break;
				}
			}
		}
		for (UserVo userVo : users.getTarget()) {
			for (UserVo tu : trainingUsers) {
				if (userVo.getId().equals(tu.getId())) {
					trainingUsers.remove(tu);
					break;
				}
			}
			trainingUsers.add(userVo);
		}
		selected.setUsers(trainingUsers);
		try {
			trainingService.saveTraining(selected);
		} catch (Exception e) {
			logger.error(e);
		}

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
				bundle.getString("trainingSaved"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveGroups() {
		List<UserGroupVo> trainingGroups = selected.getUserGroups();
		for (UserGroupVo groupVo : uGroups.getSource()) {
			for (UserGroupVo tg : trainingGroups) {
				if (groupVo.getId().equals(tg.getId())) {
					trainingGroups.remove(tg);
					break;
				}
			}
		}
		for (UserGroupVo groupVo : uGroups.getTarget()) {
			for (UserGroupVo tg : trainingGroups) {
				if (groupVo.getId().equals(tg.getId())) {
					trainingGroups.remove(tg);
					break;
				}
			}
			trainingGroups.add(groupVo);
		}
		selected.setUserGroups(trainingGroups);
		try {
			trainingService.saveTraining(selected);
		} catch (Exception e) {
			logger.error(e);
		}

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
				bundle.getString("trainingSaved"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteTraining() {
		try {
			trainingService.deleteTraining(selected.getId());
			trainings.remove(selected);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public List<TrainingVo> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<TrainingVo> trainings) {
		this.trainings = trainings;
	}

	public TrainingVo getSelected() {
		return selected;
	}

	public void setSelected(TrainingVo selected) {
		this.selected = selected;
	}

	public DualListModel<UserVo> getUsers() {
		return users;
	}

	public void setUsers(DualListModel<UserVo> users) {
		this.users = users;
	}

	public List<UserVo> getUsersSource() {
		return usersSource;
	}

	public void setUsersSource(List<UserVo> usersSource) {
		this.usersSource = usersSource;
	}

	public List<UserVo> getUsersTarget() {
		return usersTarget;
	}

	public void setUsersTarget(List<UserVo> usersTarget) {
		this.usersTarget = usersTarget;
	}

	public TreeNode getRoot1() {
		return root1;
	}

	public void setRoot1(TreeNode root1) {
		this.root1 = root1;
	}

	public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}

	public TreeNode getSelectedNode1() {
		return selectedNode1;
	}

	public void setSelectedNode1(TreeNode selectedNode1) {
		this.selectedNode1 = selectedNode1;
	}

	public TreeNode getSelectedNode2() {
		return selectedNode2;
	}

	public void setSelectedNode2(TreeNode selectedNode2) {
		this.selectedNode2 = selectedNode2;
	}

	public DualListModel<UserGroupVo> getuGroups() {
		return uGroups;
	}

	public void setuGroups(DualListModel<UserGroupVo> uGroups) {
		this.uGroups = uGroups;
	}

	public List<UserGroupVo> getuGroupsSource() {
		return uGroupsSource;
	}

	public void setuGroupsSource(List<UserGroupVo> uGroupsSource) {
		this.uGroupsSource = uGroupsSource;
	}

	public List<UserGroupVo> getuGroupsTarget() {
		return uGroupsTarget;
	}

	public void setuGroupsTarget(List<UserGroupVo> uGroupsTarget) {
		this.uGroupsTarget = uGroupsTarget;
	}

}
