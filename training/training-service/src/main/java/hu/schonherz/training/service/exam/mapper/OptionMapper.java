package hu.schonherz.training.service.exam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.exam.entity.Option;
import hu.schonherz.training.service.exam.vo.OptionVo;

public class OptionMapper {

	
	private static Mapper mapper = new DozerBeanMapper();

	public static OptionVo toVo(Option optionDto) {
		if (optionDto == null) {
			return null;
		}
		return mapper.map(optionDto, OptionVo.class);
	}

	public static Option toDto(OptionVo optionVo) {
		if (optionVo == null) {
			return null;
		}
		return mapper.map(optionVo, Option.class);
	}

	public static List<OptionVo> toVo(List<Option> optionDtos) {
		List<OptionVo> optionVos = new ArrayList<>();
		for (Option optionDto : optionDtos) {
			optionVos.add(toVo(optionDto));
		}
		return optionVos;
	}

	public static List<Option> toDto(List<OptionVo> optionVos) {
		List<Option> optionDtos = new ArrayList<>();
		for (OptionVo optionVo : optionVos) {
			optionDtos.add(toDto(optionVo));
		}
		return optionDtos;
	}
	
}
