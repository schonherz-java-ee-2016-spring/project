package hu.schonherz.training.supervisor.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.supervisor.entity.Feedback;
import hu.schonherz.training.supervisor.vo.FeedbackVo;

public class FeedbackMapper {
	private static Mapper mapper = new DozerBeanMapper();

	public static FeedbackVo toVo(Feedback feedbackDto) {
		if (feedbackDto == null) {
			return null;
		}
		return mapper.map(feedbackDto, FeedbackVo.class);
	}

	public static Feedback toDto(FeedbackVo feedbackVo) {
		if (feedbackVo == null) {
			return null;
		}
		return mapper.map(feedbackVo, Feedback.class);
	}

	public static List<FeedbackVo> toVo(List<Feedback> feedbackDtos) {
		List<FeedbackVo> feedbackVos = new ArrayList<>();
		for (Feedback feedbackDto : feedbackDtos) {
			feedbackVos.add(toVo(feedbackDto));
		}
		return feedbackVos;
	}

	public static List<Feedback> toDto(List<FeedbackVo> feedbackVos) {
		List<Feedback> feedbackDtos = new ArrayList<>();
		for (FeedbackVo feedbackVo : feedbackVos) {
			feedbackDtos.add(toDto(feedbackVo));
		}
		return feedbackDtos;
	}

}
