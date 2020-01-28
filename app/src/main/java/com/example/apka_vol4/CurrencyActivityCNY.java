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

public class CurrencyActivityCNY extends AppCompatActivity {
    private EditText minValueCny;
    private EditText maxValueCny;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueCNY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_cny);

        minValueCny = (EditText) findViewById(R.id.et_min);
        maxValueCny = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("CNY");
        valueCNY = mDatabase.child("real");

        valueCNY.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueCny = dataSnapshot.getValue().toString();
                value.setText(valueCny  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueCNY = minValueCny.getText().toString();
                String maxValueCNY = maxValueCny.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueCNY);
                newPost.put("max", maxValueCNY);

                mDatabase.child("min").setValue(minValueCNY);
                mDatabase.child("max").setValue(maxValueCNY);
            }
        });
    }

}
