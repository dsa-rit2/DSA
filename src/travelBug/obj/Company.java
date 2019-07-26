package travelBug.obj;

import java.io.Serializable;

public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	String companyName, shortForm,phoneNum,description;
	
	public Company(String companyName, String shortForm,String phoneNum, String description) {
		this.companyName = companyName;
		this.shortForm = shortForm;
		this.phoneNum = phoneNum;
		this.description = description;
		
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShortForm() {
		return shortForm;
	}
	public void setShortForm(String shortForm) {
		this.shortForm = shortForm;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void print() {
		System.out.println("================================================");
		System.out.println("Company Name: " + this.companyName);
		System.out.println("ShortForm: " + this.shortForm);
		System.out.println("Description: " + this.description);
		System.out.println("Phone Number: "+ this.phoneNum);
		System.out.println("================================================");
	}
}
