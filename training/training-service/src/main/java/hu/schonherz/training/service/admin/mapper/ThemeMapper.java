package hu.schonherz.training.service.admin.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.admin.entity.Theme;
import hu.schonherz.training.service.admin.vo.ThemeVo;

public class ThemeMapper {
	private static Mapper mapper = new DozerBeanMapper();

	public static ThemeVo toVo(Theme themeDto) {
		if (themeDto == null) {
			return null;
		}
		return mapper.map(themeDto, ThemeVo.class);
	}

	public static Theme toDto(ThemeVo themeVo) {
		if (themeVo == null) {
			return null;
		}
		return mapper.map(themeVo, Theme.class);
	}

	public static List<ThemeVo> toVo(List<Theme> themeDtos) {
		List<ThemeVo> themeVos = new ArrayList<>();
		for (Theme themeDto : themeDtos) {
			themeVos.add(toVo(themeDto));
		}
		return themeVos;
	}

	public static List<Theme> toDto(List<ThemeVo> themeVos) {
		List<Theme> themeDtos = new ArrayList<>();
		for (ThemeVo themeVo : themeVos) {
			themeDtos.add(toDto(themeVo));
		}
		return themeDtos;
	}
}
