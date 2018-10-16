package com.example.farahreza.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClinicCancelAdapter  extends BaseAdapter implements Filterable {

    private Context activity;
    private ArrayList<AppointmentInfo> allStudent = new ArrayList<>();
    private ArrayList<AppointmentInfo> allStudent1 = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public ClinicCancelAdapter(Context activity, ArrayList<AppointmentInfo> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        allStudent1=allStudent;
        Collections.sort(allStudent, new Comparator<AppointmentInfo>() {
            @Override
            public int compare(AppointmentInfo t2, AppointmentInfo t1) {

                AppointmentInfo e1=(AppointmentInfo) t2;
                AppointmentInfo e2=(AppointmentInfo) t1;

                return  e1.getName().compareToIgnoreCase(e2.getName());

            }
        });
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Filter getFilter() {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                FilterResults results = new FilterResults();
                allStudent=allStudent1;

                if (charSequence == null || charSequence.length() == 0) {
                    results.values = allStudent;
                    results.count = allStudent.size();
                }
                else
                {
                    ArrayList<AppointmentInfo> filteredContacts = new ArrayList<AppointmentInfo>();

                    for (AppointmentInfo c : allStudent) {
                        if (c.name.toUpperCase().contains(charSequence.toString().toUpperCase())) {
                            // if `contains` == true then add it
                            // to our filtered list
                            filteredContacts.add(c);
                        }
                    }

                    results.values = filteredContacts;
                    results.count = filteredContacts.size();

                }
                return  results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                allStudent = (ArrayList<AppointmentInfo>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    private static class ViewHolder{
        private TextView name,docname,time,date;
    }
    private ClinicCancelAdapter.ViewHolder viewHolder = null;

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final int pos = position;
        if(view == null){
            viewHolder = new ClinicCancelAdapter.ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_student6,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.docname= view.findViewById(R.id.docname);
            viewHolder.date= view.findViewById(R.id.date);
            viewHolder.time= view.findViewById(R.id.time);

            /*viewHolder.email = view.findViewById(R.id.email);
            viewHolder.password= view.findViewById(R.id.pass);
            viewHolder.mobileNumber = view.findViewById(R.id.phn);
            viewHolder.gender=view.findViewById(R.id.gender);
            viewHolder.date=view.findViewById(R.id.date);
            viewHolder.timeslot3=view.findViewById(R.id.timeslot3);*/

            view.setTag(viewHolder);
        }else {
            viewHolder= (ClinicCancelAdapter.ViewHolder) view.getTag();
        }

        viewHolder.name.setText(allStudent.get(pos).getName());
        viewHolder.docname.setText(allStudent.get(pos).getDocname());
        viewHolder.date.setText(allStudent.get(pos).getDate());
        viewHolder.time.setText(allStudent.get(pos).getTime());

       /* viewHolder.email.setText(allStudent.get(pos).getGender());
        viewHolder.password.setText(allStudent.get(pos).getSpec());
        viewHolder.mobileNumber.setText(allStudent.get(pos).getCapacity());
        viewHolder.gender.setText(allStudent.get(pos).getTimeslot1());
        viewHolder.date.setText(allStudent.get(pos).getTimeslot2());
        viewHolder.date.setText(allStudent.get(pos).getTimeslot3());*/
        return view;
    }



    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public AppointmentInfo getItem(int position) {
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
            viewHolder = new ClinicCancelAdapter.ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_student6,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.docname= view.findViewById(R.id.docname);
            viewHolder.date= view.findViewById(R.id.date);
            viewHolder.time= view.findViewById(R.id.time);

            /*viewHolder.email = view.findViewById(R.id.email);
            viewHolder.password= view.findViewById(R.id.pass);
            viewHolder.mobileNumber = view.findViewById(R.id.phn);
            viewHolder.gender=view.findViewById(R.id.gender);
            viewHolder.date=view.findViewById(R.id.date);
            viewHolder.timeslot3=view.findViewById(R.id.timeslot3);*/

            view.setTag(viewHolder);
        }else {
            viewHolder= (ClinicCancelAdapter.ViewHolder) view.getTag();
        }

        viewHolder.name.setText("Patient Name:"+allStudent.get(pos).getName());
        viewHolder.docname.setText("Doctor Name:"+allStudent.get(pos).getDocname());
        viewHolder.date.setText("Appointment Date:"+allStudent.get(pos).getDate());
        viewHolder.time.setText("Time:"+allStudent.get(pos).getTime());

       /* viewHolder.email.setText(allStudent.get(pos).getGender());
        viewHolder.password.setText(allStudent.get(pos).getSpec());
        viewHolder.mobileNumber.setText(allStudent.get(pos).getCapacity());
        viewHolder.gender.setText(allStudent.get(pos).getTimeslot1());
        viewHolder.date.setText(allStudent.get(pos).getTimeslot2());
        viewHolder.date.setText(allStudent.get(pos).getTimeslot3());*/
        return view;
    }
}
