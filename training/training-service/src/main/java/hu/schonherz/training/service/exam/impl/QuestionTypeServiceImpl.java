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

import hu.schonherz.training.core.exam.repository.QuestionTypeRepository;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.mapper.QuestionTypeMapper;
import hu.schonherz.training.service.exam.vo.QuestionTypeVo;

@Stateless(mappedName = "QuestionTypeService", name = "QuestionTypeService")
@Transactional(value = TxType.REQUIRED)
@Local(QuestionTypeService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class QuestionTypeServiceImpl implements QuestionTypeService {

	static final Logger logger = LogManager.getLogger(QuestionTypeServiceImpl.class.getName());

	@Autowired
	QuestionTypeRepository questionTypeRepository;

	@Override
	public List<QuestionTypeVo> getAll() throws Exception {
		try {
			return QuestionTypeMapper.toVo(questionTypeRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public QuestionTypeVo getById(Long id) throws Exception {
		try {
			return QuestionTypeMapper.toVo(questionTypeRepository.findOne(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void removeById(Long id) throws Exception {
		try {
			questionTypeRepository.delete(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void add(QuestionTypeVo vo) throws Exception {
		try {
			questionTypeRepository.saveAndFlush(QuestionTypeMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
}
