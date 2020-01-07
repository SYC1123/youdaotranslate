package com.example.translate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {
    private List<LanguageItem> languageItems = new ArrayList<>();
    private ImageView image;
    private Button ok;
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private String back;
    private LanguageItem languageItem;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        initlanguage();
        initMap();
        Intent intent = getIntent();
        final String language = intent.getStringExtra("Language");
        back=intent.getStringExtra("back");
        LanguageAdapter languageAdapter = new LanguageAdapter(LanguageActivity.this, R.layout.lang_item, languageItems);
        ListView listView = findViewById(R.id.lang_list);
        listView.setAdapter(languageAdapter);
        ok = findViewById(R.id.ok);
        //right=findViewById(R.id.image_right);
        image = findViewById(R.id.image_left);
        image.setImageResource(hashMap.get(language));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                languageItem=languageItems.get(position);
                image.setImageResource(languageItem.getImageId());
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back", back);// 返回信息
                intent.putExtra("lag",languageItem.getName());
                // 设置返回数据的状态，RESULT_OK与Send.java中的onActivityResult()里判断的对应
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    public void initMap() {
        hashMap.put("自动检测语言", R.drawable.ai);
        hashMap.put("中文", R.drawable.zh);
        hashMap.put("英文", R.drawable.en);
        hashMap.put("文言文", R.drawable.wyw);
        hashMap.put("日语", R.drawable.jp);
        hashMap.put("韩语", R.drawable.kor);
        hashMap.put("法语", R.drawable.fra);
        hashMap.put("西班牙语", R.drawable.spa);
        hashMap.put("泰语", R.drawable.th);
        hashMap.put("阿拉伯语", R.drawable.ara);
        hashMap.put("俄语", R.drawable.ru);
        hashMap.put("葡萄牙语", R.drawable.pt);
        hashMap.put("德语", R.drawable.de);
        hashMap.put("意大利语", R.drawable.it);
        hashMap.put("希腊语", R.drawable.el);
        hashMap.put("保加利亚语", R.drawable.bul);
        hashMap.put("爱沙尼亚语", R.drawable.est);
        hashMap.put("丹麦语", R.drawable.dan);
        hashMap.put("芬兰语", R.drawable.fin);
        hashMap.put("捷克语", R.drawable.cs);
        hashMap.put("瑞典语", R.drawable.swe);
        hashMap.put("繁体中文", R.drawable.cht);
        hashMap.put("越南语", R.drawable.vie);
    }

    public void initlanguage() {
        LanguageItem item = new LanguageItem("自动检测语言", R.drawable.ai);
        languageItems.add(item);
        LanguageItem item1 = new LanguageItem("中文", R.drawable.zh);
        languageItems.add(item1);
        LanguageItem item2 = new LanguageItem("英文", R.drawable.en);
        languageItems.add(item2);
        LanguageItem item3 = new LanguageItem("文言文", R.drawable.wyw);
        languageItems.add(item3);
        LanguageItem item4 = new LanguageItem("日语", R.drawable.jp);
        languageItems.add(item4);
        LanguageItem item5 = new LanguageItem("韩语", R.drawable.kor);
        languageItems.add(item5);
        LanguageItem item6 = new LanguageItem("法语", R.drawable.fra);
        languageItems.add(item6);
        LanguageItem item7 = new LanguageItem("西班牙语", R.drawable.spa);
        languageItems.add(item7);
        LanguageItem item8 = new LanguageItem("泰语", R.drawable.th);
        languageItems.add(item8);
        LanguageItem item9 = new LanguageItem("阿拉伯语", R.drawable.ara);
        languageItems.add(item9);
        LanguageItem item10 = new LanguageItem("俄语", R.drawable.ru);
        languageItems.add(item10);
        LanguageItem item11 = new LanguageItem("葡萄牙语", R.drawable.pt);
        languageItems.add(item11);
        LanguageItem item12 = new LanguageItem("德语", R.drawable.de);
        languageItems.add(item12);
        LanguageItem item13 = new LanguageItem("意大利语", R.drawable.it);
        languageItems.add(item13);
        LanguageItem item14 = new LanguageItem("希腊语", R.drawable.el);
        languageItems.add(item14);
        LanguageItem item15 = new LanguageItem("保加利亚语", R.drawable.bul);
        languageItems.add(item15);
        LanguageItem item16 = new LanguageItem("爱沙尼亚语", R.drawable.est);
        languageItems.add(item16);
        LanguageItem item17 = new LanguageItem("丹麦语", R.drawable.dan);
        languageItems.add(item17);
        LanguageItem item18 = new LanguageItem("芬兰语", R.drawable.fin);
        languageItems.add(item18);
        LanguageItem item19 = new LanguageItem("捷克语", R.drawable.cs);
        languageItems.add(item19);
        LanguageItem item20 = new LanguageItem("瑞典语", R.drawable.swe);
        languageItems.add(item20);
        LanguageItem item21 = new LanguageItem("繁体中文", R.drawable.cht);
        languageItems.add(item21);
        LanguageItem item22 = new LanguageItem("越南语", R.drawable.vie);
        languageItems.add(item22);
    }
}
