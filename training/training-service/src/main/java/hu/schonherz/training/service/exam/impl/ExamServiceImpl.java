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

import hu.schonherz.training.core.exam.entity.Exam;
import hu.schonherz.training.core.exam.repository.ExamRepository;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.mapper.ExamMapper;
import hu.schonherz.training.service.exam.vo.ExamVo;

@Stateless(mappedName = "ExamService", name = "ExamService")
@Transactional(value = TxType.REQUIRED)
@Local(ExamService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ExamServiceImpl implements ExamService {

	static final Logger logger = LogManager.getLogger(ExamServiceImpl.class.getName());

	@Autowired
	ExamRepository examRepository;

	@Override
	public void add(ExamVo vo, Long... id) throws Exception {
		try {
			examRepository.saveAndFlush(ExamMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public ExamVo getById(Long id) throws Exception {
		try {
			return ExamMapper.toVo(examRepository.findOne(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<ExamVo> getAll(Long... id) throws Exception {
		try {
			return ExamMapper.toVo(examRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<ExamVo> getAllSortedById() throws Exception {
		try {
			return ExamMapper.toVo(examRepository.findAllByOrderByIdAsc());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void updateTitle(ExamVo examVo) throws Exception {
		try {
			Exam exam = ExamMapper.toDto(examVo);
			examRepository.modifyExamTitleById(exam.getTitle(), exam.getId());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void remove(Long examId) throws Exception {
		try {
			examRepository.delete(examId);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
