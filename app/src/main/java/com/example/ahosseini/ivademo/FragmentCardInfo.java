package com.example.ahosseini.ivademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentCardInfo extends Fragment {
   TextView txt_num;
   TextView txt_amount;

   EditText et_card;
   EditText et_pin2;
   EditText et_cvv2;
   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_cardinfo,container,false);
      return view;
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }
   static Fragment newInstance(String txt){
    FragmentCardInfo fragmentCardInfo=new FragmentCardInfo();
    Bundle b=new Bundle();
    b.putString("package",txt);
    fragmentCardInfo.setArguments(b);
    return fragmentCardInfo;
   }
}
