package hu.schonherz.training.service.admin.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.admin.entity.RoleGroup;
import hu.schonherz.training.core.admin.repository.RoleGroupRepository;
import hu.schonherz.training.service.admin.RoleGroupService;
import hu.schonherz.training.service.admin.mapper.RoleGroupMapper;
import hu.schonherz.training.service.admin.vo.RoleGroupVo;

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
	public List<RoleGroupVo> getAllRoleGroup() throws Exception {
		return RoleGroupMapper.toVo(roleGroupRepository.findAll());
	}

	@Override
	public RoleGroupVo getRoleGroupByName( String roleGroupName ) throws Exception {
		RoleGroup findByName = roleGroupRepository.findByName(roleGroupName);
		if( findByName == null )
			throw new Exception("No Result");
		return RoleGroupMapper.toVo(findByName);
	}

	@Override
	public RoleGroupVo getRoleGroupById( Long roleGroupId ) throws Exception {
		return RoleGroupMapper.toVo(roleGroupRepository.findOne(roleGroupId));
	}

	@Override
	public void updateRoleGroup(RoleGroupVo roleGroup) {
		roleGroupRepository.save( RoleGroupMapper.toDto(roleGroup));
		
	}

}
