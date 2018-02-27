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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuzhongda
 */
public class InterceptorChain {
	/**
	 * 在mybatis-config.xml配置的拦截器
	 */
	private final List<Interceptor> interceptors = new ArrayList<Interceptor>();

	/**
	 * 用所有的拦截器生成对象
	 * 
	 * @param target
	 * @return
	 */
	public Object pluginAll(Object target) {
		for (Interceptor interceptor : interceptors) {
			 //调用了interceptor.plugin()方法来生成代理对象  
			target = interceptor.plugin(target);
		}
		return target;
	}

	public void addInterceptor(Interceptor interceptor) {
		interceptors.add(interceptor);
	}

	public List<Interceptor> getInterceptors() {
		return Collections.unmodifiableList(interceptors);
	}

}
