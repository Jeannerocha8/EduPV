package tcc.studio.com.edupv.BD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;

import tcc.studio.com.edupv.VideoHigienizacao;
import tcc.studio.com.edupv.R;

import static android.content.Context.WINDOW_SERVICE;

public class FullScreenMediaController extends MediaController {


    private ImageButton fullScreen;
    private String isFullScreen;


    public FullScreenMediaController(Context context) {
        super(context);
    }

    @Override
    public void setAnchorView(final View view) {

        super.setAnchorView(view);

        //image button for full screen to be added to media controller
        fullScreen = new ImageButton (super.getContext());


        final FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.RIGHT;
        params.topMargin = 8;
        params.rightMargin =40;
        addView(fullScreen, params);

        //fullscreen indicator from intent
        isFullScreen =  ((Activity)getContext()).getIntent().
                getStringExtra("fullScreenInd");

        float f = 28;


        if("y".equals(isFullScreen)){
            fullScreen.setImageResource(R.drawable.fullscreenexit);
            fullScreen.setBackgroundColor(Color.parseColor("#373435"));
            ((Activity) getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            fullScreen.setImageResource(R.drawable.iconfullscreen);
            fullScreen.setBackgroundColor(Color.parseColor("#373435"));
            ((Activity) getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //add listener to image button to handle full screen and exit full screen events
        fullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),VideoHigienizacao.class);
                Display display = ((WindowManager) getContext().getSystemService(WINDOW_SERVICE))
                        .getDefaultDisplay();
                int rotation = display.getRotation();

                if("y".equals(isFullScreen)){

                    intent.putExtra("fullScreenInd", "");

                }else{

                    intent.putExtra("fullScreenInd", "y");

                }
                ((Activity)getContext()).startActivity(intent);
            }
        });
    }
}