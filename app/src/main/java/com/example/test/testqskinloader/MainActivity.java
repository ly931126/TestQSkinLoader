package com.example.test.testqskinloader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.test.testqskinloader.util.CommonMethod;

import org.qcode.qskinloader.sample.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
	
	@BindView(R.id.current_apk_inside_change_skin)
	Button			mCurrentApkInsideChangeSkin;
	@BindView(R.id.installed_apk_change_skin)
	Button			mInstalledApkChangeSkin;
	@BindView(R.id.activity_main)
	RelativeLayout	mActivityMain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}
	
	@OnClick({R.id.current_apk_inside_change_skin, R.id.installed_apk_change_skin})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.current_apk_inside_change_skin :
                CommonMethod.startAnotherActivity(this,CurrentActivity.class);
				break;

			case R.id.installed_apk_change_skin :
                CommonMethod.startAnotherActivity(this,InstalledActivity.class);
				break;
		}
	}
	

}
