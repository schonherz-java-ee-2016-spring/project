package hu.schonherz.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.entity.User;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUserName(String name) throws Exception;

	User findUserById(Long id)throws Exception;

	User findUserByEmail(String email)throws Exception;
	
	void removeUserById(Long id); 
}