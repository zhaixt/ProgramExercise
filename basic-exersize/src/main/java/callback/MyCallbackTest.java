package callback;

import java.util.Date;

/**
 * Created by zhaixt on 2018/5/27.
 */
public class MyCallbackTest {
    public static void main(String[] args){
        CallbackManager callbackManager = new CallbackManager();
        callbackManager.doSomeThing("xiaoming",user ->
        {
            System.out.println(new Date().toString()+",sure,This is "+user);
//            try {
//
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
//        callbackManager.doAsyncThing("xiaoming");
//        System.out.println(new Date().toString()+",wa,xiaoming!");

    }
    interface Callback{
        public void doCallback(String user);
    }

    public static class CallbackManager{
        public void doSomeThing(String user,Callback userCallBack){
            System.out.println(new Date().toString()+",oh,it's "+user+"!");
        }


        public void doAsyncThing(String user,Callback userCallBack){
            System.out.println(new Date().toString()+",executing search "+user);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userCallBack.doCallback("xiaoming");
        }
    }

    public class Result{
        private Callback userCallback;
        public void calculateResult(Callback userCallBack){
            this.userCallback = userCallBack;


        }
    }
}


