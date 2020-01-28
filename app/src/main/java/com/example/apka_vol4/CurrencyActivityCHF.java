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

public class CurrencyActivityCHF extends AppCompatActivity {
    private EditText minValueChf;
    private EditText maxValueChf;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueCHF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_cad);

        minValueChf = (EditText) findViewById(R.id.et_min);
        maxValueChf = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("CHF");
        valueCHF = mDatabase.child("real");

        valueCHF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueChf = dataSnapshot.getValue().toString();
                value.setText(valueChf  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueCHF = minValueChf.getText().toString();
                String maxValueCHF = maxValueChf.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueCHF);
                newPost.put("max", maxValueCHF);

                mDatabase.child("min").setValue(minValueCHF);
                mDatabase.child("max").setValue(maxValueCHF);
            }
        });
    }

}
