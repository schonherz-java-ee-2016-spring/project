package hu.schonherz.training.service.supervisor;

import java.util.List;

import hu.schonherz.training.service.supervisor.vo.FeedbackVo;

public interface FeedbackService {

	public void giveFeedback(FeedbackVo feedbackVo) throws Exception;
	
	public List<FeedbackVo> getAll() throws Exception;
	
}
