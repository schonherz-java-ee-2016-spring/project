package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.RoleGroupVo;

public interface RoleGroupService {
	
	void createRoleGroup( RoleGroupVo roleGroup );
	
	void deleteRoleGroup( Long roleGroupId );
	
	void updateRoleGroup( RoleGroupVo roleGroup );
	
	List<RoleGroupVo> getAllRoleGroup() throws Exception;
	
	RoleGroupVo getRoleGroupByName( String roleGroupName ) throws Exception;
	
	RoleGroupVo getRoleGroupById( Long roleGroupId ) throws Exception;

}
