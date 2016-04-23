package hu.schonherz.training.service.exam;

import hu.schonherz.training.service.exam.vo.OptionVo;

public interface OptionService extends BaseService<OptionVo> {
	
	public void add(OptionVo optionVo, Long questionId);
}
