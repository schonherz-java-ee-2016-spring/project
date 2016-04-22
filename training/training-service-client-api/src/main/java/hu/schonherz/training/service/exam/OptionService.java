package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.OptionVo;

public interface OptionService {
	
	public OptionVo getById(Long id) throws Exception;

	public List<OptionVo> getAll(Long...id) throws Exception;


}
