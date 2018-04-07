package ite.kmitl.airnose.holder;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import ite.kmitl.airnose.R;

/**
 * Created by ASUS-PC on 18/1/2561.
 */

public class UserInformationViewHolder extends RecyclerView.ViewHolder {

    View mView;
    EditText edName,edPhone,edComment,edDate,edDuration,edLevel,edLocation,edSmell,edTime;
    int check;
    public DatabaseReference databaseReference;

    public UserInformationViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setName(String text) {
        edName = (EditText) mView.findViewById(R.id.edName);
        edName.setText(text);
    }

    public void setPhone(String text) {
        edPhone = (EditText) mView.findViewById(R.id.edPhone);
        edPhone.setText(text);
    }

    public void setComment(String text) {
        edComment = (EditText) mView.findViewById(R.id.edComment);
        edComment.setText(text);
    }

    public void setDate(String text){
        edDate = (EditText) mView.findViewById(R.id.edDate);
        edDate.setText(text);
    }

    public void setDuration(String text){
        edDuration = (EditText) mView.findViewById(R.id.edDuration);
        edDuration.setText(text);
    }

    public void setLevel(String text) {
        edLevel = (EditText) mView.findViewById(R.id.edlevel);
        edLevel.setText(text);
    }

    public void setLocation(String text){
        edLocation = (EditText) mView.findViewById(R.id.edLocation);
        edLocation.setText(text);
    }

    public void setSmell(String text){
        edSmell = (EditText) mView.findViewById(R.id.edSmell);
        edSmell.setText(text);
    }

    public void setTime(String text){
        edTime = (EditText) mView.findViewById(R.id.edTime);
        edTime.setText(text);
    }

    // delete data

    public void setBtnDelete(){
        Button btnDelete = (Button) mView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("ลบข้อมูล",1);
            }
        });
    }


    // edit data
    public void setBtnEdit(){
        Button btnEdit = (Button) mView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("แก้ไขข้อมูล",2);
            }
        });
    }

    private void showDialog(String text,int value) {
        check = value;
        final Dialog dialog = new Dialog(mView.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_admin_delete);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        // set dialog
        TextView tvDialogAdminText = (TextView) dialog.findViewById(R.id.tvDialogAdminText);
        Button btnDialogCancel = (Button) dialog.findViewById(R.id.btnDialogCancel);
        Button btnDialogConfirm = (Button) dialog.findViewById(R.id.btnDialogConfirm);

        tvDialogAdminText.setText(text);
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnDialogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check == 1) {
                    databaseReference.removeValue();
                    Toast.makeText(mView.getContext(),"ลบข้อมูล",Toast.LENGTH_SHORT).show();
                }
                if(check == 2){
                    databaseReference.child("name").setValue(edName.getText().toString());
                    databaseReference.child("phone").setValue(edPhone.getText().toString());
                    databaseReference.child("comment").setValue(edComment.getText().toString());
                    databaseReference.child("duration").setValue(edDuration.getText().toString());
                    databaseReference.child("level").setValue(edLevel.getText().toString());
                    databaseReference.child("location").setValue(edLocation.getText().toString());
                    databaseReference.child("smell").setValue(edSmell.getText().toString());
                    databaseReference.child("time").setValue(edTime.getText().toString());
                    databaseReference.child("date").setValue(edDate.getText().toString());
                    Toast.makeText(mView.getContext(),"แก้ไขข้อมูล",Toast.LENGTH_SHORT).show();
                }
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
