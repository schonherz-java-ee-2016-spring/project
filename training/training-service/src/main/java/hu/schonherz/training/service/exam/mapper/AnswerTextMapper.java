package hu.schonherz.training.service.exam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.exam.entity.AnswerText;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;

public class AnswerTextMapper {
	private static Mapper mapper = new DozerBeanMapper();

	public static AnswerTextVo toVo(AnswerText answerTextDto) {
		if (answerTextDto == null) {
			return null;
		}
		return mapper.map(answerTextDto, AnswerTextVo.class);
	}

	public static AnswerText toDto(AnswerTextVo answerTextVo) {
		if (answerTextVo == null) {
			return null;
		}
		return mapper.map(answerTextVo, AnswerText.class);
	}

	public static List<AnswerTextVo> toVo(List<AnswerText> answerTextDtos) {
		List<AnswerTextVo> answerTextVos = new ArrayList<>();
		for (AnswerText answerTextDto : answerTextDtos) {
			answerTextVos.add(toVo(answerTextDto));
		}
		return answerTextVos;
	}

	public static List<AnswerText> toDto(List<AnswerTextVo> answerTextVos) {
		List<AnswerText> answerTextDtos = new ArrayList<>();
		for (AnswerTextVo answerTextVo : answerTextVos) {
			answerTextDtos.add(toDto(answerTextVo));
		}
		return answerTextDtos;
	}

}
