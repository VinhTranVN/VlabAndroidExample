package com.mvp.features.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.mvp.R;
import com.mvp.features.common.BaseFragment;
import com.mvp.features.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    private RegisterContract.Presenter mPresenter;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.login_progress) ProgressBar mLoginProgress;
    @BindView(R.id.phone_number) EditText mPhoneNumber;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void bindData() {

    }

    @OnClick(R.id.register_button) void onRegisterButtonClick() {
        //TODO implement
        mPresenter.register(mPhoneNumber.getText().toString());
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mLoginProgress.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showToastMsg(String message) {
        showToast(message);
    }

    @Override
    public void showMainScreen() {
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
