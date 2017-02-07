package beauty.android.com.paradisebeaity.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import beauty.android.com.paradisebeaity.R;
import beauty.android.com.paradisebeaity.Utils.ImageUtil;

/**
 * Created by Hassan on 08/02/16.
 */


public class SwipeImageAdapter extends PagerAdapter {

    private Context ctx ;
    private LayoutInflater layoutInflater;
    public static boolean touched=false;
    public static String img1;
    public static String img2;
    public static String img3;
    public static String img4;
    public static String img5;
    public static String img6;
    public static String title;
    public static String title2;
    public static String title3;
    public static String title4;
    public static String title5;
    public static String title6;
    public static String desc;
    public static String desc2;
    public static String desc3;
    public static String desc4;
    public static String desc5;
    public static String desc6;



    public SwipeImageAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater =(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.news_image_pager,container,false);

        Typeface Bold = Typeface.createFromAsset(ctx.getAssets(),
                "BebasNeue Bold.ttf");

        Typeface Light = Typeface.createFromAsset(ctx.getAssets(),
                "HelveticaNeue Medium.ttf");

        Typeface Regular = Typeface.createFromAsset(ctx.getAssets(),
                "BebasNeue Regular.ttf");




        ImageView MyImage = (ImageView) item_view.findViewById(R.id.ProductImage);
        TextView Titre = (TextView) item_view.findViewById(R.id.ImageName);
        TextView Description = (TextView) item_view.findViewById(R.id.ImageDesc);

       // Titre.setTypeface(Bold);
        Description.setTypeface(Light);





        if(position==0)

        {

            ImageUtil.displayImage(MyImage, img1, null);
            Titre.setText(title);
            Description.setText(desc);

        container.addView(item_view);


        }

        if(position==1)

        {   ImageUtil.displayImage(MyImage, img2, null);
            Titre.setText(title2);
            Description.setText(desc2);

            container.addView(item_view);


        }

        if(position==2)

        {  ImageUtil.displayImage(MyImage, img3, null);
            Titre.setText(title3);
            Description.setText(desc3);

            container.addView(item_view);


        }

        if(position==3)

        {  ImageUtil.displayImage(MyImage, img4, null);
            Titre.setText(title4);
            Description.setText(desc4);

            container.addView(item_view);


        }

        if(position==4)

        {  ImageUtil.displayImage(MyImage, img5, null);
            Titre.setText(title5);
            Description.setText(desc5);

            container.addView(item_view);


        }

        if(position==5)

        {  ImageUtil.displayImage(MyImage, img6, null);
            Titre.setText(title6);
            Description.setText(desc6);

            container.addView(item_view);


        }

//        container.addView(item_view);
        return item_view;







    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

}
