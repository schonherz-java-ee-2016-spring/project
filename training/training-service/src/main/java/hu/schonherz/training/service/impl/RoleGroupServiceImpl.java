package hu.schonherz.training.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.repository.RoleGroupRepository;
import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.service.mapper.RoleGroupMapper;
import hu.schonherz.training.vo.RoleGroupVo;

@Stateless(mappedName = "RoleGroupService", name = "RoleGroupService")
@Transactional(value = TxType.REQUIRED)
@Local(RoleGroupService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class RoleGroupServiceImpl implements RoleGroupService {
	
	@Autowired
	RoleGroupRepository roleGroupRepository;

	@Override
	public void createRoleGroup(RoleGroupVo roleGroup) {
		roleGroupRepository.save( RoleGroupMapper.toDto(roleGroup) );
		
	}

	@Override
	public void deleteRoleGroup(Long roleGroupId) {
		roleGroupRepository.delete(roleGroupId);
		
	}

	@Override
	public List<RoleGroupVo> getAllRoleGroup() {
		return RoleGroupMapper.toVo(roleGroupRepository.findAll());
	}

	@Override
	public RoleGroupVo getRoleGroupByName( String roleGroupName ) {
		return RoleGroupMapper.toVo(roleGroupRepository.findByName(roleGroupName));
	}

	@Override
	public RoleGroupVo getRoleGroupById( Long roleGroupId ) {
		return RoleGroupMapper.toVo(roleGroupRepository.findOne(roleGroupId));
	}

	@Override
	public void updateRoleGroup(RoleGroupVo roleGroup) {
		roleGroupRepository.updateRoleGroup(roleGroup.getId(), roleGroup.getName());
		
	}

}
