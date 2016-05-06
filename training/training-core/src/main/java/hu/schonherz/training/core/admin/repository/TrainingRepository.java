package hu.schonherz.training.core.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Training;
import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.admin.entity.UserGroup;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface TrainingRepository extends JpaRepository<Training, Long> {

	Training findTrainingByName(String name);

	@Query("SELECT u FROM Training t JOIN t.users u WHERE t.id = ?1 ")
	List<User> findUsersByTrainingId(Long trainingId);

	@Query("SELECT ug FROM Training t JOIN t.userGroups ug WHERE t.id = ?1 ")
	List<UserGroup> findUserGroupsByTrainingId(Long trainingId);

}
