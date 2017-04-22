package kz.askar.sprite;

import android.graphics.Bitmap;
import android.graphics.Rect;


public class Man {
    public int currentI = 1;
    public int currentJ = 1;

    public Bitmap image;

    private Rect source;
    private Rect dest;

    int pngX ;
    int pngY;


    public Man(Bitmap image){
        this.image = image;
        pngX = image.getWidth()/6;
        pngY = image.getHeight()/3;
        source = new Rect(pngX*5,0,pngX*6,pngY);
    }

    public void moveRight(){
        currentJ++;
        if(currentJ%2==0){
        source = new Rect(pngX*5,0,pngX*6,pngY);}
        else source = new Rect(pngX*5,pngY,pngX*6,pngY*2);
    }
    public void moveLeft(){
        currentJ--;
        if(currentJ%2==0) source = new Rect(pngX*4,pngY,pngX*5,pngY*2);
        else source = new Rect(pngX*4,0,pngX*5,pngY);
    }
    public void moveDown(){
        currentI++;
        source = new Rect(pngX*5,pngY,pngX*6,pngY*2);
    }
    public void moveUp(){
        currentI--;
        source = new Rect(pngX*2,0,pngX*3,pngY);
    }



    public Rect getSrcRect(){
        return source;
    }

    public Rect getDestinationRect(){
        return dest;
    }

    public void updateRectangles(){
        int ml = DrawView.rectMultiplier;
        dest = new Rect(currentJ*ml,currentI*ml,(currentJ+1)*ml,(currentI+1)*ml);
    }



}
