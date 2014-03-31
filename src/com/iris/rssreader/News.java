package com.iris.rssreader;

public class News {
	private String publisher;
	private String Catagory;
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCatagory() {
		return Catagory;
	}
	public void setCatagory(String catagory) {
		Catagory = catagory;
	}
	public News(String publisher, String catagory) {
		super();
		this.publisher = publisher;
		Catagory = catagory;
	}
	

}
