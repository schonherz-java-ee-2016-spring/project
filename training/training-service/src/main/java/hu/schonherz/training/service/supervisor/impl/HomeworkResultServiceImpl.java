package hu.schonherz.training.service.supervisor.impl;

import java.util.ArrayList;
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
import hu.schonherz.training.core.supervisor.entity.HomeworkResult;
import hu.schonherz.training.core.supervisor.repository.HomeworkRepository;
import hu.schonherz.training.core.supervisor.repository.HomeworkResultRepository;
import hu.schonherz.training.service.admin.mapper.UserMapper;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.mapper.HomeworkMapper;
import hu.schonherz.training.service.supervisor.mapper.HomeworkResultMapper;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkVo;


@Stateless(mappedName = "HomeworkResultService", name = "HomeworkResultService")
@Transactional(value = TxType.REQUIRED)
@Local(HomeworkResultService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class HomeworkResultServiceImpl implements HomeworkResultService {

	@Autowired
	private HomeworkResultRepository homeworkResultRepository;
	
	@Autowired
	private HomeworkRepository homeworkRepository;
	
	@Override
	public HomeworkResultVo createHomeworkResult(HomeworkResultVo homeworkResultVo) {
		return HomeworkResultMapper.toVo(homeworkResultRepository.save(HomeworkResultMapper.toDto(homeworkResultVo)));
	}

	@Override
	public HomeworkResultVo getHomeworkResultById(Long id) {
		return HomeworkResultMapper.toVo(homeworkResultRepository.getOne(id));
	}

	@Override
	public List<HomeworkResultVo> getHomeworkResultsByUser(UserVo userVo) {
		return HomeworkResultMapper.toVo(homeworkResultRepository.findHomeworkResultsByUser(UserMapper.toDto(userVo)));
	}

	@Override
	public List<HomeworkResultVo> getHomeworkResultsByHomework(HomeworkVo homeworkVo) {
		return HomeworkResultMapper.toVo(homeworkResultRepository.findHomeworkResultsByHomework(HomeworkMapper.toDto(homeworkVo)));
	}

	@Override
	public List<HomeworkResultVo> getAllHomeworkResults() {
		return HomeworkResultMapper.toVo(homeworkResultRepository.findAll());
	}

	@Override
	public List<HomeworkResultVo> getHomeworkResultsBetween(Date from, Date to) {
		List<Homework> homeworks = homeworkRepository.findHomeworksByDeadlineBetween(from, to);
		List<HomeworkResult> homeworkResults = new ArrayList<>();
		
		for (Homework homework : homeworks) {
			List<HomeworkResult> homeworkResultsTemp = homeworkResultRepository.findHomeworkResultsByHomework(homework);
			homeworkResults.addAll(homeworkResultsTemp);
		}
		
		return HomeworkResultMapper.toVo(homeworkResults);
	}

	@Override
	public List<HomeworkResultVo> getHomeworkResultsByUsers(List<UserVo> userVos) {
		List<HomeworkResult> homeworkResults = new ArrayList<>();
		
		for (UserVo userVo : userVos) {
			homeworkResults.addAll(homeworkResultRepository.findHomeworkResultsByUser(UserMapper.toDto(userVo)));
		}
		
		return HomeworkResultMapper.toVo(homeworkResults);
	}

}
