package beauty.android.com.paradisebeaity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.cengalabs.flatui.FlatUI;
import com.etsy.android.grid.StaggeredGridView;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import beauty.android.com.paradisebeaity.Adapters.ExampleAdapter;
import beauty.android.com.paradisebeaity.Adapters.SampleAdapter;
import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.Models.News;
import beauty.android.com.paradisebeaity.Models.Produit;
import beauty.android.com.paradisebeaity.Utils.CustomRequest;
import beauty.android.com.paradisebeaity.ui.AnimatedExpandableListView;
import beauty.android.com.paradisebeaity.ui.BaseActivity;
import me.itangqi.waveloadingview.WaveLoadingView;

import static beauty.android.com.paradisebeaity.Adapters.ExampleAdapter.ChildItem;
import static beauty.android.com.paradisebeaity.Adapters.ExampleAdapter.GroupItem;

/**
 * Created by Hassan on 11/01/2017.
 */

public class ProductAllListActivity extends BaseActivity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public  static ListView ArticleList;
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;
    public static List<Produit> StaticList,StaticList3,StaticList4;
    protected ImageLoader imageLoader;
    RequestQueue requestQueue1;
    public  static StaggeredGridView gridView;
    public static RelativeLayout Mainlay;

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    AnimatedExpandableListView DrawerList;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ExampleAdapter adapter;

    public ViewPager viewpager;
    private NavigationTabStrip mCenterNavigationTabStrip;
    public ImageView UporDown;

    TextView cht;
    Button Recharger;


    String showUrl = "http://www.maquillagenews.com/server/getproduct2.php";

    public RelativeLayout CheckLayout;
    public CheckBox ch1,ch2,ch3,ch4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        Map<String, String> params = new HashMap<String, String>();
        adapter = new ExampleAdapter(this);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.BLOOD);


        setContentView(R.layout.loading);
        Recharger = (Button) findViewById(R.id.RechergerBTN);
        cht = (TextView) findViewById(R.id.cht);
        cht.setText("Chargement des Produit");

        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial = new InterstitialAd(ProductAllListActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
// Call displayInterstitial() function
                displayInterstitial();
            }
        });


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
        System.out.println(findViewById(android.R.id.content).getId()+" this is id");

        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.startAnimation();

        Bundle extras = getIntent().getExtras();






        List<GroupItem> items = new ArrayList<GroupItem>();
        List<ChildItem> itemsChild1 = new ArrayList<ChildItem>();
        List<ChildItem> itemsChild2 = new ArrayList<ChildItem>();
        List<ChildItem> itemsChild3 = new ArrayList<ChildItem>();
        List<ChildItem> itemsChild4 = new ArrayList<ChildItem>();
        List<ChildItem> itemsChild5 = new ArrayList<ChildItem>();

        ChildItem Child1 = new ChildItem(R.drawable.fardpaupiere,"Fard à paupières");
        ChildItem Child2 = new ChildItem(R.drawable.mascara,"Mascaras");
        ChildItem Child3 = new ChildItem(R.drawable.crayon,"Crayons");
      //  ChildItem Child4 = new ChildItem(R.drawable.eyeliner,"Eyeliners");
      //  ChildItem Child5 = new ChildItem(R.drawable.base,"Base");

        ChildItem Child6 = new ChildItem(R.drawable.rougelevre,"Rouges à lèvres");
        ChildItem Child7 = new ChildItem(R.drawable.gloss,"Gloss");
       // ChildItem Child8 = new ChildItem(R.drawable.baume,"Baumes");
        ChildItem Child9 = new ChildItem(R.drawable.crayonlevre,"Crayons");

        ChildItem Child10 = new ChildItem(R.drawable.fondtient,"Fond de teint");
       // ChildItem Child11 = new ChildItem(R.drawable.poudresoleil,"Poudres de soleil");
        ChildItem Child12 = new ChildItem(R.drawable.poudre,"Poudres");
        ChildItem Child13 = new ChildItem(R.drawable.contouring,"Contouring");
      //  ChildItem Child14 = new ChildItem(R.drawable.bbcream,"BB & CC crèmes");
        ChildItem Child15 = new ChildItem(R.drawable.blush,"Blush");
        ChildItem Child16 = new ChildItem(R.drawable.anticerne,"Anti-Cernes & Correcteurs");
        ChildItem Child17 = new ChildItem(R.drawable.primer,"Base & Primer");
        ChildItem Child18 = new ChildItem(R.drawable.enlumineur,"Enlumineur");

        ChildItem Child19 = new ChildItem(R.drawable.demaquillant,"Démaquillant et Nettoyant");
        ChildItem Child20 = new ChildItem(R.drawable.hydratant,"Hydratant");
        ChildItem Child21 = new ChildItem(R.drawable.antiride,"Anti Ride/Age");
        ChildItem Child22 = new ChildItem(R.drawable.gommage,"Masques et Gommages");

        ChildItem Child23 = new ChildItem(R.drawable.vernis2,"Vernis à Ongles");
        ChildItem Child24 = new ChildItem(R.drawable.dissolvant,"Dissolvant");
        ChildItem Child25 = new ChildItem(R.drawable.soinsongle,"Soins des Ongles");
        ChildItem Child26 = new ChildItem(R.drawable.nailart,"NailArt");

        itemsChild1.add(Child1);
        itemsChild1.add(Child2);
        itemsChild1.add(Child3);
       // itemsChild1.add(Child4);
       // itemsChild1.add(Child5);

        itemsChild2.add(Child6);
        itemsChild2.add(Child7);
       // itemsChild2.add(Child8);
        itemsChild2.add(Child9);

        itemsChild3.add(Child10);
       // itemsChild3.add(Child11);
        itemsChild3.add(Child12);
        itemsChild3.add(Child13);
       // itemsChild3.add(Child14);
        itemsChild3.add(Child15);
        itemsChild3.add(Child16);
        itemsChild3.add(Child17);
        itemsChild3.add(Child18);

        itemsChild4.add(Child19);
        itemsChild4.add(Child20);
        itemsChild4.add(Child21);
        itemsChild4.add(Child22);

        itemsChild5.add(Child23);
        itemsChild5.add(Child24);
        itemsChild5.add(Child25);
        itemsChild5.add(Child26);








        GroupItem item1 = new GroupItem("Tient",R.drawable.tient,"Teint",itemsChild3);
        GroupItem item2 = new GroupItem("Yeux",R.drawable.eye,"Yeux",itemsChild1);
        GroupItem item3 = new GroupItem("Lévres",R.drawable.lip_icone,"Lévres",itemsChild2);
        GroupItem item4 = new GroupItem("Soins Visage",R.drawable.soins_visage,"Soins Visage",itemsChild4);
        GroupItem item5 = new GroupItem("Ongles",R.drawable.ongles,"Ongles",itemsChild5);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        adapter.setData(items);




        final Map<String, String> params2 = new HashMap<String, String>();

        //params2.put("categorie", "Teint");
        StaticList =new ArrayList<Produit>();


        requestQueue1 = Volley.newRequestQueue(getApplicationContext());



        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, showUrl, params2, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("ffffffff22");
                System.out.println(response.toString());
                try {


                    JSONArray listproduit = response.getJSONArray("Produit");

                    for (int i = listproduit.length()-1; i >= 0; i--) {
                        JSONObject produit = listproduit.getJSONObject(i);





                        StaticList.add(new Produit(
                                produit.getString("nom"),
                                produit.getString("description"),
                                (float) produit.getDouble("prix"),
                                produit.getString("video"),
                                produit.getInt("rate"),
                                produit.getString("type_peau"),
                                produit.getString("type_tient"),
                                produit.getString("image"),
                                produit.getInt("Id"),
                                produit.getInt("ratedby"),
                                produit.getInt("allrate"),
                                produit.getString("categorie"),
                                produit.getString("sous_categorie")

                        ));


                        System.out.println(StaticList.size() + " my size");






                    }


                    System.out.println(params2.values());
                    System.out.println(StaticList.size()+" this is size");
                    System.out.println(" AAAAAAAAA AAAA");

                    setContentView(R.layout.all_product_pager);

                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


                    Mainlay = (RelativeLayout) findViewById(R.id.mainLayout);
                    initToolbar();
                    mTitle = mDrawerTitle = getTitle();
                    viewpager = (ViewPager) findViewById(R.id.PagerW);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mCenterNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_center);


                    viewpager.setAdapter(new myadapter(fragmentManager));
                    //viewpager.setCurrentItem(2);









                   // viewpager.setOffscreenPageLimit(6);
                    viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                        int positionCurrent;
                        int thispos;
                        boolean dontLoadList;

                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            thispos = position;
                            System.out.println(position + "pooooo");



                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            if (state == 0) { // the viewpager is idle as swipping ended

                                if (!dontLoadList) {
                                    if (thispos == 0) {
                                        StaticList3 = new ArrayList<Produit>();
                                        for (int i = StaticList.size() - 1; i >= 0; i--) {
                                            Produit P = StaticList.get(i);
                                            System.out.println(P.getType_Peau());
                                            if (Objects.equals(P.getCategorie(), "Teint")) {
                                                StaticList3.add(P);
                                            }

                                        }

                                        AllTeint.StaticList = StaticList3;
                                        SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                        AllTeint.gridView.setAdapter(adapters);
                                        adapters.notifyDataSetChanged();

                                    }

                                    if (thispos == 1) {
                                        StaticList3 = new ArrayList<Produit>();
                                        for (int i = StaticList.size() - 1; i >= 0; i--) {
                                            Produit P = StaticList.get(i);
                                            System.out.println(P.getType_Peau());
                                            if (Objects.equals(P.getCategorie(), "Yeux")) {
                                                StaticList3.add(P);
                                            }

                                        }
                                        AllYeux.StaticList = StaticList3;
                                        SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                        AllYeux.gridView.setAdapter(adapters);
                                        adapters.notifyDataSetChanged();
                                    }

                                    if (thispos == 2) {
                                        StaticList3 = new ArrayList<Produit>();
                                        for (int i = StaticList.size() - 1; i >= 0; i--) {
                                            Produit P = StaticList.get(i);
                                            System.out.println(P.getType_Peau());
                                            if (Objects.equals(P.getCategorie(), "Levres")) {
                                                StaticList3.add(P);
                                            }

                                        }
                                        AllLevres.StaticList = StaticList3;
                                        SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                        AllLevres.gridView.setAdapter(adapters);
                                        adapters.notifyDataSetChanged();

                                    }

                                    if (thispos == 3) {
                                        StaticList3 = new ArrayList<Produit>();

                                        for (int i = StaticList.size() - 1; i >= 0; i--) {
                                            Produit P = StaticList.get(i);
                                            System.out.println(P.getType_Peau());
                                            if (Objects.equals(P.getCategorie(), "Soins Visage")) {
                                                StaticList3.add(P);
                                            }

                                        }
                                        AllSoins.StaticList = StaticList3;
                                        SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                        AllSoins.gridView.setAdapter(adapters);
                                        adapters.notifyDataSetChanged();

                                    }

                                    if (thispos == 4) {
                                        StaticList3 = new ArrayList<Produit>();
                                        for (int i = StaticList.size() - 1; i >= 0; i--) {
                                            Produit P = StaticList.get(i);
                                            System.out.println(P.getType_Peau());
                                            if (Objects.equals(P.getCategorie(), "Ongles")) {
                                                StaticList3.add(P);
                                            }

                                        }
                                        AllOngles.StaticList = StaticList3;
                                        SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                        AllOngles.gridView.setAdapter(adapters);
                                        adapters.notifyDataSetChanged();

                                    }

                                    if (thispos == 5) {
                                        // CheckLayout.setVisibility(View.INVISIBLE);
                                    }
                                    //async thread code to execute loading the list...


                                }
                            }

                        }

                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            positionCurrent = position;

                            if (positionOffsetPixels == 0) {
                                //Do something on selected page at position

                                if(position==0){

                                StaticList3 = new ArrayList<Produit>();
                                for (int i = StaticList.size() - 1; i >= 0; i--) {
                                    Produit P = StaticList.get(i);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getCategorie(), "Teint")) {
                                        StaticList3.add(P);
                                    }

                                }

                                AllTeint.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                AllTeint.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();
                            }

                            }
                            if (positionOffset == 0 && positionOffsetPixels == 0) // the offset is zero when the swiping ends
                            {
                                dontLoadList = false;
                            } else
                                dontLoadList = true; // To avoid loading content for list after swiping the pager.
                        }
                    });



                    mCenterNavigationTabStrip.setViewPager(viewpager, 0);


                    //ArticleList = (ListView) findViewById(R.id.list_article);
                    //  StaticList = new ArrayList<Article>();


        /*Drawer Implementation*/
                    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

                    DrawerList = (AnimatedExpandableListView) findViewById(R.id.list_view);

                    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                            GravityCompat.START);

                    View headerView = getLayoutInflater().inflate(
                            R.layout.header_navigation_drawer_social, mDrawerList, false);



                    DrawerList.addHeaderView(headerView);// Add header before adapter (for
                    // pre-KitKat)
                    DrawerList.setAdapter(adapter);

                    DrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v,
                                                    int groupPosition, long id) {
                            // We call collapseGroupWithAnimation(int) and
                            // expandGroupWithAnimation(int) to animate group
                            // expansion/collapse.
                            if (DrawerList.isGroupExpanded(groupPosition)) {
                                DrawerList.collapseGroupWithAnimation(groupPosition);
                            } else {
                                DrawerList.expandGroupWithAnimation(groupPosition);
                            }
                            return true;
                        }

                    });

                    DrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {


                        @Override
                        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                            if(i==0 && i1==0){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Fond de Teint");
                                mDrawerLayout.closeDrawers();
                                SubCategoryActivity.CheckLayout.setVisibility(View.VISIBLE);
                                System.out.println(StaticList.size()+" siizer");

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    System.out.println(StaticList.size()+" siizer222");
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    System.out.println(P.getNom());
                                    if (Objects.equals(P.SousCategorie, "Fond")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.StaticList = StaticList3;
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==0 && i1==1){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Poudres");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Poudres")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==0 && i1==2){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Contouring");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Contouring")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();



                            }

                            if(i==0 && i1==3){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Blush");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Blush")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==0 && i1==4){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Anti-Cernes & Crémes");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Anti-Cernes")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==0 && i1==5){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Base & Primer");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Base-Primer")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==0 && i1==6){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Enlemineur");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Enlemineur")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==1 && i1==0){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Fard");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Fard")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==1 && i1==1){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Mascaras");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Mascaras")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==1 && i1==2){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Crayons Yeux");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if ((Objects.equals(P.getSousCategorie(), "Crayons" ))&& ((Objects.equals(P.getCategorie(), "Yeux" )))) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==2 && i1==0){


                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Rouge à Lèvres");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Rouge")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();

                            }

                            if(i==2 && i1==1){

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Gloss");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Gloss")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();



                            }

                            if(i==2 && i1==2)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Crayons Lèvres");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if ((Objects.equals(P.getSousCategorie(), "Crayons"))&& ((Objects.equals(P.getCategorie(), "Levres" )))) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==3 && i1==0)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Demaquillant et Nettoyant");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Demaquillant")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==3 && i1==1)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Hydratant");
                                mDrawerLayout.closeDrawers();


                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println("ddd "+ P.getNom());
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Hydratant")) {
                                        StaticList3.add(P);

                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();



                            }

                            if(i==3 && i1==2)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Anti Ride/Age");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in>= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Anti")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==3 && i1==3)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Masques et Gommages");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Masques")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==4 && i1==0)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Vernis a Ongles");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Vernis")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==4 && i1==1)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Dissolvant");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Dissolvant")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==4 && i1==2)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("Soins des Ongles");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "Soins")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }

                            if(i==4 && i1==3)
                            {

                                viewpager.setCurrentItem(5);
                                mCenterNavigationTabStrip.setTitles("NailArt");
                                mDrawerLayout.closeDrawers();

                                StaticList3 = new ArrayList<Produit>();
                                for (int in = StaticList.size() - 1; in >= 0; in--) {
                                    Produit P = StaticList.get(in);
                                    System.out.println(P.getType_Peau());
                                    if (Objects.equals(P.getSousCategorie(), "NailArt")) {
                                        StaticList3.add(P);
                                    }

                                }

                                //SubCategoryActivity.StaticList = StaticList3;
                                SampleAdapter adapters = new SampleAdapter(getApplicationContext(), R.layout.one_product_stag, StaticList3);
                                SubCategoryActivity.gridView.setAdapter(adapters);
                                adapters.notifyDataSetChanged();


                            }




                            return true;
                        }
                    });

                    //   mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
                    int color = getResources().getColor(R.color.material_grey_100);
                    color = Color.argb(0xCD, Color.red(color), Color.green(color),
                            Color.blue(color));
                    DrawerList.setBackgroundColor(color);
                    DrawerList.getLayoutParams().width = (int) getResources()
                            .getDimension(R.dimen.drawer_width_social);

                    mDrawerToggle = new ActionBarDrawerToggle(ProductAllListActivity.this, mDrawerLayout, toolbar,
                            R.string.drawer_open, R.string.drawer_close) {
                        public void onDrawerClosed(View view) {
                            System.out.println("ClosED");
                            getSupportActionBar().setTitle(mTitle);
                            // invalidateOptionsMenu();
                        }

                        public void onDrawerOpened(View drawerView) {
                            viewpager.setCurrentItem(0);
                            System.out.println("Opeened");
                            getSupportActionBar().setTitle(mDrawerTitle);
                            invalidateOptionsMenu();
                        }
                    };
                    mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrawerLayout.openDrawer(GravityCompat.START);
                        }
                    });
                    mDrawerLayout.addDrawerListener(mDrawerToggle);
                    mDrawerToggle.syncState();







                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append("eeerror");

            }
        });

        requestQueue1.add(jsObjRequest);


    /*    if (extras == null) {

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });




                }
            }, 4000);

        }*/
















    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.ArticleBtn: {
                Intent i = new Intent(ProductAllListActivity.this, ArticleListActivity.class);
                startActivity(i);
                finish();

            }
            break;

            case R.id.NewsBtn: {
                Intent i = new Intent(ProductAllListActivity.this, NewsListActivity.class);
                startActivity(i);
                finish();
            }
            break;

            case R.id.ProduitBtn: {
                Intent i = new Intent(ProductAllListActivity.this, ProductAllListActivity.class);
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
        Activity test = (Activity) this;
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

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Toast.makeText(ProductAllListActivity.this,
                    "You selected position: " + position, Toast.LENGTH_SHORT)
                    .show();
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        /*
if((findViewById(android.R.id.content).getId())!=16908290)
{ mDrawerToggle.syncState();}


       this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {


                    }},10000);
            }//public void run() {
        });
*/

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


}
class myadapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener
{


    public myadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position==0)
        {

            fragment = new AllTeint() ;




        }
        if(position==1) {
            fragment = new AllYeux();
          //  return fragment;

        }
        if(position==2) {
            fragment = new AllLevres();
           // return fragment;
        }
        if(position==3) {
            fragment = new AllSoins();
          //  return fragment;
        }

        if(position==4) {
            fragment = new AllOngles();
          //  return fragment;
        }
        if(position==5) {
            fragment = new SubCategoryActivity();
         //   return fragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
