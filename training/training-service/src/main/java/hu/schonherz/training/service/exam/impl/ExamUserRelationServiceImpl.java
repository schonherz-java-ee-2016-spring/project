package hu.schonherz.training.service.exam.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.exam.repository.ExamUserRelationRepository;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.ExamUserRelationService;
import hu.schonherz.training.service.exam.mapper.ExamUserRelationMapper;
import hu.schonherz.training.service.exam.vo.ExamUserRelationVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

@Stateless(mappedName = "ExamUserRelationService", name = "ExamUserRelationService")
@Transactional(value = TxType.REQUIRED)
@Local(ExamUserRelationService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ExamUserRelationServiceImpl implements ExamUserRelationService {

	static final Logger logger = LogManager.getLogger(ExamUserRelationServiceImpl.class.getName());

	@Autowired
	ExamUserRelationRepository examUserRelationRepository;

	@Override
	public List<ExamVo> getAllExamByUserId(Long id) throws Exception {
		try {
			List<ExamUserRelationVo> list = ExamUserRelationMapper.toVo(examUserRelationRepository.findAllByUserId(id));
			return list.stream().map(v -> v.getExam()).collect(Collectors.toList());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<UserVo> getAllUserByExamId(Long id) throws Exception {
		try {
			List<ExamUserRelationVo> list = ExamUserRelationMapper.toVo(examUserRelationRepository.findAllByExamId(id));
			return list.stream().map(v -> v.getUser()).collect(Collectors.toList());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public void save(ExamUserRelationVo vo) throws Exception {
		try {
			examUserRelationRepository.saveAndFlush(ExamUserRelationMapper.toDto(vo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
