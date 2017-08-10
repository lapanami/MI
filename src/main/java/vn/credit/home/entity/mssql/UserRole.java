/**
 * @author loc.mh
 */
package vn.credit.home.entity.mssql;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.QueryHint;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.annotations.QueryHints;

/**
 * @author loc.mh
 *
 */
@Entity
@NamedStoredProcedureQuery(name = "getUserRole", procedureName = "SP_SEC_USER_ROLE_GET", resultClasses = {
		UserRole.class }, parameters = {
				@StoredProcedureParameter(name = "I_USERNAME", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "I_APPLICATION_ID", type = String.class, mode = ParameterMode.IN) }, hints = {
						@QueryHint(value = "true", name = QueryHints.CALLABLE) })
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Id
	@Column(name = "USER_NAME")
	private String userName;

	@Id
	@Column(name = "ROLE_NAME")
	private String roleName;

	@Id
	@Column(name = "APPLICATION_ID")
	private String applicationId;

	@Id
	@Column(name = "APPLICATION_NAME")
	private String applicationName;

	@Id
	@Column(name = "CONTROLLER_NAME")
	private String controllerName;

	@Id
	@Column(name = "PAGE_NAME")
	private String pageName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
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
}
