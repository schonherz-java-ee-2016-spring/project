package hu.schonherz.training.exam.service.impl;

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

import hu.schonherz.training.exam.repository.QuestionTypeRepository;
import hu.schonherz.training.exam.service.QuestionTypeService;
import hu.schonherz.training.exam.service.mapper.QuestionTypeMapper;
import hu.schonherz.training.exam.vo.QuestionTypeVo;

@Stateless(mappedName = "QuestionTypeService", name = "QuestionTypeService")
@Transactional(value = TxType.REQUIRED)
@Local(QuestionTypeService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class QuestionTypeServiceImpl implements QuestionTypeService {

	static final Logger logger = LogManager.getLogger(QuestionTypeServiceImpl.class.getName());

	@Autowired
	QuestionTypeRepository questionTypeRepository;

	@Override
	public void createQuestionType(QuestionTypeVo questionTypeVo) throws Exception {
		try {
			questionTypeRepository.saveAndFlush(QuestionTypeMapper.toDto(questionTypeVo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

	}

	@Override
	public QuestionTypeVo getQuestionTypeById(Long questionTypeId) throws Exception {
		QuestionTypeVo questionTypeVo = null;
		try {
			questionTypeVo = QuestionTypeMapper.toVo(questionTypeRepository.findOne(questionTypeId));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
		return questionTypeVo;
	}

	@Override
	public List<QuestionTypeVo> getQuestionTypeList() throws Exception {
		try {
			return QuestionTypeMapper.toVo(questionTypeRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
