package hu.schonherz.training.service;

import java.util.List;

import hu.schonherz.training.vo.RoleVo;

public interface RoleService {

	public List<RoleVo> findAllRole() throws Exception;

	public void createRole(RoleVo role); 
	
	void deleteRole( Long roleId );

	void updateRole( RoleVo role);
	
	RoleVo getRoleByName( String roleName );
	
	RoleVo getRoleByRoleCode(String roleCode);
	
	RoleVo getRoleById(Long id);
	
}
