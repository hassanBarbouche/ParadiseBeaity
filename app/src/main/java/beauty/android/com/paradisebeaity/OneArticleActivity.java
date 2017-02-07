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
import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.Utils.ImageUtil;
import beauty.android.com.paradisebeaity.ui.BaseActivity;

/**
 * Created by Hassan on 12/01/2017.
 */

public class OneArticleActivity extends BaseActivity implements YouTubePlayer.OnInitializedListener {

 ImageView ArticleImg,ArticleImg2,AuthorImg;
 TextView ArticleTitre,ArticleDate,ArticleContenu,ArticleAuthor,ArticleContenu2;

    public  static GridView NextArticleGrid;
    public List<Article> StaticGrid;
    protected ImageLoader imageLoader;
    private YouTubePlayer player;
    private YouTubePlayerFragment youTubeView;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    public String videocode;
    public Button BtnNo,BtnYes;
    public int id,likes,hates;
    private static final int RECOVERY_REQUEST = 1;
    public Bundle extras;
    String showUrl = "http://www.maquillagenews.com/server/update_likes.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        setContentView(R.layout.one_article);


        StaticGrid = new ArrayList<Article>();

        initToolbar();

         extras = getIntent().getExtras();

        id = extras.getInt("Id");
        hates = extras.getInt("Hates");
        likes = extras.getInt("Likes");

        NextArticleGrid = (GridView) findViewById(R.id.autreList);
        ArticleTitre = (TextView) findViewById(R.id.produit_titre);
        ArticleDate = (TextView) findViewById(R.id.news_date);
        ArticleContenu = (TextView) findViewById(R.id.produit_text);
        ArticleContenu2 = (TextView) findViewById(R.id.produit_text2);
        ArticleAuthor = (TextView) findViewById(R.id.article_author);
        BtnNo = (Button) findViewById(R.id.btnno);
        BtnYes = (Button) findViewById(R.id.btnyes);

        ArticleImg = (ImageView) findViewById(R.id.produit_image);

        AuthorImg = (ImageView) findViewById(R.id.Authorimg);
        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        videocode = extras.getString("Video");

        youTubeView = (YouTubePlayerFragment)getFragmentManager()
                .findFragmentById(R.id.youtube_view);

        System.out.println(videocode);

        if ( videocode == null || Objects.equals(videocode, ""))
        {
            System.out.println(" ma famech videooo");
            youTubeView.getView().setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = youTubeView.getView().getLayoutParams();
            params.height = 0;
            youTubeView.getView().setLayoutParams(params);
        }

        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        Article NextArticle1 = new Article(
                extras.getString("Titre1"),
                extras.getString("Date1"),
                extras.getString("Contenu1"),
                extras.getString("Author1"),
                extras.getString("Image1"),
                extras.getString("AuthorImg1"),
                extras.getInt("Likes1"),
                extras.getInt("Hates1"),
                extras.getInt("Id1"),
                extras.getString("Video1")
                );

        Article NextArticle2 = new Article(
                extras.getString("Titre2"),
                extras.getString("Date2"),
                extras.getString("Contenu2"),
                extras.getString("Author2"),
                extras.getString("Image2"),
                extras.getString("AuthorImg2"),
                extras.getInt("Likes2"),
                extras.getInt("Hates2"),
                extras.getInt("Id2"),
                extras.getString("Video2")
        );


        Article NextArticle3 = new Article(
                extras.getString("Titre3"),
                extras.getString("Date3"),
                extras.getString("Contenu3"),
                extras.getString("Author3"),
                extras.getString("Image3"),
                extras.getString("AuthorImg3"),
                extras.getInt("Likes3"),
                extras.getInt("Hates3"),
                extras.getInt("Id3"),
                extras.getString("Video3")
        );

        Article NextArticle4 = new Article(
                extras.getString("Titre4"),
                extras.getString("Date4"),
                extras.getString("Contenu4"),
                extras.getString("Author4"),
                extras.getString("Image4"),
                extras.getString("AuthorImg4"),
                extras.getInt("Likes4"),
                extras.getInt("Hates4"),
                extras.getInt("Id4"),
                extras.getString("Video4")
        );

        Article NextArticle5 = new Article(
                extras.getString("Titre5"),
                extras.getString("Date5"),
                extras.getString("Contenu5"),
                extras.getString("Author5"),
                extras.getString("Image5"),
                extras.getString("AuthorImg5"),
                extras.getInt("Likes5"),
                extras.getInt("Hates5"),
                extras.getInt("Id5"),
                extras.getString("Video5")
        );

