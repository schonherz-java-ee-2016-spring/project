package hu.schonherz.training.web.exam.managedbeans;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.web.exam.base.ExamReview;

@ManagedBean(name = "examReviewForStudentBean")
@ViewScoped
public class ExamReviewForStudentBean extends ExamReview {
	private static final long serialVersionUID = 1L;


	@PostConstruct
	public void initBean() {
		try {
			user = userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			examList = examUserRelationService.getAllExamByUserId(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadContent() {
		try {
			questionList = questionService.getAllByExamId(Long.parseLong(selectedExamIdAsString));
			answerList = getAnswerService().getAllByUserId(user.getId());
			answerList = answerList.stream()
					.filter(a -> questionList.stream().flatMap(q -> q.getOptions().stream())
							.filter(qq -> qq.getId().equals(a.getOption().getId())).count() > 0)
					.collect(Collectors.toList());
			setUpselectedOptionIdList();
			updateQuestionList();
			calculateExamScore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("optionTableForm");

	}
}
