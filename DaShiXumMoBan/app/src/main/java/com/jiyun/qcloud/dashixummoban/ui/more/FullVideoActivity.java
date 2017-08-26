package com.jiyun.qcloud.dashixummoban.ui.more;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.shi.VideoBean;
import com.jiyun.qcloud.dashixummoban.widget.MediaHelp;
import com.jiyun.qcloud.dashixummoban.widget.VideoMediaController;
import com.jiyun.qcloud.dashixummoban.widget.VideoSuperPlayer;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/25.
 * 负责 全部
 * 团队 1人
 */
public class FullVideoActivity extends AppCompatActivity{
    private VideoSuperPlayer mVideo;
    private VideoBean info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full);
        mVideo = (VideoSuperPlayer) findViewById(R.id.video);
        info = (VideoBean) getIntent().getExtras().getSerializable("video");
        mVideo.loadAndPlay(MediaHelp.getInstance(), info.getUrl(), getIntent()
                .getExtras().getInt("position"), true);
        mVideo.setPageType(VideoMediaController.PageType.EXPAND);
        mVideo.setVideoPlayCallback(new VideoSuperPlayer.VideoPlayCallbackImpl() {

            @Override
            public void onSwitchPageType() {
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    finish();
                }
            }

            @Override
            public void onPlayFinish() {
                finish();
            }

            @Override
            public void onCloseVideo() {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("position", mVideo.getCurrentPosition());
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaHelp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaHelp.resume();
    }
}
