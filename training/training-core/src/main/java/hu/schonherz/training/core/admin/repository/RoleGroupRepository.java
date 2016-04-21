package hu.schonherz.training.core.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.RoleGroup;


@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {

	RoleGroup findByName(String name) throws Exception;
	
//	@Modifying
//	@Query("update RoleGroup rg set rg.name = ?2, rg.roles = ?3 where rg.id = ?1")
//	int updateRoleGroup( Long id, String name, List<Role> roles );
}