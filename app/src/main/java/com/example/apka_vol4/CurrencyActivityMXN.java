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

public class CurrencyActivityMXN extends AppCompatActivity {
    private EditText minValueMxn;
    private EditText maxValueMxn;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueMXN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_mxn);

        minValueMxn = (EditText) findViewById(R.id.et_min);
        maxValueMxn = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("MXN");
        valueMXN = mDatabase.child("real");

        valueMXN.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueMxn = dataSnapshot.getValue().toString();
                value.setText(valueMxn  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueMXN = minValueMxn.getText().toString();
                String maxValueMXN = maxValueMxn.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueMXN);
                newPost.put("max", maxValueMXN);

                mDatabase.child("min").setValue(minValueMXN);
                mDatabase.child("max").setValue(maxValueMXN);
            }
        });
    }

}
