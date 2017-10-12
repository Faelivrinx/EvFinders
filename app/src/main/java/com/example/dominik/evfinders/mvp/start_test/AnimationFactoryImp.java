package com.example.dominik.evfinders.mvp.start_test;

/**
 * Created by Dominik on 10.10.2017.
 */

public class AnimationFactoryImp implements AnimationFactory {

    @Override
    public AnimationState createState(int activeState) {

        switch (activeState) {

            case StartActivityTest.START_STATE:
                return AnimationState.START_STATE;

            case StartActivityTest.LOGIN_STATE:
                return AnimationState.LOGIN_STATE;

            case StartActivityTest.REGISTER_STATE:
                return AnimationState.REGISTER_STATE;

            default:
                throw new IllegalStateException();
        }

    }

}
