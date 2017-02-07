package beauty.android.com.paradisebeaity;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.etsy.android.grid.StaggeredGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beauty.android.com.paradisebeaity.Adapters.ExampleAdapter;
import beauty.android.com.paradisebeaity.Adapters.SampleAdapter;
import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.Utils.CustomRequest;
import beauty.android.com.paradisebeaity.ui.AnimatedExpandableListView;
import beauty.android.com.paradisebeaity.ui.BaseActivity;

import static beauty.android.com.paradisebeaity.Adapters.ExampleAdapter.*;

/**
 * Created by Hassan on 11/01/2017.
 */

public class ProductListActivity {



}