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

import hu.schonherz.training.core.exam.repository.OptionRepository;
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.mapper.OptionMapper;
import hu.schonherz.training.service.exam.vo.OptionVo;

@Stateless(mappedName = "OptionService", name = "OptionService")
@Transactional(value = TxType.REQUIRED)
@Local(OptionService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class OptionServiceImpl implements OptionService {

	static final Logger logger = LogManager.getLogger(OptionServiceImpl.class.getName());

	@Autowired
	OptionRepository optionRepository;

	@Override
	public OptionVo getById(Long id) throws Exception {
		try {
			return OptionMapper.toVo(optionRepository.findOne(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<OptionVo> getAll(Long... id) throws Exception {
		try {
			return OptionMapper.toVo(optionRepository.findAll());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

}
