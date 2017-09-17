package com.example.tayfun.mforums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagListActivity extends AppCompatActivity {

    public static List<String> tagList = new ArrayList<String>();
    MyDBHandler myDBHandler;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Chose Tags");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            ImageButton imageButton = (ImageButton) mToolbar.findViewById(R.id.new_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getTags();

                    Intent intent = new Intent(TagListActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
    public void getTags(){
        tagList.clear();//to avoid overrite
        LinearLayout layout = (LinearLayout)findViewById(R.id.tagListLayout);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if(v instanceof CheckBox){
                if(((CheckBox) v).isChecked()){
                    tagList.add((String)((CheckBox) v).getText());
                }
            }
        }
        //put the list to database
        myDBHandler = new MyDBHandler(this,null,null,1);
        myDBHandler.deleteAll("products");
        Products products = new Products(tagList.toString());
        myDBHandler.addProduct(products);
    }
    //override onBackpressed it always goes to home activity (to eliminate visual mismatches)
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(TagListActivity.this,ProfileActivity.class);
        startActivity(intent1);
    }
}
