package com.cxl.shoppingcar;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable{
	private	int addressId;
	private boolean selected; //选择
	private boolean isdeleted; //删除
	private String addressName;
	private String addressRegion;
	private String addressAddress;
	private int addressPostcode;
	private long addressPhone;
	private boolean modificationed;//修改	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressRegion() {
		return addressRegion;
	}
	public void setAddressRegion(String addressRegion) {
		this.addressRegion = addressRegion;
	}
	public String getAddressAddress() {
		return addressAddress;
	}
	public void setAddressAddress(String addressAddress) {
		this.addressAddress = addressAddress;
	}
	public int getAddressPostcode() {
		return addressPostcode;
	}
	public void setAddressPostcode(int addressPostcode) {
		this.addressPostcode = addressPostcode;
	}
	public long getAddressPhone() {
		return addressPhone;
	}
	public void setAddressPhone(long addressPhone) {
		this.addressPhone = addressPhone;
	}
	public boolean isModificationed() {
		return modificationed;
	}
	public void setModificationed(boolean modificationed) {
		this.modificationed = modificationed;
	}
	public Address(int addressId, boolean selected, boolean isdeleted,
			String addressName, String addressRegion, String addressAddress,
			int addressPostcode, long addressPhone, boolean modificationed) {
		super();
		this.addressId = addressId;
		this.selected = selected;
		this.isdeleted = isdeleted;
		this.addressName = addressName;
		this.addressRegion = addressRegion;
		this.addressAddress = addressAddress;
		this.addressPostcode = addressPostcode;
		this.addressPhone = addressPhone;
		this.modificationed = modificationed;
	}
	
	
	public Address(String addressName, String addressRegion,
			String addressAddress, int addressPostcode, long addressPhone) {
		super();
		this.addressName = addressName;
		this.addressRegion = addressRegion;
		this.addressAddress = addressAddress;
		this.addressPostcode = addressPostcode;
		this.addressPhone = addressPhone;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", selected=" + selected
				+ ", isdeleted=" + isdeleted + ", addressName=" + addressName
				+ ", addressRegion=" + addressRegion + ", addressAddress="
				+ addressAddress + ", addressPostcode=" + addressPostcode
				+ ", addressPhone=" + addressPhone + ", modificationed="
				+ modificationed + "]";
	}
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(addressName);
		parcel.writeString(addressRegion);
		parcel.writeString(addressName);
		parcel.writeInt(addressPostcode);
		parcel.writeLong(addressPhone);
		
	}
	
	public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {

		@Override
		public Address createFromParcel(Parcel source) {
			return new Address(source.readString(), source.readString(), source.readString(), source.readInt(), source.readLong());
		}

		@Override
		public Address[] newArray(int size) {
			return new Address[size];
		}
	};
}
