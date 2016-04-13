package hu.schonherz.training.service;

import java.util.List;

import hu.schonherz.training.vo.UserGroupVo;

public interface UserGroupService {

	void deleteUserGroup(Long userGroupId) throws Exception;

	void createUserGroup(UserGroupVo userGroupVo) throws Exception;

	List<UserGroupVo> getUserGroups() throws Exception;

	UserGroupVo getUserGroupById(Long userGroupId) throws Exception;

}
