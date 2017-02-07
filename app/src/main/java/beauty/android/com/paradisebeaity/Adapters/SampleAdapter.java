package beauty.android.com.paradisebeaity.Adapters;

/**
 * Created by Hassan on 14/01/2017.
 */

import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;
        import android.content.Context;
        import android.graphics.Typeface;
        import android.util.Log;
        import android.util.SparseArray;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import com.etsy.android.grid.util.DynamicHeightImageView;
        import com.nostra13.universalimageloader.core.ImageLoader;

        import beauty.android.com.paradisebeaity.Models.Article;
        import beauty.android.com.paradisebeaity.Models.Produit;
        import beauty.android.com.paradisebeaity.R;
        import beauty.android.com.paradisebeaity.Utils.ImageUtil;

public class SampleAdapter extends ArrayAdapter<Produit> {
    private static final String TAG = "SampleAdapter";
    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    public SampleAdapter(Context context, int textViewResourceId,
                        List<Produit> objects) {
        super(context, textViewResourceId, objects);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
    }
    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.one_product_stag,
                    parent, false);
            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView
                    .findViewById(R.id.ProductImageGrid);
            vh.ProdTitle = (TextView) convertView.findViewById(R.id.ProductNameGrid);
            vh.ProdPlus = (TextView) convertView.findViewById(R.id.ProductPlusGris);
            vh.ProdPrice = (TextView) convertView.findViewById(R.id.ProductPrixGrid);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        double positionHeight = getPositionRatio(position);
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
                "Helvetica Neu Bold.ttf");
        Typeface face2 = Typeface.createFromAsset(getContext().getAssets(),
                "HelveticaNeueMed.ttf");
        Produit prod = getItem(position);
        vh.ProdTitle.setTypeface(face);
        vh.ProdPlus.setTypeface(face2);
        vh.ProdPrice.setTypeface(face2);
        vh.imgView.setHeightRatio(positionHeight);
        vh.ProdTitle.setText(prod.getNom());
        vh.ProdPrice.setText("€"+prod.getPrix()+"Euro");

        ImageUtil.displayImage(vh.imgView, prod.getImage(), null);

        return convertView;
    }
    static class ViewHolder {
        DynamicHeightImageView imgView;
        TextView ProdTitle,ProdPlus,ProdPrice;
    }
    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
// if not yet done generate and stash the columns height
// in our real world scenario this will be determined by
// some match based on the known height and width of the image
// and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }
    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
// the width
    }
}
