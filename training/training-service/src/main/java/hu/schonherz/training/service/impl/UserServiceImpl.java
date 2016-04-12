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

	public UserServiceImpl() {
	}

	@Override
	public UserVo findUserByName(String name) throws Exception {
		UserVo vo = UserMapper.toVo(userRepository.findUserByUserName(name));
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
}
