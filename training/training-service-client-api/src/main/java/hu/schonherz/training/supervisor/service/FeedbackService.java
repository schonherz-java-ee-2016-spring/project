package hu.schonherz.training.supervisor.service;

import java.util.List;

import hu.schonherz.training.supervisor.vo.FeedbackVo;

public interface FeedbackService {

	public FeedbackVo giveFeedback(String ratedName) throws Exception;
	
	public FeedbackVo getFeedback(Long interviewId) throws Exception;
	
	public List<FeedbackVo> getAllFeedbackByUser(Long userId) throws Exception;
}
