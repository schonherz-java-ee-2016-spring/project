package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.UserGroupVo;

public interface UserGroupService {

	void deleteUserGroup(Long userGroupId) throws Exception;

	void saveUserGroup(UserGroupVo userGroupVo) throws Exception;

	List<UserGroupVo> getUserGroups() throws Exception;

	UserGroupVo getUserGroupById(Long userGroupId) throws Exception;

	UserGroupVo findGroupByName(String groupName) throws Exception;

}
