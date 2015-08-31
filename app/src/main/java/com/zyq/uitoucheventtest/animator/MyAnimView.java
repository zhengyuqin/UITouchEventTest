package com.zyq.uitoucheventtest.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

/**
 * @author zyq 15-8-29
 */
public class MyAnimView extends View {

	public static final float RADIUS = 50f;
	private String color;
	private Point currentPoint;

	private Paint mPaint;

	public MyAnimView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (currentPoint == null) {
			currentPoint = new Point(RADIUS, RADIUS);
			drawCircle(canvas);
			startAnimation(new BounceInterpolator());
		} else {
			drawCircle(canvas);
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		mPaint.setColor(Color.parseColor(color));
		invalidate();
	}

	private void drawCircle(Canvas canvas) {
		float x = currentPoint.getX();
		float y = currentPoint.getY();
		canvas.drawCircle(x, y, RADIUS, mPaint);
	}

	private void startAnimation(Interpolator interpolator) {
		Point startPoint = new Point(getWidth()/2, RADIUS);
		Point endPoint = new Point(getWidth()/2, getHeight() - RADIUS);
		final ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				currentPoint = (Point) animation.getAnimatedValue();
				invalidate();
			}
		});
		anim.setInterpolator(new BounceInterpolator());
		ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
		AnimatorSet animSet = new AnimatorSet();
		animSet.play(anim).with(anim2);
		animSet.setDuration(5000);
		animSet.start();

	}
}

