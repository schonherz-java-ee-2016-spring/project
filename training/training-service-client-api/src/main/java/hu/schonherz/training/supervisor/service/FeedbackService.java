package hu.schonherz.training.supervisor.service;

import hu.schonherz.training.supervisor.vo.FeedbackVo;

public interface FeedbackService {

	public FeedbackVo giveFeedback(String ratedName) throws Exception;
	
}
