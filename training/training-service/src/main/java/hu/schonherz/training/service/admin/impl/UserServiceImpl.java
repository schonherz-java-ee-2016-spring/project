package hu.schonherz.training.service.admin.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.admin.entity.RoleGroup;
import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.admin.repository.RoleGroupRepository;
import hu.schonherz.training.core.admin.repository.RoleRepository;
import hu.schonherz.training.core.admin.repository.UserRepository;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.mapper.UserMapper;
import hu.schonherz.training.service.admin.vo.UserVo;

@Stateless(mappedName = "UserService", name = "UserService")
@Transactional(value = TxType.REQUIRED)
@Local(UserService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class UserServiceImpl implements UserService {

	static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleGroupRepository roleGroupRepository;

	public UserServiceImpl() {
	}

	private RoleGroup getGuestRoleGroup() {
		RoleGroup roleGroup = roleGroupRepository.findByName("Guest Role Group");
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
		return UserMapper.toVo(userRepository.findAllByOrderByFullNameAsc());
	}

	@Override
	public void registrationUser(UserVo userVo) {
		User user = UserMapper.toDto(userVo);
		Collection<RoleGroup> roles = new ArrayList<>();
		RoleGroup roleGroup = null;
		roleGroup = getGuestRoleGroup();
		roles.add(roleGroup);
		user.setRoleGroups(roles);
		user = userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) throws Exception {
		userRepository.delete(id);
	}

	@Override
	public void modifyUser(UserVo selectedUser) throws Exception {
		userRepository.save(UserMapper.toDto(selectedUser));
	}

	@Override
	public UserVo findUserById(Long id) {
		return UserMapper.toVo(userRepository.findUserById(id));
	}

	@Override
	public void updateUser(UserVo userVo) {
		userRepository.save(UserMapper.toDto(userVo));
	}

	@Override
	public UserVo findUserByHashCode(String hashCode) {
		UserVo vo = UserMapper.toVo(userRepository.findUserByHashCode(hashCode));
		return vo;
	}
}
