package com.leatry.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private EditText mEt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_phone = findViewById(R.id.et_phone);
        Button bt_dail = findViewById(R.id.bt_dail);
        bt_dail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }else{
                    callphone();
                }
            }
        });
    }



    public void callphone(){
        String phone = mEt_phone.getText().toString().trim();
            System.out.println(phone);
            if ("".equals(phone)){ //equals比较的是内容。 == 比较的是地址
                Toast.makeText(MainActivity.this,"不能空",Toast.LENGTH_SHORT).show();
            }else{
                //1.创建一个意图对象
                Intent intent = new Intent();
                //2.设置动作
                intent.setAction(Intent.ACTION_CALL);
                //3.指定动作的数据
                intent.setData(Uri.parse("tel://" + phone));
                //4.开启一个界面，根据程序员指定的行为做事儿
                startActivity(intent);
            }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                callphone();
            } else {
                Toast.makeText(MainActivity.this, "电话授权失败了", Toast.LENGTH_SHORT).show();
            }
        }

    }
}