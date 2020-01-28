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

public class CurrencyActivityINR extends AppCompatActivity {
    private EditText minValueInr;
    private EditText maxValueInr;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueINR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_inr);

        minValueInr = (EditText) findViewById(R.id.et_min);
        maxValueInr = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("INR");
        valueINR = mDatabase.child("real");

        valueINR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueInr = dataSnapshot.getValue().toString();
                value.setText(valueInr  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueINR = minValueInr.getText().toString();
                String maxValueINR = maxValueInr.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueINR);
                newPost.put("max", maxValueINR);

                mDatabase.child("min").setValue(minValueINR);
                mDatabase.child("max").setValue(maxValueINR);
            }
        });
    }

}
