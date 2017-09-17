package com.example.tayfun.mforums;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    Toolbar mToolbar;
    MyDBHandler myDBHandler;
    private ArrayAdapter<String> adapter;
    private CheckBox mCheckBox;
    private EditText mEditText;
    private CircleImageView mCircleImageView;
    private LinearLayout mLinearLayout;
    private ListView mlistView;
    private String[] tagArray = {"","","","","","",""};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent1 = new Intent(ProfileActivity.this,MainActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(ProfileActivity.this,SendQuestionActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        navigationHighlight();

        mCheckBox = (CheckBox)findViewById(R.id.editProfile);
        mEditText = (EditText)findViewById(R.id.profileName);
        mCircleImageView = (CircleImageView)findViewById(R.id.profileImage);
        mLinearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        mlistView = (ListView)findViewById(R.id.myTags);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Profile");
            setSupportActionBar(mToolbar);
        }
        putTagsToList();
    }

    private void putTagsToList(){
        //put tag list from database to listview
        myDBHandler = new MyDBHandler(this,null,null,1);
        String profileTags = myDBHandler.databaseToString();

        Log.d("TAG: ",profileTags);
        int i = 1;
        int arrayIndex = 0;
        while(profileTags.charAt(i)!=']'){
            tagArray[arrayIndex] += ""+profileTags.charAt(i);
            if(profileTags.charAt(i+1)==','){
                arrayIndex += 1;
                i+=2;
            }
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, tagArray);
        mlistView.setAdapter(adapter);
    }
    //highlight navigation bar icon which we are on
    private void navigationHighlight(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
    }

    public void itemClicked(View view) {
        if(mCheckBox.isChecked()){
            Log.d("TAG: ","checked");
            mEditText.setEnabled(true);
        }else{
            Log.d("TAG: ","NOTchecked");
            mEditText.setEnabled(false);
        }
    }

    public void onTagClicked(View view) {
        Intent intent = new Intent(ProfileActivity.this,TagListActivity.class);
        startActivity(intent);
    }
}
