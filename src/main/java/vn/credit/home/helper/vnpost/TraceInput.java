package vn.credit.home.helper.vnpost;

import java.io.Serializable;
import java.util.Date;

public class TraceInput implements Serializable, Comparable<TraceInput>{
	
	private int no;
	private Date arriveDate;
	private String status;
	private String postName;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}	
	public Date getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	@Override
	public int compareTo(TraceInput o) {
		// TODO Auto-generated method stub
		
		if(o.getArriveDate()!=null)
			return o.getArriveDate().compareTo(getArriveDate());
		return o.getArriveDate().compareTo(getArriveDate());
		/*
		if(o.getArriveDate()!=null)
			return getArriveDate().compareTo(o.getArriveDate());
		return getArriveDate().compareTo(o.getArriveDate());
		*/
	}	
	
}
