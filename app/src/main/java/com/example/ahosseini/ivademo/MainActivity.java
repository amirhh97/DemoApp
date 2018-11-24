package com.example.ahosseini.ivademo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
   Button btn_continue;
   EditText et_number;
   FrameLayout root;
   RelativeLayout progressBar;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      btn_continue=findViewById(R.id.btn_continue);
      et_number=findViewById(R.id.phone_number);
      root=findViewById(R.id.frame);
      progressBar=findViewById(R.id.progress);
      addFragment(new FragmentNumber());
   }
   void addFragment(final Fragment fragment){
      progressBar.setVisibility(View.VISIBLE);
      new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_right,R.anim.slide_left,R.anim.pop_enter,R.anim.pop_exit);
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
            progressBar.setVisibility(View.GONE);

         }
      },1000);

   }

   @Override
   public void onBackPressed() {
      getSupportFragmentManager().popBackStack();
      if(getSupportFragmentManager().getBackStackEntryCount()==1){
         super.onBackPressed();      }

   }

   @Override
   protected void attachBaseContext(Context newBase) {
      super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
   }
}
