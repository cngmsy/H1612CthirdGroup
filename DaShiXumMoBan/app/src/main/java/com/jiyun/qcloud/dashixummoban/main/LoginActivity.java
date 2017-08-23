package com.jiyun.qcloud.dashixummoban.main;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.bt_yanzhengma)
    Button btYanzhengma;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.login_disanfang)
    ImageView loginDisanfang;
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
            String uid = data.get("uid");
            String name = data.get("screen_name");
            String gender = data.get("gender");
            String iconurl = data.get("iconurl");
            String vip = data.get("is_yellow_year_vip");
            EventBus.getDefault().post(new UserBean(iconurl,name));
finish();
            Log.e("TAG", "uid=" + uid + "\nname=" + name + "\ngender=" + gender + "\niconurl=" + iconurl+"\nvip="+vip);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    int time=60;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(time>=0){
                time--;
                btYanzhengma.setText("" + time);
                handler.postDelayed(this, 1000);
            }else{
                btYanzhengma.setText("获取验证码");
            }

        }
    };
    private EventHandler eventHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
// 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码

        initData();
    }

    private void initData() {
        SMSSDK.setAskPermisionOnReadContact(true);
        eventHandler = new EventHandler() {
            @Override//注册的时候走这个方法
            public void onRegister() {
                super.onRegister();
            }

            @Override//事件提交完成的时候走这个方法
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                if (data instanceof Throwable) {//失败
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {//SMSSDK.RESULT_COMPLETE
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        //弹一个吐司表示注册成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(LoginActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }

            @Override//事件提交之前走这个方法
            public void beforeEvent(int i, Object o) {
                super.beforeEvent(i, o);
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }


    @OnClick({R.id.bt_yanzhengma, R.id.bt_login, R.id.login_disanfang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_yanzhengma:
                handler.postDelayed(runnable, 1000);
                SMSSDK.getVerificationCode("86", edPhone.getText().toString().trim());
                break;
            case R.id.bt_login:
                finish();
                break;
            case R.id.login_disanfang:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);

                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {


    }
}
