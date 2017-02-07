package beauty.android.com.paradisebeaity.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import beauty.android.com.paradisebeaity.Models.News;
import beauty.android.com.paradisebeaity.R;
import beauty.android.com.paradisebeaity.Utils.ImageUtil;

public class NewsCustomAdapter extends ArrayAdapter<News> {

	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContext;

	  public NewsCustomAdapter(Context context, int resourceId, List<News> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContext = context;
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


	  }
	  
	  //ViewHolder Design Pattern
	  public static class ViewHolder {
		    public TextView NewsTitre, NewsDate,NewsContenu;
		    public ImageView  NewsImage,ArticleImage2,ArticleAuthorImage;
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
			  holder.NewsImage = (ImageView) rowView.findViewById(R.id.produit_image);
			  holder.NewsTitre = (TextView) rowView.findViewById(R.id.news_title);
			  holder.NewsDate = (TextView) rowView.findViewById(R.id.news_date);
			  holder.NewsContenu = (TextView) rowView.findViewById(R.id.news_contenu);



		  rowView.setTag(holder);//insérer le holder dans notre layout
		  }else {
			  //Affecter les données aux Views
			  holder = (ViewHolder) rowView.getTag(); // Si on recycle la vue, on récupère son holder en tag
		  }
		  News prod = getItem(position); // Dans tous les cas, on récupère l'article concerné

		  Typeface Bold = Typeface.createFromAsset(getContext().getAssets(),
				  "BebasNeue Bold.ttf");

		  Typeface Light = Typeface.createFromAsset(getContext().getAssets(),
				  "HelveticaNeue Medium.ttf");

		  Typeface Regular = Typeface.createFromAsset(getContext().getAssets(),
				  "BebasNeue Regular.ttf");

		 // holder.NewsTitre.setTypeface(Bold);
		  holder.NewsDate.setTypeface(Regular);
		  holder.NewsContenu.setTypeface(Light);



		  holder.NewsTitre.setText(prod.getTitre());
		  holder.NewsDate.setText(prod.getDate());
		  holder.NewsContenu.setText(prod.getContenu());
		  //holder.ArticleAuthor.setText( prod.getAuthor());

		//  holder.Productimage.setImageResource(prod.getProductImage());
		  //holder.UserImage.setImageResource(prod.getUser().getUserImg());
		  ImageUtil.displayImage(holder.NewsImage, prod.getImage(), null);

		 // Picasso.with(mContext).load(article.getImage()).into(holder.image);

		  return rowView;
	  }

	}