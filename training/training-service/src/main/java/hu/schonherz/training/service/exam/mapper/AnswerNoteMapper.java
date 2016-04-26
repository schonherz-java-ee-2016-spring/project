package hu.schonherz.training.service.exam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.exam.entity.AnswerNote;
import hu.schonherz.training.service.exam.vo.AnswerNoteVo;

public class AnswerNoteMapper {
	private static Mapper mapper = new DozerBeanMapper();

	public static AnswerNoteVo toVo(AnswerNote answerNoteDto) {
		if (answerNoteDto == null) {
			return null;
		}
		return mapper.map(answerNoteDto, AnswerNoteVo.class);
	}

	public static AnswerNote toDto(AnswerNoteVo answerNoteVo) {
		if (answerNoteVo == null) {
			return null;
		}
		return mapper.map(answerNoteVo, AnswerNote.class);
	}

	public static List<AnswerNoteVo> toVo(List<AnswerNote> answerNoteDtos) {
		List<AnswerNoteVo> answerNoteVos = new ArrayList<>();
		for (AnswerNote answerNoteDto : answerNoteDtos) {
			answerNoteVos.add(toVo(answerNoteDto));
		}
		return answerNoteVos;
	}

	public static List<AnswerNote> toDto(List<AnswerNoteVo> answerNoteVos) {
		List<AnswerNote> answerNoteDtos = new ArrayList<>();
		for (AnswerNoteVo answerNoteVo : answerNoteVos) {
			answerNoteDtos.add(toDto(answerNoteVo));
		}
		return answerNoteDtos;
	}

}
