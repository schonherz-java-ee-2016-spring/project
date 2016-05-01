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

import hu.schonherz.training.core.exam.entity.Answer;
import hu.schonherz.training.core.exam.repository.AnswerRepository;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.mapper.AnswerMapper;
import hu.schonherz.training.service.exam.vo.AnswerVo;

@Stateless(mappedName = "AnswerService", name = "AnswerService")
@Transactional(value = TxType.REQUIRED)
@Local(AnswerService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class AnswerServiceImpl implements AnswerService {

	static final Logger logger = LogManager.getLogger(AnswerServiceImpl.class.getName());

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public List<AnswerVo> getAll() throws Exception {
		try {
			return AnswerMapper.toVo(answerRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public AnswerVo getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void add(AnswerVo vo) throws Exception {
		try {
			answerRepository.saveAndFlush(AnswerMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<AnswerVo> getAllByUserId(Long id) throws Exception {
		try {
			return AnswerMapper.toVo(answerRepository.findAnswersByUserId(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void modifyGood(AnswerVo vo) throws Exception {
		try {
			Answer answer = AnswerMapper.toDto(vo);
			answerRepository.updateGoodById(answer.getId(), answer.getGood());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	} 

}
