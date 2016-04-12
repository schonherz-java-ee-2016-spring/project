package hu.schonherz.training.exam.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

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

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public void createQuestion(QuestionVo questionVo) throws Exception {
		questionRepository.saveAndFlush(QuestionMapper.toDto(questionVo));

	}

}
