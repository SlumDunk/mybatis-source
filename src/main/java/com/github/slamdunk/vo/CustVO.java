package com.github.slamdunk.vo;
/**
 * 
 * @author liuzhongda
 *
 */
public class CustVO {
	private String id;

	/**
	 * name of customer
	 */
	private String custname;

	/**
	 * identify no.
	 */
	private String certNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
}
