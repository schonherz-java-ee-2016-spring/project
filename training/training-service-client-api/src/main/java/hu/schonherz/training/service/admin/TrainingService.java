package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.TrainingVo;

public interface TrainingService {

	List<TrainingVo> getAllTrainings();

	TrainingVo getTrainingById(Long id);

	TrainingVo getTrainingByName(String name);

	void saveTraining(TrainingVo selected);

	void deleteTraining(Long id);

}
