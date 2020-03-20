package com.example.pizzastorelist.adapter;

// 어댑터 클래스의 뼈대 작업.
// 생성자 / 사용변수 3개 / getView 오버라이딩

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.pizzastorelist.R;
import com.example.pizzastorelist.datas.PizzaStore;

import java.util.List;

public class PizzaStoreAdapter extends ArrayAdapter<PizzaStore> {

    Context mContext;
    List<PizzaStore> mList;
    LayoutInflater inf;


    public PizzaStoreAdapter(@NonNull Context context, int resource, @NonNull List<PizzaStore> objects) {
        super(context, resource, objects);

        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row =convertView;

        if(row == null) {
            row = inf.inflate(R.layout.pizza_store_list_item,null);
        }

        PizzaStore data = mList.get(position);

        ImageView logoImg = row.findViewById(R.id.logoImg);
        TextView nameTxt = row.findViewById(R.id.nameTxt);

        nameTxt.setText(data.getStoreName());

        Glide.with(mContext).load(data.getLogoUrl()).into(logoImg);


        return row;
    }
}
