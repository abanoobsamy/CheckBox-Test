package com.abanoob_samy.checkboxtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.abanoob_samy.checkboxtest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements CheckedAdapter.OnCheckedItemListener  {

    private ActivityMainBinding binding;

    CheckedAdapter adapter;

//    String price, carName;
    int sum = 0;
    List<CheckedBox> data;

    String mValue = "";
    List<String> toastMessages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecycler();
        onClick();

        binding.checkBoxCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSelected) {

                adapter.setCheckedActivity(isSelected);
                if (isSelected) {
                    adapter.setMyActivityValue(binding
                            .etCarBalance.getText().toString());
                }
                adapter.notifyDataSetChanged();
            }
        });


//        binding.checkBoxCar.setOnCheckedChangeListener((compoundButton, b) -> {
//
//            adapter.setCheckedBoxes(b);
//
//            if (b) {
//                binding.button.setOnClickListener(v -> {
//
//
//                    String maximum = binding.etCarBalance.getText().toString();
//                    adapter.setPrices(maximum);
//                    adapter.notifyDataSetChanged();
//                });
//            }
//
//            adapter.notifyDataSetChanged();
//
//        });

    }

    private List<CheckedBox> stringList() {

        data = new ArrayList<>();

        data.add(new CheckedBox("#1", "1000"));
        data.add(new CheckedBox("#2", "9910"));
        data.add(new CheckedBox("#3", "10"));
        data.add(new CheckedBox("#4", "90000"));
        data.add(new CheckedBox("#5", "5000"));

        return data;
    }

    private void initRecycler(){
        adapter = new CheckedAdapter(stringList());
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnCheckedItemListener(this);
//        adapter.notifyDataSetChanged();
    }

    private void onClick(){
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValueForAll = binding.etCarBalance.getText().toString();
                adapter.setMyActivityValue(newValueForAll);
//                adapter.notifyDataSetChanged();

//                for (String checkBoxValue : toastMessages) {
//                    Toast.makeText(MainActivity.this, checkBoxValue + "", Toast.LENGTH_SHORT).show();
//                    Log.e(TAG, "onClick: " + checkBoxValue );
//                }


                Log.e(TAG, "onClick: " + toastMessages);
                Toast.makeText(MainActivity.this, toastMessages + "", Toast.LENGTH_SHORT).show();
            }
        });

        binding.etCarBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.setMyActivityValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onChecked(List<String> values) {

        try {

            if (values != null && values.size() > 0){

//                toastMessages.clear();

                for (int value = 0; value < values.size(); value++){
//                    int number = Integer.parseInt(values.get(value));
//                    sum = sum + number;
//                    mValue = values.get(value);
                    toastMessages = values;
                }
            }
            Log.e(TAG, "onChecked: " + sum );
            Log.e(TAG, "toastMessages: " + toastMessages );
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}