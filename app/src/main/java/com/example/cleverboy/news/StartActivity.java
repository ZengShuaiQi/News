package com.example.cleverboy.news;

import android.content.Intent;
import android.os.SystemClock;

import base.BaseActivity;
import utils.SharePreUtil;

/**
 * Created by clever boy on 2017/6/27.
 */

public class StartActivity extends BaseActivity {
    @Override
    public int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    public void initView() {
        new Thread() {
            public void run() {
                SystemClock.sleep(1500);

                boolean firstRun = SharePreUtil.getBoolean(getApplicationContext(),"firstRun",true);
                if(firstRun){
                    enterGuideActivity();
                }else{
                    enterMainActivity();
                }
            }
        }.start();
    }

    /**
     * 进入引导界面
     */
    private void enterGuideActivity() {
        Intent intent = new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * 进入主页面
     */
    private void enterMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
