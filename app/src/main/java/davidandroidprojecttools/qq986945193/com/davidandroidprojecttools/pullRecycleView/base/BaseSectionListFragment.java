package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.BaseViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.section.SectionData;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public abstract class BaseSectionListFragment<T> extends BaseListFragment<SectionData<T>> {
    protected static final int VIEW_TYPE_SECTION_HEADER = 1;
    protected static final int VIEW_TYPE_SECTION_CONTENT = 2;

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SECTION_HEADER) {
            return onCreateSectionHeaderViewHolder(parent);
        }
        return onCreateSectionViewHolder(parent, viewType);
    }

    protected abstract BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType);

    private BaseViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh_section_header, parent, false);
        return new SectionHeaderViewHolder(view);
    }

    @Override
    protected int getItemType(int position) {
        return mDataList.get(position).isHeader ? VIEW_TYPE_SECTION_HEADER : VIEW_TYPE_SECTION_CONTENT;
    }

    @Override
    protected boolean isSectionHeader(int position) {
        return mDataList.get(position).isHeader;
    }

    private class SectionHeaderViewHolder extends BaseViewHolder {
        private final TextView header;

        public SectionHeaderViewHolder(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.header);
        }

        @Override
        public void onBindViewHolder(int position) {
            header.setText(mDataList.get(position).header);
        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }

}
