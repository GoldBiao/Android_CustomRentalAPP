package com.keysu.customizedrental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.utils.StringUtil;
import com.keysu.customizedrental.utils.ToastUtil;
import com.kongzue.dialog.v3.FullScreenDialog;


import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, ViewTreeObserver.OnGlobalLayoutListener, TextWatcher {

    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;
    @BindView(R.id.iv_register_phone_del)
    ImageView ivRegisterUsernameDel;
    @BindView(R.id.et_register_auth_code)
    EditText etRegisterAuthCode;
    @BindView(R.id.tv_register_sms_call)
    TextView tvRegisterSmsCall;
    @BindView(R.id.et_register_pwd_input)
    EditText etRegisterPwdInput;
    @BindView(R.id.iv_register_pwd_see)
    ImageView ivRegisterPwdSee;
    @BindView(R.id.cb_protocol)
    CheckBox cbProtocol;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.bt_register_submit)
    Button btRegisterSubmit;


    //导航栏
    @BindView(R.id.ly_register_bar)
    LinearLayout mLayBackBar;
    @BindView(R.id.ib_navigation_back)
    ImageButton mIbNavigationBack;


    //设置密码框可视的图标
    private int[] images = new int[]{
            R.mipmap.ic_eye,
            R.mipmap.ic_eye_see
    };

    private boolean isEye = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initViews();
    }


    private void initViews() {
        mIbNavigationBack.setOnClickListener(this);
        tvProtocol.setOnClickListener(this);

        ivRegisterPwdSee.setOnClickListener(this);
        ivRegisterPwdSee.setImageResource(images[0]);

        etRegisterUsername.setInputType(InputType.TYPE_CLASS_PHONE);
        etRegisterAuthCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        StringUtil.StringWatcher(etRegisterPwdInput);

        ivRegisterUsernameDel.setOnClickListener(this);

        etRegisterUsername.setOnFocusChangeListener(this);
        etRegisterUsername.addTextChangedListener(this);

        etRegisterAuthCode.setOnFocusChangeListener(this);
        etRegisterAuthCode.addTextChangedListener(this);

        etRegisterPwdInput.setOnFocusChangeListener(this);
        etRegisterPwdInput.addTextChangedListener(this);

        btRegisterSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String registerUsername = etRegisterUsername.toString().trim();
        String registerAuthCode = etRegisterAuthCode.toString().trim();
        String registerPwd = etRegisterPwdInput.toString().trim();


        switch (v.getId()) {
            case R.id.et_register_username:
                etRegisterPwdInput.clearFocus();
                etRegisterAuthCode.clearFocus();
                etRegisterUsername.setFocusableInTouchMode(true);
                etRegisterUsername.requestFocus();
                break;
            case R.id.et_register_auth_code:
                etRegisterPwdInput.clearFocus();
                etRegisterUsername.clearFocus();
                etRegisterAuthCode.setFocusableInTouchMode(true);
                etRegisterAuthCode.requestFocus();
                break;
            case R.id.et_register_pwd_input:
                etRegisterUsername.clearFocus();
                etRegisterAuthCode.clearFocus();
                etRegisterPwdInput.setFocusableInTouchMode(true);
                etRegisterPwdInput.requestFocus();
                break;
            case R.id.iv_register_phone_del:
                etRegisterUsername.setText(null);
                ivRegisterUsernameDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_register_sms_call:
                break;
            case R.id.iv_register_pwd_see:
                if (!isEye){
                    ivRegisterPwdSee.setImageResource(images[1]);
                    etRegisterPwdInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isEye = true;
                }else {
                    ivRegisterPwdSee.setImageResource(images[0]);
                    etRegisterPwdInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isEye = false;
                }
                etRegisterPwdInput.setSelection(etRegisterPwdInput.getText().length());

                break;

            case R.id.cb_protocol:
                break;
            case R.id.tv_protocol:
                FullScreenDialog.show(RegisterActivity.this, R.layout.layout_tv_protocol, new FullScreenDialog.OnBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {

                    }
                }).setOkButton("确定")
                        .setTitle("服务条款");
                break;
            case R.id.bt_register_submit:

                if (!TextUtils.isEmpty(registerUsername) && !TextUtils.isEmpty(registerAuthCode) && !TextUtils.isEmpty(registerPwd)) {
                    ToastUtil.show("注册成功");
                    registerRequest();
                }

                    break;
            case R.id.ib_navigation_back:
                finish();
                break;
        }
    }

    private void registerRequest() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String registerUsername = etRegisterUsername.toString().trim();
        String registerAuthCode = etRegisterAuthCode.toString().trim();
        String registerPwd = etRegisterPwdInput.toString().trim();

        //判断显示清除按钮
        if (registerUsername.length()>0){
            ivRegisterUsernameDel.setVisibility(View.VISIBLE);
        }else {
            ivRegisterUsernameDel.setVisibility(View.INVISIBLE);
        }

        //登录按钮是否可用
        if (!TextUtils.isEmpty(registerUsername) && !TextUtils.isEmpty(registerAuthCode) && !TextUtils.isEmpty(registerPwd)) {
            btRegisterSubmit.setBackgroundResource(R.drawable.bg_login_submit);
            btRegisterSubmit.setTextColor(getResources().getColor(R.color.white));
        } else {
            btRegisterSubmit.setBackgroundResource(R.drawable.bg_login_submit_lock);
            btRegisterSubmit.setTextColor(getResources().getColor(R.color.account_lock_font_color));
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        if (id == R.id.et_login_username) {
            if (hasFocus) {
                etRegisterUsername.setActivated(true);
                etRegisterAuthCode.setActivated(false);
                etRegisterPwdInput.setActivated(false);
            }
        } else if (id == R.id.et_register_auth_code){
            if (hasFocus) {
                etRegisterUsername.setActivated(false);
                etRegisterAuthCode.setActivated(true);
                etRegisterPwdInput.setActivated(false);
            }
        }else if (id == R.id.et_register_pwd_input){
            if (hasFocus) {
                etRegisterUsername.setActivated(false);
                etRegisterAuthCode.setActivated(false);
                etRegisterPwdInput.setActivated(true);
            }
        }


    }

    @Override
    public void onGlobalLayout() {

    }
}
