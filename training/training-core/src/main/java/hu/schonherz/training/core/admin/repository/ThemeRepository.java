package hu.schonherz.training.core.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Role;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface ThemeRepository extends JpaRepository<Role, Long> {

}