package com.example.farahreza.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class studentAdapter extends BaseAdapter {
    private Context activity;
    private ArrayList<DoctorInfo> allStudent = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public studentAdapter(Context activity, ArrayList<DoctorInfo> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        Collections.sort(allStudent, new Comparator<DoctorInfo>() {
            @Override
            public int compare(DoctorInfo t2, DoctorInfo t1) {

                DoctorInfo e1=(DoctorInfo) t2;
                DoctorInfo e2=(DoctorInfo) t1;

                return  e1.getName().compareToIgnoreCase(e2.getName());

            }
        });
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView name, email, password, mobileNumber,gender,date,timeslot3;
    }
    private ViewHolder viewHolder = null;



    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public DoctorInfo getItem(int position) {
        return allStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final int pos = position;
        if(view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_student,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.password= view.findViewById(R.id.type);

            /*viewHolder.email = view.findViewById(R.id.email);
            viewHolder.password= view.findViewById(R.id.pass);
            viewHolder.mobileNumber = view.findViewById(R.id.phn);
            viewHolder.gender=view.findViewById(R.id.gender);
            viewHolder.date=view.findViewById(R.id.date);
            viewHolder.timeslot3=view.findViewById(R.id.timeslot3);*/

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(allStudent.get(pos).getName());
        viewHolder.password.setText(allStudent.get(pos).getSpec());

       /* viewHolder.email.setText(allStudent.get(pos).getGender());
        viewHolder.password.setText(allStudent.get(pos).getSpec());
        viewHolder.mobileNumber.setText(allStudent.get(pos).getCapacity());
        viewHolder.gender.setText(allStudent.get(pos).getTimeslot1());
        viewHolder.date.setText(allStudent.get(pos).getTimeslot2());
        viewHolder.date.setText(allStudent.get(pos).getTimeslot3());*/
        return view;
    }


}
