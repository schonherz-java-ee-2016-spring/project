package hu.schonherz.training.service.supervisor.impl;

import java.util.ArrayList;
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

import hu.schonherz.training.core.supervisor.entity.Feedback;
import hu.schonherz.training.core.supervisor.repository.FeedbackRepository;
import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.mapper.FeedbackMapper;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;

@Stateless(mappedName = "FeedbackService", name = "FeedbackService")
@Transactional(value = TxType.REQUIRED)
@Local(FeedbackService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class FeedbackServiceImpl implements FeedbackService {

	static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class.getName());

	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public void giveFeedback(FeedbackVo feedbackVo) throws Exception {
		try {
			feedbackRepository.saveAndFlush(FeedbackMapper.toDto(feedbackVo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public FeedbackVo getFeedback(Long feedbackId) throws Exception {
		Feedback feedback = feedbackRepository.findOne(feedbackId);
		return FeedbackMapper.toVo(feedback);
	}

	@Override
	public List<FeedbackVo> getAllFeedbackBySender(Long userId) throws Exception {
		List<FeedbackVo> result = null;
		try {
			List<Feedback> feedbacks = feedbackRepository.findAll();
			for (Feedback feedback : feedbacks) {
				if (result == null) {
					result = new ArrayList<>();
				}
				if (feedback.getSender().getId() == userId) {
					result.add(FeedbackMapper.toVo(feedback));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	@Override
	public List<FeedbackVo> getAllFeedbackByRated(Long userId) throws Exception {
		List<FeedbackVo> result = null;
		try {
			List<Feedback> feedbacks = feedbackRepository.findAll();
			for (Feedback feedback : feedbacks) {
				if (result == null) {
					result = new ArrayList<>();
				}
				if (feedback.getRated().getId() == userId) {
					result.add(FeedbackMapper.toVo(feedback));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<FeedbackVo> getAll() throws Exception {
		List<FeedbackVo> result;
		if (feedbackRepository.findAll() == null) {
			result = new ArrayList<>();
		} else {
			result = FeedbackMapper.toVo(feedbackRepository.findAll());
		}
		return result;
	}

	/**
	 * 
	 */
	public FeedbackServiceImpl() {

	}

}
