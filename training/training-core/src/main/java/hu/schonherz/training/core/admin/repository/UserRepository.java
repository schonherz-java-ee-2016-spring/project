package hu.schonherz.training.core.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.User;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUserName(String name) throws Exception;

	User findUserById(Long id)throws Exception;

	User findUserByEmail(String email)throws Exception;
	
	@Modifying
	@Query("update User u set u.userName = ?1, u.fullName = ?2, u.email = ?3 where u.id = ?4")
	void updateUser(String username, String fullname, String email, Long Id);
	
//	void updateUserById(Long id) throws Exception;
	
	User findUserByHashCode(String hashCode);

	List<User> findAllByOrderByFullNameAsc();
}