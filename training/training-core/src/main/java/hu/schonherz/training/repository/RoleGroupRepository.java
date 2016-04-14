package hu.schonherz.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.entity.RoleGroup;


@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {

	RoleGroup findByName(String name);
}