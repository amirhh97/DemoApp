package com.example.ahosseini.ivademo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentCardInfo extends Fragment {
   TextView txt_num;
   TextView txt_amount;

   EditText et_card;
   EditText et_pin2;
   EditText et_cvv2;
   Dialog_Card dialog_card;

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_cardinfo, container, false);
      setUpViews(view);
      return view;
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      dialog_card = new Dialog_Card(getContext(), new Dialog_Card.DialogCardCallback() {
         @Override
         public void onDialogCard_GotCard(String cardno, String expMonth, String expYear, boolean save) {
            et_card.setText(cardno);
            et_pin2.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_cvv2.getWindowToken(), 0);

         }
      }, false);
   }

   static Fragment newInstance(String txt, String number) {
      FragmentCardInfo fragmentCardInfo = new FragmentCardInfo();
      Bundle b = new Bundle();
      b.putString("package", txt);
      b.putString("number", number);
      fragmentCardInfo.setArguments(b);
      return fragmentCardInfo;
   }

   void setUpViews(final View view) {
      txt_amount = view.findViewById(R.id.txt_amount);
      txt_num = view.findViewById(R.id.txt_num);
      String temp = getArguments().getString("number");
      if (temp.equals("")) temp = "09121234567";
      txt_num.setText(temp);
      txt_amount.setText(getArguments().getString("package"));
      et_card = view.findViewById(R.id.et_card);
      et_cvv2 = view.findViewById(R.id.et_cvv2);
      et_pin2 = view.findViewById(R.id.et_pin2);

      et_card.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dialog_card.show();
         }
      });
      et_card.setOnFocusChangeListener(new View.OnFocusChangeListener() {
         @Override
         public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
               dialog_card.show();

         }
      });
   }


}
