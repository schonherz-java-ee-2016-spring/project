package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;

public interface TrainingService {

	List<TrainingVo> getAllTrainings();

	TrainingVo getTrainingById(Long id);

	TrainingVo getTrainingByName(String name);

	void saveTraining(TrainingVo selected);

	void deleteTraining(Long id);

	List<UserVo> getAllUsers(Long trainingId);

	List<UserGroupVo> getAllUserGroups(Long trainingId);
}
