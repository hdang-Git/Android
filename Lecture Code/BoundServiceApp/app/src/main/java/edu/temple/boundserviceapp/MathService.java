package edu.temple.boundserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MathService extends Service {
    IBinder binder = new MathBinder();
    //Short way - return reference to service
/*
    public MathService MathBinder extends Binder{
        public void getAllFunctions(){
            return MathService.this;
        }
    }
*/

    //Long way - only exposes addition & subtraction
    public class MathBinder extends Binder{
        public int addTwoNumbers(int a, int b){
            return MathService.this.addTwoNumbers(a, b);
        }

        //call subtract on binder, it calls subtract on service
        public int subtract(int a, int b){
            return MathService.this.subtract(a, b);
        }

        public MathService getService(){
            return MathService.this;
        }
    }
    //IBINDER object returns interface - used to tell any component what the services are
    //means that the binder that the service returns to client can vary
    /*
     *  Dtermine which function to expose to client
     *
     * */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public int addTwoNumbers(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public double exp(int a, int b){
        return 0;
    }
}
