package hu.schonherz.training.service.exam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.exam.relationtable.ExamUserRelation;
import hu.schonherz.training.service.exam.vo.ExamUserRelationVo;

public class ExamUserRelationMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static ExamUserRelationVo toVo(ExamUserRelation examUserRelationDto) {
		if (examUserRelationDto == null) {
			return null;
		}
		return mapper.map(examUserRelationDto, ExamUserRelationVo.class);
	}

	public static ExamUserRelation toDto(ExamUserRelationVo examUserRelationVo) {
		if (examUserRelationVo == null) {
			return null;
		}
		return mapper.map(examUserRelationVo, ExamUserRelation.class);
	}

	public static List<ExamUserRelationVo> toVo(List<ExamUserRelation> examUserRelationDtos) {
		List<ExamUserRelationVo> examUserRelationVos = new ArrayList<>();
		System.out.println("Cimek");
		for (ExamUserRelation examUserRelationDto : examUserRelationDtos) {
			examUserRelationVos.add(toVo(examUserRelationDto));
		}
		return examUserRelationVos;
	}

	public static List<ExamUserRelation> toDto(List<ExamUserRelationVo> examUserRelationVos) {
		List<ExamUserRelation> examUserRelationDtos = new ArrayList<>();
		for (ExamUserRelationVo examUserRelationVo : examUserRelationVos) {
			examUserRelationDtos.add(toDto(examUserRelationVo));
		}
		return examUserRelationDtos;
	}

}