        Article NextArticle6 = new Article(
                extras.getString("Titre6"),
                extras.getString("Date6"),
                extras.getString("Contenu6"),
                extras.getString("Author6"),
                extras.getString("Image6"),
                extras.getString("AuthorImg6"),
                extras.getInt("Likes6"),
                extras.getInt("Hates6"),
                extras.getInt("Id6"),
                extras.getString("Video6")
        );



        StaticGrid.add(NextArticle1);
        StaticGrid.add(NextArticle2);
        StaticGrid.add(NextArticle3);
        StaticGrid.add(NextArticle4);
        StaticGrid.add(NextArticle5);
        StaticGrid.add(NextArticle6);

        Typeface Bold = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "BebasNeue Bold.ttf");

        Typeface Light = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "HelveticaNeue Medium.ttf");

        Typeface Regular = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "BebasNeue Regular.ttf");

        ArticleTitre.setTypeface(Bold);
        ArticleContenu.setTypeface(Light);
        ArticleContenu2.setTypeface(Light);
        ArticleDate.setTypeface(Regular);
       // ArticleAuthor.setTypeface(Bold);

        int textlenth = extras.getString("Contenu").length();
        String text1,text2;

        if(textlenth >500)
        {
            text1 = (extras.getString("Contenu").substring(0, 500))+"-";
            text2 = extras.getString("Contenu").substring(300, textlenth);
            ArticleContenu2.setText(text2);
            ArticleContenu.setText(text1);
        }

        else{

            ArticleContenu.setText(extras.getString("Contenu"));
        }




        ArticleTitre.setText(extras.getString("Titre"));
        ArticleAuthor.setText(extras.getString("Author"));
        ArticleDate.setText(extras.getString("Date"));
        BtnNo.setText("Non ("+extras.getInt("Hates")+")");
        BtnYes.setText("Oui ("+extras.getInt("Likes")+")");

        BtnYes.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {



                StringRequest request = new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        BtnYes.setText((extras.getInt("Likes")+1)+"");
                        BtnNo.setEnabled(false);
                        BtnYes.setEnabled(false);

                        System.out.println(response.toString());
                        System.out.println(extras.getInt("Id")+" idddd");
                        System.out.println((extras.getInt("Likes")+1));
                        //	  loading.dismiss();
                        Toast.makeText(OneArticleActivity.this, "Rating effectué !", Toast.LENGTH_LONG).show();
                        System.out.println(response + "this is resp");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // loading.dismiss();

                        //Showing toast
                        Toast.makeText(OneArticleActivity.this, error.getMessage()+"", Toast.LENGTH_LONG).show();
                        System.out.println(error.getMessage() + "this is resp");
                    }
                })

                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();




                        //  Note.setText(String.format("%.02f", finalrate));



                        params.put("id", id+"");
                        params.put("likes", (likes+1)+"");
                        params.put("hates",hates+"");





                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(OneArticleActivity.this);


                requestQueue.add(request);

            }
        });

        BtnNo.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {


                StringRequest request = new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        BtnNo.setText((extras.getInt("Hates")+1)+"");
                        BtnNo.setEnabled(false);
                        BtnYes.setEnabled(false);

                        System.out.println(response.toString());
                        //	  loading.dismiss();
                        Toast.makeText(OneArticleActivity.this, "Rating effectué !", Toast.LENGTH_LONG).show();
                        System.out.println(response + "this is resp");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // loading.dismiss();

                        //Showing toast
                        Toast.makeText(OneArticleActivity.this, error.getMessage()+"", Toast.LENGTH_LONG).show();
                        System.out.println(error.getMessage() + "this is resp");
                    }
                })

                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();




                        //  Note.setText(String.format("%.02f", finalrate));
                        System.out.println(extras.getInt("Id")+"");

                        params.put("id", id+"");
                        params.put("likes", likes+"");
                        params.put("hates",(hates+1)+"");





                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(OneArticleActivity.this);


                requestQueue.add(request);

            }
        });




        ImageUtil.displayImage(ArticleImg, extras.getString("Image"), null);
        ImageUtil.displayRoundImage(AuthorImg, extras.getString("AuthorImg"), null);

        NextArticleGrid.setAdapter(new NextArticleCustomAdapter(getBaseContext(), R.layout.one_article_grid, StaticGrid));

        NextArticleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Article prod = (Article) parent.getItemAtPosition(position);

                Article prod1 = (Article) parent.getItemAtPosition(0);
                Article prod2 = (Article) parent.getItemAtPosition(1);
                Article prod3 = (Article) parent.getItemAtPosition(2);
                Article prod4 = (Article) parent.getItemAtPosition(3);
                Article prod5 = (Article) parent.getItemAtPosition(4);
                Article prod6 = (Article) parent.getItemAtPosition(5);


                //prodimg = prod.getProductImage();
                Intent i = new Intent(OneArticleActivity.this, OneArticleActivity.class);
                i.putExtra("Titre", prod.Titre + "");
                i.putExtra("Date", prod.Date);
                i.putExtra("Contenu", prod.Contenu + "");
                i.putExtra("Author", prod.Author + "");
                i.putExtra("Image", prod.Image + "");
                i.putExtra("AuthorImg", prod.AuthorImg + "");
                i.putExtra("Likes", prod.Likes);
                i.putExtra("Hates", prod.Hates);
                i.putExtra("Id", prod.Id);
                i.putExtra("Video", prod.Video);

                i.putExtra("Titre1", prod1.Titre + "");
                i.putExtra("Date1", prod1.Date);
                i.putExtra("Contenu1", prod1.Contenu + "");
                i.putExtra("Author1", prod1.Author + "");
                i.putExtra("Image1", prod1.Image + "");
                i.putExtra("AuthorImg1", prod1.AuthorImg + "");
                i.putExtra("Likes1", prod1.Likes);
                i.putExtra("Hates1", prod1.Hates);
                i.putExtra("Id1", prod1.Id);
                i.putExtra("Video1", prod1.Video);

                i.putExtra("Titre2", prod2.Titre + "");
                i.putExtra("Date2", prod2.Date);
                i.putExtra("Contenu2", prod2.Contenu + "");
                i.putExtra("Author2", prod2.Author + "");
                i.putExtra("Image2", prod2.Image + "");
                i.putExtra("AuthorImg2", prod2.AuthorImg + "");
                i.putExtra("Likes2", prod2.Likes);
                i.putExtra("Hates2", prod2.Hates);
                i.putExtra("Id2", prod2.Id);
                i.putExtra("Video2", prod2.Video);

                i.putExtra("Titre3", prod3.Titre + "");
                i.putExtra("Date3", prod3.Date);
                i.putExtra("Contenu3", prod3.Contenu + "");
                i.putExtra("Author3", prod3.Author + "");
                i.putExtra("Image3", prod3.Image + "");
                i.putExtra("AuthorImg3", prod3.AuthorImg + "");
                i.putExtra("Likes3", prod3.Likes);
                i.putExtra("Hates3", prod3.Hates);
                i.putExtra("Id3", prod3.Id);
                i.putExtra("Video3", prod3.Video);

                i.putExtra("Titre4", prod4.Titre + "");
                i.putExtra("Date4", prod4.Date);
                i.putExtra("Contenu4", prod4.Contenu + "");
                i.putExtra("Author4", prod4.Author + "");
                i.putExtra("Image4", prod4.Image + "");
                i.putExtra("AuthorImg4", prod4.AuthorImg + "");
                i.putExtra("Likes4", prod4.Likes);
                i.putExtra("Hates4", prod4.Hates);
                i.putExtra("Id4", prod4.Id);
                i.putExtra("Video4", prod4.Video);

                i.putExtra("Titre5", prod5.Titre + "");
                i.putExtra("Date5", prod5.Date);
                i.putExtra("Contenu5", prod5.Contenu + "");
                i.putExtra("Author5", prod5.Author + "");
                i.putExtra("Image5", prod5.Image + "");
                i.putExtra("AuthorImg5", prod5.AuthorImg + "");
                i.putExtra("Likes5", prod5.Likes);
                i.putExtra("Hates5", prod5.Hates);
                i.putExtra("Id5", prod5.Id);
                i.putExtra("Video5", prod5.Video);

                i.putExtra("Titre6", prod6.Titre + "");
                i.putExtra("Date6", prod6.Date);
                i.putExtra("Contenu6", prod6.Contenu + "");
                i.putExtra("Author6", prod6.Author + "");
                i.putExtra("Image6", prod6.Image + "");
                i.putExtra("AuthorImg6", prod6.AuthorImg + "");
                i.putExtra("Likes6", prod6.Likes);
                i.putExtra("Hates6", prod6.Hates);
                i.putExtra("Id6", prod6.Id);
                i.putExtra("Video6", prod6.Video);








                startActivity(i);
                finish();



            }});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ArticleListActivity.class);
        intent.putExtra("Title","FromOne");
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ArticleBtn: {
                Intent i = new Intent(OneArticleActivity.this, ArticleListActivity.class);
                startActivity(i);
                finish();

            }
            break;

            case R.id.NewsBtn: {
                Intent i = new Intent(OneArticleActivity.this, NewsListActivity.class);
                startActivity(i);
                finish();
            }
            break;

            case R.id.ProduitBtn: {
                Intent i = new Intent(OneArticleActivity.this, ProductAllListActivity.class);
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

