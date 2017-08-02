package vn.credit.home.entity.mssql;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SEC_PAGE database table.
 * 
 */
@Entity
@Table(name = "SEC_PAGE")
@NamedQuery(name = "SecPage.findAll", query = "SELECT s FROM SecPage s")
public class SecPage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAGE_ID")
	private String pageId;

	@Column(name = "CONTROLLER_ID")
	private String controllerId;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "IS_MENU_ITEM")
	private int isMenuItem;

	@Column(name = "LAST_CHANGED_BY")
	private String lastChangedBy;

	@Column(name = "LAST_CHANGED_DATE")
	private Timestamp lastChangedDate;

	@Column(name = "ORDER_NUMBER")
	private int orderNumber;

	@Column(name = "PAGE_NAME")
	private String pageName;

	@Column(name = "PAGE_PARENT_ID")
	private String pageParentId;

	@Column(name = "PAGE_TITLE")
	private String pageTitle;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private int status;

	public SecPage() {
	}

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getControllerId() {
		return this.controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
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

	public int getIsMenuItem() {
		return this.isMenuItem;
	}

	public void setIsMenuItem(int isMenuItem) {
		this.isMenuItem = isMenuItem;
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

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageParentId() {
		return this.pageParentId;
	}

	public void setPageParentId(String pageParentId) {
		this.pageParentId = pageParentId;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
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