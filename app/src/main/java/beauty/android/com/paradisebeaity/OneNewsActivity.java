package beauty.android.com.paradisebeaity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import beauty.android.com.paradisebeaity.Utils.ImageUtil;
import beauty.android.com.paradisebeaity.ui.BaseActivity;

/**
 * Created by Hassan on 12/01/2017.
 */

public class OneNewsActivity extends BaseActivity  {

     ImageView NewsImg,NewsImg2;
    TextView NewsTitle,NewsDate,NewsText,NewsSource;



    protected ImageLoader imageLoader;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        setContentView(R.layout.one_news_layout);

        NewsTitle = (TextView) findViewById(R.id.produit_titre);
        NewsDate = (TextView) findViewById(R.id.news_date);
        NewsText = (TextView) findViewById(R.id.produit_text);
        NewsSource = (TextView) findViewById(R.id.news_source);
        NewsImg = (ImageView) findViewById(R.id.produit_image) ;
        NewsImg2 = (ImageView) findViewById(R.id.photo2) ;




        initToolbar();

        Typeface Bold = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "BebasNeue Bold.ttf");

        Typeface Light = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "HelveticaNeue Medium.ttf");

        Typeface Regular = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "BebasNeue Regular.ttf");

        NewsTitle.setTypeface(Bold);
        NewsText.setTypeface(Light);
        NewsSource.setTypeface(Bold);
        NewsDate.setTypeface(Regular);

        Bundle extras = getIntent().getExtras();
        NewsTitle.setText(extras.getString("Titre"));
        NewsDate.setText(extras.getString("Date"));
        NewsText.setText(extras.getString("Text"));
        NewsSource.setText(extras.getString("Source"));



        ImageUtil.displayImage(NewsImg, extras.getString("Image"), null);
        ImageUtil.displayImage(NewsImg2, extras.getString("Image2"), null);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(OneNewsActivity.this, NewsListActivity.class);
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
                Intent i = new Intent(OneNewsActivity.this, ArticleListActivity.class);
                startActivity(i);
                finish();

            }
            break;

            case R.id.NewsBtn: {
                Intent i = new Intent(OneNewsActivity.this, NewsListActivity.class);
                startActivity(i);
                finish();
            }
            break;

            case R.id.ProduitBtn: {
                Intent i = new Intent(OneNewsActivity.this, ProductAllListActivity.class);
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






}

