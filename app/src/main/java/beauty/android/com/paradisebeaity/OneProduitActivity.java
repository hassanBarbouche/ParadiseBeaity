package beauty.android.com.paradisebeaity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import beauty.android.com.paradisebeaity.Adapters.NextArticleCustomAdapter;
import beauty.android.com.paradisebeaity.Adapters.SampleAdapter;
import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.Models.Produit;
import beauty.android.com.paradisebeaity.Utils.ImageUtil;
import beauty.android.com.paradisebeaity.ui.BaseActivity;

/**
 * Created by Hassan on 12/01/2017.
 */

public class OneProduitActivity extends BaseActivity implements YouTubePlayer.OnInitializedListener {

 ImageView ProduitImg;
 TextView ProduitTitre,ProduitContenu,Note,Prix;



    protected ImageLoader imageLoader;
    private YouTubePlayer player;
    private YouTubePlayerFragment youTubeView;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    public String videocode;
    public RatingBar Ratebar;
    public Button NoterBtn;
    public Bundle extras;
    public int Ratedby,Ratenbr,Rate,AllRate;
    private static final int RECOVERY_REQUEST = 1;
    String showUrl = "http://www.maquillagenews.com/server/update_rate.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        setContentView(R.layout.one_product);




        initToolbar();

         extras = getIntent().getExtras();

        NoterBtn = (Button) findViewById(R.id.noterBtn);
        ProduitTitre = (TextView) findViewById(R.id.produit_titre);
        Note = (TextView) findViewById(R.id.Note);
        Prix = (TextView) findViewById(R.id.produit_prix);
        ProduitContenu = (TextView) findViewById(R.id.produit_text);
        Ratebar =(RatingBar) findViewById(R.id.ratingBar);


        Ratenbr = extras.getInt("Rate");
        Ratedby = extras.getInt("RatedBy");
        AllRate = extras.getInt("AllRate");
        videocode = extras.getString("Vid");



        Note.setText(Ratenbr+"");

        if(Ratenbr == 0)
        {
            Ratebar.setRating(0);
        }

        if(Ratenbr == 1)
        {
            Ratebar.setRating(1);
        }

        if(Ratenbr == 2)
        {
            Ratebar.setRating(2);
        }

        if(Ratenbr == 3)
        {
            Ratebar.setRating(3);
        }

        if(Ratenbr == 4)
        {
            Ratebar.setRating(4);
        }

        if(Ratenbr == 5)
        {
            Ratebar.setRating(5);
        }

        ProduitImg = (ImageView) findViewById(R.id.produit_image);


        NoterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                  NoterBtn.setEnabled(false);
                final float myRates = Ratebar.getRating();
                final float finalrates  ;
                System.out.println(myRates);
                System.out.println(Ratedby + " ratedby");

                finalrates = (AllRate + myRates)/(Ratedby+1);
                System.out.println(finalrates +" finaaal");
                Ratebar.setRating(finalrates);
                Note.setText(String.format("%.01f", finalrates));


                StringRequest request = new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                        //	  loading.dismiss();
                        Toast.makeText(OneProduitActivity.this, "Rating effectué !", Toast.LENGTH_LONG).show();
                        System.out.println(response + "this is resp");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // loading.dismiss();

                        //Showing toast
                        Toast.makeText(OneProduitActivity.this, error.getMessage()+"", Toast.LENGTH_LONG).show();
                        System.out.println(error.getMessage() + "this is resp");
                    }
                })

                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();



                         float myRate = Ratebar.getRating();
                         float finalrate  ;




                            System.out.println(myRate);
                            System.out.println(Ratedby + " ratedby");


                            finalrate = (AllRate + myRate)/(Ratedby+1);
                            System.out.println(finalrate +" finaaal");
                            Ratebar.setRating(finalrate);
                          //  Note.setText(String.format("%.02f", finalrate));
                            params.put("id", extras.getInt("Id")+"");
                            params.put("rate", finalrate+"");
                            params.put("ratedby",(Ratedby+1)+"");
                            params.put("allrate", AllRate+ myRate+"");




                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(OneProduitActivity.this);


                requestQueue.add(request);


            }
        });

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        youTubeView = (YouTubePlayerFragment)getFragmentManager()
                .findFragmentById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        if ( videocode == null || Objects.equals(videocode, ""))
        {
            System.out.println(" ma famech videooo");
            youTubeView.getView().setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = youTubeView.getView().getLayoutParams();
            params.height = 0;
            youTubeView.getView().setLayoutParams(params);
        }


        Typeface Bold = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "BebasNeue Bold.ttf");

        Typeface Light = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "HelveticaNeue Medium.ttf");

        Typeface Regular = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "BebasNeue Regular.ttf");


        System.out.println(extras.getString("Contenu")+" laaa laaaaa");

        ProduitTitre.setTypeface(Bold);
        ProduitTitre.setTypeface(Regular);
        ProduitContenu.setTypeface(Light);


        ProduitTitre.setText(extras.getString("Titre"));
        ProduitContenu.setText(extras.getString("Text"));
        Prix.setText("€"+extras.getFloat("Prix")+ " Euro");


        ImageUtil.displayImage(ProduitImg, extras.getString("Image"), null);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(OneProduitActivity.this, ProductAllListActivity.class);
        i.putExtra("Titre", "from One");
        startActivity(i);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.ArticleBtn: {
                Intent i = new Intent(OneProduitActivity.this, ArticleListActivity.class);
                startActivity(i);
                finish();

            }
            break;

            case R.id.NewsBtn: {
                Intent i = new Intent(OneProduitActivity.this, NewsListActivity.class);
                startActivity(i);
                finish();
            }
            break;

            case R.id.ProduitBtn: {
                Intent i = new Intent(OneProduitActivity.this, ProductAllListActivity.class);
                startActivity(i);
                finish();
            }
            break;

        }
        return super.onOptionsItemSelected(item);


    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.maquillage_news_icon);
        //mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolBarTextView.setText("Paradise Beauty ");
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if(videocode!=null && videocode !="")
        {   this.player = youTubePlayer;
            player.setPlayerStateChangeListener(playerStateChangeListener);
            player.setPlaybackEventListener(playbackEventListener);

            if (!b  ) {

                player.cueVideo(videocode); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            }
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);

        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().

        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().

        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.

        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }

}

