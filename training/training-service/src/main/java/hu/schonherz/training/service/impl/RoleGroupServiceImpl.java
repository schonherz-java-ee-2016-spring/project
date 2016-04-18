package hu.schonherz.training.service.impl;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.entity.Role;
import hu.schonherz.training.repository.RoleGroupRepository;
import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.service.mapper.RoleGroupMapper;
import hu.schonherz.training.service.mapper.RoleMapper;
import hu.schonherz.training.vo.RoleGroupVo;
import hu.schonherz.training.vo.RoleVo;

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
		// DTO-t csinálok a RoleVo listából
//		List<Role> roles = RoleMapper.toDto((List<RoleVo>)roleGroup.getRoles());
		
//		System.out.println("A SERVICE-BEN: " + roles);
		
		// updatelem a jogcsoportot
//		roleGroupRepository.updateRoleGroup(roleGroup.getId(), roleGroup.getName(), roles);
		roleGroupRepository.save( RoleGroupMapper.toDto(roleGroup));
		
	}

}
