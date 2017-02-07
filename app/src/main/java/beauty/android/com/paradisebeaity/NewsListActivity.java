package beauty.android.com.paradisebeaity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.clockbyte.admobadapter.expressads.AdmobExpressAdapterWrapper;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import beauty.android.com.paradisebeaity.Adapters.ArticleCustomAdapter;
import beauty.android.com.paradisebeaity.Adapters.NewsCustomAdapter;
import beauty.android.com.paradisebeaity.Adapters.SwipeImageAdapter;
import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.Models.News;
import beauty.android.com.paradisebeaity.Models.Produit;
import beauty.android.com.paradisebeaity.Utils.CustomRequest;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by Hassan on 11/01/2017.
 */

public class NewsListActivity extends AppCompatActivity {

    public  static ListView NewsList;
    public List<News> StaticList,StaticList2;
    protected ImageLoader imageLoader;
    RequestQueue requestQueue1;
    String showUrl = "http://www.maquillagenews.com/server/showNews2.php";
    public AutoScrollViewPager viewpager;
    public Map<String, String> params ;
    AdmobExpressAdapterWrapper adapterWrapper;
    public String datte;
    private NavigationTabStrip mCenterNavigationTabStrip;
    public int day,month,year,week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         params = new HashMap<String, String>();
        AdRequest adRequest = new AdRequest.Builder().build();
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3470589097458386~3219428758");
        String[] testDevicesIds = new String[]{getString(R.string.banner_admob_unit_id), adRequest.DEVICE_ID_EMULATOR};
        adapterWrapper = new AdmobExpressAdapterWrapper(this, getString(R.string.banner2),new AdSize(AdSize.FULL_WIDTH,80));
        adapterWrapper.setLimitOfAds(2);
        adapterWrapper.setNoOfDataBetweenAds(3);
        adapterWrapper.setFirstAdIndex(2);


        Calendar c = Calendar.getInstance();
         day = c.get(Calendar.DAY_OF_MONTH);
         month = c.get(Calendar.MONTH)+1;
         year = c.get(Calendar.YEAR);
        week = c.get(Calendar.WEEK_OF_MONTH);

        if(month<10){ datte= day+"/"+"0"+month+"/"+year; }
        if (day<10) {datte= "0"+day+"/"+month+"/"+year;}

        datte= day+"/"+month+"/"+year;
        System.out.println(datte + " this is daaate");




        StaticList = new ArrayList<News>();
        setContentView(R.layout.loading);

        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.startAnimation();




          //  setContentView(R.layout.list_news);
            requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            //System.out.println("ww");

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, showUrl, params, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("ffffffff22");
                    System.out.println(response.toString());
                    try {


                        JSONArray listarticle = response.getJSONArray("News");

                        for (int i = listarticle.length() - 1; i >= 0; i--) {
                            JSONObject article = listarticle.getJSONObject(i);


                            StaticList.add(new News(
                                    article.getString("titre"),
                                    article.getString("contenu"),
                                    article.getString("image"),
                                    article.getString("date"),
                                    article.getString("source"),
                                    article.getString("image2")
                            ));

                            if (i == 0) {
                                SwipeImageAdapter.img1 = article.getString("image");
                                System.out.println(article.getString("image"));
                            }

                            System.out.println(StaticList.size() + " my size");


                        }


                        setContentView(R.layout.list_news);
                        initToolbar();

                        mCenterNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_center);
                        mCenterNavigationTabStrip.setTabIndex(0);
                        mCenterNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
                            @Override
                            public void onStartTabSelected(final String title, final int index) {

                                final ProgressDialog loading;
                                loading = ProgressDialog.show(NewsListActivity.this, "Chargement...", "Un petit moment svp...", false, false);

                                loading.setContentView(R.layout.progress_dialog);
                                loading.setCanceledOnTouchOutside(true);
                                StaticList2 = new ArrayList<News>();

                                if ( index == 0)
                                {

                                    StaticList2.clear();


                                    NewsCustomAdapter adapter = new NewsCustomAdapter(getBaseContext(), R.layout.one_news, StaticList);



                                    adapterWrapper.setAdapter(adapter);



                                    NewsList.setAdapter(adapterWrapper);
                                    loading.dismiss();
                                }
                                if ( index == 1)
                                {
                                    StaticList2.clear();

                                    for (int i = StaticList.size()-1; i >= 0; i--)
                                    {
                                        News P = StaticList.get(i);
                                        String NewsDate[] = P.getDate().split("/");
                                        int NewsDay = Integer.parseInt(NewsDate[0]);
                                        int NewsMonth = Integer.parseInt(NewsDate[1]);
                                        System.out.println(NewsMonth+ "newsmonth");
                                        System.out.println(NewsDay+ "newsday");
                                        //System.out.println(P.getNom());
                                        // Boolean B = like(P.getNom(),RechercheEnter.getText()+"");
                                        if (NewsDay == day && NewsMonth == month)
                                        {
                                            StaticList2.add(P);
                                        }




                                    }

                                    NewsCustomAdapter adapter = new NewsCustomAdapter(getBaseContext(), R.layout.one_news, StaticList2);

                                    adapterWrapper.setAdapter(adapter);



                                    NewsList.setAdapter(adapterWrapper);
                                    loading.dismiss();
                                }

                                if ( index == 2)
                                {
                                    StaticList2.clear();

                                    for (int i = StaticList.size()-1; i >= 0; i--)
                                    {
                                        News P = StaticList.get(i);
                                        //System.out.println(P.getNom());
                                        // Boolean B = like(P.getNom(),RechercheEnter.getText()+"");
                                        String NewsDate[] = P.getDate().split("/");
                                        int NewsMonth = Integer.parseInt(NewsDate[1]);
                                        int NewsDay = Integer.parseInt(NewsDate[0]);
                                        System.out.println(NewsMonth + " thiss month");

                                        if (NewsMonth == month && (NewsDay> day-6) )
                                        {
                                            StaticList2.add(P);
                                        }




                                    }



                                    NewsCustomAdapter adapter = new NewsCustomAdapter(getBaseContext(), R.layout.one_news, StaticList2);

                                    adapterWrapper.setAdapter(adapter);



                                    NewsList.setAdapter(adapterWrapper);
                                    loading.dismiss();
                                }

                                if ( index == 3)
                                {
                                    StaticList2.clear();

                                    for (int i = StaticList.size()-1; i >= 0; i--)
                                    {
                                        News P = StaticList.get(i);
                                        //System.out.println(P.getNom());
                                        // Boolean B = like(P.getNom(),RechercheEnter.getText()+"");
                                        String NewsDate[] = P.getDate().split("/");
                                        int NewsMonth = Integer.parseInt(NewsDate[1]);
                                        System.out.println(NewsMonth + " thiss month");

                                        if (NewsMonth == month )
                                        {
                                            StaticList2.add(P);
                                        }




                                    }
                                    NewsCustomAdapter adapter = new NewsCustomAdapter(getBaseContext(), R.layout.one_news, StaticList2);

                                    adapterWrapper.setAdapter(adapter);



                                    NewsList.setAdapter(adapterWrapper);
                                    loading.dismiss();
                                }





                            }

