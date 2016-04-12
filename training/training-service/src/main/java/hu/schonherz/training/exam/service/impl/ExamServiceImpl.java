package hu.schonherz.training.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.exam.repository.ExamRepository;
import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.service.mapper.ExamMapper;
import hu.schonherz.training.exam.vo.ExamVo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamRepository examRepository;

	@Override
	public void createExam(ExamVo examVo) throws Exception {
		examRepository.saveAndFlush(ExamMapper.toDto(examVo));
		
	}
}
