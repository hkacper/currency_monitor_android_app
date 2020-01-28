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

public class CurrencyActivityZAR extends AppCompatActivity {
    private EditText minValueZar;
    private EditText maxValueZar;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueZAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_zar);

        minValueZar = (EditText) findViewById(R.id.et_min);
        maxValueZar = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("ZAR");
        valueZAR = mDatabase.child("real");

        valueZAR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueZar = dataSnapshot.getValue().toString();
                value.setText(valueZar  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueZAR = minValueZar.getText().toString();
                String maxValueZAR = maxValueZar.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueZAR);
                newPost.put("max", maxValueZAR);

                mDatabase.child("min").setValue(minValueZAR);
                mDatabase.child("max").setValue(maxValueZAR);
            }
        });
    }

}
