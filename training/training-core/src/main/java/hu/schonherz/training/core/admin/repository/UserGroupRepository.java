package hu.schonherz.training.core.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.UserGroup;


@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup findUserGroupByGroupName(String groupName);
	
	List<UserGroup> findAllByOrderByGroupNameAsc();
}