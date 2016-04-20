package hu.schonherz.training.service.supervisor.impl;

import java.util.ArrayList;
import java.util.Date;
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

import hu.schonherz.training.core.supervisor.entity.Interview;
import hu.schonherz.training.core.supervisor.repository.InterviewRepository;
import hu.schonherz.training.service.supervisor.InterviewService;
import hu.schonherz.training.service.supervisor.mapper.InterviewMapper;
import hu.schonherz.training.service.supervisor.vo.InterviewVo;

@Stateless(mappedName = "InterviewService", name = "InterviewService")
@Transactional(value = TxType.REQUIRED)
@Local(InterviewService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class InterviewServiceImpl implements InterviewService {

	static final Logger logger = LogManager.getLogger(InterviewServiceImpl.class.getName());

	@Autowired
	InterviewRepository interviewRepository;

	@Override
	public void addInterview(InterviewVo interviewVo) throws Exception {
		try {
			interviewRepository.saveAndFlush(InterviewMapper.toDto(interviewVo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public InterviewVo getInterview(Long interviewId) throws Exception {
		Interview interview = interviewRepository.findOne(interviewId);
		return InterviewMapper.toVo(interview);
	}

	@Override
	public List<InterviewVo> getAll() throws Exception {
		return InterviewMapper.toVo(interviewRepository.findAll());
	}

	@Override
	public List<InterviewVo> getAllByInterviewed(Long userId) throws Exception {
		List<InterviewVo> result = null;
		try {
			List<Interview> interviews = interviewRepository.findAll();
			for (Interview interview : interviews) {
				if (result == null) {
					result = new ArrayList<>();
				}
				if (interview.getInterviewed().getId() == userId) {
					result.add(InterviewMapper.toVo(interview));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<InterviewVo> getAllByInterviewer(Long userId) throws Exception {
		List<InterviewVo> result = null;
		try {
			List<Interview> interviews = interviewRepository.findAll();
			for (Interview interview : interviews) {
				if (result == null) {
					result = new ArrayList<>();
				}
				if (interview.getInterviewer().getId() == userId) {
					result.add(InterviewMapper.toVo(interview));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public InterviewVo getAllByInterviewedAndDate(Long userId, Date interviewDate) throws Exception {
		InterviewVo result = null;
		try {
			List<Interview> interviews = interviewRepository.findAll();
			for (Interview interview : interviews) {
				
				if (interview.getInterviewed().getId() == userId && interview.getInterviewDate() == interviewDate) {
					result = InterviewMapper.toVo(interview);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public InterviewVo getAllByInterviewerAndDate(Long userId, Date interviewDate) throws Exception {
		InterviewVo result = null;
		try {
			List<Interview> interviews = interviewRepository.findAll();
			for (Interview interview : interviews) {
				if (interview.getInterviewer().getId() == userId && interview.getInterviewDate() == interviewDate) {
					result = InterviewMapper.toVo(interview);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void deleteInterview(Long interviewId) throws Exception {
		interviewRepository.delete(interviewId);
	}

	@Override
	public void deleteAllInterviewsOnDay(Date interviewDate) throws Exception {
		List<Interview> interviews = interviewRepository.findAll();
		for (Interview interview : interviews) {
			if (interview.getInterviewDate() == interviewDate) {
				interviewRepository.delete(interview.getId());
			}
		}
	}

}
