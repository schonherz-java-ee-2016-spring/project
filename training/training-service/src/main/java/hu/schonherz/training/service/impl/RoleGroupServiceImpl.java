package hu.schonherz.training.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.vo.RoleGroupVo;

@Stateless(mappedName = "RoleGroupService", name = "RoleGroupService")
@Transactional(value = TxType.REQUIRED)
@Local(RoleGroupService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class RoleGroupServiceImpl implements RoleGroupService {

	@Override
	public void createRoleGroup(RoleGroupVo roleGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoleGroup(Long roleGroupId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoleGroupVo> getAllRoleGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleGroupVo getRoleGroupByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleGroupVo getRoleGroupById() {
		// TODO Auto-generated method stub
		return null;
	}

}
