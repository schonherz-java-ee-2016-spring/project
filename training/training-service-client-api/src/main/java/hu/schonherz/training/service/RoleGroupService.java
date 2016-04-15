package hu.schonherz.training.service;

import java.util.List;

import hu.schonherz.training.vo.RoleGroupVo;

public interface RoleGroupService {
	
	void createRoleGroup( RoleGroupVo roleGroup );
	
	void deleteRoleGroup( Long roleGroupId );
	
	List<RoleGroupVo> getAllRoleGroup();
	
	RoleGroupVo getRoleGroupByName();
	
	RoleGroupVo getRoleGroupById();

}
