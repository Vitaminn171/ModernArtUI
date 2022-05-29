package com.example.modernartui;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


/*
Tên: Lý Quốc An
MSSV: 3119410002
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ModernArtUI";

    private ConstraintLayout palette;

    public Resources.Theme context;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        palette = findViewById( R.id.palette );
        SeekBar seek = findViewById( R.id.seekBar );

        seek.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser) {

                for ( int i = 0; i < palette.getChildCount(); i++ ) {
                    View child = palette.getChildAt( i );

                    int originalColor = Color.parseColor( ( String ) child.getTag() );
                    int invertedColor = ( 0x00FFFFFF - ( originalColor | 0xFF000000 ) ) |
                            ( originalColor & 0xFF000000 );

                    if ( getResources().getColor(R.color.white,context ) != originalColor &&
                            getResources().getColor(R.color.black,context) != originalColor ) {

                        int origR = ( originalColor >> 16 ) & 0x000000FF;
                        int origG = ( originalColor >> 8 ) & 0x000000FF;
                        int origB = originalColor & 0x000000FF;

                        int invR = ( invertedColor >> 16 ) & 0x000000FF;
                        int invG = ( invertedColor >> 8 ) & 0x000000FF;
                        int invB = invertedColor & 0x000000FF;

                        child.setBackgroundColor( Color.rgb(
                                ( int ) ( origR + ( invR - origR ) * ( progress / 100f ) ),
                                ( int ) ( origG + ( invG - origG ) * ( progress / 100f ) ),
                                ( int ) ( origB + ( invB - origB ) * ( progress / 100f ) ) ) );
                        child.invalidate();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) {

            }


            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        } );
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "The activity is visible and about to be started.");
    }

}