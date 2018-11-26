package com.example.ahosseini.ivademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentAmount extends Fragment {
   RecyclerView rv_price;

   LinearLayout ll_topup;
   LinearLayout ll_pin;

   Adapter_Prices rv_adapater;

   ArrayList<String>itemsTopUp;
   ArrayList<String>itemsPin;

   RadioButton rb_topUp;
   RadioButton rb_pin;

   TextView title;
   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_amount, container, false);
      setupViews(view);
      LinearLayoutManager manager=new LinearLayoutManager(getContext());
      rv_price.setLayoutManager(manager);
      rv_price.setAdapter(rv_adapater);
      return view;
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      itemsTopUp=new ArrayList<>();
      itemsTopUp.add("10,000 ريال عادی");
      itemsTopUp.add("20,000 ريال عادی");
      itemsTopUp.add("50,000 ريال عادی");
      itemsTopUp.add("100,000 ريال عادی");
      itemsTopUp.add("200,000 ريال عادی");
      itemsPin=new ArrayList<>(itemsTopUp);
      itemsTopUp.add("10,000 ريال شگفت انگیز");
      itemsTopUp.add("20,000 ريال شگفت انگیز");
      itemsTopUp.add("50,000 ريال شگفت انگیز");
      itemsTopUp.add("100,000 ريال شگفت انگیز");
      itemsTopUp.add("200,000 ريال شگفت انگیز");
      rv_adapater=new Adapter_Prices(itemsTopUp,getArguments().getString("mobileNum"),getActivity());
   }

   static Fragment newInstance(String number) {
      FragmentAmount fragment = new FragmentAmount();
      Bundle b = new Bundle();
      b.putString("mobileNum", number);
      fragment.setArguments(b);
      return fragment;
   }
   void setupViews(View view){
      ll_topup=view.findViewById(R.id.container1_type);
      ll_pin=view.findViewById(R.id.container2_type);
      rv_price=view.findViewById(R.id.rv_prices);
      rb_pin=view.findViewById(R.id.rb_code);
      rb_topUp=view.findViewById(R.id.rb_topup);
      title=view.findViewById(R.id.txt_amount);
      rb_topUp.setChecked(true);
      ll_topup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            rb_topUp.setChecked(true);

         }
      });
      ll_pin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            rb_pin.setChecked(true);


         }
      });
      rb_topUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            rb_pin.setChecked(!isChecked);
            rv_adapater.setItems(itemsTopUp);
            rv_adapater.notifyDataSetChanged();
         }
      });
      rb_pin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            rb_topUp.setChecked(!isChecked);
            rv_adapater.setItems(itemsPin);
            rv_adapater.notifyDataSetChanged();
         }
      });
      if(getArguments().getString("mobileNum")!=null&&!getArguments().getString("mobileNum").equals(""))
      title.setText(getArguments().getString("mobileNum"));
      }
}
