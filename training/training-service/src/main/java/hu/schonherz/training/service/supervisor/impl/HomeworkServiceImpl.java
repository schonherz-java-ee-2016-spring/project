package hu.schonherz.training.service.supervisor.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.supervisor.entity.Homework;
import hu.schonherz.training.core.supervisor.repository.HomeworkRepository;
import hu.schonherz.training.service.supervisor.HomeworkService;
import hu.schonherz.training.service.supervisor.mapper.HomeworkMapper;
import hu.schonherz.training.service.supervisor.vo.HomeworkVo;


@Stateless(mappedName = "HomeworkService", name = "HomeworkService")
@Transactional(value = TxType.REQUIRED)
@Local(HomeworkService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class HomeworkServiceImpl implements HomeworkService {

	@Autowired
	HomeworkRepository homeworkRepository;
	
	
	@Override
	public HomeworkVo createHomework(HomeworkVo homeworkVo) {
		Homework homework = HomeworkMapper.toDto(homeworkVo);
		homework = homeworkRepository.save(homework);
		return HomeworkMapper.toVo(homework);
	}

	@Override
	public void deleteHomework(Long id) {
		homeworkRepository.delete(id);

	}

	@Override
	public HomeworkVo getHomeworkById(Long id) {
		Homework homework = homeworkRepository.findOne(id);
		return HomeworkMapper.toVo(homework);
	}

	@Override
	public HomeworkVo getHomeworkByTitle(String title) {
		Homework homework = homeworkRepository.findHomeworkByTitle(title);
		return HomeworkMapper.toVo(homework);
	}

	@Override
	public List<HomeworkVo> getAllHomeworks() {
		return HomeworkMapper.toVo(homeworkRepository.findAll());
	}

	@Override
	public List<HomeworkVo> getHomeworksBetween(Date from, Date to) {
		return HomeworkMapper.toVo(homeworkRepository.findHomeworksByDeadlineBetween(from, to));
	}

}
