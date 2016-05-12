package hu.schonherz.training.service.supervisor.impl;

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

import hu.schonherz.training.core.supervisor.repository.ExamResultRepository;
import hu.schonherz.training.service.admin.mapper.UserMapper;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.impl.ExamServiceImpl;
import hu.schonherz.training.service.exam.mapper.ExamMapper;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.supervisor.ExamResultService;
import hu.schonherz.training.service.supervisor.mapper.ExamResultMapper;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;

@Stateless(mappedName = "ExamResultService", name = "ExamResultService")
@Transactional(value = TxType.REQUIRED)
@Local(ExamResultService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ExamResultServiceImpl implements ExamResultService {

	static final Logger logger = LogManager.getLogger(ExamServiceImpl.class.getName());

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
	public List<ExamResultVo> getExamResultByExam(ExamVo examVo) throws Exception {
		return ExamResultMapper.toVo(examResultRepository.findExamResultsByExam(ExamMapper.toDto(examVo)));
	}

	@Override
	public void add(ExamResultVo examResultVo) throws Exception {
		try {
			examResultRepository.saveAndFlush(ExamResultMapper.toDto(examResultVo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public ExamResultVo getByExamIdAndUserId(Long examId, Long userId) throws Exception {
		return ExamResultMapper.toVo(examResultRepository.findByExamIdAndUserId(examId, userId));
	}

	@Override
	public void modifyPoints(Long examId, Long userId, Integer points) throws Exception {
		try {
			examResultRepository.updatePointsByExamIdAndUserId(examId, userId, points);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
