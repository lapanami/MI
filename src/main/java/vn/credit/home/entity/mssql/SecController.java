package vn.credit.home.entity.mssql;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SEC_CONTROLLER database table.
 * 
 */
@Entity
@Table(name = "SEC_CONTROLLER")
@NamedQuery(name = "SecController.findAll", query = "SELECT s FROM SecController s")
public class SecController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTROLLER_ID")
	private String controllerId;

	@Column(name = "APPLICATION_ID")
	private String applicationId;

	@Column(name = "CONTROLLER_NAME")
	private String controllerName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "LAST_CHANGED_BY")
	private String lastChangedBy;

	@Column(name = "LAST_CHANGED_DATE")
	private Timestamp lastChangedDate;

	@Column(name = "ORDER_NUMBER")
	private int orderNumber;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private int status;

	public SecController() {
	}

	public String getControllerId() {
		return this.controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getControllerName() {
		return this.controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
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

	public int getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}