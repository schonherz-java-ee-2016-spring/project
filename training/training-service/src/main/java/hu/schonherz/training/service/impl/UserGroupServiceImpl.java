package hu.schonherz.training.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.repository.UserGroupRepository;
import hu.schonherz.training.service.UserGroupService;
import hu.schonherz.training.service.mapper.UserGroupMapper;
import hu.schonherz.training.vo.UserGroupVo;

@Stateless(mappedName = "UserGroupService", name = "UserGroupService")
@Transactional(value = TxType.REQUIRED)
@Local(UserGroupService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	UserGroupRepository userGroupRepository;

	@Override
	public void deleteUserGroup(Long userGroupId) throws Exception {
		userGroupRepository.delete(userGroupId);
	}

	@Override
	public void createUserGroup(UserGroupVo userGroupVo) throws Exception {
		userGroupRepository.save(UserGroupMapper.toDto(userGroupVo));
	}

	@Override
	public List<UserGroupVo> getUserGroups() throws Exception {
		return UserGroupMapper.toVo(userGroupRepository.findAll());
	}

	@Override
	public UserGroupVo getUserGroupById(Long userGroupId) throws Exception {
		return UserGroupMapper.toVo(userGroupRepository.findOne(userGroupId));
	}

	@Override
	public UserGroupVo findGroupByName(String groupName) {
		return UserGroupMapper.toVo(userGroupRepository.findUserGroupByGroupName(groupName));
	}

}
