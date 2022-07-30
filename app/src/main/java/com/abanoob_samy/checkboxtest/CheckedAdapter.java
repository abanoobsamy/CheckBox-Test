package com.abanoob_samy.checkboxtest;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.abanoob_samy.checkboxtest.databinding.ItemCheckboxBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CheckedAdapter extends RecyclerView.Adapter<CheckedAdapter.CheckedHolder> {


    List<String> mValues = new ArrayList<>();
    List<CheckedBox> mListCheckedBox = new ArrayList<>();
    OnCheckedItemListener onCheckedItemListener;

    private String myActivityValue;

    private boolean isCheckedActivity = false;

    public boolean isCheckedActivity() {
        return isCheckedActivity;
    }

    public void setCheckedActivity(boolean checkedActivity) {
        isCheckedActivity = checkedActivity;
    }

    public String getMyActivityValue() {
        return myActivityValue;
    }

    public void setMyActivityValue(String myActivityValue) {
        this.myActivityValue = myActivityValue;
    }

    public void setOnCheckedItemListener(OnCheckedItemListener onCheckedItemListener) {
        this.onCheckedItemListener = onCheckedItemListener;
    }

    public CheckedAdapter(List<CheckedBox> checkedBox) {
        this.mListCheckedBox = checkedBox;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CheckedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_checkbox, parent, false);
        return new CheckedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckedHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.etCarBalance.setText(mListCheckedBox.get(position).getPrice());
        holder.binding.checkBoxCar.setText(mListCheckedBox.get(position).getCarName());

        if (isCheckedActivity()) {
            holder.binding.checkBoxCar.setChecked(true);
        }
        else {
            holder.binding.checkBoxCar.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mListCheckedBox == null ? 0 : mListCheckedBox.size();
    }


    public class CheckedHolder extends RecyclerView.ViewHolder {

        ItemCheckboxBinding binding;


        public CheckedHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCheckboxBinding.bind(itemView);

            binding.checkBoxCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        binding.etCarBalance.setText(getMyActivityValue());
                        mListCheckedBox.get(getAdapterPosition()).setPrice(getMyActivityValue());
                    }
                    else {
                        mListCheckedBox.get(getAdapterPosition()).setPrice(getMyActivityValue());
                    }
                }
            });

            binding.checkBoxCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (binding.checkBoxCar.isChecked()) {
                        mValues.add(mListCheckedBox.get(getAdapterPosition()).getPrice());
                    } else {
                        mValues.remove(mListCheckedBox.get(getAdapterPosition()).getPrice());
                    }
                    onCheckedItemListener.onChecked(mValues);
                }
            });

        }
    }

    public interface OnCheckedItemListener {
        void onChecked(List<String> values);
    }
}


