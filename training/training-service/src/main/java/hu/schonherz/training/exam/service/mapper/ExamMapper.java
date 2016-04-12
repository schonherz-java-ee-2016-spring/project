package hu.schonherz.training.exam.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.exam.entity.Exam;
import hu.schonherz.training.exam.vo.ExamVo;

public class ExamMapper {
	
	private static Mapper mapper = new DozerBeanMapper();
	
	public static ExamVo toVo(Exam examDto) {
		if (examDto == null) {
			return null;
		}
		return mapper.map(examDto, ExamVo.class);
	}

	public static Exam toDto(ExamVo examVo) {
		if (examVo == null) {
			return null;
		}
		return mapper.map(examVo, Exam.class);
	}

	public static List<ExamVo> toVo(List<Exam> examDtos) {
		List<ExamVo> examVos = new ArrayList<>();
		for (Exam examDto : examDtos) {
			examVos.add(toVo(examDto));
		}
		return examVos;
	}

	public static List<Exam> toDto(List<ExamVo> examVos) {
		List<Exam> examDtos = new ArrayList<>();
		for (ExamVo examVo : examVos) {
			examDtos.add(toDto(examVo));
		}
		return examDtos;
	}

}
