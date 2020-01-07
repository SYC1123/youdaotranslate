package com.example.translate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String APP_ID = "20200105000372904";
    private static final String SECURITY_KEY = "tfRk5tW566V2znagDjDu";
    private androidx.constraintlayout.widget.ConstraintLayout mFromlayout;
    private ImageView mImageView;
    private TextView mFrom;
    private ImageView mExchange;
    private androidx.constraintlayout.widget.ConstraintLayout mTolayout;
    private TextView mTo;
    private EditText mSrc;
    private Button mTrans;
    private ListView listView;
    private List<MyResult> myResults = new ArrayList<>();
    private boolean flag = true;
    private HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //修改系统策略，放开所有的权限
//代码添加到onCreate回调方法中即可
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        initMap();
        bindViews();
        ResultAdapter resultAdapter = new ResultAdapter(MainActivity.this, R.layout.result_item, myResults);
        listView = findViewById(R.id.listview);
        listView.setAdapter(resultAdapter);
        mTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = mSrc.getText().toString();
                Log.d("MainAcvitity", "src is " + src);
                TransApi api = new TransApi(APP_ID, SECURITY_KEY);
                if (hashMap.get(mTo.getText().toString()).equals("自动检测语言")) {
                    Toast.makeText(MainActivity.this, "被翻译语言不可为自动检测", Toast.LENGTH_SHORT).show();
                } else {
                    String json = api.getTransResult(src, hashMap.get(mFrom.getText().toString()), hashMap.get(mTo.getText().toString()));
                    String result = null;
                    String original = null;
                    Log.d("MainAcvitity", "Json is " + json);
                    Gson gson = new Gson();//创建GSON实例
                    TranslateResult appList = gson.fromJson(json, TranslateResult.class);//传入数据开始解析
                    Log.d("MainAcvitity", "from is " + appList.getFrom());
                    Log.d("MainAcvitity", "to is " + appList.getTo());
                    Log.d("MainAcvitity", "result is " + appList.getTrans_result());
                    for (int i = 0; i < appList.getTrans_result().size(); i++) {
                        Log.d("MainAcvitity", "原文 is " + appList.getTrans_result().get(i).getSrc());
                        Log.d("MainAcvitity", "翻译 is " + appList.getTrans_result().get(i).getDst());
                        result = appList.getTrans_result().get(i).getDst();
                        original = appList.getTrans_result().get(i).getSrc();
                        Log.d("MainAcvitity", "翻译 is " + result);
                    }
                    MyResult myResult = new MyResult(original, result);
                    myResults.add(0, myResult);
                    ResultAdapter resultAdapter = new ResultAdapter(MainActivity.this, R.layout.result_item, myResults);
                    listView.setAdapter(resultAdapter);
                }
            }
        });
        mFromlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
                intent.putExtra("Language", mFrom.getText().toString());
                intent.putExtra("back", "from");
                startActivityForResult(intent, 0);
            }
        });
        mTolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
                intent.putExtra("Language", mTo.getText().toString());
                intent.putExtra("back", "to");
                startActivityForResult(intent, 1);
            }
        });
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = mFrom.getText().toString();
                mFrom.setText(mTo.getText().toString());
                mTo.setText(temp);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {                                        // 判断操作类型
            case RESULT_OK:
                if (data.getStringExtra("back").equals("from")) {
                    mFrom.setText(data.getStringExtra("lag"));
                } else {
                    mTo.setText(data.getStringExtra("lag"));
                }
                break;
            default:
                break;
        }
    }

    public void initMap() {
        hashMap.put("自动检测语言", "");
        hashMap.put("中文", "zh");
        hashMap.put("英文", "en");
        hashMap.put("文言文", "wyw");
        hashMap.put("日语", "jp");
        hashMap.put("韩语", "kor");
        hashMap.put("法语", "fra");
        hashMap.put("西班牙语", "spa");
        hashMap.put("泰语", "th");
        hashMap.put("阿拉伯语", "ara");
        hashMap.put("俄语", "ru");
        hashMap.put("葡萄牙语", "pt");
        hashMap.put("德语", "de");
        hashMap.put("意大利语", "it");
        hashMap.put("希腊语", "el");
        hashMap.put("保加利亚语", "bul");
        hashMap.put("爱沙尼亚语", "est");
        hashMap.put("丹麦语", "dan");
        hashMap.put("芬兰语", "fin");
        hashMap.put("捷克语", "cs");
        hashMap.put("瑞典语", "swe");
        hashMap.put("繁体中文", "cht");
        hashMap.put("越南语", "vie");
    }

    private void bindViews() {
        mFromlayout = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.fromlayout);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mFrom = (TextView) findViewById(R.id.from);
        mExchange = (ImageView) findViewById(R.id.exchange);
        mTolayout = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.tolayout);
        mTo = (TextView) findViewById(R.id.to);
        mSrc = (EditText) findViewById(R.id.src);
        mTrans = (Button) findViewById(R.id.trans);
    }
}
