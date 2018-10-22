package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1.最简单的平铺直述
        canvas.save();
        canvas.translate(point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);// 3.将图形移动回到原来位置
        camera.save();
        camera.rotateX(30);// 2.以原点为中心旋转
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-point1.x - bitmap.getWidth() / 2, -point1.y - bitmap.getHeight() / 2);// 1.将图形中心移动到原点
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();
        /**
         * 1.canvas的组合操作是倒序执行的
         * 2.camera旋转是以左上角原点为圆心，先将图形移动到原点位置
         * 3.旋转完之后再移动回到原来位置
         */

        // 2.matrix结合canvas
        canvas.save();
        camera.save();
        camera.rotateY(30);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-point2.x - bitmap.getWidth() / 2, -point2.y - bitmap.getHeight() / 2);// rotate之前先移动中心到原点
        matrix.postTranslate(point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);// rotate之后移动到原来的位置
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
