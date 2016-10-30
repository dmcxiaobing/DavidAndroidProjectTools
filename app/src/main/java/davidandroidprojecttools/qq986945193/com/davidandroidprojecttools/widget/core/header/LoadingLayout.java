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
package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.core.header;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
/**
 * @author ：程序员小冰
 *
 * @新浪微博 ：http://weibo.com/mcxiaobing
 *
 * @GitHub: https://github.com/QQ986945193
 *
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 *
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 *
 */

//下拉刷新基类
public abstract class LoadingLayout extends FrameLayout {

    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    private View mInnerLayout;
    //下拉刷新图片，进度条
    protected final ImageView mHeaderImage;
    protected final ProgressBar mHeaderProgress;
    //下拉刷新提示文字
    private final TextView mHeaderText;
    private final TextView mSubHeaderText;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;

    public LoadingLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_view, this);
        mInnerLayout = (View) findViewById(R.id.fl_inner);
        mHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        mHeaderProgress = (ProgressBar) mInnerLayout.findViewById(R.id.pull_to_refresh_progress);
        mSubHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);
        mHeaderImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh_image);
        // 提示文字
        mPullLabel = context.getString(R.string.header_hint_refresh_normal);
        mRefreshingLabel = context.getString(R.string.header_hint_refresh_loading);
        mReleaseLabel = context.getString(R.string.header_hint_refresh_ready);
        reset();
    }
    public void reset() {
        if (null != mHeaderText) {
            mHeaderText.setText(mPullLabel);
        }
        mHeaderImage.setVisibility(View.VISIBLE);

        if (null != mSubHeaderText) {
            if (TextUtils.isEmpty(mSubHeaderText.getText())) {
                mSubHeaderText.setVisibility(View.GONE);
            } else {
                mSubHeaderText.setVisibility(View.VISIBLE);
            }
        }
    }
    public void onPullToRefresh(){
        if (null != mHeaderText) {
            mHeaderText.setText(mPullLabel);
        }
    };
    public void onReleaseToClose(){
        if (null != mHeaderText) {
            mHeaderText.setText(mPullLabel);
        }
    };
    public void onRefreshSlopReach(){
        if (null != mHeaderText) {
            mHeaderText.setText(mReleaseLabel);
        }
    };
    public void onRefresh(){
        if (null != mHeaderText) {
            mHeaderText.setText(mRefreshingLabel);
        }
    };

    public final void setLoadingDrawable(Drawable imageDrawable) {

        mHeaderImage.setImageDrawable(imageDrawable);

    }

    private void setSubHeaderText(CharSequence label) {
        if (null != mSubHeaderText) {
            if (TextUtils.isEmpty(label)) {
                mSubHeaderText.setVisibility(View.GONE);
            } else {
                mSubHeaderText.setText(label);
                if (View.GONE == mSubHeaderText.getVisibility()) {
                    mSubHeaderText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
