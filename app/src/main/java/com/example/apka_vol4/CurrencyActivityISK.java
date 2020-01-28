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

public class CurrencyActivityISK extends AppCompatActivity {
    private EditText minValueIsk;
    private EditText maxValueIsk;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueISK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_isk);

        minValueIsk = (EditText) findViewById(R.id.et_min);
        maxValueIsk = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("ISK");
        valueISK = mDatabase.child("real");

        valueISK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueIsk = dataSnapshot.getValue().toString();
                value.setText(valueIsk  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueISK = minValueIsk.getText().toString();
                String maxValueISK = maxValueIsk.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueISK);
                newPost.put("max", maxValueISK);

                mDatabase.child("min").setValue(minValueISK);
                mDatabase.child("max").setValue(maxValueISK);
            }
        });
    }

}
