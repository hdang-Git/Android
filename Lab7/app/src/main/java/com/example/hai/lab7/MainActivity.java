package com.example.hai.lab7;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

import static android.R.attr.keycode;
import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_ENTER;

public class MainActivity extends AppCompatActivity {
    ArrayList<WebFragment> fragments = new ArrayList<>();
    int currentIndex = 0;
    int sizeIndex = 0;
    int fragNum = 1;
    Logger log = Logger.getAnonymousLogger();
    EditText textField;
    String input;
    WebFragment receiver;
    ViewPager pager;
    PagerAdapter pa;
    boolean addressBarLoaded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Set up view pager
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3); // the number of "off screen" pages to keep loaded each side of the current page
        pager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

        textField = (EditText) findViewById(R.id.editText);


        //textField.setOnKeyListener(null);
        //textField.setOnEditorActionListener(null);
        //textField.setImeOptions(EditorInfo.IME_ACTION_GO);

        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                log.info("onEditorAction called");
                if(actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                    log.info("Enter key pressed");
                    go();
                }
                return false;
            }
        });

        //Disable title
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        //Service other applications that call this app
        Uri data = getIntent().getData();
        if(data != null) {
            String url = data.toString();
            receiver.changeURL(url);
        }
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                default:
                    WebFragment frag = new WebFragment();
                    fragments.add(sizeIndex, frag);
                    sizeIndex++;
                    return frag;
            }
        }

        @Override
        public int getCount() {
            return fragNum;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(addressBarLoaded){
            menu.getItem(0).setIcon(R.drawable.ic_arrow_forward_black_24dp);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_refresh_black_24dp);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_go:
                go();
                return true;

            case R.id.action_prev:
                //User chose the "previous" action
                log.info("Prev Pressed.");
                pager.setCurrentItem(pager.getCurrentItem()-1);

                return true;

            case R.id.action_next:
                //User chose the "next" action
                log.info("Next Pressed.");
                pager.setCurrentItem(pager.getCurrentItem()+1);
                return true;

            case R.id.action_new:
                //User chose the "new" action to get a new window
                log.info("New Pressed.");
                //Create new fragment and add web frag to array
                WebFragment fragment = new WebFragment();
                fragments.add(sizeIndex, fragment);
                fragNum++;
                //link to adapter and notify of the change
                pa = pager.getAdapter();
                pa.notifyDataSetChanged();
                receiver = fragment;
                pager.setCurrentItem(sizeIndex);

                //Once the new button is pressed, call onPrepareOptionsMenu() to change to go icon
                invalidateOptionsMenu();
                addressBarLoaded = true;
                return true;

            case R.id.action_back:
                // User chose the "back" item...
                log.info("Back Pressed.");
                receiver.goBackward();
                return true;

            case R.id.action_forward:
                // User chose the "forward" action
                log.info("Forward Pressed.");
                receiver.goForward();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void go(){
        //User pressed the "go" button to go to the page
        log.info("Go Pressed.");
        //Once the go button is pressed, call onPrepareOptionsMenu() to change to refresh icon
        invalidateOptionsMenu();
        //Save current state
        currentIndex = pager.getCurrentItem();
        receiver = fragments.get(currentIndex);
        input = textField.getText().toString();
        //Call fragment to load url
        receiver.changeURL(input);
        addressBarLoaded = false;
    }
    /*
        if(event.getAction() == EditorInfo.IME_ACTION_DONE && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            log.info("Success on enter key");
            input = textField.getText().toString();
            if(input != null)
                receiver.changeURL(input);
        }*/
    /*
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //if in edittext the url is typed in with enter key pressed, pass the url
        log.info("onKeyUp() called");


        switch(keyCode){
            case KeyEvent.KEYCODE_ENTER:
                input = textField.getText().toString();
                receiver.changeURL(input);
                return true;

            default:
                return super.onKeyUp(keyCode, event);
        }
    }
    */
}
