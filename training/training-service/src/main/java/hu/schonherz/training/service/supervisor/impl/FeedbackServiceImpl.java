package hu.schonherz.training.service.supervisor.impl;

import java.util.ArrayList;
import java.util.Collections;
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
	public List<FeedbackVo> getAll() throws Exception {
		List<FeedbackVo> result;
		if (feedbackRepository.findAll() == null) {
			result = new ArrayList<>();
		} else {
			List<Feedback> unsortedResult = new ArrayList<>();
			unsortedResult = feedbackRepository.findAll();
			Collections.sort(unsortedResult, Feedback.FeedbackDateComparator);
			result = FeedbackMapper.toVo(unsortedResult);
		}
		
		return result;
	}

	/**
	 * 
	 */
	public FeedbackServiceImpl() {

	}

}
