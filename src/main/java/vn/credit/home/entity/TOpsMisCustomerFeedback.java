package vn.credit.home.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_OPS_MIS_CUSTOMER_FEEDBACK database table.
 * 
 */
@Entity
@Table(name="T_OPS_MIS_CUSTOMER_FEEDBACK")
@NamedQuery(name="TOpsMisCustomerFeedback.findAll", query="SELECT t FROM TOpsMisCustomerFeedback t")
public class TOpsMisCustomerFeedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TIMESTAMP\"")
	private Date timestamp;

	private String username;

	private BigDecimal vote;

	public TOpsMisCustomerFeedback() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getVote() {
		return this.vote;
	}

	public void setVote(BigDecimal vote) {
		this.vote = vote;
	}

}