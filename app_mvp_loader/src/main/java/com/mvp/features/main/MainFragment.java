package com.mvp.features.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.features.common.BaseFragment;

import java.util.List;

import butterknife.BindView;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class MainFragment extends BaseFragment implements MainContract.View {

    @BindView(R.id.main_tab_host) FragmentTabHost mTabHost;
    @BindView(R.id.data_response) TextView mTvDataResponse;

    private MainContract.Presenter mPresenter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.progressbar) ProgressBar mProgressBar;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println(">>> MainFragment -> onStart");
        mPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println(">>> MainFragment -> onStop ");
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    protected void bindData() {
        // init tab
        initTabs(mPresenter.getTabs());
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mProgressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void initTabs(List<TabInfo> tabList) {
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.tab_content);
        for (TabInfo tabInfo : tabList) {
            mTabHost.addTab(
                    mTabHost.newTabSpec(tabInfo.toString()).setIndicator(
                            getTabIndicatorView(mTabHost.getContext(), tabInfo.getTitle(), tabInfo.getIconRes())
                    ),
                    tabInfo.getFragment(),
                    null);
        }

    }

    private View getTabIndicatorView(Context context, String title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }

    @Override
    public void showTab(int tabIndex) {
        // TODO show tab here
    }

    @Override
    public void showDataResponse(String text) {
        mTvDataResponse.setText(text);
    }
}
