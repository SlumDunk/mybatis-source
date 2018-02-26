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
package org.apache.ibatis.cache.decorators;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

/**
 * Lru (least recently used) cache decorator
 *
 * @author liuzhongda
 */
public class LruCache implements Cache {

  private final Cache delegate;
  private Map<Object, Object> keyMap;
  private Object eldestKey;

  public LruCache(Cache delegate) {
    this.delegate = delegate;
    setSize(1024);
  }

  
  public String getId() {
    return delegate.getId();
  }

  
  public int getSize() {
    return delegate.getSize();
  }

  public void setSize(final int size) {
	//注意：第三个参数为true，LinkedHashMap会以访问顺序排序，最近使用的排在最前面  
    keyMap = new LinkedHashMap<Object, Object>(size, .75F, true) {
      private static final long serialVersionUID = 4267176411845948333L;

      //当put()方法被调用里，这个方法会触发，返回true，eldest将会被删除  
      protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
        boolean tooBig = size() > size;
        if (tooBig) {
        	  //保证被删除的key,下面的cycleKeyList方法有用  
          eldestKey = eldest.getKey();
        }
        return tooBig;
      }
    };
  }

  /**
   * 一个新的key加入时，需要检查是否要把旧的删除 
   */
  public void putObject(Object key, Object value) {
    delegate.putObject(key, value);
    cycleKeyList(key);
  }

  
  public Object getObject(Object key) {
    keyMap.get(key); //touch
    return delegate.getObject(key);
  }

  
  public Object removeObject(Object key) {
    return delegate.removeObject(key);
  }

  
  public void clear() {
    delegate.clear();
    keyMap.clear();
  }

  
  public ReadWriteLock getReadWriteLock() {
    return null;
  }

  private void cycleKeyList(Object key) {
	//触发重排序
    keyMap.put(key, key);
    if (eldestKey != null) {
    	  //删除最旧的那个key 
      delegate.removeObject(eldestKey);
      eldestKey = null;
    }
  }

}
