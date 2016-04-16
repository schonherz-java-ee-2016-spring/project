package hu.schonherz.training.exam.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.exam.repository.OptionRepository;
import hu.schonherz.training.exam.service.OptionService;
import hu.schonherz.training.exam.service.mapper.OptionMapper;
import hu.schonherz.training.exam.vo.OptionVo;

@Stateless(mappedName = "OptionService", name = "OptionService")
@Transactional(value = TxType.REQUIRED)
@Local(OptionService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class OptionServiceImpl implements OptionService{

	@Autowired
	OptionRepository optionRepository;
	
	static final Logger logger = LogManager.getLogger(OptionServiceImpl.class.getName());

	
	@Override
	public void createOption(OptionVo optionVo) throws Exception {
		try {
			optionRepository.saveAndFlush(OptionMapper.toDto(optionVo));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Override
	public void deleteOption(OptionVo optionVo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
