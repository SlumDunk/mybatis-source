package com.github.slamdunk.vo;

import java.util.List;

import com.github.slamdunk.enums.UserType;

public class UserVO {
	/**
	 * user id, primary key
	 */
	private int id;

	private String username;

	/**
	 * user no.
	 */
	private String svcnum;

	private String password;

	/**
	 * an user can only have one customer
	 */
	private CustVO cust;

	/**
	 * an user can have several accounts
	 */
	private List<AcctVO> accts;

	/**
	 * type of user, GENERAL and VIP
	 */
	private UserType type;

	public UserVO(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CustVO getCust() {
		return cust;
	}

	public void setCust(CustVO cust) {
		this.cust = cust;
	}

	public List<AcctVO> getAccts() {
		return accts;
	}

	public void setAccts(List<AcctVO> accts) {
		this.accts = accts;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getSvcnum() {
		return svcnum;
	}

	public void setSvcnum(String svcnum) {
		this.svcnum = svcnum;
	}
}
