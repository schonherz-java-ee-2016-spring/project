package hu.schonherz.training.service.supervisor.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.supervisor.repository.UserDetailsRepository;
import hu.schonherz.training.service.admin.mapper.UserMapper;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.UserDetailsService;
import hu.schonherz.training.service.supervisor.mapper.UserDetailsMapper;
import hu.schonherz.training.service.supervisor.vo.UserDetailsVo;

@Stateless(mappedName = "UserDetailsService", name = "UserDetailsService")
@Transactional(value = TxType.REQUIRED)
@Local(UserDetailsService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public void add(UserDetailsVo userDetailsVo) throws Exception {
		userDetailsRepository.saveAndFlush(UserDetailsMapper.toDto(userDetailsVo));

	}

	@Override
	public List<UserDetailsVo> getAll() throws Exception {
		return UserDetailsMapper.toVo(userDetailsRepository.findAll());
	}

	@Override
	public UserDetailsVo getUserDetailsByUser(UserVo userVo) throws Exception {
		return UserDetailsMapper.toVo(userDetailsRepository.findUserDetailsByUser(UserMapper.toDto(userVo)));
	}

	@Override
	public void modifyUserDetails(Long userId, String phoneNumber, String address, String placeOfBirth,
			Date dateOfBirth, String nationality) throws Exception {
		userDetailsRepository.updateUserDetailsByUserId(userId, phoneNumber, address, placeOfBirth, dateOfBirth,
				nationality);

	}

}
