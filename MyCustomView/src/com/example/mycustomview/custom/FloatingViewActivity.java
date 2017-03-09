package com.example.mycustomview.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.floatingview.Floating;
import com.xuexiang.view.floatingview.FloatingBuilder;
import com.xuexiang.view.floatingview.FloatingElement;
import com.xuexiang.view.floatingview.effect.ScaleFloatingTransition;
import com.xuexiang.view.floatingview.effect.TranslateFloatingTransition;
import com.xuexiang.view.floatingview.spring.ReboundListener;
import com.xuexiang.view.floatingview.spring.SimpleReboundListener;
import com.xuexiang.view.floatingview.spring.SpringHelper;
import com.xuexiang.view.floatingview.transition.BaseFloatingPathTransition;
import com.xuexiang.view.floatingview.transition.FloatingPath;
import com.xuexiang.view.floatingview.transition.FloatingTransition;
import com.xuexiang.view.floatingview.transition.PathPosition;
import com.xuexiang.view.floatingview.transition.YumFloating;

public class FloatingViewActivity extends BaseActivity {

    private Floating floating;
    private View icPlaneView;
    private View icPaperAirPlaneView;
    private int screenWidth;
    private int screenHeight;

    @Override
	public void onCreateActivity() {
    	 setContentView(R.layout.activity_floatingview);
    	 initTitleBar(TAG);
         floating = new Floating(this);

//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//             WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//             localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//         }

         screenWidth = DisplayUtils.getScreenWidth(this);
         screenHeight = DisplayUtils.getScreenWidth(this);
         initLayout();
	}

    private void initLayout() {
        
        int margin = DisplayUtils.dip2px(this,15);
        int w = screenWidth - margin * 2;
        int h = (int) (w * 0.53f);
        
        RelativeLayout bikeRootView = (RelativeLayout) findViewById(R.id.itemBikeContainerView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bikeRootView.getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = h;
        
        RelativeLayout clockRootView = (RelativeLayout) findViewById(R.id.itemClockContainerView);
        RelativeLayout.LayoutParams clockRootViewLayoutParams = (RelativeLayout.LayoutParams) clockRootView.getLayoutParams();
        clockRootViewLayoutParams.width = w;
        clockRootViewLayoutParams.height = h;
        
        icPlaneView = findViewById(R.id.icPlane);
        icPlaneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = new ImageView(FloatingViewActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPlaneView.getMeasuredWidth(), icPlaneView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.ic_floating_plane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new PlaneFloating())
                        .build();
                floating.startFloating(floatingElement);
            }
        });

        icPaperAirPlaneView = findViewById(R.id.icPaperAirPlane);
        icPaperAirPlaneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(FloatingViewActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPaperAirPlaneView.getMeasuredWidth(), icPaperAirPlaneView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.ic_paper_airplane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                floating.startFloating(floatingElement);
            }
        });
        
        View icCommandLineView = findViewById(R.id.icCommandLine);
        icCommandLineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(FloatingViewActivity.this);
                textView.setText("Hello FloatingView");

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(textView)
                        .offsetY(-v.getMeasuredHeight())
                        .floatingTransition(new ScaleFloatingTransition())
                        .build();
                floating.startFloating(floatingElement);
            }
        });
        
        View icLikeView = findViewById(R.id.icLike);
        icLikeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(R.layout.layout_like)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                floating.startFloating(floatingElement);
                
            }
        });
        
        
        final View icStarView  = findViewById(R.id.icStar);
        icStarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                ImageView imageView = new ImageView(FloatingViewActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icStarView.getMeasuredWidth(), icStarView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.ic_star_floating);

                final FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new StarFloating())
                        .build();
                
                SpringHelper.createWithBouncinessAndSpeed(0f,1f,11,15).reboundListener(new ReboundListener() {
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        v.setScaleX((float) currentValue);
                        v.setScaleY((float) currentValue);
                    }

                    @Override
                    public void onReboundEnd() {
                        floating.startFloating(floatingElement);
                    }
                }).start();
                
                
                
            }
        });
        
        
        final View icBeerView  = findViewById(R.id.icBeer);
        icBeerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(FloatingViewActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icBeerView.getMeasuredWidth(), icBeerView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.ic_beer);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new BeerFloating())
                        .build();
                floating.startFloating(floatingElement);
            }
        });
    }

    class PlaneFloating extends BaseFloatingPathTransition {

        @Override
        public FloatingPath getFloatingPath() {
            Path path = new Path();
            path.moveTo(0, 0);
            path.quadTo(1200, -300, 0, -600);
            path.rLineTo(0, -screenHeight - 300);
            return FloatingPath.create(path, false);
        }

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            
            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(getStartPathPosition(), getEndPathPosition());
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (Float) valueAnimator.getAnimatedValue();
                    PathPosition floatingPosition = getFloatingPosition(value);
                    yumFloating.setTranslationX(floatingPosition.x);
                    yumFloating.setTranslationY(floatingPosition.y);

                }
            });
            translateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.setTranslationX(0);
                    yumFloating.setTranslationY(0);
                    yumFloating.setAlpha(0f);
                    yumFloating.clear();
                }
            });
            

            SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f, 14, 15)
                    .reboundListener(new SimpleReboundListener() {
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);

            translateAnimator.setDuration(3000);
            translateAnimator.start();
        }
    }
    
    class StarFloating implements FloatingTransition {

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,10, 15)
                    .reboundListener(new SimpleReboundListener(){
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);


            ValueAnimator rotateAnimator = ObjectAnimator.ofFloat(0, 360);
            rotateAnimator.setDuration(500);
            rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
            rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
            rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yumFloating.setRotation((Float) valueAnimator.getAnimatedValue());
                }
            });

            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
            translateAnimator.setDuration(600);
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yumFloating.setTranslationY(-(Float) valueAnimator.getAnimatedValue());
                }
            });
            translateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.setAlpha(0f);
                    yumFloating.clear();
                }
            });
            rotateAnimator.start();
            translateAnimator.start();
        }
    }
    
    
    class BeerFloating extends BaseFloatingPathTransition{

        
        @Override
        public FloatingPath getFloatingPath() {
            Path path = new Path();
            path.rLineTo(-100,0);
            path.quadTo(0,-200,100,0);
            path.quadTo(0,200,-100,0);
            return FloatingPath.create(path, false);
        }

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
            translateAnimator.setDuration(600);
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (Float) valueAnimator.getAnimatedValue();
                    PathPosition floatingPosition = getFloatingPosition(value);
                    yumFloating.setTranslationX(floatingPosition.x);
                    yumFloating.setTranslationY(floatingPosition.y);

                }
            });
           

            ValueAnimator alphaAnimation = ObjectAnimator.ofFloat(1f,0f);
            alphaAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    yumFloating.setAlpha((Float) animation.getAnimatedValue());
                }
            });
            alphaAnimation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.clear();
                }
            });
            alphaAnimation.setStartDelay(550);
            alphaAnimation.setDuration(300);
            translateAnimator.start();
            alphaAnimation.start();
        }
    }

}
