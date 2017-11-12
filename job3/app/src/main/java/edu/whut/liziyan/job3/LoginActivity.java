package edu.whut.liziyan.job3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText unameEdt=(EditText)findViewById(R.id.edt_username);
        final EditText pwdEdt=(EditText)findViewById(R.id.edt_password);


        /**这里别忘了把CheckBox通过findViewById关联进来**/
        final CheckBox savenameChk=(CheckBox)findViewById(R.id.chk_savename);

        /**从SharedPreferences通过键名uname获取保存的用户名，若没有则为空。
         * 并填入用户名对话框
         * 如何将数据存入SharedPreferences参看“教学资源---示例代码---课件例子-- SharedPreferences存储”**/
        SharedPreferences pref = getSharedPreferences("userinfo", MODE_PRIVATE);
        unameEdt.setText(pref.getString("uname", ""));



        Button quitBtn=(Button)findViewById(R.id.btn_quit);
        Button loginBtn=(Button)findViewById(R.id.btn_login);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(unameEdt.getText().toString()==null||unameEdt.getText().toString().equals("") || !pwdEdt.getText().toString().equals("123456")){
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }else{

                    /**登陆成功后，界面跳转前的处理：判断记住用户名复选框是否选中(checkbox.ischecked()是否为true)
                     * 选中，则将用户名记入SharedPreferences（键名为uname）
                     * 未选中，则将空字符串记入SharedPreferences（键名也为uname），覆盖原来保存的用户名。
                     * 如何将数据存入SharedPreferences参看“教学资源---示例代码---课件例子-- SharedPreferences存储”
                     * **/
                    SharedPreferences.Editor editor = getSharedPreferences("userinfo", MODE_PRIVATE).edit();
                    if(savenameChk.isChecked()){
                        editor.putString("uname", unameEdt.getText().toString());
                    }else{
                        editor.putString("uname", "");
                    }
                    editor.apply();


                    Intent intent=new Intent(LoginActivity.this,ListActivity.class);
                    intent.putExtra("uname",unameEdt.getText().toString());
                    startActivity(intent);
                }
            }
        });


    }
}
