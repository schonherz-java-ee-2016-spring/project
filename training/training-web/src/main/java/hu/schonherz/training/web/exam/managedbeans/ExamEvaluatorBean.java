package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.AnswerTextService;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;
import hu.schonherz.training.service.exam.vo.eval.EvalRecord;

@ManagedBean(name = "examEvaluatorBean")
@ViewScoped
public class ExamEvaluatorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;
	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private AnswerService answerService;
	@EJB
	private AnswerTextService answerTextService;

	private List<ExamVo> examList;
	private String selectedExamIdAsString;

	private List<UserVo> userList;
	private String selectedUserIdAsString;

	private List<EvalRecord> evalRecordList;

	@PostConstruct
	public void initBean() {
		try {
			examList = examService.getAllSortedById();
			userList = userService.findAllUser();
			evalRecordList = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadContent() {
		try {
			evalRecordList.clear();

			List<QuestionVo> textBasedQuestionList = questionService.getAllByExamId(Long.parseLong(selectedExamIdAsString))
					.stream().filter(q -> q.getQuestionType().getId() == 3).collect(Collectors.toList());

			List<OptionVo> optionList = textBasedQuestionList.stream().flatMap(q -> q.getOptions().stream())
					.collect(Collectors.toList());

			List<AnswerVo> answerList = answerService.getAllByUserId(Long.parseLong(selectedUserIdAsString)).stream()
					.filter(a -> optionList.stream()
							.filter(o -> o.getId().longValue() == a.getOption().getId().longValue()).count() > 0)
					.collect(Collectors.toList());

			for (AnswerVo answer : answerList) {

				EvalRecord record = new EvalRecord();
				record.setUser(userService.findUserById(Long.parseLong(selectedUserIdAsString)));

				record.setAnswer(answer);
				AnswerTextVo answerText = answerTextService.getByAnswerId(answer.getId());
				record.setAnswerText(answerText);

				QuestionVo question = questionService.getAllByExamId(Long.parseLong(selectedExamIdAsString))
						.stream().filter(q -> q.getQuestionType().getId() == 3).filter(q -> q.getOptions().get(0)
								.getId().longValue() == answer.getOption().getId().longValue()).findFirst().orElse(null);

				record.setQuestion(question);
				record.setOption(question.getOptions().get(0));

				evalRecordList.add(record);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("answerTextEvaluatorForm");
	}

	public void applyEvaluation() throws Exception {
		for (EvalRecord evalRecord : evalRecordList) {
			answerService.modifyGood(evalRecord.getAnswer());
		}
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

	public String getSelectedExamIdAsString() {
		return selectedExamIdAsString;
	}

	public void setSelectedExamIdAsString(String selectedExamIdAsString) {
		this.selectedExamIdAsString = selectedExamIdAsString;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public List<UserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}

	public String getSelectedUserIdAsString() {
		return selectedUserIdAsString;
	}

	public void setSelectedUserIdAsString(String selectedUserIdAsString) {
		this.selectedUserIdAsString = selectedUserIdAsString;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AnswerTextService getAnswerTextService() {
		return answerTextService;
	}

	public void setAnswerTextService(AnswerTextService answerTextService) {
		this.answerTextService = answerTextService;
	}

	public List<EvalRecord> getEvalRecordList() {
		return evalRecordList;
	}

	public void setEvalRecordList(List<EvalRecord> evalRecordList) {
		this.evalRecordList = evalRecordList;
	}
}
