package hu.schonherz.training.service.supervisor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.supervisor.entity.Interview;
import hu.schonherz.training.service.supervisor.vo.InterviewVo;

public class InterviewMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static InterviewVo toVo(Interview interviewDto) {
		if (interviewDto == null) {
			return null;
		}
		return mapper.map(interviewDto, InterviewVo.class);
	}

	public static Interview toDto(InterviewVo interviewVo) {
		if (interviewVo == null) {
			return null;
		}
		return mapper.map(interviewVo, Interview.class);
	}

	public static List<InterviewVo> toVo(List<Interview> interviewDtos) {
		List<InterviewVo> interviewVos = new ArrayList<>();
		for (Interview interviewDto : interviewDtos) {
			interviewVos.add(toVo(interviewDto));
		}
		return interviewVos;
	}

	public static List<Interview> toDto(List<InterviewVo> interviewVos) {
		List<Interview> interviewDtos = new ArrayList<>();
		for (InterviewVo interviewVo : interviewVos) {
			interviewDtos.add(toDto(interviewVo));
		}
		return interviewDtos;
	}

}
