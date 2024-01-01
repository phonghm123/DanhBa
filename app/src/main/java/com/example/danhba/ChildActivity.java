package com.example.danhba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ChildActivity extends AppCompatActivity {
    private Button btnAdd , btnList;
    private EditText edtId, edtName, edtNumber;
    ArrayList<ThongTin> lstDanhBa = new ArrayList<>();
    InforAdapter inforAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        map();
        Intent myIntent = new Intent(ChildActivity.this, MainActivity.class);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khai bao cac bien
                int id = Integer.parseInt(edtId.getText().toString());
                String name =edtName.getText().toString();
                String number = edtNumber.getText().toString();
                //tao bundle de luu du lieu
                Bundle lstBundle = new Bundle();
                //dua du lieu vao trong bundle
                lstBundle.putInt("ma",id);
                lstBundle.putString("ten",name);
                lstBundle.putString("number", number);
                // dua du lieu vao trong intent
                myIntent.putExtra("myInfor",lstBundle);
                setResult(RESULT_OK, myIntent);
                finish();
//            ThongTin thongTin =new ThongTin(id,name,number);
//            lstDanhBa.add(thongTin);
//                inforAdapter.notifyDataSetChanged();
//                startActivity(myIntent);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(myIntent);
            }
        });
    }
    private void map(){

        btnAdd = findViewById(R.id.btnAdd);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        btnList = findViewById(R.id.btnList);

    }
}