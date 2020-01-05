package com.example.translate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
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
                String json = api.getTransResult(src, "auto", "zh");
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
                myResults.add(0,myResult);
                ResultAdapter resultAdapter = new ResultAdapter(MainActivity.this, R.layout.result_item, myResults);
                listView.setAdapter(resultAdapter);
            }
        });


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
