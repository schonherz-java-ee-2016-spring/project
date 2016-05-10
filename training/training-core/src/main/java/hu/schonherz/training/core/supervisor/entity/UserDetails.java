package hu.schonherz.training.core.supervisor.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;

@Entity
@Table(name = "user_details")
public class UserDetails extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7782993761424770881L;

	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "nationality")
	private String nationality;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
