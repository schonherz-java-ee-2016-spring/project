package hu.schonherz.training.service.supervisor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.supervisor.entity.Homework;
import hu.schonherz.training.service.supervisor.vo.HomeworkVo;

public class HomeworkMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static HomeworkVo toVo(Homework homeworkDto) {
		if (homeworkDto == null) {
			return null;
		}
		return mapper.map(homeworkDto, HomeworkVo.class);
	}

	public static Homework toDto(HomeworkVo homeworkVo) {
		if (homeworkVo == null) {
			return null;
		}
		return mapper.map(homeworkVo, Homework.class);
	}

	public static List<HomeworkVo> toVo(List<Homework> homeworkDtos) {
		List<HomeworkVo> homeworkVos = new ArrayList<>();
		for (Homework homeworkDto : homeworkDtos) {
			homeworkVos.add(toVo(homeworkDto));
		}
		return homeworkVos;
	}

	public static List<Homework> toDto(List<HomeworkVo> homeworkVos) {
		List<Homework> homeworkDtos = new ArrayList<>();
		for (HomeworkVo homeworkVo : homeworkVos) {
			homeworkDtos.add(toDto(homeworkVo));
		}
		return homeworkDtos;
	}

}
