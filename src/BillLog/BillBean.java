package BillLog;

public class BillBean {
	
	String mobile;
	
	float cqty;
	float bqty;
	float cbill;
	float bbill;
	float total;
	float receive;
	String enddate;
	int month;
	int year;
	public BillBean() {
		// TODO Auto-generated constructor stub
	}
	public BillBean(String mobile, float cqty, float bqty, float cbill, float bbill, float total, float receive,
			String enddate, int month, int year) {
		super();
		this.mobile = mobile;
		this.cqty = cqty;
		this.bqty = bqty;
		this.cbill = cbill;
		this.bbill = bbill;
		this.total = total;
		this.receive = receive;
		this.enddate = enddate;
		this.month = month;
		this.year = year;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public float getCqty() {
		return cqty;
	}
	public void setCqty(float cqty) {
		this.cqty = cqty;
	}
	public float getBqty() {
		return bqty;
	}
	public void setBqty(float bqty) {
		this.bqty = bqty;
	}
	public float getCbill() {
		return cbill;
	}
	public void setCbill(float cbill) {
		this.cbill = cbill;
	}
	public float getBbill() {
		return bbill;
	}
	public void setBbill(float bbill) {
		this.bbill = bbill;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getReceive() {
		return receive;
	}
	public void setReceive(float receive) {
		this.receive = receive;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}