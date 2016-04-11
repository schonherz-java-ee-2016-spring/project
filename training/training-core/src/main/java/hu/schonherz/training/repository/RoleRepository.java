package hu.schonherz.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.entity.Role;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Deprecated
	@Query("select roles from User u join u.roles roles where u.id=?1")
	List<Role> findRolesByUserId(Long userId);
}
