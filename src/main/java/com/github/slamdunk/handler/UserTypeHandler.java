package com.github.slamdunk.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.slamdunk.enums.UserType;

/**
 * 
 * @author liuzhongda
 *
 * @param <UserType>
 */
public class UserTypeHandler extends BaseTypeHandler<UserType> {
	private Class<UserType> type;

	public UserTypeHandler(Class<UserType> type) {
		super();
		this.type = type;
	}

	@Override
	public UserType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		int constant = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return convert(constant);
		}
	}

	@Override
	public UserType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		int constant = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return convert(constant);
		}
	}

	@Override
	public UserType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		int constant = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return convert(constant);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, UserType parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		ps.setInt(i, parameter.getValue());
	}

	private UserType convert(int constant) {
		UserType[] userTypeEnums = type.getEnumConstants();
		for (UserType userType : userTypeEnums) {
			if (userType.getValue() == constant) {
				return userType;
			}
		}
		return null;
	}
}
