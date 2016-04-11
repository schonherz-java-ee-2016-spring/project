package hu.schonherz.training.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.entity.Role;
import hu.schonherz.training.vo.RoleVo;

public class RoleMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static RoleVo toVo(Role roleDto) {
		if (roleDto == null) {
			return null;
		}
		return mapper.map(roleDto, RoleVo.class);
	}

	public static Role toDto(RoleVo roleVo) {
		if (roleVo == null) {
			return null;
		}
		return mapper.map(roleVo, Role.class);
	}

	public static List<RoleVo> toVo(List<Role> roleDtos) {
		List<RoleVo> roleVos = new ArrayList<>();
		for (Role roleDto : roleDtos) {
			roleVos.add(toVo(roleDto));
		}
		return roleVos;
	}

	public static List<Role> toDto(List<RoleVo> roleVos) {
		List<Role> roleDtos = new ArrayList<>();
		for (RoleVo roleVo : roleVos) {
			roleDtos.add(toDto(roleVo));
		}
		return roleDtos;
	}

}
