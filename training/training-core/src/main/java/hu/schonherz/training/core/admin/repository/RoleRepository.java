package hu.schonherz.training.core.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Role;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleRepository extends JpaRepository<Role, Long> {


	Role findByName(String name);
	
	Role findByRoleCode(String roleCode);
	
	@Modifying
	@Query("update Role r set r.name = ?2 where r.id = ?1")
	int updateRole( Long id, String name);
}
