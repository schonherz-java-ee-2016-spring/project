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

import hu.schonherz.training.core.exam.repository.AnswerNoteRepository;
import hu.schonherz.training.service.exam.AnswerNoteService;
import hu.schonherz.training.service.exam.mapper.AnswerNoteMapper;
import hu.schonherz.training.service.exam.vo.AnswerNoteVo;

@Stateless(mappedName = "AnswerNoteService", name = "AnswerNoteService")
@Transactional(value = TxType.REQUIRED)
@Local(AnswerNoteService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class AnswerNoteServiceImpl implements AnswerNoteService {

	static final Logger logger = LogManager.getLogger(AnswerNoteServiceImpl.class.getName());

	@Autowired
	AnswerNoteRepository answerNoteRepository;

	@Deprecated
	@Override
	public List<AnswerNoteVo> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public AnswerNoteVo getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public void removeById(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(AnswerNoteVo vo) throws Exception {
		try {
			answerNoteRepository.saveAndFlush(AnswerNoteMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public AnswerNoteVo getByAnswerId(Long id) throws Exception {
		try {
			return AnswerNoteMapper.toVo(answerNoteRepository.findNoteByAnswerId(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
