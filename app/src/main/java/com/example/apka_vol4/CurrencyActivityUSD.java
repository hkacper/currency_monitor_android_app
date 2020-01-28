package com.example.apka_vol4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CurrencyActivityUSD extends AppCompatActivity {
    private EditText minValueUsd;
    private EditText maxValueUsd;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueUSD;
    //private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_usd);

        minValueUsd = (EditText) findViewById(R.id.et_min);
        maxValueUsd = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("USD");
        //db = FirebaseFirestore.getInstance();

        valueUSD = mDatabase.child("real");

        valueUSD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueUsd = dataSnapshot.getValue().toString();
                value.setText(valueUsd  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueUSD = minValueUsd.getText().toString();
                String maxValueUSD = maxValueUsd.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueUSD);
                newPost.put("max", maxValueUSD);

                //db.collection("Currencies").document("USD").set(newPost);

                mDatabase.child("min").setValue(minValueUSD);
                mDatabase.child("max").setValue(maxValueUSD);
            }
        });
    }

}
