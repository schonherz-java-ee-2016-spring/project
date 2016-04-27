package hu.schonherz.training.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.admin.repository.EventRepository;
import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.mapper.EventMapper;
import hu.schonherz.training.service.admin.vo.EventVo;

@Stateless(mappedName = "EventService", name = "EventService")
@Transactional(value = TxType.REQUIRED)
@Local(EventService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;

	@Override
	public void createEvent(EventVo eventVo) {
		eventRepository.save(EventMapper.toDto(eventVo));
	}

	@Override
	public EventVo findEventByName(String name) throws Exception {
		EventVo vo = EventMapper.toVo(eventRepository.findEventByName(name));
		return vo;
	}

	@Override
	public List<EventVo> findEventByType(String type) throws Exception {
		List<EventVo> vos = EventMapper.toVo(eventRepository.findAllByType(type));
		return vos;
	}

	@Override
	public List<EventVo> findAllEvent() throws Exception {
		List<EventVo> events = new ArrayList<>();
		events = EventMapper.toVo(eventRepository.findAll());
		return events;
	}

	@Override
	public void deleteEventById(Long id) throws Exception {
		eventRepository.delete(id);
	}

	@Override
	public void updateEvent(EventVo eventVo) {
		eventRepository.save(EventMapper.toDto(eventVo));
	}

	@Override
	public EventVo findEventById(Long id) {
		EventVo vo = EventMapper.toVo(eventRepository.findEventById(id));
		return vo;
	}

	@Override
	public List<EventVo> findEventByPlace(String place) {
		List<EventVo> vos = EventMapper.toVo(eventRepository.findAllByPlace(place));
		return vos;
	}

}
