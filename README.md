第一部分     APK内带后缀名的换肤
--------------------

- 注：[参考链接@zhangwei](https://github.com/VeiZhang/QSkinLoader)
- [参考链接@fengtianyou](https://github.com/fengtianyou)
- [参考链接@QQLiu](https://github.com/qqliu10u/QSkinLoader)
### 1.添加QSkinLoader的依赖

- 具体如下：
```
AndroidStudio添加jCenter远程依赖到module里的build.gradle：

dependencies {
    compile 'com.excellence:skinloader:1.2.2'
    // 或者直接使用最新版本
    // compile 'com.excellence:skinloader:+'

        compile 'com.excellence:basetools:1.2.2'
}

```
### 2.将需要换肤的view继承BaseActivity ,如XXActivity
- 注：BaseActivity在依赖中已经存在，不用重写
### 3.在需要换肤的view的布局中添加属性
- 具体如下
```
xml中添加标识

skin的命名空间

xmlns:skin="http://schemas.android.com/android/skin"
然后对所有需要换肤的View增加属性

<View
    skin:enable="true"
/>
```

### 4.创建自己的Application 继承Application ,并在清单文件中调用，否则会报空指针异常
- [示例自定义Application的具体内容如下](http://note.youdao.com/noteshare?id=8a127080d7bf036c5110468608193c6d)

### 5.创建SkinChangeHelper和SkinConfigHelper 用作换肤的辅助类
- [SkinChangeHelper](http://note.youdao.com/noteshare?id=963577007153f36c52aa25de1d5b96ca)
- [SkinConfigHelper](http://note.youdao.com/noteshare?id=6ac307700138865a861ed27f4a5094cb)

### 6.在代码中换肤功能的实现
```
public class CurrentActivity extends BaseActivity {

    @BindView(R.id.image_btn)
    ImageButton mImageBtn;

    private SkinChangeHelper mSkinChangeHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
mSkinChangeHelper = SkinChangeHelper.getInstance();
        ButterKnife.bind(this);
    }


/**
在点击事件中切换默认皮肤和需要更换的皮肤
*/
    @OnClick(R.id.image_btn)
    public void onClick() {
    		boolean isDefaultSkin = mSkinChangeHelper.isDefaultMode();

        if (isDefaultSkin) {
        //根据后缀名更换皮肤， “”引号内的内容为要更换新的皮肤时，新皮肤的后缀名，很重要，在更换字体颜色和图片时后缀必须是这个；
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
        //恢复默认皮肤
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
```
### 7. 布局的代码详情
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:skin="http://schemas.android.com/android/skin"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical"
              skin:enable="true">

    <ImageButton
        android:id="@+id/image_btn"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true"
        android:adjustViewBounds="true"
        android:background="@color/image_color"
        android:padding="50dp"
        android:scaleType="fitXY"
        android:src="@drawable/girlfly"
        skin:enable="true"/>

</LinearLayout>
```
### 8.在Values中的注意事项
#### （1）在colors.xml中，注意新皮肤的颜色值必须带着后缀名，例如：
```
//默认的颜色值
<color name="image_color">#0accac</color>
//新皮肤的颜色值
    <color name="image_color_change">#a0a0a0</color>
```
#### (2)在drawable文件夹中，注意新皮肤的图片资源要带后缀名

如
```
默认皮肤图片资源
girlfly.png
新皮肤图片资源
girlfly_change.png
```
第二部分  通过已安装的apk换肤
---
### 1.在项目中新建一个Moudle作为皮肤包 ,[方法参照](http://blog.csdn.net/qq_35902556/article/details/53422771)
#### （1）皮肤包清单文件中做以下修改即可
```
<?xml version="1.0" encoding="utf-8"?>
<manifest package="com.example.skinresource"
          xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <!--<action android:name="android.intent.action.MAIN"/>-->

                <!--<category android:name="android.intent.category.LAUNCHER"/>-->
                <category android:name="android.intent.category.DEFAULT"/>

            </intent-filter>
        </activity>
    </application>

</manifest>
```
#### (2)准备需要换肤的背景资源，在drawable中如
```
//默认的皮肤背景
installed_apk_bg.png

installed_apk_bg_one.png
installed_apk_bg_two.png
installed_apk_bg_three.png
installed_apk_bg_four.png
```
#### (3)准备需要换肤的字体颜色文件
- 注：（有必要的话也可在values的strings.xml文件中准备需要换肤的文字内容）
- 在colors.xml文件中设置，具体如下
```
//默认皮肤的文字颜色
 <color name="installed_apk_text_color">#0acaca</color>
 //需要更换皮肤的文字颜色
    <color name="installed_apk_text_color_one">#adadad</color>
    <color name="installed_apk_text_color_two">#FF4081</color>
    <color name="installed_apk_text_color_three">#01b</color>
    <color name="installed_apk_text_color_four">#000</color>
```
### 2.调用的项目中
### (1)资源文件也要设置默认的背景，字体颜色，字体内容等，否则在布局中找不到
分别是：
```
installed_apk_bg.png
<color name="installed_apk_text_color">#0acaca</color>
```
### (2)布局文件如下所示，要在需要更换皮肤的view中添加skin:enable="true";
在layout的顶部加上
```
xmlns:skin="http://schemas.android.com/android/skin"
```
属性
- 布局文件如下：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:skin="http://schemas.android.com/android/skin"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:background="@drawable/installed_apk_bg"
              android:orientation="horizontal"
              skin:enable="true">

    <Button
        android:id="@+id/default_skin_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        skin:enable="true"
        android:gravity="center"
        android:layout_weight="1"
        android:text="@string/installed_apk_text_content"
        android:textColor="@color/installed_apk_text_color"/>

    <Button
        android:id="@+id/skin_one_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        skin:enable="true"
        android:layout_weight="1"
        android:gravity="center"
        android:text="皮肤一"
        android:textColor="@color/installed_apk_text_color"/>

    <Button
        android:id="@+id/skin_two_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        skin:enable="true"
        android:gravity="center"
        android:layout_weight="1"
        android:text="皮肤二"
        android:textColor="@color/installed_apk_text_color"/>

    <Button
        android:id="@+id/skin_three_text"
        skin:enable="true"
        android:layout_weight="1"
        android:gravity="center"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="皮肤三"
        android:textColor="@color/installed_apk_text_color"/>

    <Button
        android:id="@+id/skin_four_text"
        android:gravity="center"
        android:layout_width="wrap_content"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        skin:enable="true"
        android:text="皮肤四"
        android:textColor="@color/installed_apk_text_color"/>

</LinearLayout>
```
#### 3.在代码中的使用如下
```
/**
 * Created by liye on 2017/7/24. 通过已安装的apk换肤
 通过包名、后缀，查找指定的已安装应用中的资源，进行换肤
 */
public class InstalledActivity extends BaseActivity {

    @BindView(R.id.default_skin_text)
    Button mDefaultSkinText;
    @BindView(R.id.skin_one_text)
    Button mSkinOneText;
    @BindView(R.id.skin_two_text)
    Button mSkinTwoText;
    @BindView(R.id.skin_three_text)
    Button mSkinThreeText;
    @BindView(R.id.skin_four_text)
    Button mSkinFourText;
    // 后缀
    private String mSuffix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.default_skin_text, R.id.skin_one_text, R.id.skin_two_text, R.id.skin_three_text, R.id.skin_four_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.default_skin_text:
                mSuffix = "";
                break;
            case R.id.skin_one_text:
                mSuffix = "_one";
                break;
            case R.id.skin_two_text:
                mSuffix = "_two";
                break;
            case R.id.skin_three_text:
                mSuffix = "_three";
                break;
            case R.id.skin_four_text:
                mSuffix = "_four";
                break;
        }
        refreshSkin();
    }


    private void refreshSkin() {
//通过皮肤包的包名，和文字颜色，图片的相同后缀实现换肤，调用 SkinChangeHelper.getInstance().changeSkinByPackageSuffix（）方法
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
```
第三部分  对字体大小进行更换
---
### 1.在values的dimens.xml中设置字体大小，如下
```
//设置默认字体大小
<dimen name="textsize">24sp</dimen>

//设置更换字体大小
    <dimen name="textsize_small">10sp</dimen>
    <dimen name="textsize_normal">30sp</dimen>
    <dimen name="textsize_big">60sp</dimen>

```
### 2.布局如下
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    android:id="@+id/activity_change_text_size"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:skin="http://schemas.android.com/android/skin"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.test.testqskinloader.ChangeTextSizeActivity">

    <Button
        android:id="@+id/text_one"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="@dimen/margin_huge"
        android:text="@string/good_morning"
        android:gravity="center"
        android:textColor="@android:color/holo_green_light"
        android:textSize="@dimen/textsize"
        skin:enable="true"/>

    <Button
        android:id="@+id/text_two"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/text_one"
        android:layout_centerHorizontal="true"
        android:layout_centerInParent="true"
        android:gravity="center"
        android:text="@string/good_afternoon"
        android:textColor="@android:color/holo_green_light"
        android:textSize="@dimen/textsize"
        skin:enable="true"/>


    <Button
        android:id="@+id/text_three"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/text_two"
        android:layout_centerHorizontal="true"
        android:gravity="center"
        android:layout_marginBottom="@dimen/margin_huge"
        android:text="@string/good_evenning"
        android:textColor="@android:color/holo_green_light"
        android:textSize="@dimen/textsize"
        skin:enable="true"/>

</RelativeLayout>
```
### 3.代码实现界面如下
```
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
    //调用SkinChangeHelper.getInstance().changeSizeSkinByPackageSuffix（）方法改变文字的大小
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
```
第四部分  换肤切换语言
---
### 1. 在点击之后切换语言配置
```
首先切换语言配置

Configuration config = getResources().getConfiguration();
switch (position)
{
case 0:
    config.locale = Locale.ENGLISH;
    break;

case 1:
    config.locale = Locale.CHINESE;
    break;
}
getResources().updateConfiguration(config, getResources().getDisplayMetrics());
```
### 2.接着调用skinHelper中的方法切换语言

```
//通过指定包名、后缀查找已安装应用中的资源，切换语言
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
```

