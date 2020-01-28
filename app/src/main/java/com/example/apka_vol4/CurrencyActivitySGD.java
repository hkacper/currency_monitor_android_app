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

public class CurrencyActivitySGD extends AppCompatActivity {
    private EditText minValueSgd;
    private EditText maxValueSgd;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueSGD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_sgd);

        minValueSgd = (EditText) findViewById(R.id.et_min);
        maxValueSgd = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("SGD");
        valueSGD = mDatabase.child("real");

        valueSGD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueSgd = dataSnapshot.getValue().toString();
                value.setText(valueSgd  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueSGD = minValueSgd.getText().toString();
                String maxValueSGD = maxValueSgd.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueSGD);
                newPost.put("max", maxValueSGD);

                mDatabase.child("min").setValue(minValueSGD);
                mDatabase.child("max").setValue(maxValueSGD);
            }
        });
    }

}
