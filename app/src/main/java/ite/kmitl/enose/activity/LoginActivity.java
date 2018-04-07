package ite.kmitl.enose.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karan.churi.PermissionManager.PermissionManager;

import ite.kmitl.enose.R;

public class LoginActivity extends AppCompatActivity {


    // request permission
    PermissionManager permissionManager;

    Button btnUser;
    Button btnAdmin;
    EditText edIdAdmin;
    EditText edPasswordAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(LoginActivity.this);

        initInstance();
    }

    private void initInstance() {
        btnUser = (Button) findViewById(R.id.btnUser);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        edIdAdmin = (EditText) findViewById(R.id.edIdAdmin);
        edPasswordAdmin = (EditText) findViewById(R.id.edPasswordAdmin);
        btnUser.setOnClickListener(btnLoginClick);
        btnAdmin.setOnClickListener(btnLoginClick);
    }


    /**************
     * Listener zone
     */
    final View.OnClickListener btnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnUser){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            if(view == btnAdmin){
                if(edIdAdmin.getText().toString().equals("admin") && edPasswordAdmin.getText().toString().equals("airkmitl2016")){
                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(intent);
                }
                else if(edIdAdmin.getText().toString().equals("staff") && edPasswordAdmin.getText().toString().equals("airkmitl2016")){
                    Intent intent = new Intent(LoginActivity.this,StaffActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"ID or Password not true",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
