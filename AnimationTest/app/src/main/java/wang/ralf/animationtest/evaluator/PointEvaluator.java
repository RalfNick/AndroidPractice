package wang.ralf.animationtest.evaluator;

import android.animation.TypeEvaluator;

import wang.ralf.animationtest.entity.MyPoint;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PointEvaluator
 * @email -
 * @date 2019/05/03 11:23
 **/
public class PointEvaluator implements TypeEvaluator<MyPoint> {

    @Override
    public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
        int x = (int) (fraction * (endValue.getX() - startValue.getX()) + startValue.getX());
        int y = (int) (fraction * (endValue.getY() - startValue.getY()) + startValue.getY());
        return new MyPoint(x, y);
    }
}
