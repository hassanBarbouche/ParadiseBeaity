package beauty.android.com.paradisebeaity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.clockbyte.admobadapter.AdmobAdapterCalculator;
import com.clockbyte.admobadapter.AdmobAdapterWrapper;
import com.clockbyte.admobadapter.expressads.AdmobExpressAdapterWrapper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import beauty.android.com.paradisebeaity.Adapters.ArticleCustomAdapter;
import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.Utils.CustomRequest;

/**
 * Created by Hassan on 11/01/2017.
 */

public class ArticleListActivity extends AppCompatActivity {

    public  static ListView ArticleList;
    public List<Article> StaticList;
    protected ImageLoader imageLoader;
    TextView cht;
    RequestQueue requestQueue1;
    AdmobExpressAdapterWrapper adapterWrapper;
    Button Recharger;
    String showUrl = "http://www.maquillagenews.com/server/showArticles2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        AdRequest adRequest = new AdRequest.Builder().build();
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3470589097458386~3219428758");
        String[] testDevicesIds = new String[]{getString(R.string.banner_admob_unit_id), adRequest.DEVICE_ID_EMULATOR};
        adapterWrapper = new AdmobExpressAdapterWrapper(this, getString(R.string.banner_admob_unit_id),new AdSize(AdSize.FULL_WIDTH,150));


        setContentView(R.layout.loading);
        Recharger = (Button) findViewById(R.id.RechergerBTN);
        cht = (TextView) findViewById(R.id.cht);
        cht.setText("Chargement des Article");


        Recharger.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(getApplicationContext(), ArticleListActivity.class);
                startActivity(intent);
                finish();
            }
        });



        Bundle extras = getIntent().getExtras();




            StaticList = new ArrayList<Article>();


            requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            //System.out.println("ww");
        /*    final ProgressDialog loading;
            loading = ProgressDialog.show(ArticleListActivity.this, "Chargement...", "Un petit moment svp...", false, false);
            loading.setContentView(R.layout.progress_dialog);
            loading.setCanceledOnTouchOutside(true);*/

            Map<String, String> params = new HashMap<String, String>();
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, showUrl, params, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("ffffffff22");
                    System.out.println(response.toString());
                    try {


                        JSONArray listarticle = response.getJSONArray("Article");

                        for (int i = listarticle.length() - 1; i >= 0; i--) {
                            JSONObject article = listarticle.getJSONObject(i);


                            StaticList.add(new Article(
                                    article.getString("titre"),
                                    article.getString("date"),
                                    article.getString("contenu"),
                                    article.getString("author"),
                                    article.getString("image"),
                                    article.getString("authorimg"),
                                    article.getInt("likes"),
                                    article.getInt("hates"),
                                    article.getInt("Id"),
                                    article.getString("video")
                            ));


                            System.out.println(StaticList.size() + " my size");


                        }


                        setContentView(R.layout.list_article);
                        initToolbar();


                        ArticleList = (ListView) findViewById(R.id.list_article);

                        System.out.println(StaticList.size() + " this is size");
                        ArticleCustomAdapter adapter = new ArticleCustomAdapter(getBaseContext(), R.layout.one_article2, StaticList);
                        adapter.notifyDataSetChanged();
                        adapterWrapper.setAdapter(adapter);
                        adapterWrapper.setFirstAdIndex(2);
                        adapterWrapper.setLimitOfAds(2);
                        adapterWrapper.setNoOfDataBetweenAds(4);

                       // adapterWrapper.setAdSize(new AdSize(AdSize.FULL_WIDTH,150));
                        ArticleList.setAdapter(adapterWrapper);

                      //  loading.dismiss();

                        ArticleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {





                                Article prod = (Article) parent.getItemAtPosition(position);

                                Article prod1 = (Article) parent.getItemAtPosition(0);
                                Article prod2 = (Article) parent.getItemAtPosition(1);
                                Article prod3 = (Article) parent.getItemAtPosition(3);
                                Article prod4 = (Article) parent.getItemAtPosition(4);
                                Article prod5 = (Article) parent.getItemAtPosition(5);
                                Article prod6 = (Article) parent.getItemAtPosition(6);


                                //prodimg = prod.getProductImage();
                                Intent i = new Intent(ArticleListActivity.this, OneArticleActivity.class);
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


                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.append("eeerror");
                  //  loading.dismiss();
                }
            });

            requestQueue1.add(jsObjRequest);



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
                Intent i = new Intent(ArticleListActivity.this, ArticleListActivity.class);
                startActivity(i);
                finish();

            }
            break;

            case R.id.NewsBtn: {
                Intent i = new Intent(ArticleListActivity.this, NewsListActivity.class);
                startActivity(i);
                finish();
            }
            break;

            case R.id.ProduitBtn: {
                Intent i = new Intent(ArticleListActivity.this, ProductAllListActivity.class);
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
    public void onBackPressed() {

    }
}