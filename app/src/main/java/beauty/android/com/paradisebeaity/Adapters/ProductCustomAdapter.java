package beauty.android.com.paradisebeaity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import beauty.android.com.paradisebeaity.Models.Article;
import beauty.android.com.paradisebeaity.R;
import beauty.android.com.paradisebeaity.Utils.ImageUtil;

public class ProductCustomAdapter extends ArrayAdapter<Article> {

	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContext;

	  public ProductCustomAdapter(Context context, int resourceId, List<Article> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContext = context;
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


	  }
	  
	  //ViewHolder Design Pattern
	  public static class ViewHolder {
		    public TextView ArticleTitre, ArticlePlus;
		    public ImageView ArticleImage,ArticleImage2,ArticleAuthorImage;
		  }
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  View rowView = convertView;
		  //parent est le layout , convertview est notre vue avec scroll = rowView, resourceid bagla leha = 0

		  ViewHolder holder = new ViewHolder();
		  //Réutiliser les Views
		  if (rowView == null) {
			rowView = inflater.inflate(resourceId, parent, false);
			  //Configuration du ViewHolder
			  holder.ArticleImage = (ImageView) rowView.findViewById(R.id.ProductImageGrid);
			  holder.ArticleTitre = (TextView) rowView.findViewById(R.id.ProductNameGrid);
			  holder.ArticlePlus = (TextView) rowView.findViewById(R.id.ProductPlusGris);



		  rowView.setTag(holder);//insérer le holder dans notre layout
		  }else {
			  //Affecter les données aux Views
			  holder = (ViewHolder) rowView.getTag(); // Si on recycle la vue, on récupère son holder en tag
		  }
		  Article prod = getItem(position); // Dans tous les cas, on récupère l'article concerné

		  // On place dans le holder les informations sur l'article
		  holder.ArticleTitre.setText(prod.getTitre());

		  holder.ArticlePlus.setText( prod.getAuthor());

		//  holder.Productimage.setImageResource(prod.getProductImage());
		  //holder.UserImage.setImageResource(prod.getUser().getUserImg());
		  ImageUtil.displayImage(holder.ArticleImage, prod.getImage(), null);

		 // Picasso.with(mContext).load(article.getImage()).into(holder.image);

		  return rowView;
	  }

	}