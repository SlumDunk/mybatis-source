/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

/**
 * 封装了JDBC Statement操作，负责对JDBC statement 的操作，
 * 如设置参数、将Statement结果集转换成List集合。
 * 功能：
 * 1. 对于JDBC的PreparedStatement类型的对象，创建的过程中，我们使用的是SQL语句字符串会包含 若干个? 占位符，
 *    我们其后再对占位符进行设值。StatementHandler通过parameterize(statement)方法对Statement进行设值；       
 * 2. StatementHandler通过List<E> query(Statement statement, ResultHandler resultHandler)方法
 *    来完成执行Statement，和将Statement对象返回的resultSet封装成List；
 * @author liuzhongda
 */
public interface StatementHandler {
	/**
	 * 从连接中获取一个Statement 
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
  Statement prepare(Connection connection)
      throws SQLException;
  /**
   * 设置statement执行里所需的参数
   * @param statement
   * @throws SQLException
   */
  void parameterize(Statement statement)
      throws SQLException;
  /**
   * 批量
   * @param statement
   * @throws SQLException
   */
  void batch(Statement statement)
      throws SQLException;
  /**
   * 更新：update/insert/delete语句  
   * @param statement
   * @return
   * @throws SQLException
   */
  int update(Statement statement)
      throws SQLException;
  /**
   * 执行查询 
   * @param statement
   * @param resultHandler
   * @return
   * @throws SQLException
   */
  <E> List<E> query(Statement statement, ResultHandler resultHandler)
      throws SQLException;

  BoundSql getBoundSql();

  ParameterHandler getParameterHandler();

}
