package hu.schonherz.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.entity.Role;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleRepository extends JpaRepository<Role, Long> {

}
