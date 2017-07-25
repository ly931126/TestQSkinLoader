package com.example.test.testqskinloader;

import org.qcode.qskinloader.sample.BaseActivity;

import com.example.test.testqskinloader.util.SkinChangeHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 通过指定包名、后缀查找已安装应用中的资源，切换字体大小

 */
public class ChangeTextSizeActivity extends BaseActivity {

    @BindView(R.id.text_one)
    Button mTextOne;
    @BindView(R.id.text_two)
    Button mTextTwo;
    @BindView(R.id.text_three)
    Button mTextThree;
    // 根据后缀名更换字体大小
    private String mSuffix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text_size);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text_one, R.id.text_two, R.id.text_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_one:
                mSuffix = "_small";
                break;
            case R.id.text_two:
                mSuffix = "_normal";
                break;
            case R.id.text_three:
                mSuffix = "_big";
                break;
        }
        changeTextSize(mSuffix);
    }

    private void changeTextSize(String suffix) {
        SkinChangeHelper.getInstance().changeSizeSkinByPackageSuffix(getPackageName(), suffix, new SkinChangeHelper.OnSkinChangeListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(ChangeTextSizeActivity.this, "文字大小更换成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError() {
                Toast.makeText(ChangeTextSizeActivity.this, "文字大小更换失败", Toast.LENGTH_LONG).show();
            }
        });
    }
}
