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

public class CurrencyActivityRON extends AppCompatActivity {
    private EditText minValueRon;
    private EditText maxValueRon;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueRON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_ron);

        minValueRon = (EditText) findViewById(R.id.et_min);
        maxValueRon = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("RON");
        valueRON = mDatabase.child("real");

        valueRON.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueRon = dataSnapshot.getValue().toString();
                value.setText(valueRon  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueRON = minValueRon.getText().toString();
                String maxValueRON = maxValueRon.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueRON);
                newPost.put("max", maxValueRON);

                mDatabase.child("min").setValue(minValueRON);
                mDatabase.child("max").setValue(maxValueRON);
            }
        });
    }

}
