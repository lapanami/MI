package vn.credit.home.entity.oracle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.QueryHint;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.annotations.QueryHints;

@Entity
@NamedStoredProcedureQuery(name = "getUserMenu", procedureName = "SP_SEC_USER_MENU_GET", resultClasses = {
		UserMenu.class }, parameters = {
				@StoredProcedureParameter(name = "O_RECORDSET", type = void.class, mode = ParameterMode.REF_CURSOR),
				@StoredProcedureParameter(name = "I_USERNAME", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "I_APPLICATION_ID", type = String.class, mode = ParameterMode.IN) }, hints = {
						@QueryHint(value = "true", name = QueryHints.CALLABLE) })
public class UserMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAGE_ID")
	private String pageId;

	@Column(name = "CONTROLLER_NAME")
	private String controllerName;

	@Column(name = "PAGE_NAME")
	private String pageName;

	@Column(name = "PAGE_TITLE")
	private String pageTitle;

	@Column(name = "C_ORDER_NUMBER")
	private Integer cOrderNumber;

	@Column(name = "P_ORDER_NUMBER")
	private Integer pOrderNumber;

	@Column(name = "IS_MENU_ITEM")
	private boolean isMenuItem;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "PAGE_PARENT_ID")
	private String pageParentId;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Integer getcOrderNumber() {
		return cOrderNumber;
	}

	public void setcOrderNumber(Integer cOrderNumber) {
		this.cOrderNumber = cOrderNumber;
	}

	public Integer getpOrderNumber() {
		return pOrderNumber;
	}

	public void setpOrderNumber(Integer pOrderNumber) {
		this.pOrderNumber = pOrderNumber;
	}

	public boolean isMenuItem() {
		return isMenuItem;
	}

	public void setMenuItem(boolean isMenuItem) {
		this.isMenuItem = isMenuItem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPageParentId() {
		return pageParentId;
	}

	public void setPageParentId(String pageParentId) {
		this.pageParentId = pageParentId;
	}

}
