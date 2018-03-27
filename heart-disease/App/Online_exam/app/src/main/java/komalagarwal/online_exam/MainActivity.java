package komalagarwal.online_exam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import komalagarwal.online_exam.R.layout;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    Intent toreg;
    DBregister mydb;
    EditText et1,et2;
    public static final String MyPREFERENCESun = "MyPref" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.editText3);
        et2 = (EditText) findViewById(R.id.editText4);
        String s1="",s2="";
        s1=et1.getText().toString();
        s2=et2.getText().toString();
        et1.setText("");
        et2.setText("");
        addListenerOnButton();
        sharedpreferences = getSharedPreferences(MyPREFERENCESun, Context.MODE_PRIVATE);
        mydb=new DBregister(this);
    }
    private void addListenerOnButton() {

        b1 = (Button) findViewById(R.id.button4);
        b2 = (Button) findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toreg = new Intent(MainActivity.this, Register.class);
                startActivity(toreg);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et1 = (EditText) findViewById(R.id.editText3);
                et2 = (EditText) findViewById(R.id.editText4);
                String s1="",s2="";
                s1=et1.getText().toString();
                s2=et2.getText().toString();
                int a=1;
                Cursor rs = null, rs1 = null;
                String pwd = "";
                if(s1.equals("")||s2.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "ENTER USERNAME AND PASSWORD", Toast.LENGTH_SHORT).show();
                    a=0;
                }
                else {

                    System.out.println("username=" + s1 + " password=" + s2 + " ");
                    rs = mydb.getpass(s1);
                    rs.moveToFirst();
                }
                if(rs==null||a==0)
                    Toast.makeText(getApplicationContext(), "INCORRECT USERNAME", Toast.LENGTH_SHORT).show();
                else  {
                    pwd = rs.getString(rs.getColumnIndex(DBregister.REGISTER_COLUMN_PASSWORD));
                    System.out.println("password frm database" + pwd);
                    a=1;
                }
                if(pwd.equals(" ")||s2.equals(" "))
                    Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                if (s2.equals(pwd)&&(a==1))
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Name, s1);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    toreg = new Intent(MainActivity.this, Selector.class);
                    startActivity(toreg);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                }


            }



        });
    }
}
