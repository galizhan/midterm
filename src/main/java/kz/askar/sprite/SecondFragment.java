package kz.askar.sprite;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{
    FragmentTransaction fragmentTransaction;
    int currentRow = 1; int currentCol = 1;
    public static int [][] m = {
            {0,0,0,0,0,0},
            {0,1,1,1,1,0},
            {0,0,1,1,1,0},
            {0,0,0,1,1,0},
            {0,0,0,1,1,0},
            {0,0,0,0,1,0},
    };
    Bitmap manImage;
    Man man;


    public SecondFragment() {
        // Required empty public constructor
    }
    SharedPreferences spf;
    long startTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, null);

        manImage = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        man = new Man(manImage);
        ((FrameLayout)v.findViewById(R.id.convas_container)).addView(new DrawView(getActivity(),man));
        Button up = (Button)v.findViewById(R.id.up);
        Button down = (Button)v.findViewById(R.id.down);
        Button left = (Button)v.findViewById(R.id.left);
        Button right = (Button)v.findViewById(R.id.right);
        startTime = System.currentTimeMillis();
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        return v;
    }



    @Override
    public void onClick(View v) {
        Log.d("current",currentCol+"");

            try {
                switch (v.getId()) {
                case R.id.up:

                    if ((currentRow - 1) >= 0 && m[currentRow - 1][currentCol] == 1) {
                        man.moveUp();
                        currentRow--;
                        isChanged(v);
                    }
                    break;
                case R.id.down:
                    if ((currentRow + 1) <= 5 && m[currentRow + 1][currentCol] == 1) {
                        man.moveDown();
                        currentRow++;
                        isChanged(v);
                    }
                    break;
                case R.id.left:
                    if ((currentCol - 1) >= 0 && m[currentRow][currentCol - 1] == 1) {
                        man.moveLeft();
                        if (currentCol >= 1)
                            currentCol--;
                        isChanged(v);
                    }
                    break;
                case R.id.right:
                    if ((currentRow + 1) <= 5 && m[currentRow][currentCol + 1] == 1) {
                        man.moveRight();
                        if (currentCol <= 7) currentCol++;
                        isChanged(v);
                    }
                    break;

            }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }

    public void isChanged(View v){
        if (currentCol==4 && currentRow==5){
            long endTime = System.currentTimeMillis();
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new FirstFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            spf = v.getContext().getSharedPreferences("new", Context.MODE_PRIVATE);
            if (spf.getLong("record",0)< endTime-startTime){
                SharedPreferences.Editor ed = spf.edit();
                ed.putLong("record",endTime-startTime);
                ed.commit();
            }
        }
    }
    public void generateMaze(){
        int [][] m;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(i==1&&j==1)m[i][j]=1;
                else if(i==0)m[i][j]=0;
                else if(j==0)m[i][j]=0;


            }
        }
    }
}
