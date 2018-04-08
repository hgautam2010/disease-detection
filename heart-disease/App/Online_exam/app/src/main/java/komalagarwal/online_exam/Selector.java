package komalagarwal.online_exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selector extends AppCompatActivity {

    Button b1;
    Intent toreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        addListenerOnButton();
    }
    private void addListenerOnButton() {

        b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toreg = new Intent(Selector.this, Heart_symptom.class);
                startActivity(toreg);
            }
        });
    }
}
