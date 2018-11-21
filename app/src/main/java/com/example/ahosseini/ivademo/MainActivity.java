package com.example.ahosseini.ivademo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
   Button btn_continue;
   EditText et_number;
   RelativeLayout root;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      btn_continue=findViewById(R.id.btn_continue);
      et_number=findViewById(R.id.phone_number);
      root=findViewById(R.id.frame);
      btn_continue.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            root.removeAllViews();
            fragmentTransaction.add(R.id.frame,new FragmentAmount());
            fragmentTransaction.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
         }
      });
   }
}
