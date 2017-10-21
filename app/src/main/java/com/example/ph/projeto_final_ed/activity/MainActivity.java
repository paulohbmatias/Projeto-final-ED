package com.example.ph.projeto_final_ed.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.fragment.ListFragment;
import com.example.ph.projeto_final_ed.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    Fragment listFragment, stackFragment, queueFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    list();
                    return true;
                case R.id.navigation_stack:
                    //mTextMessage.setText(R.string.title_stack);
                    return true;
                case R.id.navigation_queue:
                    //mTextMessage.setText(R.string.title_queue);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        listFragment = new ListFragment();
        list();


    }

    private void list(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, listFragment);
        fragmentTransaction.commit();
    }

}
