package beauty.android.com.paradisebeaity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;


import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.Timer;
import java.util.TimerTask;

import beauty.android.com.paradisebeaity.Utils.Utils;
import beauty.android.com.paradisebeaity.ui.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.waveloadingview.WaveLoadingView;

import static javax.xml.datatype.DatatypeConstants.DURATION;

public class SplashScreenActivity extends BaseActivity {
    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static final int DURATION = 30000;
    private RectF mDisplayRect = new RectF();
    private final Matrix mMatrix = new Matrix();
    private int mDirection = RightToLeft;
    private ValueAnimator mCurrentAnimator;
    private float mScaleFactor;
    public ImageView AA;
    public  WaveLoadingView mWaveLoadingView ;
    public ImageView Logo;
    ShimmerTextView myShimmerTextView;
    public Button passBtn;
    public VideoView videoview;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        Logo = (ImageView) findViewById(R.id.logo);
        myShimmerTextView = (ShimmerTextView) findViewById(R.id.shimmer_tv) ;
        passBtn = (Button) findViewById(R.id.passBtn);
        passBtn.setEnabled(false);
        mWaveLoadingView.startAnimation();



        ButterKnife.bind(this);


         videoview = (VideoView) findViewById(R.id.videoView);
        setDimension();
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.beauty);
        videoview.setVideoURI(uri);
        videoview.start();

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        passBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(getApplicationContext(), NewsListActivity.class);
                intent.putExtra("From","one");
                startActivity(intent);
                finish();
            }
        });



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Your logic here...

                // When you need to modify a UI element, do so on the UI thread.
                // 'getActivity()' is required as this is being ran from a Fragment.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // This code will always run on the UI thread, therefore is safe to modify UI elements.

                        mWaveLoadingView.setProgressValue(100);
                        mWaveLoadingView.setBorderColor(Color.parseColor("#ffffff"));
                        mWaveLoadingView.setBorderWidth(4);
                        Logo.setVisibility(View.INVISIBLE);
                        passBtn.setEnabled(true);
                        myShimmerTextView.setVisibility(View.VISIBLE);
                        Shimmer shimmer = new Shimmer();
                        shimmer.start(myShimmerTextView);
                    }
                });
            }
        }, 10000, 3000); // End of your timer code.
            }

    @Override
    public void onBackPressed() {

    }

    private void setDimension() {
        // Adjust the size of the video
        // so it fits on the screen
        float videoProportion = getVideoProportion();
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float screenProportion = (float) screenHeight / (float) screenWidth;
        android.view.ViewGroup.LayoutParams lp = videoview.getLayoutParams();

        if (videoProportion < screenProportion) {
            lp.height= screenHeight;
            lp.width = (int) ((float) screenHeight / videoProportion);
        } else {
            lp.width = screenWidth;
            lp.height = (int) ((float) screenWidth * videoProportion);
        }
        videoview.setLayoutParams(lp);
    }

    // This method gets the proportion of the video that you want to display.
// I already know this ratio since my video is hardcoded, you can get the
// height and width of your video and appropriately generate  the proportion
//    as :height/width
    private float getVideoProportion(){
        return 1.5f;
    }
}


