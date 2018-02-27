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
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * @author liuzhongda
 */
public interface Interceptor {
	/**
	 * jdk动态代码中的InvocationHandler.invoke()方法执行里，这个方法会被调用
	 * 
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	Object intercept(Invocation invocation) throws Throwable;

	/**
	 * 生成一个代理对象
	 * 
	 * @param target
	 * @return
	 */
	Object plugin(Object target);

	/**
	 * 设置属性
	 * 
	 * @param properties
	 */
	void setProperties(Properties properties);

}
