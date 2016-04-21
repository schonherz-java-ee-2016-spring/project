package hu.schonherz.training.core.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(BaseEntity.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	String recUser;

	String modUser;
	@Temporal(TemporalType.TIMESTAMP)
	Date recDate;

	@Temporal(TemporalType.TIMESTAMP)
	Date modDate;

	@PrePersist
	public void prePersist() {
		recUser = getUser();
		recDate = new Date();

	}

	private String getUser() {
		String userName;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof String) {
				userName = (String) principal;
			} else {
				org.springframework.security.core.userdetails.User userPrincipal = (org.springframework.security.core.userdetails.User) principal;
				userName = userPrincipal.getUsername();
			}

		} catch (Exception e) {
			userName = "unknown";
		}
		return userName;
	}

	@PreUpdate
	public void preUpdate() {
		modUser = getUser();
		modDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecUser() {
		return recUser;
	}

	public void setRecUser(String recUser) {
		this.recUser = recUser;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
}
