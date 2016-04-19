package hu.schonherz.training.exam.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.exam.entity.Question;
import hu.schonherz.training.exam.vo.QuestionVo;

public class QuestionMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static QuestionVo toVo(Question questionDto) {
		if (questionDto == null) {
			return null;
		}
		return mapper.map(questionDto, QuestionVo.class);
	}

	public static Question toDto(QuestionVo questionVo) {
		if (questionVo == null) {
			return null;
		}
		return mapper.map(questionVo, Question.class);
	}

	public static List<QuestionVo> toVo(List<Question> questionDtos) {
		List<QuestionVo> questionVos = new ArrayList<>();
		for (Question questionDto : questionDtos) {
			questionVos.add(toVo(questionDto));
		}
		return questionVos;
	}

	public static List<Question> toDto(List<QuestionVo> questionVos) {
		List<Question> questionDtos = new ArrayList<>();
		for (QuestionVo questionVo : questionVos) {
			questionDtos.add(toDto(questionVo));
		}
		return questionDtos;
	}

}
