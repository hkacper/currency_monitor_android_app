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

public class CurrencyActivityCZK extends AppCompatActivity {
    private EditText minValueCzk;
    private EditText maxValueCzk;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueCZK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_czk);

        minValueCzk = (EditText) findViewById(R.id.et_min);
        maxValueCzk = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("CZK");
        valueCZK = mDatabase.child("real");

        valueCZK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueCzk = dataSnapshot.getValue().toString();
                value.setText(valueCzk  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueCZK = minValueCzk.getText().toString();
                String maxValueCZK = maxValueCzk.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueCZK);
                newPost.put("max", maxValueCZK);

                mDatabase.child("min").setValue(minValueCZK);
                mDatabase.child("max").setValue(maxValueCZK);
            }
        });
    }

}
