    package com.example.android.quizapp;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        int score, sc1, sc2, sc3, sc4, sc5, sc = 0;
        int selectedId;

        public void SUBMIT(View view) {
            RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radiogp1);
            selectedId = radioGroup1.getCheckedRadioButtonId();
            if (selectedId > 0) {
                RadioButton radioid = (RadioButton) findViewById(selectedId);
                String s1 = (String) radioid.getText();

                if (s1.equals("september 2008")) {
                    sc = 1;
                } else {
                    sc = 0;
                }
                sc1 = sc;
            }
            RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radiogp2);
            selectedId = radioGroup2.getCheckedRadioButtonId();
            if (selectedId > 0) {
                RadioButton radioid = (RadioButton) findViewById(selectedId);
                String s2 = (String) radioid.getText();
                if (s2.equals("Cupcake")) {
                    sc = 1;
                } else {
                    sc = 0;
                }
                sc2 = sc;
            }
            RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.radiogp5);
            selectedId = radioGroup5.getCheckedRadioButtonId();
            if (selectedId > 0) {
                RadioButton radioid = (RadioButton) findViewById(selectedId);
                String s5 = (String) radioid.getText();
                if (s5.equals("It has all the information about an application.")) {
                    sc = 1;
                } else {
                    sc = 0;
                }
                sc5 = sc;
            }
            score = sc1 + sc2 + sc5 + q4() + q3();
            Toast.makeText(getApplication(), "Your score is : " + score, Toast.LENGTH_SHORT).show();
        }

        public int q4() {
            EditText text1 = (EditText) findViewById(R.id.wques);
            String s = text1.getText().toString();
            String s1 = s.toLowerCase();
            if (s1.equals("java native interface")) {
                sc = 1;
            }
            else
            {
                sc = 0;
            }
            sc4 = sc;
            return sc4;
        }

        public int q3() {
            CheckBox l1 = (CheckBox) findViewById(R.id.cb1);
            CheckBox l2 = (CheckBox) findViewById(R.id.cb2);
            CheckBox l3 = (CheckBox) findViewById(R.id.cb3);
            CheckBox l4 = (CheckBox) findViewById(R.id.cb4);
            boolean l10, l20, l30, l40;
            l10 = l1.isChecked();
            l20 = l2.isChecked();
            l30 = l3.isChecked();
            l40 = l4.isChecked();
            if (((l10 == true) && (l20 == true))&&((l30 == false) && (l40 == false)))
            {
                sc = 1;
            } else {
                sc = 0;
            }
            sc3 = sc;
            return sc3;
        }
    }