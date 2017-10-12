package com.example.dominik.evfinders.mvp.start_test;

import android.graphics.drawable.AnimationDrawable;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Dominik on 10.10.2017.
 */

public abstract class CustomAnimation extends AnimationDrawable {

//    /** Handles the animation callback. */
//    Handler mAnimationHandler;
//
//    public CustomAnimation(AnimationDrawable aniDrawable) {
//        /* Add each frame to our animation drawable */
//        for (int i = 0; i < aniDrawable.getNumberOfFrames(); i++) {
//            this.addFrame(aniDrawable.getFrame(i), aniDrawable.getDuration(i));
//        }
//    }
//
//    @Override
//    public void start() {
//        super.start();
//        /*
//         * Call super.start() to call the base class start animation method.
//         * Then add a handler to call onAnimationFinish() when the total
//         * duration for the animation has passed
//         */
//        mAnimationHandler = new Handler() {
//            @Override
//            public void publish(LogRecord logRecord) {
//
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public void close() throws SecurityException {
//
//            }
//        };
//        mAnimationHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                onAnimationStart();
//            }
//        };
//        mAnimationHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                onAnimationFinish();
//            }
//        }, getTotalDuration());
//
//    }
//
//    /**
//     * Gets the total duration of all frames.
//     *
//     * @return The total duration.
//     */
//    public int getTotalDuration() {
//
//        int iDuration = 0;
//
//        for (int i = 0; i < this.getNumberOfFrames(); i++) {
//            iDuration += this.getDuration(i);
//        }
//
//        return iDuration;
//    }
//
//    /**
//     * Called when the animation finishes.
//     */
//    public abstract void onAnimationFinish();
//    /**
//     * Called when the animation starts.
//     */
//    public abstract void onAnimationStart();
}
