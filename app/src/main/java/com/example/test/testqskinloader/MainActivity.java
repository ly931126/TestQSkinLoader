package com.example.test.testqskinloader;

import android.os.Bundle;
import android.widget.Button;

import com.example.test.testqskinloader.util.CommonMethod;

import org.qcode.qskinloader.sample.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.current_apk_inside_change_skin)
    Button mCurrentApkInsideChangeSkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.current_apk_inside_change_skin)
    public void onClick() {
        CommonMethod.startAnotherActivity(this,CurrentActivity.class);
    }
}
