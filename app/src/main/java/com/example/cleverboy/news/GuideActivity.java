package com.example.cleverboy.news;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import base.BaseActivity;
import utils.SharePreUtil;

/**
 * Created by clever boy on 2017/6/27.
 */

public class GuideActivity extends BaseActivity {
    private int count = 0;
    private int[] imagesArray = new int[] {
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };
    private boolean mExitActivity = false;
    private Button mBtnGo;
    private ImageView mIv01;
    private MediaPlayer mMediaPlayer;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        mBtnGo = (Button) findViewById(R.id.btn_go);
        mIv01 = (ImageView) findViewById(R.id.iv_01);
        starAnimation();
    }

    @Override
    public void initListener() {
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitActivity = true;
        stopMusic();
    }

    @Override
    public void initData() {
        SharePreUtil.saveBoolean(this,"firstRun",false);
    }

    /**
     * 图片开始显示动画
     */
    private void starAnimation() {

        mIv01.setBackgroundResource(imagesArray[count%imagesArray.length]);
        count++;

        mIv01.setScaleX(1.0f);
        mIv01.setScaleY(1.0f);
        mIv01.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(!mExitActivity){
                    starAnimation();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }
    /**
     * 进入主页面
     */
    private void enterMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 循环播放背景音乐
     */
    private void playBackgroundMusic(){
        mMediaPlayer = MediaPlayer.create(this,R.raw.new_version);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setVolume(1.0f,1.0f);
        mMediaPlayer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        playBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }

    private void stopMusic() {
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
