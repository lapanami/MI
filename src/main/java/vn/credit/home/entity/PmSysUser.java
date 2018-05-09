package vn.credit.home.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="PM_SYS_USER")
@NamedQuery(name="PmSysUser.findAll", query="SELECT p FROM PmSysUser p")
public class PmSysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long userid;

	@Column(name="ACTIVATION_CODE")
	private String activationCode;

	private String email;

	private String fullname;

	private java.math.BigDecimal groupid;

	private String image;

	private String inuse;

	@Temporal(TemporalType.DATE)
	private Date loggedtime;

	private String password;

	private String status;

	private String username;

	public PmSysUser() {
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public java.math.BigDecimal getGroupid() {
		return this.groupid;
	}

	public void setGroupid(java.math.BigDecimal groupid) {
		this.groupid = groupid;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInuse() {
		return this.inuse;
	}

	public void setInuse(String inuse) {
		this.inuse = inuse;
	}

	public Date getLoggedtime() {
		return this.loggedtime;
	}

	public void setLoggedtime(Date loggedtime) {
		this.loggedtime = loggedtime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
