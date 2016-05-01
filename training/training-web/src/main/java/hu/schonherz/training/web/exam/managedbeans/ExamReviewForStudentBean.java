package hu.schonherz.training.web.exam.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "examReviewForStudentBean")
@ViewScoped
public class ExamReviewForStudentBean extends ExamReviewBean {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void initBean() {
		try {
			setUser(userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
			examList = examUserRelationService.getAllExamByUserId(user.getId());
			setAnswerList(new ArrayList<>());
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
		RequestContext.getCurrentInstance().update("optionTableForm");
	}

	private void updateQuestionList() throws Exception {
		for (QuestionVo question : questionList) {
			if (question.getQuestionType().getName().equalsIgnoreCase("TEXT")) {
				OptionVo option = question.getOptions().get(0);
				setUpOptionByTextBased(option);
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
		System.out.println(option.getId() + "----> " + option.getText() + " - >>> " + option.getCorrect());

		if (list.isEmpty()) {
			if (option.getCorrect() == false) {
				option.setCorrect(null);
			}
			return;
		}

		AnswerVo answer = list.get(0);
		if (answer.getGood() == true && option.getCorrect() == true) {
			option.setCorrect(true);
		} else {
			option.setCorrect(false);
		}
	}

	private void setUpselectedOptionIdList() {
		selectedOptionIdList = new ArrayList<Long>();
		answerList.forEach(a -> selectedOptionIdList.add(a.getOption().getId()));
	}

}
