package hu.schonherz.training.supervisor.service;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.supervisor.vo.InterviewVo;

public interface InterviewService {

	public InterviewVo addInterview(InterviewVo interviewVo) throws Exception;
	
	public InterviewVo getInterview(Long interviewId) throws Exception;
	
	public List<InterviewVo> getAllByUser(Long userId) throws Exception;
	
	public List<InterviewVo> getAllByUserAndDate(Long userId, Date interviewDate) throws Exception;
	
	public void deleteInterview(Long interviewId) throws Exception;
	
	public void deleteAllInterviewsOnDay(Date interviewDate) throws Exception;
	
}
