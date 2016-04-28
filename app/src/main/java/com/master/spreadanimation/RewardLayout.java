package com.master.spreadanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Master on 2016/4/18.
 */
public class RewardLayout extends RelativeLayout {
    private Bitmap mBackgroundBitmap = null;
    private Paint mPaint=null;
    public RewardLayout(Context context) {
        super(context);
        initialize();
    }

    public RewardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public RewardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize(){

        initViews();
        mPaint=new Paint();
        mPaint.setFilterBitmap(false);
    }


    private void initViews() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.reward_popup, this, true);

    }

    public void startAnimation(){
        doAnimation(0.0f,1.0f,false);
    }

    private  void stopAnimation(){
        doAnimation(1.0f,0.0f,true);
    }

    public void doAnimation(float start , float end,final boolean isHideView){
        if (mBackgroundBitmap == null) {
            buildDrawingCache();

//            mBackgroundBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            mBackgroundBitmap=  getDrawingCache();
        }
        ValueAnimator animator= ValueAnimator.ofFloat(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float)animation.getAnimatedValue();

                int width = mBackgroundBitmap.getWidth();
                int height = mBackgroundBitmap.getHeight();
                Bitmap.Config config = mBackgroundBitmap.getConfig();

                int  bWidth=(int)(width*value);
                if(bWidth==0){
                    bWidth=1;
                }

                int bHeight=(int)(height*value*7);

                if(bHeight==0){
                    bHeight=1;
                }

                if(bHeight>height){
                    bHeight=height;
                }


                Bitmap newImage = Bitmap.createBitmap(bWidth, bHeight, config);

                Canvas canvas = new Canvas(newImage);
                canvas.drawBitmap(mBackgroundBitmap, 0, 0, null);

                mPaint.setXfermode(  new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                canvas.drawBitmap(newImage, 0, 0, mPaint);
                mPaint.setXfermode(null);

//                setImageBitmap(newImage);


            }
        });

        animator.setDuration(2000);
        animator.setRepeatCount(0);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
        //5.为ValueAnimator设置目标对象并开始执行动画
        animator.setTarget(RewardLayout.this);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if(isHideView){
                    RewardLayout.this.setVisibility(View.GONE);
                }else{
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stopAnimation();
                        }
                    },3000);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }

}
