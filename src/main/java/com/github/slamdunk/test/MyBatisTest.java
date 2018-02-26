package com.github.slamdunk.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @author liuzhongda
 *
 */
public class MyBatisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resouce = "config/mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resouce);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			System.out.println(sqlSessionFactory.getConfiguration());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
