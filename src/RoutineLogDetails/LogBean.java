package RoutineLogDetails;

public class LogBean {

	int mobile;
	float cqty;
	float bqty;
	String date;
	String month;
	String year;
	
	public LogBean() {
		// TODO Auto-generated constructor stub
	}

	public LogBean(int mobile, float cqty, float bqty, String date, String month, String year) {
		super();
		this.mobile = mobile;
		this.cqty = cqty;
		this.bqty = bqty;
		this.date = date;
		this.month = month;
		this.year = year;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
}
