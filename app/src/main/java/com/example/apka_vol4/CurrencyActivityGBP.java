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

public class CurrencyActivityGBP extends AppCompatActivity {
    private EditText minValueGbp;
    private EditText maxValueGbp;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueGBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_gbp);

        minValueGbp = (EditText) findViewById(R.id.et_min);
        maxValueGbp = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("GBP");

        valueGBP = mDatabase.child("real");

        valueGBP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueGbp = dataSnapshot.getValue().toString();
                value.setText(valueGbp  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueGBP = minValueGbp.getText().toString();
                String maxValueGBP = maxValueGbp.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueGBP);
                newPost.put("max", maxValueGBP);

                mDatabase.child("min").setValue(minValueGBP);
                mDatabase.child("max").setValue(maxValueGBP);
            }
        });
    }

}
