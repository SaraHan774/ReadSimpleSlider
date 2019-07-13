//package com.gahee.rss_v1.helpers;
//
//import android.widget.Toast;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class Timers {
//
//    Timer timer;
//    int page = 0;
//
//    public void pageSwitcher(int seconds) {
//        timer = new Timer(); // At this line a new Thread will be created
//        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
//    }
//
//    // this is an inner class...
//    class RemindTask extends TimerTask {
//
//        @Override
//        public void run() {
//            // As the TimerTask run on a seprate thread from UI thread we have
//            // to call runOnUiThread to do work on UI thread.
//            runOnUiThread(new Runnable() {
//                public void run() {
//
//                    if (page > 1) {
//                        timer.cancel();
//                        // Showing a toast for just testing purpose
//                        Toast.makeText(getApplicationContext(), "Timer stoped",
//                                Toast.LENGTH_LONG).show();
//                    } else {
//                        viewPager.setCurrentItem(page++);
//                    }
//                }
//            });
//
//        }
//    }
//}
