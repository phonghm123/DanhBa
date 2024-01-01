package com.example.danhba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ThongTin> lstDanhBa = new ArrayList();
    InforAdapter inforAdapter;
    private ListView lsvDanhBa;
    private EditText edtTen, edtSDT, edtMa, edtSearch;
    private Button btnThem, btnSua, btnBack;
    int viTri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
        lstDanhBa.add(new ThongTin(1, "Phong1", "0929282"));
        lstDanhBa.add(new ThongTin(2, "Trinh Dinh Quang", "02020202"));
        inforAdapter = new InforAdapter(this, 1, lstDanhBa);
        lsvDanhBa.setAdapter(inforAdapter);
//        Intent myIntent = new Intent(MainActivity.this, ChildActivity.class);
//        startActivity(myIntent);
//        // Nhan intent
//        Intent myIntentt = new Intent();
        // lay bundle ra khoi intent
//        Bundle myBundle = getIntent().getBundleExtra("myInfor");
//        // lay du lieu trong bundle
//        int ma =myBundle.getInt("ma");
//        String ten = myBundle.getString("ten");
//        String sdt = myBundle.getString("number");
//        ThongTin thongTin = new ThongTin(ma,ten,sdt);
//        lstDanhBa.add(thongTin);
//        inforAdapter.notifyDataSetChanged();


        lsvDanhBa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lstDanhBa.remove(position);
                inforAdapter.notifyDataSetChanged();
                return false;
            }
        });
        lsvDanhBa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtMa.setText(lstDanhBa.get(position).getMa() + "");
                edtTen.setText(lstDanhBa.get(position).getName().toString());
                edtSDT.setText(lstDanhBa.get(position).getNumber().toString());
                viTri = position;
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = edtSearch.getText().toString().trim().toLowerCase();
                ArrayList<ThongTin> filteredList = new ArrayList<>();

                // Lọc danh sách theo số điện thoại hoặc tên
                for (ThongTin thongTin : lstDanhBa) {
                    String ten = thongTin.getName().toLowerCase();
                    String sdt = thongTin.getNumber().toLowerCase();

                    if (ten.contains(searchText) || sdt.contains(searchText)) {
                        filteredList.add(thongTin);
                    }
                }

                // Hiển thị danh sách đã lọc trong ListView
                inforAdapter = new InforAdapter(MainActivity.this, 1, filteredList);
                lsvDanhBa.setAdapter(inforAdapter);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenIntent = new Intent(MainActivity.this, ChildActivity.class);
                startActivityForResult(chuyenIntent,1);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongTin t = new ThongTin(Integer.parseInt(edtMa.getText().toString()), edtTen.getText().toString(), edtSDT.getText().toString());
                lstDanhBa.set(viTri, t);
                inforAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle myBundle = data.getBundleExtra("myInfor");
                int ma = myBundle.getInt("ma");
                String ten = myBundle.getString("ten");
                String sdt = myBundle.getString("number");
                ThongTin thongTin = new ThongTin(ma, ten, sdt);
                lstDanhBa.add(thongTin);
                inforAdapter.notifyDataSetChanged();
            }
        }



    }

    private void map() {

        lsvDanhBa = findViewById(R.id.lsvDanhBa);
        edtSearch = findViewById(R.id.edtSearch);
        edtMa = findViewById(R.id.edtMa);
        edtSDT = findViewById(R.id.edtSDT);
        edtTen = findViewById(R.id.edtTen);
        btnSua = findViewById(R.id.btnSua);
        btnThem = findViewById(R.id.btnThem);
        btnBack = findViewById(R.id.btnBack);

    }
}