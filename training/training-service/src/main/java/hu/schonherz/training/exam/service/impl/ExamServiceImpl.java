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
	public void createExam(ExamVo examVo) throws Exception {
		try {
			examRepository.saveAndFlush(ExamMapper.toDto(examVo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

	}
}
