package com.github.slamdunk.enums;

/**
 * 
 * @author liuzhongda
 *
 */
public enum UserType {
	/**
	 * general user
	 */
	GENERAL(1),

	/**
	 * VIP user
	 */
	IMPORTANT(2);

	private int value;

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static UserType getUserType(int value) {
		for (UserType userType : UserType.values()) {
			if (userType.getValue() == value) {
				return userType;
			}
		}
		return null;
	}

}
