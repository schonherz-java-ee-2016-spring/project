package hu.schonherz.training.service.admin.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.admin.repository.TrainingRepository;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.mapper.TrainingMapper;
import hu.schonherz.training.service.admin.vo.TrainingVo;

@Stateless(mappedName = "TrainingService", name = "TrainingService")
@Transactional(value = TxType.REQUIRED)
@Local(TrainingService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class TrainingServiceImpl implements TrainingService {
	@Autowired
	TrainingRepository trainingRepository;

	@Override
	public List<TrainingVo> getAllTrainings() {
		return TrainingMapper.toVo(trainingRepository.findAll());
	}

	@Override
	public TrainingVo getTrainingById(Long id) {
		return TrainingMapper.toVo(trainingRepository.findOne(id));
	}

	@Override
	public TrainingVo getTrainingByName(String name) {
		return TrainingMapper.toVo(trainingRepository.findTrainingByName(name));
	}

	@Override
	public void saveTraining(TrainingVo trainingVo) {
		trainingRepository.save(TrainingMapper.toDto(trainingVo));
	}

	@Override
	public void deleteTraining(Long id) {
		trainingRepository.delete(id);
	}

}
