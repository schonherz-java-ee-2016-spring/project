package hu.schonherz.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.entity.UserGroup;


@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup findUserGroupByGroupName(String groupName);
	
}