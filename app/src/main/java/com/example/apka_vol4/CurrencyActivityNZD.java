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

public class CurrencyActivityNZD extends AppCompatActivity {
    private EditText minValueNzd;
    private EditText maxValueNzd;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueNZD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_nzd);

        minValueNzd = (EditText) findViewById(R.id.et_min);
        maxValueNzd = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("NZD");
        valueNZD = mDatabase.child("real");

        valueNZD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueNzd = dataSnapshot.getValue().toString();
                value.setText(valueNzd  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueNZD = minValueNzd.getText().toString();
                String maxValueNZD = maxValueNzd.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueNZD);
                newPost.put("max", maxValueNZD);

                mDatabase.child("min").setValue(minValueNZD);
                mDatabase.child("max").setValue(maxValueNZD);
            }
        });
    }

}
