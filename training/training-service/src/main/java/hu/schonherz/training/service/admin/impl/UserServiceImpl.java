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

	private RoleGroup getGuestRoleGroup() throws Exception {
		RoleGroup roleGroup = roleGroupRepository.findByName("Guest Role Group");
//		if (roleGroup == null) {
//			roleGroup = new RoleGroup();
//			roleGroup.setName("Guest Role Group");
//			roleGroup = roleGroupRepository.save(roleGroup);
//		}
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
	public void registrationUser(UserVo userVo) {
		User user = UserMapper.toDto(userVo);
		Collection<RoleGroup> roles = new ArrayList<>();
//		if (roles == null) {
//			roles = new ArrayList<>();
//		}
		RoleGroup roleGroup = null;
		try {
			roleGroup = getGuestRoleGroup();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
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
//		userRepository.updateUser(selectedUser.getUserName(), selectedUser.getFullName(), selectedUser.getEmail(),
//				selectedUser.getId());
		userRepository.save(UserMapper.toDto(selectedUser));
	}

	@Override
	public UserVo findUserById(Long id) {
		User user = null;
		try {
			user = userRepository.findUserById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return UserMapper.toVo(user);
	}

	@Override
	public void updateUser(UserVo userVo) {
//		String email = userVo.getEmail();
//		userVo.setEmail(email + "asd");
//		userRepository.save(UserMapper.toDto(userVo));
//		userVo.setEmail(email);
		userRepository.save(UserMapper.toDto(userVo));
	}

	@Override
	public UserVo findUserByHashCode(String hashCode) {
		UserVo vo = UserMapper.toVo(userRepository.findUserByHashCode(hashCode));
		return vo;
	}
}
