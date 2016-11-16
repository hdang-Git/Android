package com.example.hai.lab7;

import android.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.example.hai.lab7.R.id.frag1;

public class MainActivity extends AppCompatActivity {
    ArrayList<WebFragment> fragments;
    FragmentManager fm = getFragmentManager();
    int currentIndex = 0;

    Logger log = Logger.getAnonymousLogger();
    EditText textField;
    String input;
    WebFragment receiver = new WebFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        fragments = new ArrayList<>();
        //textField = (EditText) findViewById(R.id.editText);

        //Disable title
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);


        fm
          .beginTransaction()
          .add(frag1, receiver)
          .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_refresh:
                //User pressed the "refresh" button to refresh the page
                log.info("Refresh Pressed.");
                textField = (EditText) findViewById(R.id.editText);
                input = textField.getText().toString();
                log.info("You entered: " + input);
                receiver.changeURL(input);
                return true;

            case R.id.action_prev:
                //User chose the "previous" action
                log.info("Prev Pressed.");
                return true;

            case R.id.action_next:
                //User chose the "next" action
                log.info("Next Pressed.");
                return true;

            case R.id.action_new:
                //User chose the "new" action to get a new window
                log.info("New Pressed.");

                fragments.add(new WebFragment());
                currentIndex = fragments.size() - 1;
                fm.beginTransaction()
                        .replace(R.id.frag1, fragments.get(currentIndex))
                        .commit();
                fm.executePendingTransactions();
                /*
                textField = (EditText) findViewById(R.id.editText);
                textField.setText("");
                log.info("You entered: " + input);
                fragments.get(currentIndex).changeURL(input);
                //receiver.changeURL(input);
                */
                return true;

            case R.id.action_back:
                // User chose the "back" item...
                log.info("Back Pressed.");
                return true;

            case R.id.action_forward:
                // User chose the "forward" action
                log.info("Forward Pressed.");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
