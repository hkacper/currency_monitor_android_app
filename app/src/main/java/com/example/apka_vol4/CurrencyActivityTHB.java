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

public class CurrencyActivityTHB extends AppCompatActivity {
    private EditText minValueThb;
    private EditText maxValueThb;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueTHB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_thb);

        minValueThb = (EditText) findViewById(R.id.et_min);
        maxValueThb = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("THB");
        valueTHB = mDatabase.child("real");

        valueTHB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueThb = dataSnapshot.getValue().toString();
                value.setText(valueThb  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueTHB = minValueThb.getText().toString();
                String maxValueTHB = maxValueThb.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueTHB);
                newPost.put("max", maxValueTHB);

                mDatabase.child("min").setValue(minValueTHB);
                mDatabase.child("max").setValue(maxValueTHB);
            }
        });
    }

}
