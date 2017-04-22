package kz.askar.sprite;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Bimurat Mukhtar on 22.04.rectMultiplier17.
 */

public class DrawView extends android.view.SurfaceView
        implements SurfaceHolder.Callback{

    public static int rectMultiplier = 100;
    boolean isRunning = false;
    SurfaceHolder surfaceHolder;

    Man man;

    public DrawView(Context context,Man man) {
        super(context);
        this.man = man;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;
        isRunning = true;
        long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if(canvas == null) continue;
                    drawRect(canvas);
                    drawMan(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(50);
                        update();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        long drawTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    public void update(){
        if(man!=null){
            man.updateRectangles();
        }
    }

    public void drawRect(Canvas canvas){
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setStrokeWidth(1);
        canvas.drawColor(Color.WHITE);

        for(int i = 0; i<6; i++){
            for(int j = 0; j<6;j++){
                if(SecondFragment.m[i][j]==1){
                    p.setColor(Color.BLACK);
                    canvas.drawRect(j*rectMultiplier,i*rectMultiplier,(j+1)*rectMultiplier,(i+1)*rectMultiplier,p);
                }else{
                    p.setColor(Color.RED);
                    canvas.drawRect(j*rectMultiplier,i*rectMultiplier,(j+1)*rectMultiplier,(i+1)*rectMultiplier,p);
                }
            }
        }
    }

    public void drawMan(Canvas canvas){
        man.updateRectangles();
        canvas.drawBitmap(man.image, man.getSrcRect(),
                man.getDestinationRect(), null);

    }
}