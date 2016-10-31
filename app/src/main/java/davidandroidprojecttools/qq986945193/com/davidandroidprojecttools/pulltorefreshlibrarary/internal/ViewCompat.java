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
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;

@SuppressWarnings("deprecation")
public class ViewCompat {

	public static void postOnAnimation(View view, Runnable runnable) {
		if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			SDK16.postOnAnimation(view, runnable);
		} else {
			view.postDelayed(runnable, 16);
		}
	}

	public static void setBackground(View view, Drawable background) {
		if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			SDK16.setBackground(view, background);
		} else {
			view.setBackgroundDrawable(background);
		}
	}

	public static void setLayerType(View view, int layerType) {
		if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
			SDK11.setLayerType(view, layerType);
		}
	}

	@TargetApi(11)
	static class SDK11 {

		public static void setLayerType(View view, int layerType) {
			view.setLayerType(layerType, null);
		}
	}

	@TargetApi(16)
	static class SDK16 {

		public static void postOnAnimation(View view, Runnable runnable) {
			view.postOnAnimation(runnable);
		}

		public static void setBackground(View view, Drawable background) {
			view.setBackground(background);
		}

	}

}
