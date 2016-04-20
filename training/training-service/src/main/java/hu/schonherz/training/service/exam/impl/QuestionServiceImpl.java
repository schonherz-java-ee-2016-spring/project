package hu.schonherz.training.service.exam.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.exam.repository.QuestionRepository;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.mapper.QuestionMapper;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@Stateless(mappedName = "QuestionService", name = "QuestionService")
@Transactional(value = TxType.REQUIRED)
@Local(QuestionService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class QuestionServiceImpl implements QuestionService {

	static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class.getName());

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public void create(QuestionVo vo) throws Exception {
		try {
			questionRepository.saveAndFlush(QuestionMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public QuestionVo findById(Long id) throws Exception {
		try {
			return QuestionMapper.toVo(questionRepository.findOne(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<QuestionVo> findAll() throws Exception {
		try {
			return QuestionMapper.toVo(questionRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
}
