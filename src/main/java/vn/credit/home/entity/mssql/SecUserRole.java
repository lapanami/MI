package vn.credit.home.entity.mssql;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SEC_USER_ROLE database table.
 * 
 */
@Entity
@Table(name = "SEC_USER_ROLE")
@NamedQuery(name = "SecUserRole.findAll", query = "SELECT s FROM SecUserRole s")
public class SecUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ROLE_ID")
	private String userRoleId;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "LAST_CHANGED_BY")
	private String lastChangedBy;

	@Column(name = "LAST_CHANGED_DATE")
	private Timestamp lastChangedDate;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "STATUS")
	private int status;

	@Column(name = "USER_ID")
	private String userId;

	public SecUserRole() {
	}

	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
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

	public String getLastChangedBy() {
		return this.lastChangedBy;
	}

	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

	public Timestamp getLastChangedDate() {
		return this.lastChangedDate;
	}

	public void setLastChangedDate(Timestamp lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}