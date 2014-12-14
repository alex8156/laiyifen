package com.user.servlet;

public class User {
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		private	int user_id;
		private String username;
		private String password;
		private String telephone;
		private String email;
		private String region;
		private String street_address;
		private String postcode;
		private float account_balance;
		private float account_integral;
		private int[] ii;
		private String[] ss;
		
		public User(int user_id, String username, String password,
				String telephone, String region, String street_address,
				String postcode, String email) {
			super();
			this.user_id = user_id;
			this.username = username;
			this.password = password;
			this.telephone = telephone;
			this.email = email;
			this.region = region;
			this.street_address = street_address;
			this.postcode = postcode;
		}
		
		public User(int user_id, String username, String password,
				String telephone, String region, String street_address,
				String postcode, String email, float account_balance, float account_integral) {
			super();
			this.user_id = user_id;
			this.username = username;
			this.password = password;
			this.telephone = telephone;
			this.email = email;
			this.region = region;
			this.street_address = street_address;
			this.postcode = postcode;
			this.account_balance = account_balance;
			this.account_integral = account_integral;
		}
	
		public User(String username, String password, String telephone, String email) {
			this.username = username;
			this.password = password;
			this.telephone = telephone;
			this.email = email;
		}
		public User(String username, String password) {
			this.username = username;
			this.password = password;
	
		}
		public User(float account_balance, float account_integral) {
			this.account_balance = account_balance;
			this.account_integral = account_integral;
	
		}
	
		public User(String username, String region, String street_address,
				String postcode, String email) {
			this.username = username;
			this.email = email;
			this.region = region;
			this.street_address = street_address;
			this.postcode = postcode;
		}


		@Override
		public String toString() {
			return "User [用户ID=" + user_id + ", 用户名=" + username
					+ ", 密码=" + password + ", 手机=" + telephone
					+ ", 邮箱=" + email + ", 地区=" + region
					+ ", 地址=" + street_address + ", 邮编="
					+ postcode + ", 积分=" + account_balance
					+ ", 余额=" + account_integral + "]";
		}

		public int getUser_id() {
			return user_id;
		}
	
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
	
		public String getUsername() {
			return username;
		}
	
		public void setUsername(String username) {
			this.username = username;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getTelephone() {
			return telephone;
		}
	
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getRegion() {
			return region;
		}
	
		public void setRegion(String region) {
			this.region = region;
		}
	
		public String getStreet_address() {
			return street_address;
		}
	
		public void setStreet_address(String street_address) {
			this.street_address = street_address;
		}
	
		public String getPostcode() {
			return postcode;
		}
	
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}

		public float getAccount_balance() {
			return account_balance;
		}

		public void setAccount_balance(float account_balance) {
			this.account_balance = account_balance;
		}

		public float getAccount_integral() {
			return account_integral;
		}

		public void setAccount_integral(float account_integral) {
			this.account_integral = account_integral;
		}



		

}
