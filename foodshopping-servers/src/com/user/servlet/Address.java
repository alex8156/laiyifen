package com.user.servlet;

public class Address {
	String reciever_address_name;// 收件人
	String reciever_address_region;// 地区
	String reciever_address_address;// 街道地址
	int reciever_address_postcode;// 邮编
	long reciever_address_phone;// 收件人电话

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String reciever_address_name,
			String reciever_address_region, String reciever_address_address,
			int reciever_address_postcode, long reciever_address_phone) {
		super();
		this.reciever_address_name = reciever_address_name;
		this.reciever_address_region = reciever_address_region;
		this.reciever_address_address = reciever_address_address;
		this.reciever_address_postcode = reciever_address_postcode;
		this.reciever_address_phone = reciever_address_phone;
	}

	@Override
	public String toString() {
		return "Address [reciever_address_name=" + reciever_address_name
				+ ", reciever_address_region=" + reciever_address_region
				+ ", reciever_address_address=" + reciever_address_address
				+ ", reciever_address_postcode=" + reciever_address_postcode
				+ ", reciever_address_phone=" + reciever_address_phone + "]";
	}

	public String getReciever_address_name() {
		return reciever_address_name;
	}

	public void setReciever_address_name(String reciever_address_name) {
		this.reciever_address_name = reciever_address_name;
	}

	public String getReciever_address_region() {
		return reciever_address_region;
	}

	public void setReciever_address_region(String reciever_address_region) {
		this.reciever_address_region = reciever_address_region;
	}

	public String getReciever_address_address() {
		return reciever_address_address;
	}

	public void setReciever_address_address(String reciever_address_address) {
		this.reciever_address_address = reciever_address_address;
	}

	public int getReciever_address_postcode() {
		return reciever_address_postcode;
	}

	public void setReciever_address_postcode(int reciever_address_postcode) {
		this.reciever_address_postcode = reciever_address_postcode;
	}

	public long getReciever_address_phone() {
		return reciever_address_phone;
	}

	public void setReciever_address_phone(long reciever_address_phone) {
		this.reciever_address_phone = reciever_address_phone;
	}

}