                            @Override
                            public void onEndTabSelected(final String title, final int index) {
// Trigger when strip finish move and select index
                                System.out.println( index + title +" fffdddd");
                            }
                        });
                        NewsList = (ListView) findViewById(R.id.list_news);

                        viewpager = (AutoScrollViewPager) findViewById(R.id.ImageNewsPager);
                        SwipeImageAdapter ss = new SwipeImageAdapter(getApplicationContext());
                        ss.notifyDataSetChanged();
                        viewpager.setAdapter(ss);
                        viewpager.startAutoScroll();
                        viewpager.setInterval(5000);
                        CirclePageIndicator titleIndicator = (CirclePageIndicator) findViewById(R.id.titles);
                        titleIndicator.setViewPager(viewpager);


                        System.out.println(StaticList.size() + " this is size");
                        NewsCustomAdapter adapter = new NewsCustomAdapter(getBaseContext(), R.layout.one_news, StaticList);

                        adapterWrapper.setAdapter(adapter);
                        NewsList.setAdapter(adapterWrapper);


                        SwipeImageAdapter.img1 = StaticList.get(0).getImage();
                        SwipeImageAdapter.img2 = StaticList.get(1).getImage();
                        SwipeImageAdapter.img3 = StaticList.get(2).getImage();
                        SwipeImageAdapter.img4 = StaticList.get(3).getImage();
                        SwipeImageAdapter.img5 = StaticList.get(4).getImage();
                        SwipeImageAdapter.img6 = StaticList.get(5).getImage();
                        SwipeImageAdapter.title = StaticList.get(0).getTitre();
                        SwipeImageAdapter.title2 = StaticList.get(1).getTitre();
                        SwipeImageAdapter.title3 = StaticList.get(2).getTitre();
                        SwipeImageAdapter.title4 = StaticList.get(3).getTitre();
                        SwipeImageAdapter.title5 = StaticList.get(4).getTitre();
                        SwipeImageAdapter.title6 = StaticList.get(5).getTitre();
                        SwipeImageAdapter.desc = StaticList.get(0).getContenu();
                        SwipeImageAdapter.desc2 = StaticList.get(1).getContenu();
                        SwipeImageAdapter.desc3 = StaticList.get(2).getContenu();
                        SwipeImageAdapter.desc4 = StaticList.get(3).getContenu();
                        SwipeImageAdapter.desc5 = StaticList.get(4).getContenu();
                        SwipeImageAdapter.desc6 = StaticList.get(5).getContenu();

                        NewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {


                                News prod = (News) parent.getItemAtPosition(position);


                                //prodimg = prod.getProductImage();
                                Intent i = new Intent(NewsListActivity.this, OneNewsActivity.class);
                                i.putExtra("Titre", prod.Titre + "");
                                i.putExtra("Date", prod.Date);
                                i.putExtra("Text", prod.Contenu + "");
                                i.putExtra("Image", prod.Image + "");
                                i.putExtra("Source", prod.Source + "");
                                i.putExtra("Image2", prod.Image2 + "");

                                startActivity(i);
                                finish();


                            }
                        });


                        //  loading.dismiss();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(" ereurhaaaaaa" + e.getMessage());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(" ereurhaaaaaa" + error.getMessage());
                    //  loading.dismiss();
                    // setContentView(R.layout.loading);
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
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.ArticleBtn:
            {
                Intent i = new Intent(NewsListActivity.this, ArticleListActivity.class);
                startActivity(i);
                finish();

            }
                break;

            case R.id.NewsBtn:
            {Intent i = new Intent(NewsListActivity.this, NewsListActivity.class);
                startActivity(i);
                finish();}
            break;

            case R.id.ProduitBtn:
            {
                Intent i = new Intent(NewsListActivity.this, ProductAllListActivity.class);
                startActivity(i);
                finish();
            }
            break;


        }

        //noinspection SimplifiableIfStatement


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