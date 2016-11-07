package edu.temple.cis3505_lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements PaletteFragment.SenderInterface {

    CanvasFragment receiver;
    boolean twoPanes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.frag2)!= null);
        Logger log = Logger.getAnonymousLogger();
        log.info("Two panes Value: " + twoPanes);

        //Load default spinner fragment
        PaletteFragment sender = new PaletteFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.frag1, sender)
                .commit();

        receiver = new CanvasFragment();
        //if canvas fragment is visible in current layout, load canvas fragment (show both fragments)
        if(twoPanes){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frag2, receiver)
                    .commit();
        }
    }

    private void doTransition(){
        Logger log = Logger.getAnonymousLogger();
        log.info("doTransition() is called");
        getFragmentManager()
                .beginTransaction()
                .add(R.id.frag1, receiver)
                .addToBackStack(null)
                .commit();
        getFragmentManager().executePendingTransactions();  //blocking call
    }

    //Overridden interface implementation
    @Override
    public void passColor(String color){
        if (!twoPanes){
            doTransition();
        }
        receiver.changeBackgroundColor(color);
    }
}
