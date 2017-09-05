package vn.credit.home.entity.oracle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.QueryHint;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.QueryHints;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@NamedStoredProcedureQuery(name = "listUser", procedureName = "LIST_USER", resultClasses = {
		User.class }, parameters = {
				@StoredProcedureParameter(name = "I_USERNAME", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "O_RECORDSET", type = void.class, mode = ParameterMode.REF_CURSOR) }, hints = {
						@QueryHint(value = "true", name = QueryHints.CALLABLE) })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "AUTHEN_TYPE")
	private BigDecimal authenType;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	private String email;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "OUT_DOMAIN")
	private String outDomain;

	private String password;

	private BigDecimal status;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_TYPE")
	private BigDecimal userType;

	public User() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BigDecimal getAuthenType() {
		return this.authenType;
	}

	public void setAuthenType(BigDecimal authenType) {
		this.authenType = authenType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOutDomain() {
		return this.outDomain;
	}

	public void setOutDomain(String outDomain) {
		this.outDomain = outDomain;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getUserType() {
		return this.userType;
	}

	public void setUserType(BigDecimal userType) {
		this.userType = userType;
	}

}