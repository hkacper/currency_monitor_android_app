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

public class CurrencyActivityMYR extends AppCompatActivity {
    private EditText minValueMyr;
    private EditText maxValueMyr;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueMYR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_myr);

        minValueMyr = (EditText) findViewById(R.id.et_min);
        maxValueMyr = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("MYR");
        valueMYR = mDatabase.child("real");

        valueMYR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueMyr = dataSnapshot.getValue().toString();
                value.setText(valueMyr  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueMYR = minValueMyr.getText().toString();
                String maxValueMYR = maxValueMyr.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueMYR);
                newPost.put("max", maxValueMYR);

                mDatabase.child("min").setValue(minValueMYR);
                mDatabase.child("max").setValue(maxValueMYR);
            }
        });
    }

}
