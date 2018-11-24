package com.example.ahosseini.ivademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class FragmentNumber extends Fragment {
   Button btn_continue;
   EditText number;
   ImageView img_irancell;
   ImageView img_hamrahAval;
   ImageView img_rightel;

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_number, container, false);
      setupViews(view);
      return view;
   }

   private void setupViews(final View view) {
      btn_continue = view.findViewById(R.id.btn_continue);
      number = view.findViewById(R.id.phone_number);
      img_irancell = view.findViewById(R.id.img_irancell);
      img_hamrahAval = view.findViewById(R.id.img_hamrahaval);
      img_rightel = view.findViewById(R.id.img_rightel);
      btn_continue.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            ((MainActivity) getActivity()).addFragment(FragmentAmount.newInstance(number.getText().toString()));
         }
      });
      img_rightel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            resetImages();
            img_rightel.setImageResource(R.drawable.colored_rightel);
         }
      });
      img_irancell.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            resetImages();
            img_irancell.setImageResource(R.drawable.colored_irancell);

         }
      });
      img_hamrahAval.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            resetImages();
            img_hamrahAval.setImageResource(R.drawable.colored_hamrah_avval);
         }
      });
      number.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
            String mobileNo = String.valueOf(number.getText());
            if(mobileNo.length()<4){
               resetImages();
            }
            if (mobileNo.length() == 4) {
               if (mobileNo.startsWith("091") || mobileNo.startsWith("099")) {
                  resetImages();
                  img_hamrahAval.setImageResource(R.drawable.colored_hamrah_avval);

               } else if (mobileNo.startsWith("093") || mobileNo.startsWith("090") || mobileNo.startsWith("0941")) {
                  resetImages();
                  img_irancell.setImageResource(R.drawable.colored_irancell);

               } else if (mobileNo.startsWith("092")) {
                  resetImages();
                  img_rightel.setImageResource(R.drawable.colored_rightel);

               } else
                  resetImages();
            }
         }
      });

   }

   void resetImages() {
      img_rightel.setImageResource(R.drawable.rightel_off);
      img_irancell.setImageResource(R.drawable.irancell_off);
      img_hamrahAval.setImageResource(R.drawable.hamrah_avval_off);

   }
}
