package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

import hu.schonherz.training.service.admin.vo.UserVo;

public class UserDetailsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6437523676717393640L;

	private UserVo user;
	private String placeOfBirth;
	private Date dateOfBirth;
	private String phoneNumber;
	private String address;
	private String nationality;

	public UserDetailsVo() {
		super();
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
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
