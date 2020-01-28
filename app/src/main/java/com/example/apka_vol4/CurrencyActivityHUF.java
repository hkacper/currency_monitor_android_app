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

public class CurrencyActivityHUF extends AppCompatActivity {
    private EditText minValueHuf;
    private EditText maxValueHuf;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueHUF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_huf);

        minValueHuf = (EditText) findViewById(R.id.et_min);
        maxValueHuf = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("HUF");
        valueHUF = mDatabase.child("real");

        valueHUF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueHuf = dataSnapshot.getValue().toString();
                value.setText(valueHuf);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueHUF = minValueHuf.getText().toString();
                String maxValueHUF = maxValueHuf.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueHUF);
                newPost.put("max", maxValueHUF);

                mDatabase.child("min").setValue(minValueHUF);
                mDatabase.child("max").setValue(maxValueHUF);
            }
        });
    }

}
