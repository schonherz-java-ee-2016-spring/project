package hu.schonherz.training.service.impl;

import java.util.ArrayList;
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
	public List<RoleVo> findAllRole() throws Exception {
		List<RoleVo> vos;		
		if (roleRepository.findAll() == null) {
			vos = new ArrayList<>();
		} else {
			vos = RoleMapper.toVo(roleRepository.findAll());
		}
		return vos;
	}
	
	@Override
	public void createRole(RoleVo role) {
		roleRepository.save( RoleMapper.toDto(role) );
		
	}

	@Override
	public RoleVo getRoleByName(String roleName) {
		return RoleMapper.toVo(roleRepository.findByName(roleName));
	}

	@Override
	public RoleVo getRoleByRoleCode(String roleCode) {
		return RoleMapper.toVo(roleRepository.findByRoleCode(roleCode));
	}
	
	@Override
	public RoleVo getRoleById( Long roleId ) {
		return RoleMapper.toVo(roleRepository.findOne(roleId));
		}

	@Override
	public void deleteRole(Long roleId) {
			roleRepository.delete(roleId);		
		}
	
	@Override
	public void updateRole(RoleVo role) {
		roleRepository.updateRole(role.getId(), role.getName());
		
	}
	




	
}
