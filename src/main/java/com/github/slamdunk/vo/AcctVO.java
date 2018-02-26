package com.github.slamdunk.vo;

/**
 * 
 * @author liuzhongda
 *
 */
public class AcctVO {
	private String id;

	private String payName;

	/**
	 * no. of credit card of bank
	 */
	private String bankNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
}
