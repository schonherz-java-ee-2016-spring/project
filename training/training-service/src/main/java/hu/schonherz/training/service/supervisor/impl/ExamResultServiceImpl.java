package hu.schonherz.training.service.supervisor.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.supervisor.repository.ExamResultRepository;
import hu.schonherz.training.service.admin.mapper.EventMapper;
import hu.schonherz.training.service.admin.mapper.UserMapper;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.ExamResultService;
import hu.schonherz.training.service.supervisor.mapper.ExamResultMapper;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;

@Stateless(mappedName = "ExamResultService", name = "ExamResultService")
@Transactional(value = TxType.REQUIRED)
@Local(ExamResultService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })

public class ExamResultServiceImpl implements ExamResultService {

	@Autowired
	private ExamResultRepository examResultRepository;

	@Override
	public List<ExamResultVo> getAll() throws Exception {
		return ExamResultMapper.toVo(examResultRepository.findAll());
	}

	@Override
	public List<ExamResultVo> getExamResultByUser(UserVo userVo) throws Exception {
		return ExamResultMapper.toVo(examResultRepository.findExamResultsByUser(UserMapper.toDto(userVo)));
	}

	@Override
	public List<ExamResultVo> getExamResultByExam(EventVo examVo) throws Exception {
		return ExamResultMapper.toVo(examResultRepository.findExamResultsByExam(EventMapper.toDto(examVo)));
	}

}
