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
package org.apache.ibatis.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.cache.decorators.TransactionalCache;

/**
 * @author liuzhongda
 */
public class TransactionalCacheManager {
	/**
	 * 管理了多个Cache，每个Cache对应一个TransactionalCache
	 */
	private Map<Cache, TransactionalCache> transactionalCaches = new HashMap<Cache, TransactionalCache>();

	/**
	 * 清空未commit()的临时数据
	 * 
	 * @param cache
	 */
	public void clear(Cache cache) {
		getTransactionalCache(cache).clear();
	}
	/**
	 * 获取缓存数据
	 * @param cache
	 * @param key
	 * @return
	 */
	public Object getObject(Cache cache, CacheKey key) {
		return getTransactionalCache(cache).getObject(key);
	}
	/**
	 * 设置缓存数据，数据应该被保存在临时区域，只commit才会保存在cache中 
	 * @param cache
	 * @param key
	 * @param value
	 */
	public void putObject(Cache cache, CacheKey key, Object value) {
		getTransactionalCache(cache).putObject(key, value);
	}
	/**
	 * 数据临时数据刷新的Cache中，使用数据对其他的SqlSession对象也可见  
	 */
	public void commit() {
		for (TransactionalCache txCache : transactionalCaches.values()) {
			txCache.commit();
		}
	}
	/**
	 * 回滚，应该是清除临时区域的数据  
	 */
	public void rollback() {
		for (TransactionalCache txCache : transactionalCaches.values()) {
			txCache.rollback();
		}
	}
	/**
	 * 获取对应的TransactionalCache,没有就生成一个  
	 * @param cache
	 * @return
	 */
	private TransactionalCache getTransactionalCache(Cache cache) {
		TransactionalCache txCache = transactionalCaches.get(cache);
		if (txCache == null) {
			txCache = new TransactionalCache(cache);
			transactionalCaches.put(cache, txCache);
		}
		return txCache;
	}

}
