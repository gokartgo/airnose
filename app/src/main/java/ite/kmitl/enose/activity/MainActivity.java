package ite.kmitl.enose.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.karan.churi.PermissionManager.PermissionManager;

import ite.kmitl.enose.R;
import ite.kmitl.enose.fragment.FragmentSentInformation;
import ite.kmitl.enose.fragment.MainFragment;


public class MainActivity extends AppCompatActivity implements FragmentSentInformation.FragmentListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance(),"MainFragment")
                    .commit();
        }
    }

    private void initInstance() {
    }

    @Override
    public void onSendInformationClick() {
        finish();
    }
}
