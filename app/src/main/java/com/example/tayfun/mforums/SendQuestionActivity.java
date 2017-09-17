package com.example.tayfun.mforums;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SendQuestionActivity extends AppCompatActivity {

    Toolbar mToolbar;
    private EditText mTitle;
    private EditText mContent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent1 = new Intent(SendQuestionActivity.this,MainActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    Intent intent3 = new Intent(SendQuestionActivity.this,ProfileActivity.class);
                    startActivity(intent3);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_question);
        navigationHighlight();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (EditText) findViewById(R.id.title);
        mContent = (EditText) findViewById(R.id.content);

        if (mToolbar != null) {
            mToolbar.setTitle("Send Question");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //When click send button
            ImageButton imageButton = (ImageButton) mToolbar.findViewById(R.id.new_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Test...Will add backend later this is just for visualization
                    //passes header and text to Main Activity's list
                    for (int i = 0;i<15;i++){
                        if(MainActivity.stuff[i]=="" || MainActivity.stuff[i]=="No Posts Currently available"){
                            MainActivity.stuff[i] = mTitle.getText().toString();
                            MainActivity.substuff[i] = mContent.getText().toString();
                            i=15;
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            });
        }
    }
    //highlight navigation bar icon which we are on
    private void navigationHighlight(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }

    //Onclick top toolbar back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //override onbackpressed it always goes to home activity (to eliminate visual mismatches)
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(SendQuestionActivity.this,MainActivity.class);
        startActivity(intent1);
    }
}
