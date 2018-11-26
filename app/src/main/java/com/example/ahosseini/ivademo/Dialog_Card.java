package com.example.ahosseini.ivademo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Dialog_Card extends Dialog {


   EditText et_card;
   EditText et_expYear;
   EditText et_expMonth;
   Button btn_save;
   Button btn_use;
   Button btn_back;
   Button btn_add;

   AppCompatSpinner sp_cards;
   TextView tv_cards_empty;

   ImageView iv_clearAll;
   ProgressBar pb_cardLoad;

   ViewGroup ll_close;
   ViewGroup holder_btns_usesave = findViewById(R.id.holder_buttons_useSave);
   ViewGroup holder_btns_add = findViewById(R.id.holder_buttons_add);

   DialogCardCallback callback;
   boolean addCard;
   Context context;

   String inputCardToken;
   ArrayList<String>cards;

   public Dialog_Card(@NonNull Context context, DialogCardCallback callback, boolean addCard) {
      super(context);
      this.callback = callback;
      this.addCard = addCard;
      this.context = context;
      this.setCancelable(false);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

      this.setContentView(R.layout.dialog_card);
      setupViews();
      this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      this.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation_from_down;

      WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
      lp.copyFrom(this.getWindow().getAttributes());
      lp.width=WindowManager.LayoutParams.MATCH_PARENT;

      lp.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
      this.getWindow().setAttributes(lp);

      cards=new ArrayList<>();
      loadCardTokens(cards);
      setListeners();


   }


   private void setListeners() {
      et_card.addTextChangedListener(new FourDigitCardFormatWatcher());


      et_card.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.length() > 0)
               iv_clearAll.setVisibility(View.VISIBLE);

            if (s.length() == 19) {
               et_expMonth.requestFocus();
            }
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
      });


      et_expMonth.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 2) {
               et_expYear.requestFocus();
            }
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
      });
      et_expYear.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 2) {
            }
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
      });

      btn_use.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            dismiss();
            callback.onDialogCard_GotCard(et_card.getText().toString().replaceAll("-", ""), et_expMonth.getText().toString(), et_expYear.getText().toString(), false);
         }


      });

      btn_save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
            callback.onDialogCard_GotCard(et_card.getText().toString(), et_expMonth.getText().toString(), et_expYear.getText().toString(), true);
         }

      });

      btn_add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
            callback.onDialogCard_GotCard(et_card.getText().toString(), et_expMonth.getText().toString(), et_expYear.getText().toString(), true);

         }
      });

      btn_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });


      iv_clearAll.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            et_card.setText("");
            et_card.setEnabled(true);
            et_card.setAlpha(1f);

            btn_save.setVisibility(View.VISIBLE);
            et_expMonth.setText("");
            et_expYear.setText("");
            iv_clearAll.setVisibility(View.INVISIBLE);
            et_card.requestFocus();
            sp_cards.setSelection(0);
         }
      });
      ll_close.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });
   }


   @Override
   public void dismiss() {
      super.dismiss();
      // Statics.hideKeyboard(getContext(), getCurrentFocus());
      getWindow().setSoftInputMode(
              WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
      );
   }

   private void loadCardTokens(ArrayList<String> cardTokens) {
      //  ArrayList<Model_CardToken> cardTokens = repository_cardTokens.getCardTokens_cache();
      cardTokens.add("6037-99**-****-2548");
      cardTokens.add("5022-29**-****-2596");
      if (cardTokens.size() == 0) {
         tv_cards_empty.setVisibility(View.VISIBLE);
         sp_cards.setVisibility(View.GONE);
         return;
      }

      final Adapter_Cards2 adapter = new Adapter_Cards2(cardTokens, context);
      sp_cards.setAdapter(adapter);
      sp_cards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                  dismiss();
                  callback.onDialogCard_GotCard(adapter.getItem(position),"","",true);

               }
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {
         }
      });
   }

   public void btnCardDialogCancel(View dialog) {

      // Statics.hideKeyboard(getContext(), ll_close);
      this.dismiss();


   }

   void setupViews() {
      et_card = findViewById(R.id.et_card);
      et_expYear = findViewById(R.id.et_expYear);
      et_expMonth = findViewById(R.id.et_expMonth);

      btn_save = findViewById(R.id.btn_save);
      btn_use = findViewById(R.id.btn_use);
      btn_back = findViewById(R.id.btn_back);
      btn_add = findViewById(R.id.btn_add);

      sp_cards = findViewById(R.id.sp_cards);
      tv_cards_empty = findViewById(R.id.tv_cards_empty);


      iv_clearAll = findViewById(R.id.iv_dialog_card_clearAll);

      pb_cardLoad = findViewById(R.id.cardloadPB);

      ll_close = findViewById(R.id.ll_close);
      holder_btns_usesave = findViewById(R.id.holder_buttons_useSave);
      holder_btns_add = findViewById(R.id.holder_buttons_add);


   }

   public interface DialogCardCallback {
      void onDialogCard_GotCard(String cardno, String expMonth, String expYear, boolean save);

   }
}
