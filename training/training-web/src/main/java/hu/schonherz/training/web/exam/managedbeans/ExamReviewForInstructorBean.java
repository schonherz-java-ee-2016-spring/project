package hu.schonherz.training.web.exam.managedbeans;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.web.exam.base.ExamReview;

@ManagedBean(name = "examReviewForInstructorBean")
@ViewScoped
public class ExamReviewForInstructorBean extends ExamReview {
	private static final long serialVersionUID = 1L;

	private Boolean showTable;
	private String selectedUserIdAsString;
	private List<UserVo> userList;

	@PostConstruct
	public void initBean() {
		try {
			userList = userService.findAllUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadExamList() {
		try {
			Long userId = Long.parseLong(selectedUserIdAsString);
			user = userService.findUserById(userId);
			examList = examUserRelationService.getAllExamByUserId(userId);
			showTable = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadContent() {
		try {
			questionList = questionService.getAllById(Long.parseLong(selectedExamIdAsString));
			answerList = getAnswerService().getAllByUserId(user.getId());
			answerList = answerList.stream()
					.filter(a -> questionList.stream().flatMap(q -> q.getOptions().stream())
							.filter(qq -> qq.getId().equals(a.getOption().getId())).count() > 0)
					.collect(Collectors.toList());
			setUpselectedOptionIdList();
			updateQuestionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		showTable = true;
	}

	public Boolean getShowTable() {
		return showTable;
	}

	public void setShowTable(Boolean showTable) {
		this.showTable = showTable;
	}

	public String getSelectedUserIdAsString() {
		return selectedUserIdAsString;
	}

	public void setSelectedUserIdAsString(String selectedUserIdAsString) {
		this.selectedUserIdAsString = selectedUserIdAsString;
	}

	public List<UserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}

}
