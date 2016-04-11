package hu.schonherz.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.entity.RoleGroup;


@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {

	@Query("select roles from roleGroup g join g.roles roles where g.id=?1")
	List<RoleGroup> findRolesByGroupId(Long groupId);
}