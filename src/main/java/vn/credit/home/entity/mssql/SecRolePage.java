package vn.credit.home.entity.mssql;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SEC_ROLE_PAGE database table.
 * 
 */
@Entity
@Table(name = "SEC_ROLE_PAGE")
@NamedQuery(name = "SecRolePage.findAll", query = "SELECT s FROM SecRolePage s")
public class SecRolePage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROLE_PAGE_ID")
	private String rolePageId;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "LAST_CHANGED_BY")
	private String lastChangedBy;

	@Column(name = "LAST_CHANGED_DATE")
	private Timestamp lastChangedDate;

	@Column(name = "PAGE_ID")
	private String pageId;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "STATUS")
	private int status;

	public SecRolePage() {
	}

	public String getRolePageId() {
		return this.rolePageId;
	}

	public void setRolePageId(String rolePageId) {
		this.rolePageId = rolePageId;
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

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
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

}