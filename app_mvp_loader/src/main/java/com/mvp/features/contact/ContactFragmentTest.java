package com.mvp.features.contact;

import android.os.Bundle;

import com.mvp.R;
import com.mvp.features.common.BaseFragment;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class ContactFragmentTest extends BaseFragment {


    public static ContactFragmentTest newInstance() {
        Bundle args = new Bundle();
        ContactFragmentTest fragment = new ContactFragmentTest();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindData() {
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_history;
    }

}
