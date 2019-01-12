package komalagarwal.online_exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class Softmax_regression extends AppCompatActivity {

    Spinner spinner ;
    ArrayAdapter<CharSequence> adapter;
    int selected=0;
    private static final String MODEL_FILE = "file:///android_asset/frozen_h.pb";

    private static final String INPUT_NODE = "inputs/inputs";
    private static final String OUTPUT_NODE = "predictions/predictions";

    private static final long INPUT_SIZE = 10;

    private TensorFlowInferenceInterface inferenceInterface;

    static {
        System.loadLibrary("tensorflow_inference");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softmax_regression2);
        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILE);
        System.out.println("model loaded successfully");

        spinner= (Spinner) findViewById(R.id.spinner) ;
        adapter= ArrayAdapter.createFromResource(this,R.array.famhist,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position) + "Selected", Toast.LENGTH_SHORT).show();
                if(position==0)
                    selected=1;
                else
                    selected=0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                //InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                //inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                final EditText Num1 = (EditText) findViewById(R.id.editText1);
                final EditText Num2 = (EditText) findViewById(R.id.editText2);
                final EditText Num3 = (EditText) findViewById(R.id.editText3);
                final EditText Num4 = (EditText) findViewById(R.id.editText4);
                final EditText Num6 = (EditText) findViewById(R.id.editText6);
                final EditText Num7 = (EditText) findViewById(R.id.editText7);
                final EditText Num8 = (EditText) findViewById(R.id.editText8);
                final EditText Num9 = (EditText) findViewById(R.id.editText9);

                float num1 = Float.parseFloat(Num1.getText().toString());
                float num2 = Float.parseFloat(Num2.getText().toString());
                float num3 = Float.parseFloat(Num3.getText().toString());
                float num4 = Float.parseFloat(Num4.getText().toString());
                float num6 = Float.parseFloat(Num6.getText().toString());
                float num7 = Float.parseFloat(Num7.getText().toString());
                float num8 = Float.parseFloat(Num8.getText().toString());
                float num9 = Float.parseFloat(Num9.getText().toString());

                int selected2=0;
                if(selected==1)
                    selected2=0;
                if(selected==0)
                    selected2=1;
                float[] inputFloats = {num1, num2, num3, num4, num6, num7, num8, num9, selected2, selected};
                System.out.println(inputFloats.length);
                System.out.println("INPUT VALUES ARE-------------------------------------------------------");
                System.out.println(inputFloats[0]+" "+inputFloats[1]+" "+inputFloats[2]+" "+inputFloats[3]+" "+inputFloats[4]+" "+inputFloats[5]+" "+inputFloats[6]+" "+inputFloats[7]);

                inferenceInterface.feed(INPUT_NODE,inputFloats,1,INPUT_SIZE);
                //inferenceInterface.feed(INPUT_NODE, INPUT_SIZE, inputFloats);
                //INPUT_NODE, inputFloats
                String[] outputNames;
                outputNames = new String[]{OUTPUT_NODE};
                inferenceInterface.run(outputNames);


                float[] result=new float[1000];
                inferenceInterface.fetch(OUTPUT_NODE, result);

                String s="class";
                for(int i=0;i<10;i++)
                    System.out.println("result="+result[i]);
                if (result[0]<1.5)
                {
                    s="NO HEART DISEASE !!";
                }
                else if (result[0]>=1.5)
                {
                    s="U CAN HAVE HEART DISEASE !!";

                }

                System.out.println(Float.toString(result[0]));

                final TextView textViewR = (TextView) findViewById(R.id.textView19);
                //textViewR.setText(Integer.toString(class_id));
                textViewR.setText(s);
            }
        });
    }
}
