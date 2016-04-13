package hu.schonherz.training.exam.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.exam.repository.QuestionRepository;
import hu.schonherz.training.exam.service.QuestionService;
import hu.schonherz.training.exam.service.mapper.QuestionMapper;
import hu.schonherz.training.exam.vo.QuestionVo;

@Stateless(mappedName = "QuestionService", name = "QuestionService")
@Transactional(value = TxType.REQUIRED)
@Local(QuestionService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class QuestionServiceImpl implements QuestionService {

	static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class.getName());

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public void createQuestion(QuestionVo questionVo) throws Exception {
		try {
			questionRepository.saveAndFlush(QuestionMapper.toDto(questionVo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Override
	public void removeQuestion(QuestionVo questionVo) {
		try {
//			questionRepository.delete(QuestionMapper.toDto(questionVo));
			questionRepository.delete(QuestionMapper.toDto(questionVo).getId());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

}
