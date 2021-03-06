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
package org.apache.ibatis.executor;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

/**
 *  一级缓存: 基于PerpetualCache 的 HashMap本地缓存，其存储作用域为 Session，
 *  当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
　*　2. 二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义存储源，如 Ehcache。
　*　3. 对于缓存数据更新机制，当某一个作用域(一级缓存Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被clear。
 * MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
 * 1、根据传递的参数，完成SQL语句的动态解析，生成BoundSql对象，供如StatementHandler使用；
 * 2、为查询创建缓存，以提高性能；
 * 3、创建JDBC的如Statement连接对象，传递给如StatementHandler对象，返回List查询结果。
 * @author liuzhongda
 */
public interface Executor {

  ResultHandler NO_RESULT_HANDLER = null;
  /**
   * 执行update/delete/insert
   * @param ms
   * @param parameter
   * @return
   * @throws SQLException
   */
  int update(MappedStatement ms, Object parameter) throws SQLException;
  /**
   * 执行查询
   * @param ms
   * @param parameter
   * @param rowBounds
   * @param resultHandler
   * @param cacheKey
   * @param boundSql
   * @return
   * @throws SQLException
   */
  <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException;
  /**
   * 执行查询
   * @param ms
   * @param parameter
   * @param rowBounds
   * @param resultHandler
   * @return
   * @throws SQLException
   */
  <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;
  /**
   * 
   * @return
   * @throws SQLException
   */
  List<BatchResult> flushStatements() throws SQLException;
  /**
   * 提交事务
   * @param required
   * @throws SQLException
   */
  void commit(boolean required) throws SQLException;
  /**
   * 事务回滚
   * @param required
   * @throws SQLException
   */
  void rollback(boolean required) throws SQLException;
  /**
   * 生成缓存的key
   * @param ms
   * @param parameterObject
   * @param rowBounds
   * @param boundSql
   * @return
   */
  CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);
  /**
   * 是否存在缓存
   * @param ms
   * @param key
   * @return
   */
  boolean isCached(MappedStatement ms, CacheKey key);
  /**
   * 清除一级缓存
   */
  void clearLocalCache();
  /**
   * 延迟加载
   * @param ms
   * @param resultObject
   * @param property
   * @param key
   * @param targetType
   */
  void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType);
  /**
   * 获取事务
   * @return
   */
  Transaction getTransaction();
  /**
   * 关闭事务
   * @param forceRollback
   */
  void close(boolean forceRollback);
  /**
   * 连接是否关闭
   * @return
   */
  boolean isClosed();
  /**
   * 适配执行器
   * @param executor
   */
  void setExecutorWrapper(Executor executor);

}
