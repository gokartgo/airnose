package ite.kmitl.enose.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ite.kmitl.enose.R;

/**
 * Created by ASUS-PC on 18/1/2561.
 */

public class UserInformationViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public UserInformationViewHolder(View itemView) {
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

    public void setDuration(String text){
        TextView tvDuration = (TextView) mView.findViewById(R.id.tvDuration);
        tvDuration.setText("Duration : "+text);
    }

    public void setLevel(String text) {
        TextView tvLevel = (TextView) mView.findViewById(R.id.tvlevel);
        tvLevel.setText("Level : "+text);
    }

    public void setLocation(String text){
        TextView tvLocation = (TextView) mView.findViewById(R.id.tvLocation);
        tvLocation.setText("Location : "+text);
    }

    public void setSmell(String text){
        TextView tvSmell = (TextView) mView.findViewById(R.id.tvSmell);
        tvSmell.setText("Smell : "+text);
    }

    public void setTime(String text){
        TextView tvTime = (TextView) mView.findViewById(R.id.tvTime);
        tvTime.setText("Time : "+text);
    }
}
