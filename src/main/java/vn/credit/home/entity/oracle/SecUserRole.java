package vn.credit.home.entity.oracle;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SEC_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="SEC_USER_ROLE")
@NamedQuery(name="SecUserRole.findAll", query="SELECT s FROM SecUserRole s")
public class SecUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ROLE_ID")
	private String userRoleId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="LAST_CHANGED_BY")
	private String lastChangedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHANGED_DATE")
	private Date lastChangedDate;

	private String remark;

	@Column(name="ROLE_ID")
	private byte[] roleId;

	private BigDecimal status;

	@Column(name="USER_ID")
	private byte[] userId;

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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastChangedBy() {
		return this.lastChangedBy;
	}

	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

	public Date getLastChangedDate() {
		return this.lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public byte[] getRoleId() {
		return this.roleId;
	}

	public void setRoleId(byte[] roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public byte[] getUserId() {
		return this.userId;
	}

	public void setUserId(byte[] userId) {
		this.userId = userId;
	}

}