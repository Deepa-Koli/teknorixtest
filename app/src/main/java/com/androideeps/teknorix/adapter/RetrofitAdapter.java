package com.androideeps.teknorix.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androideeps.teknorix.R;
import com.androideeps.teknorix.activities.DetailActivity;
import com.androideeps.teknorix.models.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RetrofitAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Datum> dataModelArrayList;

    public RetrofitAdapter(Context context, ArrayList<Datum> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_model, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvcountry = (TextView) convertView.findViewById(R.id.country);
            holder.tvcity = (TextView) convertView.findViewById(R.id.city);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(dataModelArrayList.get(position).getAvatar()).into(holder.iv);
        holder.tvname.setText(dataModelArrayList.get(position).getEmail());
        holder.tvcountry.setText(dataModelArrayList.get(position).getFirstName());
        holder.tvcity.setText(dataModelArrayList.get(position).getLastName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("avatar",dataModelArrayList.get(position).getAvatar());
                intent.putExtra("email", dataModelArrayList.get(position).getEmail());
                intent.putExtra("first_name",dataModelArrayList.get(position).getFirstName());
                intent.putExtra("last_name",dataModelArrayList.get(position).getLastName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvname, tvcountry, tvcity;
        protected ImageView iv;
    }
}