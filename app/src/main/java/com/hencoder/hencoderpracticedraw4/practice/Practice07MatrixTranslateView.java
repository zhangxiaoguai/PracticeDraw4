package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice07MatrixTranslateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Matrix matrix1;
    Matrix matrix2;

    public Practice07MatrixTranslateView(Context context) {
        super(context);
    }

    public Practice07MatrixTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07MatrixTranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        matrix1 = new Matrix();
        matrix1.postTranslate(-point1.x / 2, -point1.y / 2);
        matrix1.preRotate(180, point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);

        matrix2 = new Matrix();
//        matrix2.preTranslate(100, 0);
//        matrix2.postRotate(90, point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);

        matrix2.postTranslate(100, 0);
        matrix2.preRotate(90, point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        /**
         * Matrix关于平移旋转也是有顺序区别的，先旋转和先平移效果不同
         */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        /**
         * concat:用 Canvas 当前的变换矩阵和 Matrix 相乘，即基于 Canvas 当前的变换，叠加上 Matrix 中的变换。
         */
        canvas.concat(matrix1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        /**
         * set:用 Matrix 直接替换 Canvas 当前的变换矩阵，即抛弃 Canvas 当前的变换，改用 Matrix 的变换，不同的系统中 setMatrix(matrix) 的行为可能不一致，所以尽量用 concat(matrix)
         */
        canvas.setMatrix(matrix2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
