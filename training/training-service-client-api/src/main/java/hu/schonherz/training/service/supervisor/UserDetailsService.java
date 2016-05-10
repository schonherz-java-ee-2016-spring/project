package hu.schonherz.training.service.supervisor;

import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.UserDetailsVo;

public interface UserDetailsService {

	public void add(UserDetailsVo userDetailsVo) throws Exception;

	public List<UserDetailsVo> getAll() throws Exception;

	public UserDetailsVo getUserDetailsByUser(UserVo userVo) throws Exception;

	public void modifyPhoneNumberAndAddress(Long userId, String phoneNumber, String address) throws Exception;

}
