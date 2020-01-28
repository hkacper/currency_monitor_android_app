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

public class CurrencyActivityJPY extends AppCompatActivity {
    private EditText minValueJpy;
    private EditText maxValueJpy;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueJPY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_jpy);

        minValueJpy = (EditText) findViewById(R.id.et_min);
        maxValueJpy = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("JPY");
        valueJPY = mDatabase.child("real");

        valueJPY.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueJpy = dataSnapshot.getValue().toString();
                value.setText(valueJpy  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueJPY = minValueJpy.getText().toString();
                String maxValueJPY = maxValueJpy.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueJPY);
                newPost.put("max", maxValueJPY);

                mDatabase.child("min").setValue(minValueJPY);
                mDatabase.child("max").setValue(maxValueJPY);
            }
        });
    }

}
