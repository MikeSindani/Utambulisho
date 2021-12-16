package com.example.utambulishoversion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utambulishoversion.R;
import com.example.utambulishoversion.modal.QrscanInterroModal;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ScanInterroRvAdapter  extends RecyclerView.Adapter<ScanInterroRvAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<QrscanInterroModal> QrscanInterroModalArrayList;
    private Context context;

    // constructor
    public ScanInterroRvAdapter (ArrayList<QrscanInterroModal> QrscanInterroModalArrayList, Context context) {
        this.QrscanInterroModalArrayList = QrscanInterroModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScanInterroRvAdapter .ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qrscan_rv_item, parent, false);
        return new ScanInterroRvAdapter .ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanInterroRvAdapter .ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        QrscanInterroModal modal = QrscanInterroModalArrayList.get(position);
        holder.courseNameTV.setText("Scan:\n "+modal.getCourseName());
        holder.courseDescTV.setText("Promotion: "+modal.getCourseDescription());
        holder.courseDurationTV.setText("Cours: "+modal.getCourseDuration());
        holder.courseTracksTV.setText("Nom: "+modal.getCourseTracks());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return QrscanInterroModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private TextView courseNameTV, courseDescTV, courseDurationTV, courseTracksTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseNameTV = itemView.findViewById(R.id.idTVCourseName1);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription1);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration1);
            courseTracksTV = itemView.findViewById(R.id.idTVCourseTracks1);
        }
    }
}

