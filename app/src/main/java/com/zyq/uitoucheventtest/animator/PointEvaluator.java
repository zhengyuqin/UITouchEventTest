package com.zyq.uitoucheventtest.animator;

import android.animation.TypeEvaluator;

/**
 * @author zyq 15-8-29
 */
public class PointEvaluator implements TypeEvaluator {


	@Override
	public Object evaluate(float fraction, Object startValue, Object endValue) {
		Point startPoint = (Point) startValue;
		Point endPoint = (Point) endValue;
		float x = startPoint.getX() + (endPoint.getX() - startPoint.getX()) * fraction;
		float y = startPoint.getY() + (endPoint.getY() - startPoint.getY()) * fraction;
		Point point = new Point(x, y);
		return point;
	}
}
