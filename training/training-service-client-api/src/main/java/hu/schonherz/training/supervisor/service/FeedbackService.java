package hu.schonherz.training.supervisor.service;

import java.util.List;

import hu.schonherz.training.supervisor.vo.FeedbackVo;

public interface FeedbackService {

	public void giveFeedback(FeedbackVo feedbackVo) throws Exception;
	
	public List<FeedbackVo> getAll() throws Exception;
	
	public FeedbackVo getFeedback(Long feedbackId) throws Exception;
	
	public List<FeedbackVo> getAllFeedbackBySender(Long userId) throws Exception;
	
	public List<FeedbackVo> getAllFeedbackByRated(Long userId) throws Exception;
}
