package hu.schonherz.training.service.supervisor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.exam.entity.ExamResult;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;

public class ExamResultMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static ExamResultVo toVo(ExamResult examResultDto) {
		if (examResultDto == null) {
			return null;
		}
		return mapper.map(examResultDto, ExamResultVo.class);
	}

	public static ExamResult toDto(ExamResultVo examResultVo) {
		if (examResultVo == null) {
			return null;
		}
		return mapper.map(examResultVo, ExamResult.class);
	}

	public static List<ExamResultVo> toVo(List<ExamResult> examResultDtos) {
		List<ExamResultVo> examResultVos = new ArrayList<>();
		for (ExamResult examResultDto : examResultDtos) {
			examResultVos.add(toVo(examResultDto));
		}
		return examResultVos;
	}

	public static List<ExamResult> toDto(List<ExamResultVo> examResultVos) {
		List<ExamResult> examResultDtos = new ArrayList<>();
		for (ExamResultVo examResultVo : examResultVos) {
			examResultDtos.add(toDto(examResultVo));
		}
		return examResultDtos;
	}

}
