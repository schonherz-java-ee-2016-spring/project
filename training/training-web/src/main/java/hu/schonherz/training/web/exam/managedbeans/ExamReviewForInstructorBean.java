package hu.schonherz.training.web.exam.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "examReviewForInstructorBean")
@ViewScoped
public class ExamReviewForInstructorBean extends ExamReviewBean {
	private static final long serialVersionUID = 1L;

	private Boolean showTable;
	private String userIdAsString;
	private List<UserVo> userList;

	@PostConstruct
	public void initBean() {
		try {
			setUser(userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
			userList = userService.findAllUser();
			setAnswerList(new ArrayList<>());
			examList = new ArrayList<ExamVo>();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadExamList() {
		try {
			Long userId = Long.parseLong(userIdAsString);
			user = userService.findUserById(userId);
			examList = examUserRelationService.getAllExamByUserId(userId);
			showTable = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

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

	private void updateQuestionList() throws Exception {
		for (QuestionVo question : questionList) {
			if (question.getQuestionType().getName().equalsIgnoreCase("TEXT")) {
				for (OptionVo option : question.getOptions()) {
					setUpOptionByTextBased(option);
				}
			} else {
				for (OptionVo option : question.getOptions()) {
					setUpOptionByNonTextBased(option);
				}
			}
		}
	}

	private void setUpOptionByTextBased(OptionVo option) throws Exception {
		AnswerTextVo answerText = answerTextService.getByAnswerId(answerList.stream().filter(a -> {
			if (a.getOption().getId().equals(option.getId())) {
				option.setCorrect(a.getGood());
				return true;
			}
			return false;
		}).findFirst().get().getId());
		option.setText(answerText.getText());
	}

	private void setUpOptionByNonTextBased(OptionVo option) throws Exception {
		List<AnswerVo> list = answerList.stream().filter(a -> a.getOption().getId().equals(option.getId()))
				.collect(Collectors.toList());

		if (list.isEmpty()) {
			option.setCorrect(null);
			return;
		}

		AnswerVo answer = list.get(0);
		if (answer.getGood() == true) {
			option.setCorrect(true);
		} else if (answer.getGood() == false) {
			option.setCorrect(false);
		}
	}

	private void setUpselectedOptionIdList() {
		selectedOptionIdList = new ArrayList<Long>();
		answerList.forEach(a -> selectedOptionIdList.add(a.getOption().getId()));
	}

	public List<UserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}

	public String getUserIdAsString() {
		return userIdAsString;
	}

	public void setUserIdAsString(String userIdAsString) {
		this.userIdAsString = userIdAsString;
	}

	public Boolean getShowTable() {
		return showTable;
	}

	public void setShowTable(Boolean showTable) {
		this.showTable = showTable;
	}
}
