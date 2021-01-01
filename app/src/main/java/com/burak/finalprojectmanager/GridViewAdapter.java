package com.burak.finalprojectmanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    ArrayList<Internship> incomingIntern = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public GridViewAdapter(Activity activity, ArrayList<Internship> incomingIntern){
        this.incomingIntern=incomingIntern;
        this.context=activity;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return incomingIntern.size();
    }

    @Override
    public Internship getItem(int position) {
        return incomingIntern.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View create = layoutInflater.inflate(R.layout.gridviewlayout,null);

        TextView tv_id=(TextView) create.findViewById(R.id.tv_id);
        TextView tv_name=(TextView) create.findViewById(R.id.tv_name);
        TextView tv_class_no=(TextView) create.findViewById(R.id.tv_class_no);
        TextView tv_mail=(TextView) create.findViewById(R.id.tv_mail);
        TextView tv_age=(TextView) create.findViewById(R.id.tv_age);
        TextView tv_sector=(TextView) create.findViewById(R.id.tv_sector);
        TextView tv_acceptance=(TextView) create.findViewById(R.id.tv_acceptance);

        int queue=position+1;

        tv_id.setText(String.valueOf("id: "+queue));
        tv_name.setText(incomingIntern.get(position).getName());
        tv_class_no.setText(incomingIntern.get(position).getClass_no());
        tv_mail.setText(incomingIntern.get(position).getMail());
        tv_age.setText(incomingIntern.get(position).getAge());
        tv_sector.setText(incomingIntern.get(position).getSector());
        tv_acceptance.setText(incomingIntern.get(position).getAcceptance());

        return create;
    }
}
