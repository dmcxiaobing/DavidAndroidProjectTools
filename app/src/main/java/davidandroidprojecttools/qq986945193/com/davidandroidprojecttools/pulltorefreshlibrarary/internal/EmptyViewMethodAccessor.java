/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pulltorefreshlibrarary.internal;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
import android.view.View;

/**
 * Interface that allows PullToRefreshBase to hijack the call to
 * AdapterView.setEmptyView()
 *
 */
public interface EmptyViewMethodAccessor {

	/**
	 * Calls upto AdapterView.setEmptyView()
	 * 
	 * @param emptyView - to set as Empty View
	 */
	public void setEmptyViewInternal(View emptyView);

	/**
	 * Should call PullToRefreshBase.setEmptyView() which will then
	 * automatically call through to setEmptyViewInternal()
	 * 
	 * @param emptyView - to set as Empty View
	 */
	public void setEmptyView(View emptyView);

}
