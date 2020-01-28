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

public class CurrencyActivityHRK extends AppCompatActivity {
    private EditText minValueHrk;
    private EditText maxValueHrk;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueHRK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_hrk);

        minValueHrk = (EditText) findViewById(R.id.et_min);
        maxValueHrk = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("HRK");
        valueHRK = mDatabase.child("real");

        valueHRK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueHrk = dataSnapshot.getValue().toString();
                value.setText(valueHrk);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueHRK = minValueHrk.getText().toString();
                String maxValueHRK = maxValueHrk.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueHRK);
                newPost.put("max", maxValueHRK);

                mDatabase.child("min").setValue(minValueHRK);
                mDatabase.child("max").setValue(maxValueHRK);
            }
        });
    }

}
