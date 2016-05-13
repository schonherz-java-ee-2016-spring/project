package hu.schonherz.training.service.supervisor;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.UserDetailsVo;

public interface UserDetailsService {

	public void add(UserDetailsVo userDetailsVo) throws Exception;

	public List<UserDetailsVo> getAll() throws Exception;

	public UserDetailsVo getUserDetailsByUser(UserVo userVo) throws Exception;

	public void modifyUserDetails(Long userId, String phoneNumber, String address, String placeOfBirth,
			Date dateOfBirth, String nationality) throws Exception;

}
