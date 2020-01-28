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

public class CurrencyActivityAUD extends AppCompatActivity {
    private EditText minValueAud;
    private EditText maxValueAud;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueAUD;
    //private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_aud);

        minValueAud = (EditText) findViewById(R.id.et_min);
        maxValueAud = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("AUD");
        //db = FirebaseFirestore.getInstance();

        valueAUD = mDatabase.child("real");

        valueAUD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueAud = dataSnapshot.getValue().toString();
                value.setText(valueAud  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueAUD = minValueAud.getText().toString();
                String maxValueAUD = maxValueAud.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueAUD);
                newPost.put("max", maxValueAUD);

                //db.collection("Currencies").document("USD").set(newPost);

                mDatabase.child("min").setValue(minValueAUD);
                mDatabase.child("max").setValue(maxValueAUD);
            }
        });
    }

}
