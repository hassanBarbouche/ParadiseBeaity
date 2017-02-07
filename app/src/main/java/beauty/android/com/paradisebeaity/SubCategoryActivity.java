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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.Objects;

import beauty.android.com.paradisebeaity.Adapters.SampleAdapter;
import beauty.android.com.paradisebeaity.Models.Produit;
import beauty.android.com.paradisebeaity.Utils.CustomRequest;

/**
 * Created by Hassan on 15/01/2017.
 */

public class SubCategoryActivity extends Fragment {

    public  static StaggeredGridView gridView;
    public static List<Produit> StaticList,StaticList3;
    RequestQueue requestQueue1;
    public ExpandableRelativeLayout expandableLayout;
    public Button RechercheToglle;
    public ImageView NoProd;
    int countbtn;
    public static String SubCat,Cat;
    public static CheckBox ch1,ch2,ch3,ch4;
    public static RelativeLayout CheckLayout;
    public Button Recherche;
    ImageView UporDown;
    public EditText RechercheEnter;
    String showUrl = "http://www.maquillagenews.com/server/getproductbySubCategry.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_pager_fragment2,container,false);

        CheckLayout= (RelativeLayout) view.findViewById(R.id.CheckLayout);
        gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
         expandableLayout
                = (ExpandableRelativeLayout) view.findViewById(R.id.relative1);


        RechercheToglle = (Button) view.findViewById(R.id.RecherchetToggle);
        NoProd = (ImageView) view.findViewById(R.id.noprod);
        RechercheEnter = (EditText) view.findViewById(R.id.RechercheEnter);
        UporDown = (ImageView) view.findViewById(R.id.UporDown);

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

        StaticList = new ArrayList<Produit>();
        StaticList3 = new ArrayList<Produit>();

        ch1= (CheckBox) view.findViewById(R.id.peau_normal);
        ch2= (CheckBox) view.findViewById(R.id.peau_grasse);
        ch3= (CheckBox) view.findViewById(R.id.peau_seche);

        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                           @Override
                                           public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                               if(isChecked) {

                                                   for (int i = StaticList.size() - 1; i >= 0; i--) {
                                                       Produit P = StaticList.get(i);
                                                       System.out.println(P.getType_Peau());
                                                       if (Objects.equals(P.getType_Teint(), "Legere")) {
                                                           StaticList3.add(P);
                                                       }

                                                   }

                                                   SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList3);

                                                   gridView.setAdapter(adapters3);
                                                   adapters3.notifyDataSetChanged();

                                               }

                                               else {

                                                   if (!ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked() )
                                                   {
                                                       SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList);

                                                       gridView.setAdapter(adapters3);
                                                       adapters3.notifyDataSetChanged();

                                                   }

                                                   for (int i = StaticList.size() - 1; i >= 0; i--) {
                                                       Produit P = StaticList.get(i);
                                                       System.out.println(P.getType_Peau());
                                                       if (Objects.equals(P.getType_Teint(), "Legere")) {
                                                           StaticList3.remove(P);
                                                       }

                                                   }

                                                   SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList3);

                                                   gridView.setAdapter(adapters3);
                                                   adapters3.notifyDataSetChanged();


                                               }

                                           }
                                       }
        );

        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                           @Override
                                           public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                               if(isChecked) {

                                                   for (int i = StaticList.size() - 1; i >= 0; i--) {
                                                       Produit P = StaticList.get(i);
                                                       System.out.println(P.getType_Peau());
                                                       if (Objects.equals(P.getType_Teint(), "Moyenne")) {
                                                           StaticList3.add(P);
                                                       }

                                                   }

                                                   SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList3);

                                                   gridView.setAdapter(adapters3);
                                                   adapters3.notifyDataSetChanged();


                                               }

                                               else {

                                                   if (!ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked())
                                                   {
                                                       SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList);

                                                       gridView.setAdapter(adapters3);
                                                       adapters3.notifyDataSetChanged();

                                                   }

                                                   for (int i = StaticList.size() - 1; i >= 0; i--) {
                                                       Produit P = StaticList.get(i);
                                                       System.out.println(P.getType_Teint());
                                                       if (Objects.equals(P.getType_Teint(), "Moyenne")) {
                                                           StaticList3.remove(P);
                                                       }

                                                   }

                                                   SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList3);

                                                   gridView.setAdapter(adapters3);
                                                   adapters3.notifyDataSetChanged();


                                               }
                                           }
                                       }
        );

        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                           @Override
                                           public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                               if(isChecked) {
                                                   for (int i = StaticList.size()-1; i >= 0; i--)
                                                   {
                                                       Produit P = StaticList.get(i);
                                                       System.out.println(P.getType_Teint());
                                                       if (Objects.equals(P.getType_Teint(), "Forte"))
                                                       {
                                                           StaticList3.add(P);
                                                       }

                                                   }

                                                   SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList3);

                                                   gridView.setAdapter(adapters3);
                                                   adapters3.notifyDataSetChanged();



                                               }

                                               else {

                                                   if (!ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked() )
                                                   {
                                                       SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList);

                                                       gridView.setAdapter(adapters3);
                                                       adapters3.notifyDataSetChanged();

                                                   }

                                                   for (int i = StaticList.size() - 1; i >= 0; i--) {
                                                       Produit P = StaticList.get(i);
                                                       System.out.println(P.getType_Teint());
                                                       if (Objects.equals(P.getType_Teint(), "Forte")) {
                                                           StaticList3.remove(P);
                                                       }

                                                   }

                                                   SampleAdapter adapters3 = new SampleAdapter(getActivity().getApplicationContext(), R.layout.one_product_stag, StaticList3);

                                                   gridView.setAdapter(adapters3);
                                                   adapters3.notifyDataSetChanged();


                                               }

                                           }


                                       }
        );



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
                i.putExtra("Rate", prod.Rate );
                i.putExtra("Prix", prod.getPrix() );



                startActivity(i);
                getActivity().finish();


            }
        });


        return  view;


    }
}
