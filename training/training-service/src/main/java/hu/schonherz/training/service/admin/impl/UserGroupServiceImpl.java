package hu.schonherz.training.service.admin.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.admin.repository.UserGroupRepository;
import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.mapper.UserGroupMapper;
import hu.schonherz.training.service.admin.vo.UserGroupVo;

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
	public void saveUserGroup(UserGroupVo userGroupVo) throws Exception {
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
	public UserGroupVo findGroupByName(String groupName) throws Exception {
		return UserGroupMapper.toVo(userGroupRepository.findUserGroupByGroupName(groupName));
	}

}
