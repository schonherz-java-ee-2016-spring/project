package hu.schonherz.training.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.entity.UserGroup;
import hu.schonherz.training.vo.UserGroupVo;

public class UserGroupMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static UserGroupVo toVo(UserGroup userGroupDto) {
		if (userGroupDto == null) {
			return null;
		}
		return mapper.map(userGroupDto, UserGroupVo.class);
	}

	public static UserGroup toDto(UserGroupVo userGroupVo) {
		if (userGroupVo == null) {
			return null;
		}
		return mapper.map(userGroupVo, UserGroup.class);
	}

	public static List<UserGroupVo> toVo(List<UserGroup> userGroupDtos) {
		List<UserGroupVo> userGroupVos = new ArrayList<>();
		for (UserGroup userGroupDto : userGroupDtos) {
			userGroupVos.add(toVo(userGroupDto));
		}
		return userGroupVos;
	}

	public static List<UserGroup> toDto(List<UserGroupVo> userGroupVos) {
		List<UserGroup> userGroupDtos = new ArrayList<>();
		for (UserGroupVo userGroupVo : userGroupVos) {
			userGroupDtos.add(toDto(userGroupVo));
		}
		return userGroupDtos;
	}

}
