package hu.schonherz.training.service.supervisor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.supervisor.entity.HomeworkResult;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;

public class HomeworkResultMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static HomeworkResultVo toVo(HomeworkResult homeworkResultDto) {
		if (homeworkResultDto == null) {
			return null;
		}
		return mapper.map(homeworkResultDto, HomeworkResultVo.class);
	}

	public static HomeworkResult toDto(HomeworkResultVo homeworkResultVo) {
		if (homeworkResultVo == null) {
			return null;
		}
		return mapper.map(homeworkResultVo, HomeworkResult.class);
	}

	public static List<HomeworkResultVo> toVo(List<HomeworkResult> homeworkResultDtos) {
		List<HomeworkResultVo> homeworkResultVos = new ArrayList<>();
		for (HomeworkResult homeworkResultDto : homeworkResultDtos) {
			homeworkResultVos.add(toVo(homeworkResultDto));
		}
		return homeworkResultVos;
	}

	public static List<HomeworkResult> toDto(List<HomeworkResultVo> homeworkResultVos) {
		List<HomeworkResult> homeworkResultDtos = new ArrayList<>();
		for (HomeworkResultVo homeworkResultVo : homeworkResultVos) {
			homeworkResultDtos.add(toDto(homeworkResultVo));
		}
		return homeworkResultDtos;
	}

}
