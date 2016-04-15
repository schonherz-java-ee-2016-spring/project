package hu.schonherz.training.supervisor.service;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.supervisor.vo.InterviewVo;

public interface InterviewService {

	public void addInterview(InterviewVo interviewVo) throws Exception;
	
	public InterviewVo getInterview(Long interviewId) throws Exception;
	
	public List<InterviewVo> getAll() throws Exception;
	
	public List<InterviewVo> getAllByInterviewed(Long userId) throws Exception;
	
	public List<InterviewVo> getAllByInterviewer(Long userId) throws Exception;
	
	public InterviewVo getAllByInterviewedAndDate(Long userId, Date interviewDate) throws Exception;
	
	public InterviewVo getAllByInterviewerAndDate(Long userId, Date interviewDate) throws Exception;
	
	public void deleteInterview(Long interviewId) throws Exception;
	
	public void deleteAllInterviewsOnDay(Date interviewDate) throws Exception;
	
}
