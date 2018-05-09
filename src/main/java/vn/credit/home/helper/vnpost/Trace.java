package vn.credit.home.helper.vnpost;

import java.io.Serializable;
import java.util.Date;

public class Trace implements Serializable, Comparable<Trace>{
	
	private int no;
	private Date deliveryDate;
	private Date inputDate;
	private String deliveryPost;
	private String receiver;
	private String status;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getDeliveryPost() {
		return deliveryPost;
	}
	public void setDeliveryPost(String deliveryPost) {
		this.deliveryPost = deliveryPost;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	@Override
	public int compareTo(Trace o) {
		// TODO Auto-generated method stub
		if(o.getInputDate()!=null)
			return o.getInputDate().compareTo(getInputDate());		
		return o.getDeliveryDate().compareTo(getDeliveryDate());
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}		
}
