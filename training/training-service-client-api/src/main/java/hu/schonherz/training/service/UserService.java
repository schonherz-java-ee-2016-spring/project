package hu.schonherz.training.service;

import java.util.List;

import hu.schonherz.training.vo.UserVo;

public interface UserService {

	public UserVo findUserByName(String name) throws Exception;
	
	public UserVo findUserByEmail(String email) throws Exception;
	
	public List<UserVo> findAllUser() throws Exception;
	
	
}
