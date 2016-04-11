package hu.schonherz.training.service;

import hu.schonherz.training.vo.UserVo;

public interface UserService {

	public UserVo findUserByName(String name) throws Exception;
}
