package beauty.android.com.paradisebeaity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.etsy.android.grid.StaggeredGridView;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import beauty.android.com.paradisebeaity.Adapters.SampleAdapter;
import beauty.android.com.paradisebeaity.Models.Produit;
import beauty.android.com.paradisebeaity.Utils.CustomRequest;

/**
 * Created by Hassan on 15/01/2017.
 */

public class AllOngles extends Fragment {

    public  static StaggeredGridView gridView;
    public static List<Produit> StaticList,StaticList2;
    RequestQueue requestQueue1;
    public ExpandableRelativeLayout expandableLayout;
    public Button RechercheToglle;
    public static Button Recherche;
    public EditText RechercheEnter;
    int countbtn ;
    ImageView UporDown;
    public ImageView NoProd;
    String showUrl = "http://www.maquillagenews.com/server/getproductbyCategorieOnly.php";
    public static RelativeLayout CheckLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_pager_fragment,container,false);

        CheckLayout = (RelativeLayout) view.findViewById(R.id.CheckLayout);
        CheckLayout.setVisibility(View.INVISIBLE);


        gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
         expandableLayout
                = (ExpandableRelativeLayout) view.findViewById(R.id.relative1);
        UporDown = (ImageView) view.findViewById(R.id.UporDown);

        RechercheToglle = (Button) view.findViewById(R.id.RecherchetToggle);
        NoProd = (ImageView) view.findViewById(R.id.noprod);
        Recherche = (Button) view.findViewById(R.id.RechercheBTN);
        RechercheEnter = (EditText) view.findViewById(R.id.RechercheEnter);

        RechercheToglle.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                countbtn = countbtn +1;
                if((countbtn % 2)==0)
                    UporDown.setImageResource(R.drawable.down2);
                else  UporDown.setImageResource(R.drawable.up2);
                expandableLayout.toggle();
                expandableLayout.moveChild(0);
// move optional position
                expandableLayout.move(500);


            }
        });

        Recherche.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                StaticList2 = new ArrayList<Produit>();
                //StaticList.clear();

                for (int i = StaticList.size()-1; i >= 0; i--)
                {
                    Produit P = StaticList.get(i);
                    System.out.println(P.getNom());
                    Boolean B = like(P.getNom(),RechercheEnter.getText()+"");
                    if ((P.getNom().toUpperCase()).contains((RechercheEnter.getText()+"").toUpperCase()))
                    {
                        StaticList2.add(P);
                    }




                }

                SampleAdapter adapters2 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList2);

                gridView.setAdapter(adapters2);
                adapters2.notifyDataSetChanged();


            }
        });
     //   expandableLayout.toggle();
// expand
     //   expandableLayout.expand();
// collapse
      //  expandableLayout.collapse();

// move position of child view
        //expandableLayout.moveChild(0);
// move optional position
        //expandableLayout.move(500);

// set base position which is close position
        //expandableLayout.setClosePosition(500);

        //ArticleList = (ListView) findViewById(R.id.list_article);
    /*    StaticList = new ArrayList<Produit>();
        Map<String, String> params = new HashMap<String, String>();

        params.put("categorie", "Ongles");


        requestQueue1 = Volley.newRequestQueue(getActivity().getApplicationContext()
        );
        //System.out.println("ww");
        final ProgressDialog loading;
        loading = ProgressDialog.show(getActivity(), "Chargement...", "Un petit moment svp...", false, false);

        loading.setContentView(R.layout.progress_dialog);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, showUrl, params, new Response.Listener<JSONObject>() {

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
                                (float) produit.getInt("prix"),
                                produit.getString("video"),
                                produit.getInt("rate"),
                                produit.getString("type_peau"),
                                produit.getString("type_tient"),
                                produit.getString("image")

                        ));


                        System.out.println(StaticList.size() + " my size");






                    }








                    System.out.println(StaticList.size()+" this is size");
                    if (StaticList.size()==0)
                    { NoProd.setVisibility(View.VISIBLE);}

                    SampleAdapter adapters = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList);

                    gridView.setAdapter(adapters);
                    adapters.notifyDataSetChanged();

                    loading.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append("eeerror");
                loading.dismiss();
            }
        });

        requestQueue1.add(jsObjRequest);  */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Produit prod = (Produit) parent.getItemAtPosition(position);


                //prodimg = prod.getProductImage();
                Intent i = new Intent(getActivity().getApplicationContext(), OneProduitActivity.class);
                i.putExtra("Titre", prod.getNom() + "");
                i.putExtra("Text", prod.getDescription() + "");
                i.putExtra("Image", prod.Image + "");
                i.putExtra("Rate", prod.getRate() );
                i.putExtra("RatedBy", prod.getRatedBy() );
                i.putExtra("Id", prod.getId() );
                i.putExtra("AllRate", prod.getAllRate() );
                i.putExtra("Prix", prod.getPrix() );



                startActivity(i);
                getActivity().finish();


            }
        });


        return  view;


    }

    public static boolean like(final String str, final String expr)
    {
        String regex = quotemeta(expr);
        regex = regex.replace("_", ".").replace("%", ".*?");
        Pattern p = Pattern.compile(regex,
                Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        return p.matcher(str).matches();
    }

    public static String quotemeta(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("String cannot be null");
        }

        int len = s.length();
        if (len == 0)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++)
        {
            char c = s.charAt(i);
            if ("[](){}.*+?$^|#\\".indexOf(c) != -1)
            {
                sb.append("\\");
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
