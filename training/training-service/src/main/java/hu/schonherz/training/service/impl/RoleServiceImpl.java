package hu.schonherz.training.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.repository.RoleRepository;
import hu.schonherz.training.service.RoleService;
import hu.schonherz.training.service.mapper.RoleMapper;
import hu.schonherz.training.vo.RoleVo;

@Stateless(mappedName = "RoleService", name = "RoleService")
@Transactional(value = TxType.REQUIRED)
@Local(RoleService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<RoleVo> getAllRole() {
		return RoleMapper.toVo(roleRepository.findAll());
	}

}
