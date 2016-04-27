package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.RoleVo;

public interface RoleService {

	public List<RoleVo> findAllRole() throws Exception;

	public void createRole(RoleVo role); 
	
	void deleteRole( Long roleId );
	
	RoleVo getRoleByName( String roleName );
	
	RoleVo getRoleByRoleCode(String roleCode);
	
	RoleVo getRoleById(Long id);
	
}
