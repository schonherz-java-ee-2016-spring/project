package hu.schonherz.training.service.exam.impl;

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

import hu.schonherz.training.core.exam.repository.AnswerRepository;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.vo.AnswerVo;

@Stateless(mappedName = "AnswerService", name = "AnswerService")
@Transactional(value = TxType.REQUIRED)
@Local(AnswerService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class })
public class AnswerServiceImpl implements AnswerService {

	static final Logger logger = LogManager.getLogger(AnswerServiceImpl.class.getName());
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Override
	public AnswerVo getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerVo> getAll(Long... id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
