package com.example.test.testqskinloader;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.test.testqskinloader.util.SkinChangeHelper;

import org.qcode.qskinloader.sample.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liye on 2017/7/24.
 */
public class CurrentActivity extends BaseActivity {

	@BindView(R.id.image_btn)
	ImageButton					mImageBtn;

	private SkinChangeHelper	mSkinChangeHelper	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current);
		mSkinChangeHelper = SkinChangeHelper.getInstance();
		ButterKnife.bind(this);
	}

	@OnClick(R.id.image_btn)
	public void onClick() {
		boolean isDefaultSkin = mSkinChangeHelper.isDefaultMode();
		if (isDefaultSkin) {
			mSkinChangeHelper.changeSkinBySuffix("_change", new SkinChangeHelper.OnSkinChangeListener() {
				@Override
				public void onSuccess() {
					Toast.makeText(CurrentActivity.this, "换肤成功", Toast.LENGTH_LONG).show();
				}

				@Override
				public void onError() {
					Toast.makeText(CurrentActivity.this, "换肤失败", Toast.LENGTH_LONG).show();
				}
			});
		} else {
			mSkinChangeHelper.restoreDefaultSkinByAPKOrPackageOrSuffix(new SkinChangeHelper.OnSkinChangeListener() {
				@Override
				public void onSuccess() {
					Toast.makeText(CurrentActivity.this, "切换默认皮肤成功", Toast.LENGTH_LONG).show();

				}

				@Override
				public void onError() {
					Toast.makeText(CurrentActivity.this, "切换默认皮肤失败", Toast.LENGTH_LONG).show();

				}
			});
		}
	}
}
