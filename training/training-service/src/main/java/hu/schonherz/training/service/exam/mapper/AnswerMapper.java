package hu.schonherz.training.service.exam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.exam.entity.Answer;
import hu.schonherz.training.service.exam.vo.AnswerVo;

public class AnswerMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static AnswerVo toVo(Answer answerDto) {
		if (answerDto == null) {
			return null;
		}
		return mapper.map(answerDto, AnswerVo.class);
	}

	public static Answer toDto(AnswerVo answerVo) {
		if (answerVo == null) {
			return null;
		}
		return mapper.map(answerVo, Answer.class);
	}

	public static List<AnswerVo> toVo(List<Answer> answerDtos) {
		List<AnswerVo> answerVos = new ArrayList<>();
		for (Answer answerDto : answerDtos) {
			answerVos.add(toVo(answerDto));
		}
		return answerVos;
	}

	public static List<Answer> toDto(List<AnswerVo> answerVos) {
		List<Answer> answerDtos = new ArrayList<>();
		for (AnswerVo answerVo : answerVos) {
			answerDtos.add(toDto(answerVo));
		}
		return answerDtos;
	}
}
