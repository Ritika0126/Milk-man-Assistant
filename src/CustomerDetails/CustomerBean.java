package CustomerDetails;

public class CustomerBean {

	String name;
	String address;
	String area;
	String city;
	String mobile;
	float cqty;
	float bqty;
	float crate;
	float brate;
	String dos;
	int status;
	public CustomerBean() {
		// TODO Auto-generated constructor stub
	}
	public CustomerBean(String name, String address, String area, String city, String mobile, float cqty, float bqty,
			float crate, float brate, String dos, int status) {
		super();
		this.name = name;
		this.address = address;
		this.area = area;
		this.city = city;
		this.mobile = mobile;
		this.cqty = cqty;
		this.bqty = bqty;
		this.crate = crate;
		this.brate = brate;
		this.dos = dos;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public float getCrate() {
		return crate;
	}
	public void setCrate(float crate) {
		this.crate = crate;
	}
	public float getBrate() {
		return brate;
	}
	public void setBrate(float brate) {
		this.brate = brate;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
