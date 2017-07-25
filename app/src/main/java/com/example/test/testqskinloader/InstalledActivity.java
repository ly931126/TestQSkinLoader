package com.example.test.testqskinloader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.testqskinloader.util.SkinChangeHelper;

import org.qcode.qskinloader.sample.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liye on 2017/7/24. 通过已安装的apk换肤
 */
public class InstalledActivity extends BaseActivity {
	
	@BindView(R.id.default_skin_text)
	Button			mDefaultSkinText;
	@BindView(R.id.skin_one_text)
	Button			mSkinOneText;
	@BindView(R.id.skin_two_text)
	Button			mSkinTwoText;
	@BindView(R.id.skin_three_text)
	Button			mSkinThreeText;
	@BindView(R.id.skin_four_text)
	Button			mSkinFourText;
	// 后缀
	private String	mSuffix	= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_installed);
		ButterKnife.bind(this);
	}
	
	@OnClick({R.id.default_skin_text, R.id.skin_one_text, R.id.skin_two_text, R.id.skin_three_text, R.id.skin_four_text})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.default_skin_text :
				mSuffix = "";
				break;
			case R.id.skin_one_text :
				mSuffix = "_one";
				break;
			case R.id.skin_two_text :
				mSuffix = "_two";
				break;
			case R.id.skin_three_text :
				mSuffix = "_three";
				break;
			case R.id.skin_four_text :
				mSuffix = "_four";
				break;
		}
		refreshSkin();
	}
	
	private void refreshSkin() {
		SkinChangeHelper.getInstance().changeSkinByPackageSuffix("com.example.skinresource", mSuffix, new SkinChangeHelper.OnSkinChangeListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(InstalledActivity.this, "已安装的apk换肤成功", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onError() {
				Toast.makeText(InstalledActivity.this, "已安装的apk换肤失败，请确定皮肤包是否安装", Toast.LENGTH_LONG).show();
			}
		});
	}
}
