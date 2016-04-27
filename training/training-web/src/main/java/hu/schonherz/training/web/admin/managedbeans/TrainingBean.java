package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.vo.ThemeVo;
import hu.schonherz.training.service.admin.vo.TrainingVo;

@ManagedBean(name = "trainingBean")
@ViewScoped
public class TrainingBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private TrainingService trainingService;

	@EJB
	private ThemeService themeService;

	private List<TrainingVo> trainings;
	private TrainingVo selected;
	private Boolean isDisabled = true;

	private TreeNode root1;

	private TreeNode root2;

	private TreeNode selectedNode1;

	private TreeNode selectedNode2;

	@PostConstruct
	public void init() {
		try {
			trainings = trainingService.getAllTrainings();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveTraining() {
		List<TreeNode> nodes = root1.getChildren();
		List<ThemeVo> vos = new ArrayList<>();
		for (TreeNode treeNode : nodes) {
			ThemeVo tvo = themeService.getThemeByName(treeNode.getRowKey());
			vos.add(tvo);
		}
		selected.setThemes(vos);
		trainingService.saveTraining(selected);
	}

	public void treeAction(){
		root1 = new DefaultTreeNode("Root", null);

		List<ThemeVo> tvos = trainingService.getTrainingById(selected.getId()).getThemes();
		for (ThemeVo themeVo : tvos) {
			TreeNode t = new DefaultTreeNode(themeVo.getName(), root1);
			for (int j = 1; j < 4; ++j) {
				new DefaultTreeNode("Tétel " + j, t);
			}
		}

		root2 = new DefaultTreeNode("Root2", null);
		tvos = themeService.findAllTheme();
		for (ThemeVo themeVo : tvos) {
			TreeNode t = new DefaultTreeNode(themeVo.getName(), root2);
			for (int j = 1; j < 4; ++j) {
				new DefaultTreeNode("Tétel " + j, t);
			}
		}

	}

	public void onDragDrop(TreeDragDropEvent event) {
		TreeNode dragNode = event.getDragNode();
		TreeNode dropNode = event.getDropNode();
		int dropIndex = event.getDropIndex();

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(),
				"Dropped on " + dropNode.getData() + " at " + dropIndex);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void selectTrainingListener(SelectEvent event) {
		isDisabled = false;
	}

	public void displaySelectedSingle() {
		if (selectedNode1 != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",
					selectedNode1.getData().toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void createAction() {
		selected = new TrainingVo();
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
			e.printStackTrace();
		}
		if (isCreateAction == null) {
			selected = new TrainingVo();
		}
	}

	public void deleteTraining() {
		try {
			trainingService.deleteTraining(selected.getId());
			trainings.remove(selected);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
