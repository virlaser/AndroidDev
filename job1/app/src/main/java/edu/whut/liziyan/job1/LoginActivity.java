package edu.whut.liziyan.job1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        Button btnQuit = (Button) findViewById(R.id.btn_quit);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        final EditText textUser = (EditText) findViewById(R.id.edt_username);
        final EditText textPwd = (EditText) findViewById(R.id.edt_password);

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(textPwd.getText().toString().equals("") || !textPwd.getText().toString().equals("123456"))){
                    Intent it = new Intent(LoginActivity.this, ListActivity.class);
                    it.putExtra("usr", textUser.getText().toString());
                    startActivity(it);
                }else{
                    String msg = "密码错误，请重试";
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}