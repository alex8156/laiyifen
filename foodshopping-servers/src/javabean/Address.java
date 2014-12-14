package javabean;

public class Address {
	private	int addressId;
	private boolean selected; //选择
	private boolean isdeleted; //删除
	private String addressName;
	private String addressRegion;
	private String addressAddress;
	private int addressPostcode;
	private int addressPhone;//地址
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
	public int getAddressPhone() {
		return addressPhone;
	}
	public void setAddressPhone(int addressPhone) {
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
			int addressPostcode, int addressPhone, boolean modificationed) {
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
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", selected=" + selected
				+ ", isdeleted=" + isdeleted + ", addressName=" + addressName
				+ ", addressRegion=" + addressRegion + ", addressAddress="
				+ addressAddress + ", addressPostcode=" + addressPostcode
				+ ", addressPhone=" + addressPhone + ", modificationed="
				+ modificationed + "]";
	}
	
	
}
