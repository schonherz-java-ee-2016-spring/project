package hu.schonherz.training.supervisor.service;

import java.util.Date;

import hu.schonherz.training.supervisor.vo.InterviewVo;

public interface InterviewService {

	public InterviewVo addInterview(InterviewVo interviewVo) throws Exception;
	
	public InterviewVo findAllByUser(Long userId) throws Exception;
	
	public InterviewVo findAllByUserAndDate(Long userId, Date interviewDate) throws Exception;
	
	public void deleteInterview(Long interviewId) throws Exception;
	
	public void deleteAllInterviewsOnDay(Date interviewDate) throws Exception;
	
}
