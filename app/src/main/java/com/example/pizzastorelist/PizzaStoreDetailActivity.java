package com.example.pizzastorelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pizzastorelist.databinding.ActivityPizzaStoreDetailBinding;
import com.example.pizzastorelist.datas.PizzaStore;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class PizzaStoreDetailActivity extends BaseActivity {

    PizzaStore store = null;
    ActivityPizzaStoreDetailBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pizza_store_detail);

        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.storelogoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, LogoViewActivity.class);
                intent.putExtra("logoUrl", store.getLogoUrl());
                startActivity(intent);

            }
        });


        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                사용자의 허가가 OK인 상황에서 실행하도록. => TedPermission

                PermissionListener pl = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        String phoneNumUri = String.format("tel:%s", store.getPhoneNum());

                        Uri uri = Uri.parse("tell:전화번호");
                        Intent intent = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
//                      권한을 사용자가 거부한 상황 => 전화 권한을 허가 해야 사용 가능합니다. Toast
                        Toast.makeText(mContext,"전화 권한을 실행해야 사용가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                };
//                onClick 실행? 사용자가 손으로 클릭 할때 실행
//                권한 대응 상황?

                TedPermission.with(mContext).setPermissionListener(pl).setDeniedMessage("[설정]에서 허용해줘야 사용가능합니다.")
                        .setPermissions(Manifest.permission.CALL_PHONE).check();


            }
        });

    }

    @Override
    public void setValues() {

        store = (PizzaStore) getIntent().getSerializableExtra("store");

        binding.storeNameTxt.setText(store.getStoreName());
        binding.storePhoneTxt.setText(store.getPhoneNum());
        Glide.with(mContext).load(store.getLogoUrl()).into(binding.storelogoImg);

    }


}
