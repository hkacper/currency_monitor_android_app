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

public class CurrencyActivityTRY extends AppCompatActivity {
    private EditText minValueTry;
    private EditText maxValueTry;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueTRY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_try);

        minValueTry = (EditText) findViewById(R.id.et_min);
        maxValueTry = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("TRY");
        valueTRY = mDatabase.child("real");

        valueTRY.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueTry = dataSnapshot.getValue().toString();
                value.setText(valueTry  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueTRY = minValueTry.getText().toString();
                String maxValueTRY = maxValueTry.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueTRY);
                newPost.put("max", maxValueTRY);

                mDatabase.child("min").setValue(minValueTRY);
                mDatabase.child("max").setValue(maxValueTRY);
            }
        });
    }

}
