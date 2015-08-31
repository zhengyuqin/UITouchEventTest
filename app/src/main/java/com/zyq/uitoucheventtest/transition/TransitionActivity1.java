package com.zyq.uitoucheventtest.transition;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.zyq.uitoucheventtest.R;

public class TransitionActivity1 extends ActionBarActivity {

    private Scene scene1;
    private Scene scene2;
    private Transition transition;
    private TransitionManager mTransitionManager;
    private boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);


        RelativeLayout baseLayout = (RelativeLayout)findViewById(R.id.base);
        ViewGroup startViews = (ViewGroup)getLayoutInflater()
                .inflate(R.layout.start_layout, baseLayout, false);
        ViewGroup endViews = (ViewGroup)getLayoutInflater()
                .inflate(R.layout.end_layout, baseLayout, false);



        scene1 = new Scene(baseLayout, startViews);
        scene2 = new Scene(baseLayout, endViews);

//        scene1 = Scene.getSceneForLayout(baseLayout,R.layout.start_layout,this);
//        scene2 = Scene.getSceneForLayout(baseLayout,R.layout.end_layout,this);


        TransitionInflater transitionInflater = TransitionInflater.from(this);
        mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_manager,baseLayout);
        transition = new AutoTransition();
        transition.setDuration(2000);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        //initialize flag
        start=true;

    }

    public void changeScene(View v){

        //check flag
        if(start) {
            //TransitionManager.go(scene2, transition);
            mTransitionManager.transitionTo(scene2);
            start=false;
        }
        else {
            //TransitionManager.go(scene1, transition);
            mTransitionManager.transitionTo(scene1);
            start=true;
        }
    }

}
