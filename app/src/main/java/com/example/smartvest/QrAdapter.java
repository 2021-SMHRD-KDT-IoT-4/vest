package com.example.smartvest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class QrAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<QrVO> data;
    LayoutInflater inflater;
    ViewHolder viewHolder;

    public QrAdapter(Context context, int layout, List<QrVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // converView가 안 만들어져 있다면,
            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();

            // xml파일에 부여한 id를 초기화!
            viewHolder.tv_w_name = convertView.findViewById(R.id.tv_w_name);
            viewHolder.tv_inout = convertView.findViewById(R.id.tv_inout);
            viewHolder.tv_edu = convertView.findViewById(R.id.tv_edu);
            viewHolder.tv_limit = convertView.findViewById(R.id.tv_limit);
            viewHolder.tv_live = convertView.findViewById(R.id.tv_live);


            // convertView가 만들어져 있는지 판단하기 위한 도구
            convertView.setTag(viewHolder);

        }

        viewHolder = (ViewHolder) convertView.getTag();

        // 가져온거 직접 데이터 사이즈만큼 업데이트 해주기
        viewHolder.tv_w_name.setText(data.get(position).getTv_w_name());
        viewHolder.tv_inout.setText(data.get(position).getTv_inout());
        viewHolder.tv_edu.setText(data.get(position).getTv_edu());
        viewHolder.tv_limit.setText(data.get(position).getTv_limit());
        viewHolder.tv_live.setText(data.get(position).getTv_live());



        return convertView;


    }
}
