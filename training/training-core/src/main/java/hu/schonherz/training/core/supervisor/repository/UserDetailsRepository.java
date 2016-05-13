package hu.schonherz.training.core.supervisor.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.supervisor.entity.UserDetails;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	public UserDetails findUserDetailsByUser(User user);

	public UserDetails findUserDetailsByUserId(Long userId);

	@Modifying
	@Query(value = "UPDATE user_details SET phone_number=?2, address=?3, place_of_birth=?4, date_of_birth=?5, nationality=?6 WHERE user_id=?1", nativeQuery = true)
	public void updateUserDetailsByUserId(Long userId, String phoneNumber, String address, String placeOfBirth,
			Date dateOfBirth, String nationality);

}
