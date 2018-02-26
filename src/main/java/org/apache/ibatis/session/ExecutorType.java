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
package org.apache.ibatis.session;

/**
 * 配置和设定执行器
 * SIMPLE 执行器执行其它语句。
 * REUSE 执行器可能重复使用prepared statements 语句。
 * BATCH执行器可以重复执行语句和批量更新。
 * @author liuzhongda
 */
public enum ExecutorType {
  SIMPLE, REUSE, BATCH
}
