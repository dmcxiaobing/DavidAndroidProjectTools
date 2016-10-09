/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.zxing.camera;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

final class AutoFocusCallback implements Camera.AutoFocusCallback {

  private static final String TAG = AutoFocusCallback.class.getSimpleName();

  private static final long AUTOFOCUS_INTERVAL_MS = 1500L;

  private Handler autoFocusHandler;
  private int autoFocusMessage;

  void setHandler(Handler autoFocusHandler, int autoFocusMessage) {
    this.autoFocusHandler = autoFocusHandler;
    this.autoFocusMessage = autoFocusMessage;
  }

  public void onAutoFocus(boolean success, Camera camera) {
    if (autoFocusHandler != null) {
      Message message = autoFocusHandler.obtainMessage(autoFocusMessage, success);
      autoFocusHandler.sendMessageDelayed(message, AUTOFOCUS_INTERVAL_MS);
      autoFocusHandler = null;
    } else {
      Log.d(TAG, "Got auto-focus callback, but no handler for it");
    }
  }

}
