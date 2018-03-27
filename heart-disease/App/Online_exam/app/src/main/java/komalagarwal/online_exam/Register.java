package komalagarwal.online_exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button b1;
    Intent tomain;
    DBregister mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addListenerOnButton();
        mydb=new DBregister(this);
    }
    private void addListenerOnButton() {

        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et1 = (EditText) findViewById(R.id.editText);
                et2 = (EditText) findViewById(R.id.editText2);
                et3 = (EditText) findViewById(R.id.editText5);
                et4 = (EditText) findViewById(R.id.editText6);
                String s1 = "", s2 = "", s3 = "", s4 = "";
                s1 = et1.getText().toString();
                s2 = et2.getText().toString();
                s3 = et3.getText().toString();
                s4 = et4.getText().toString();
                int b=0;
                if (s1.equals("") && s2.equals("") && s3.equals("") && s4.equals("")) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "FILL ALL FIELDS", Toast.LENGTH_LONG).show();
                }
                if (s1.length() < 5 && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "USERNAME LENGTH SHOULD BE MINIMUM 5", Toast.LENGTH_LONG).show();
                }
                if ((s2.length()) < 6 && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "PASSWORD LENGTH SHOULD BE MINIMUM 6", Toast.LENGTH_LONG).show();
                }
                if (!s3.equals(s2) && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "PASSWORD DOES NOT MATCHED", Toast.LENGTH_LONG).show();
                }
                if (s4.length() != 10 && b==0) {
                    b = 1;
                    Toast.makeText(getApplicationContext(), "ENTER CORRECT PHONE NUMBER", Toast.LENGTH_LONG).show();
                }

                if (b == 0) {

                    int len = s1.length();
                    int len1 = s2.length();
                    mydb.insertContact(s1, s2, s4);
                    Toast.makeText(getApplicationContext(), "SUCCESSFULLY REGISTERED NOW ENTER DETAILS TO LOGIN !!", Toast.LENGTH_LONG).show();
                    tomain = new Intent(Register.this, MainActivity.class);
                    startActivity(tomain);
                }
            }});
    }
}
