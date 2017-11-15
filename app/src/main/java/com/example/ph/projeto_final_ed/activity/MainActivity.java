package com.example.ph.projeto_final_ed.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.ph.projeto_final_ed.R;
import com.example.ph.projeto_final_ed.fragment.ListFragment;
import com.example.ph.projeto_final_ed.fragment.QueueFragment;
import com.example.ph.projeto_final_ed.fragment.StackFragment;
import com.example.ph.projeto_final_ed.fragment.TreeOptionFragment;
import com.example.ph.projeto_final_ed.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    list();
                    return true;
                case R.id.navigation_stack:
                    stack();
                    return true;
                case R.id.navigation_queue:
                    queue();
                    return true;
                case R.id.navigation_tree:
                    tree();
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

        list();


    }

    private void list(){
        Fragment listFragment = new ListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, listFragment);
        fragmentTransaction.commit();
    }
    private void stack(){
        Fragment stackFragment = new StackFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, stackFragment);
        fragmentTransaction.commit();
    }
    private void queue(){
        Fragment queueFragment = new QueueFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, queueFragment);
        fragmentTransaction.commit();
    }
    private void tree(){
        Fragment treeFragment = new TreeOptionFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, treeFragment);
        fragmentTransaction.commit();
    }

}
