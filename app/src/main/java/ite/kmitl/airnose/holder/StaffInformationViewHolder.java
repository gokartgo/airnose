package ite.kmitl.airnose.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ite.kmitl.airnose.R;

/**
 * Created by ASUS-PC on 18/1/2561.
 */

public class StaffInformationViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public StaffInformationViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setComment(String text) {
        TextView tvComment = (TextView) mView.findViewById(R.id.tvComment);
        tvComment.setText("Comment : "+text);
    }

    public void setDate(String text){
        TextView tvDate = (TextView) mView.findViewById(R.id.tvDate);
        tvDate.setText("Date : "+text);
    }

    public void setTime(String text){
        TextView tvTime = (TextView) mView.findViewById(R.id.tvTime);
        tvTime.setText("Time : "+text);
    }
}
