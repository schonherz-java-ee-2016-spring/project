package hu.schonherz.training.service.admin.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.admin.entity.Event;
import hu.schonherz.training.service.admin.vo.EventVo;

public class EventMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static EventVo toVo(Event eventDto) {
		if (eventDto == null) {
			return null;
		}
		return mapper.map(eventDto, EventVo.class);
	}

	public static Event toDto(EventVo eventVo) {
		if (eventVo == null) {
			return null;
		}
		return mapper.map(eventVo, Event.class);
	}

	public static List<EventVo> toVo(List<Event> eventDtos) {
		List<EventVo> eventVos = new ArrayList<>();
		for (Event eventDto : eventDtos) {
			eventVos.add(toVo(eventDto));
		}
		return eventVos;
	}

	public static List<Event> toDto(List<EventVo> eventVos) {
		List<Event> eventDtos = new ArrayList<>();
		for (EventVo eventVo : eventVos) {
			eventDtos.add(toDto(eventVo));
		}
		return eventDtos;
	}

}
