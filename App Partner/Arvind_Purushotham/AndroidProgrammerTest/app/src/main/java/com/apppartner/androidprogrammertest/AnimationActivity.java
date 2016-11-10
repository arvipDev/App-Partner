package com.apppartner.androidprogrammertest;

import android.content.ClipData;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.controllers.CustomToolbarSvc;
import com.apppartner.androidprogrammertest.models.MachinatoFont;

/*
        -------------------------- IMPORTANT MESSAGE -----------------------------
        Please view the developer notes, section 4.0 for important observations
        --------------------------------------------------------------------------
*/

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        CustomToolbarSvc toolbar = new CustomToolbarSvc(this, "Animation");
        toolbar.activateToolbar();

        AssetManager manage = getApplicationContext().getAssets();

        ImageView iv_animation_icon = (ImageView) findViewById(R.id.iv_animation_icon);
        iv_animation_icon.setOnTouchListener(new MyTouchListener());
        iv_animation_icon.setOnDragListener(new MyDragListener());

        TextView tv_animation_text = (TextView) findViewById(R.id.tv_animation_text);
        tv_animation_text.setText("Animate the App Partner icon. Make it fade to 0% alpha " +
                "and then to 100% alpha when the fade button is pressed. Allow it to be dragged around " +
                "the screen by touching it and dragging.");
        tv_animation_text.setTypeface(MachinatoFont.getExtraLight(manage));
        TextView tv_animation_text_bold = (TextView) findViewById(R.id.tv_animation_text_bold);
        tv_animation_text_bold.setTypeface(MachinatoFont.getSemiBold(manage));
    }

    // The other lifecycles of the activity are not required.

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onBackButtonPressed(View view) {
        finish();
    }

    public void animate(View view) {
        ImageView iv_animation_icon = (ImageView) findViewById(R.id.iv_animation_icon);
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);
        iv_animation_icon.startAnimation(anim);
    }

    //WARNING - Used a depreciated method
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(clipData, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    //The description mentioned the requirement to only drag the image, therefore
    // no drop is impplemented, the co-ordinates are not recorded.
    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundResource(R.drawable.ic_apppartner);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundResource(R.drawable.ic_apppartner);
                    break;
                case DragEvent.ACTION_DROP:
                    v.setBackgroundResource(R.drawable.ic_apppartner);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundResource(R.drawable.ic_apppartner);
                default:
                    v.setBackgroundResource(R.drawable.ic_apppartner);
                    break;
            }
            v.setVisibility(View.VISIBLE);
            return true;
        }
    }
}
