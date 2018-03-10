package ite.kmitl.enose.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ite.kmitl.enose.R;
import ite.kmitl.enose.dao.UserInformationData;
import ite.kmitl.enose.holder.StaffInformationViewHolder;
import ite.kmitl.enose.holder.UserInformationViewHolder;

public class StaffActivity extends AppCompatActivity {

    private RecyclerView mUserList;

    // add firebase database stuff
    FirebaseDatabase database;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        // declare database reference object
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        mUserList = (RecyclerView) findViewById(R.id.userList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<UserInformationData,StaffInformationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserInformationData, StaffInformationViewHolder>(
                UserInformationData.class
                ,R.layout.view_user_information_staff
                ,StaffInformationViewHolder.class
                ,databaseReference
        ) {

            @Override
            protected void populateViewHolder(StaffInformationViewHolder viewHolder, UserInformationData model, int position) {
                viewHolder.setTime(model.getTime());
                viewHolder.setDate(model.getDate());
                viewHolder.setComment(model.getComment());
            }
        };

        mUserList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mUserList.setLayoutManager(linearLayoutManager);
        mUserList.setAdapter(firebaseRecyclerAdapter);
    }
}
