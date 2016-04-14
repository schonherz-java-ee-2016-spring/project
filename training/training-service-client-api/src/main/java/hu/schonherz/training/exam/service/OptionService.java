package hu.schonherz.training.exam.service;

import hu.schonherz.training.exam.vo.OptionVo;

public interface OptionService {

	public void createOption(OptionVo optionVo) throws Exception;

	public void deleteOption(OptionVo optionVo) throws Exception;

}
