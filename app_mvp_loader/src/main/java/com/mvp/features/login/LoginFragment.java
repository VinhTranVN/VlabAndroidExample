package com.mvp.features.login;

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

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.login_progress) ProgressBar mLoginProgress;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;

    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void bindData() {
        // default data
        email.setText("VinhTran");
    }

    @OnClick(R.id.email_sign_in_button) void onLoginButtonClick() {
        mPresenter.login(email.getText().toString(), password.getText().toString());
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
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
