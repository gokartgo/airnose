package ite.kmitl.enose.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import ite.kmitl.enose.R;
import ite.kmitl.enose.holder.UserInformationViewHolder;
import ite.kmitl.enose.dao.UserInformationData;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView mUserList;

    // add firebase database stuff
    FirebaseDatabase database;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // declare database reference object
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        mUserList = (RecyclerView) findViewById(R.id.userList);


    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<UserInformationData,UserInformationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserInformationData, UserInformationViewHolder>(
                UserInformationData.class
                ,R.layout.view_user_information
                ,UserInformationViewHolder.class
                ,databaseReference
        ) {
            @Override
            protected void populateViewHolder(UserInformationViewHolder viewHolder, UserInformationData model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setComment(model.getComment());
                viewHolder.setDate(model.getDate());
                viewHolder.setDuration(model.getDuration());
                viewHolder.setLevel(model.getLevel());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setSmell(model.getSmell());
                viewHolder.setTime(model.getTime());
                viewHolder.databaseReference = getRef(position);
                viewHolder.setBtnDelete();
                viewHolder.setBtnEdit();
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
