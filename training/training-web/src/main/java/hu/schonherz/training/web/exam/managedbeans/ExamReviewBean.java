package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "examReviewBean")
@SessionScoped
public class ExamReviewBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private UserService userService;
	@EJB
	private AnswerService answerService;

	private List<ExamVo> examList;
	private String selectedExamIdAsString;
	private String selectedUserIdAsString;
	private UserVo user;

	private List<QuestionVo> questionList;
	private List<AnswerVo> answerList;

	private OptionVo selected;
	private List<OptionVo> selecteds;

	private int counter = 0;

	@PostConstruct
	public void initBean() {
		try {
			questionList = new ArrayList<>();
			examList = getExamService().getAllSortedById();
			setUser(userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
			setAnswerList(new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeTab() {
		if (questionList.size() > 0) {
			selected = questionList.get(counter).getOptions().stream().filter(o -> answerList.contains(o)).findFirst()
					.get();
			selecteds = questionList.get(counter).getOptions().stream().filter(o -> answerList.contains(o))
					.collect(Collectors.toList());
		}
	}

	public void loadContent() throws Exception {
		try {
			setQuestionList(questionService.getAllById(Long.parseLong(selectedExamIdAsString)));
			answerList = getAnswerService().getAllByUserId(user.getId());

			answerList = answerList.stream()
					.filter(a -> questionList.stream().flatMap(q -> q.getOptions().stream())
							.filter(qq -> qq.getId().equals(a.getOption().getId())).count() > 0)
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("tabView");
	}

	/**
	 * @return the examService
	 */
	public ExamService getExamService() {
		return examService;
	}

	/**
	 * @param examService
	 *            the examService to set
	 */
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	/**
	 * @return the examList
	 */
	public List<ExamVo> getExamList() {
		return examList;
	}

	/**
	 * @param examList
	 *            the examList to set
	 */
	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public List<AnswerVo> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}

	public String getSelectedExamIdAsString() {
		return selectedExamIdAsString;
	}

	public void setSelectedExamIdAsString(String selectedExamIdAsString) {
		this.selectedExamIdAsString = selectedExamIdAsString;
	}

	public String getSelectedUserIdAsString() {
		return selectedUserIdAsString;
	}

	public void setSelectedUserIdAsString(String selectedUserIdAsString) {
		this.selectedUserIdAsString = selectedUserIdAsString;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public OptionVo getSelected() {
		return selected;
	}

	public void setSelected(OptionVo selected) {
		this.selected = selected;
	}

	public List<OptionVo> getSelecteds() {
		return selecteds;
	}

	public void setSelecteds(List<OptionVo> selecteds) {
		this.selecteds = selecteds;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

}
