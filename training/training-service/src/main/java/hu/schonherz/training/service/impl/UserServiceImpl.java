package hu.schonherz.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.entity.RoleGroup;
import hu.schonherz.training.entity.User;
import hu.schonherz.training.repository.RoleGroupRepository;
import hu.schonherz.training.repository.RoleRepository;
import hu.schonherz.training.repository.UserRepository;
import hu.schonherz.training.service.UserService;
import hu.schonherz.training.service.mapper.UserMapper;
import hu.schonherz.training.vo.UserVo;

@Stateless(mappedName = "UserService", name = "UserService")
@Transactional(value = TxType.REQUIRED)
@Local(UserService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleGroupRepository roleGroupRepository;

	public UserServiceImpl() {
	}

	private RoleGroup getGuestRoleGroup() {
		RoleGroup roleGroup = roleGroupRepository.findByName("ROLE_GUEST");
		if (roleGroup == null) {
			roleGroup = new RoleGroup();
			roleGroup.setName("ROLE_GUEST");
			roleGroup = roleGroupRepository.save(roleGroup);
		}
		return roleGroup;
	}

	@Override
	public UserVo findUserByName(String name) throws Exception {
		UserVo vo = UserMapper.toVo(userRepository.findUserByUserName(name));
		return vo;
	}
	@Override
	public UserVo findUserByEmail(String email) throws Exception {
		UserVo vo = UserMapper.toVo(userRepository.findUserByEmail(email));
		return vo;
	}

	@Override
	public List<UserVo> findAllUser() throws Exception {
		List<UserVo> vos;
		if (userRepository.findAll() == null) {
			vos = new ArrayList<>();
		} else {
			vos = UserMapper.toVo(userRepository.findAll());
		}
		return vos;
	}

	@Override
	public UserVo registrationUser(UserVo userVo) throws Exception {
		User user = UserMapper.toDto(userVo);
		Collection<RoleGroup> roles = user.getRoleGroups();
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<>();
		}
		RoleGroup roleGroup = getGuestRoleGroup();
		roles.add(roleGroup);
		user.setRoleGroups(roles);
		user = userRepository.save(user);
		return UserMapper.toVo(user);
	}

	@Override
	public void deleteUserById(Long id) throws Exception {
		userRepository.removeUserById(id);
	}

	@Override
	public void modifyUser(UserVo selectedUser) {
		
		
		
	}

	@Override
	public UserVo findUserById(Long id) {
		User user = null;
		try {
			user = userRepository.findUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UserMapper.toVo(user);
	}
}
