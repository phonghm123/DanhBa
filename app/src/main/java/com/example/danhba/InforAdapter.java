package com.example.danhba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class InforAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<ThongTin> lst;
    public InforAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lst = objects;
    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_layout, null);
            //map du lieu trong item
            TextView tvId = convertView.findViewById(R.id.itemTvId);
            TextView tvName = convertView.findViewById(R.id.itemTvName);
            TextView tvNumber = convertView.findViewById(R.id.itemTvNumber);
            ThongTin thongTin = new ThongTin();
            thongTin = lst.get(position);
            tvId.setText(thongTin.getMa() + "");
            tvName.setText(thongTin.getName());
            tvNumber.setText(thongTin.getNumber());
            return convertView;
    }
}
