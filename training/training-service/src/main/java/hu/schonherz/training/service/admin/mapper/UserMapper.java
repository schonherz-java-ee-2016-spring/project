package hu.schonherz.training.service.admin.mapper;

import java.util.ArrayList;
import java.util.List;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.service.admin.vo.UserVo;

public class UserMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static UserVo toVo(User userDto) {
		if (userDto == null) {
			return null;
		}
		return mapper.map(userDto, UserVo.class);
	}

	public static User toDto(UserVo userVo) {
		if (userVo == null) {
			return null;
		}
		return mapper.map(userVo, User.class);
	}

	public static List<UserVo> toVo(List<User> userDtos) {
		List<UserVo> userVos = new ArrayList<>();
		for (User userDto : userDtos) {
			userVos.add(toVo(userDto));
		}
		return userVos;
	}

	public static List<User> toDto(List<UserVo> userVos) {
		List<User> userDtos = new ArrayList<>();
		for (UserVo userVo : userVos) {
			userDtos.add(toDto(userVo));
		}
		return userDtos;
	}

}
