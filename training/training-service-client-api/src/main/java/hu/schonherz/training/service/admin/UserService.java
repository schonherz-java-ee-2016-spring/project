package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;

public interface UserService {

	public UserVo findUserByName(String name) throws Exception;
	
	public void registrationUser(UserVo userVo) throws Exception;
	
	public UserVo findUserByEmail(String email) throws Exception;
	
	public List<UserVo> findAllUser() throws Exception;
	
	public void deleteUserById(Long id) throws Exception;
	
	public void updateUser(UserVo userVo);
	
	public void modifyUser(UserVo selectedUser) throws Exception;

	public UserVo findUserById(Long id);
	
	public UserVo findUserByHashCode(String password);
}
