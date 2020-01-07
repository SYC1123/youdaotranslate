package com.example.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class LanguageAdapter extends ArrayAdapter<LanguageItem> {
    private int resourceId;

    public LanguageAdapter(Context context, int resourceid, List<LanguageItem> objects) {
        super(context, resourceid, objects);
        resourceId = resourceid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LanguageItem item = getItem(position); // 获取当前项的Fruit实例
        View view;
//提升ListView的运行效率，可以看到，现在我们在 getView()方法中进行了判断，如果 convertView为空，则使用LayoutInflater去加载布局，如果不为空则直接对 convertView进行重用。这样就大大提高了 ListView的运行效率，在快速滚动的时候也可以表现出更好的性能。
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }

        ImageView fruitImage = (ImageView) view.findViewById(R.id.lang_image);
        TextView fruitName = (TextView) view.findViewById(R.id.lang_name);
        fruitImage.setImageResource(item.getImageId());
        fruitName.setText(item.getName());
        return view;
    }

}



