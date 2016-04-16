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

import hu.schonherz.training.exam.entity.Exam;
import hu.schonherz.training.exam.repository.ExamRepository;
import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.service.mapper.ExamMapper;
import hu.schonherz.training.exam.vo.ExamVo;

@Stateless(mappedName = "ExamService", name = "ExamService")
@Transactional(value = TxType.REQUIRED)
@Local(ExamService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ExamServiceImpl implements ExamService {

	static final Logger logger = LogManager.getLogger(ExamServiceImpl.class.getName());

	@Autowired
	ExamRepository examRepository;

	@Override
	public List<ExamVo> getExamList() throws Exception {
		try {
			return ExamMapper.toVo(examRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
	

	@Override
	public List<ExamVo> getExamListSortedById() throws Exception {
	
		try {
			return ExamMapper.toVo(examRepository.findAllByOrderByIdAsc());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void createExam(ExamVo examVo) throws Exception {
		try {
			examRepository.saveAndFlush(ExamMapper.toDto(examVo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void modifyExamTitle(ExamVo examVo) throws Exception {
		try {
			Exam exam = ExamMapper.toDto(examVo);
			examRepository.modifyExamTitleById(exam.getTitle(), exam.getId());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public ExamVo getExamById(Long id) throws Exception {
		ExamVo examVo = null;
		try {
			examVo = ExamMapper.toVo(examRepository.findOne(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
		return examVo;	
	}
}
