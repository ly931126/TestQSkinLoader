package com.example.test.testqskinloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.qcode.qskinloader.sample.BaseActivity;

import com.example.test.testqskinloader.util.SkinChangeHelper;
import com.excellence.basetoolslibrary.baseadapter.CommonAdapter;
import com.excellence.basetoolslibrary.baseadapter.ViewHolder;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeLanActivity extends BaseActivity {

    @BindView(R.id.chinese_btn)
    Button mChineseBtn;
    @BindView(R.id.english_btn)
    Button mEnglishBtn;
    @BindView(R.id.gridview)
    GridView mGridview;

    private List<String> mGridViewData = null;
    private Configuration mConfiguration = null;
    private boolean isTouchLeft = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_lan);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.chinese_btn, R.id.english_btn})
    public void onClick(View view) {
        mConfiguration = getResources().getConfiguration();
        switch (view.getId()) {
            case R.id.chinese_btn:
                isTouchLeft = true;
                judgeLocale(isTouchLeft);
                break;
            case R.id.english_btn:
                isTouchLeft = false;
                judgeLocale(isTouchLeft);
                break;
        }

        updateLan(mConfiguration);
        initGridView(mConfiguration);

    }

    private void judgeLocale(boolean isTouch) {
        if (isTouch) {
            mConfiguration.locale = Locale.CHINESE;
        } else {
            mConfiguration.locale = Locale.ENGLISH;
        }
    }

    private void initGridView(Configuration configuration) {
        updateLan(configuration);
        mGridViewData = new ArrayList<>();
        mGridViewData.add(0, getResources().getString(R.string.good_morning));
        mGridViewData.add(1, getResources().getString(R.string.good_afternoon));
        mGridViewData.add(2, getResources().getString(R.string.good_evening));

        mGridview.setAdapter(new LangAdapter(this, mGridViewData, R.layout.item_lang_gridview));
    }

    private class LangAdapter extends CommonAdapter<String> {

        public LangAdapter(Context context, List<String> datas, @LayoutRes int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(ViewHolder viewHolder, String item, int position) {
            viewHolder.setText(R.id.text, item);
        }
    }


    private void updateLan(Configuration configuration) {
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        SkinChangeHelper.getInstance().changeLanguageConfigByPackageSuffix(getPackageName(), "", new SkinChangeHelper.OnSkinChangeListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(ChangeLanActivity.this, "语言切换成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(ChangeLanActivity.this, "语言切换失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
