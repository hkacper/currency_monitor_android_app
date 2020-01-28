package com.example.apka_vol4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CurrencyActivityHKD extends AppCompatActivity {
    private EditText minValueHkd;
    private EditText maxValueHkd;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueHKD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_hkd);

        minValueHkd = (EditText) findViewById(R.id.et_min);
        maxValueHkd = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("HKD");
        valueHKD = mDatabase.child("real");

        valueHKD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueHkd = dataSnapshot.getValue().toString();
                value.setText(valueHkd  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueHKD = minValueHkd.getText().toString();
                String maxValueHKD = maxValueHkd.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueHKD);
                newPost.put("max", maxValueHKD);

                mDatabase.child("min").setValue(minValueHKD);
                mDatabase.child("max").setValue(maxValueHKD);
            }
        });
    }

}
