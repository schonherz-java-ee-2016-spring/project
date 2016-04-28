package hu.schonherz.training.service.admin.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.admin.entity.Training;
import hu.schonherz.training.service.admin.vo.TrainingVo;

public class TrainingMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static TrainingVo toVo(Training trainingDto) {
		if (trainingDto == null) {
			return null;
		}
		return mapper.map(trainingDto, TrainingVo.class);
	}

	public static Training toDto(TrainingVo trainingVo) {
		if (trainingVo == null) {
			return null;
		}
		return mapper.map(trainingVo, Training.class);
	}

	public static List<TrainingVo> toVo(List<Training> trainingDtos) {
		List<TrainingVo> trainingVos = new ArrayList<>();
		for (Training trainingDto : trainingDtos) {
			trainingVos.add(toVo(trainingDto));
		}
		return trainingVos;
	}

	public static List<Training> toDto(List<TrainingVo> trainingVos) {
		List<Training> trainingDtos = new ArrayList<>();
		for (TrainingVo trainingVo : trainingVos) {
			trainingDtos.add(toDto(trainingVo));
		}
		return trainingDtos;
	}

}
