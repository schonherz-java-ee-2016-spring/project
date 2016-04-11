package hu.schonherz.training.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.entity.RoleGroup;
import hu.schonherz.training.vo.RoleGroupVo;

public class RoleGroupMapper {

	private static Mapper mapper = new DozerBeanMapper();
	
	public static RoleGroupVo toVo(RoleGroup roleGroupDto) {
		if (roleGroupDto == null) {
			return null;
		}
		return mapper.map(roleGroupDto, RoleGroupVo.class);
	}

	public static RoleGroup toDto(RoleGroupVo roleGroupVo) {
		if (roleGroupVo == null) {
			return null;
		}
		return mapper.map(roleGroupVo, RoleGroup.class);
	}
	
	public static List<RoleGroupVo> toVo(List<RoleGroup> roleGroupDtos) {
		List<RoleGroupVo> roleGroupVos = new ArrayList<>();
		for (RoleGroup RoleGroupDto : roleGroupDtos) {
			roleGroupVos.add(toVo(RoleGroupDto));
		}
		return roleGroupVos;
	}

	public static List<RoleGroup> toDto(List<RoleGroupVo> roleGroupVos) {
		List<RoleGroup> roleGroupDtos = new ArrayList<>();
		for (RoleGroupVo roleGroupVo : roleGroupVos) {
			roleGroupDtos.add(toDto(roleGroupVo));
		}
		return roleGroupDtos;
	}
	
}
