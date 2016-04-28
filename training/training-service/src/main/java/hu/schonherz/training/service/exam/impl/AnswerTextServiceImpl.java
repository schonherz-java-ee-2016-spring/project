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

import hu.schonherz.training.core.exam.repository.AnswerTextRepository;
import hu.schonherz.training.service.exam.AnswerTextService;
import hu.schonherz.training.service.exam.mapper.AnswerTextMapper;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;

@Stateless(mappedName = "AnswerTextService", name = "AnswerTextService")
@Transactional(value = TxType.REQUIRED)
@Local(AnswerTextService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class AnswerTextServiceImpl implements AnswerTextService {
	
	static final Logger logger = LogManager.getLogger(AnswerTextServiceImpl.class.getName());
	
	@Autowired
	AnswerTextRepository answerTextRepository;

	@Deprecated
	@Override
	public List<AnswerTextVo> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public AnswerTextVo getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public void removeById(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(AnswerTextVo vo) throws Exception {
		try {
			answerTextRepository.saveAndFlush(AnswerTextMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public AnswerTextVo getByAnswerId(Long id) throws Exception {
		try {
			return AnswerTextMapper.toVo(answerTextRepository.findTextByAnswerId(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
