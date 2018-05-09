package vn.credit.home.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RQ_REPORT database table.
 * 
 */
@Entity
@Table(name="RQ_REPORT")
@NamedQuery(name="RqReport.findAll", query="SELECT r FROM RqReport r")
public class RqReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String chartcolumns;

	private String chartlabel;

	private String dsc;

	private String haschart;

	private String name;

	private BigDecimal priority;

	private String sqlquery;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	public RqReport() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChartcolumns() {
		return this.chartcolumns;
	}

	public void setChartcolumns(String chartcolumns) {
		this.chartcolumns = chartcolumns;
	}

	public String getChartlabel() {
		return this.chartlabel;
	}

	public void setChartlabel(String chartlabel) {
		this.chartlabel = chartlabel;
	}

	public String getDsc() {
		return this.dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public String getHaschart() {
		return this.haschart;
	}

	public void setHaschart(String haschart) {
		this.haschart = haschart;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public String getSqlquery() {
		return this.sqlquery;
	}

	public void setSqlquery(String sqlquery) {
		this.sqlquery = sqlquery;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}