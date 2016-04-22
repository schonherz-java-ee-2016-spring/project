package hu.schonherz.training.service.exam.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.exam.entity.Exam;
import hu.schonherz.training.core.exam.entity.Question;
import hu.schonherz.training.core.exam.repository.ExamRepository;
import hu.schonherz.training.core.exam.repository.QuestionRepository;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.mapper.ExamMapper;
import hu.schonherz.training.service.exam.mapper.QuestionMapper;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@Stateless(mappedName = "QuestionService", name = "QuestionService")
@Transactional(value = TxType.REQUIRED)
@Local(QuestionService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class QuestionServiceImpl implements QuestionService {

	static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class.getName());

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	ExamRepository examRepository;

	@Override
	public void add(QuestionVo vo, Long... id) throws Exception {
		Long examId = id[0];
		try {
			Exam exam = examRepository.findOne(examId);
			Collection<Question> questions = exam.getQuestions();
			if (questions == null) {
				exam.setQuestions(new ArrayList<>());
			}
			exam.getQuestions().add(QuestionMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public QuestionVo getById(Long id) throws Exception {
		try {
			return QuestionMapper.toVo(questionRepository.findOne(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<QuestionVo> getAll(Long... id) throws Exception {
		Long examId = id[0];
		try {
			ExamVo examVo = ExamMapper.toVo(examRepository.findOne(examId));
			List<QuestionVo> questionVoList = examVo.getQuestions();

			return questionVoList.stream().distinct().collect(Collectors.toList());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
