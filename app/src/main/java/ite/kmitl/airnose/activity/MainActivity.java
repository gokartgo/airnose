package ite.kmitl.airnose.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ite.kmitl.airnose.R;
import ite.kmitl.airnose.fragment.FragmentSentInformation;
import ite.kmitl.airnose.fragment.MainFragment;


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
