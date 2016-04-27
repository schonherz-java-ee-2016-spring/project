package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.EventVo;

public interface EventService {

	void createEvent(EventVo eventVo);

	public EventVo findEventByName(String name) throws Exception;

	public List<EventVo> findEventByType(String type) throws Exception;

	public List<EventVo> findAllEvent() throws Exception;

	public void deleteEventById(Long id) throws Exception;

	public void updateEvent(EventVo eventVo);

	public EventVo findEventById(Long id);
	
	public List<EventVo> findEventByPlace(String place);
	
}
