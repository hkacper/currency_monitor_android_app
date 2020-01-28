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

public class CurrencyActivityBRL extends AppCompatActivity {
    private EditText minValueBrl;
    private EditText maxValueBrl;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueBRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_brl);

        minValueBrl = (EditText) findViewById(R.id.et_min);
        maxValueBrl = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("BRL");
        valueBRL = mDatabase.child("real");

        valueBRL.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueBrl = dataSnapshot.getValue().toString();
                value.setText(valueBrl  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueBRL = minValueBrl.getText().toString();
                String maxValueBRL = maxValueBrl.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueBRL);
                newPost.put("max", maxValueBRL);

                mDatabase.child("min").setValue(minValueBRL);
                mDatabase.child("max").setValue(maxValueBRL);
            }
        });
    }

}
