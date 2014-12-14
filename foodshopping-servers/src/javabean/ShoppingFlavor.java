package javabean;

public class ShoppingFlavor {

	private int flavor_category_id;
	private String flavor_category_name;
	private String flavor_category_description;
	private String flavor_category_image;
	public ShoppingFlavor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShoppingFlavor(int flavor_category_id, String flavor_category_name,
			String flavor_category_description, String flavor_category_image) {
		super();
		this.flavor_category_id = flavor_category_id;
		this.flavor_category_name = flavor_category_name;
		this.flavor_category_description = flavor_category_description;
		this.flavor_category_image = flavor_category_image;
	}
	public int getFlavor_category_id() {
		return flavor_category_id;
	}
	public void setFlavor_category_id(int flavor_category_id) {
		this.flavor_category_id = flavor_category_id;
	}
	public String getFlavor_category_name() {
		return flavor_category_name;
	}
	public void setFlavor_category_name(String flavor_category_name) {
		this.flavor_category_name = flavor_category_name;
	}
	public String getFlavor_category_description() {
		return flavor_category_description;
	}
	public void setFlavor_category_description(String flavor_category_description) {
		this.flavor_category_description = flavor_category_description;
	}
	public String getFlavor_category_image() {
		return flavor_category_image;
	}
	public void setFlavor_category_image(String flavor_category_image) {
		this.flavor_category_image = flavor_category_image;
	}
	
	
}
