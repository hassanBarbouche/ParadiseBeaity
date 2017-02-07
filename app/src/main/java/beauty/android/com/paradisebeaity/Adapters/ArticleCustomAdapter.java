package beauty.android.com.paradisebeaity.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import beauty.android.com.paradisebeaity.R;
import beauty.android.com.paradisebeaity.Utils.ImageUtil;
import beauty.android.com.paradisebeaity.Models.Article;

import java.util.List;

public class ArticleCustomAdapter extends ArrayAdapter<Article> {

	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContext;

	  public ArticleCustomAdapter(Context context, int resourceId, List<Article> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContext = context;
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


	  }
	  
	  //ViewHolder Design Pattern
	  public static class ViewHolder {
		    public TextView ArticleTitre, ArticleDate,ArticleContenu,ArticleAuthor,ArticleContenu2,Likes;
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
			  holder.ArticleAuthorImage = (ImageView) rowView.findViewById(R.id.AuthorImg2);
			  holder.ArticleImage = (ImageView) rowView.findViewById(R.id.ArticleImage2);
			  holder.ArticleTitre = (TextView) rowView.findViewById(R.id.ArticleName2);
			  holder.Likes = (TextView) rowView.findViewById(R.id.NbrLike);

			  holder.ArticleContenu = (TextView) rowView.findViewById(R.id.ArticleText2);
			  holder.ArticleAuthor = (TextView) rowView.findViewById(R.id.AuthorName2);


		  rowView.setTag(holder);//insérer le holder dans notre layout
		  }else {
			  //Affecter les données aux Views
			  holder = (ViewHolder) rowView.getTag(); // Si on recycle la vue, on récupère son holder en tag
		  }
		  Article prod = getItem(position); // Dans tous les cas, on récupère l'article concerné

		  Typeface Bold = Typeface.createFromAsset(getContext().getAssets(),
				  "BebasNeue Bold.ttf");

		  Typeface Light = Typeface.createFromAsset(getContext().getAssets(),
				  "HelveticaNeue Medium.ttf");

		  Typeface Regular = Typeface.createFromAsset(getContext().getAssets(),
				  "BebasNeue Regular.ttf");
		  holder.ArticleTitre.setTypeface(Bold);
		  holder.ArticleContenu.setTypeface(Light);
		  holder.ArticleAuthor.setTypeface(Regular);

		  holder.ArticleTitre.setText("#"+prod.getTitre());
		  //holder.ArticleDate.setText(prod.getDate());
		  holder.ArticleContenu.setText(prod.getContenu());
		  holder.Likes.setText(prod.getLikes()+"");
		  holder.ArticleAuthor.setText( "By "+prod.getAuthor());

		//  holder.Productimage.setImageResource(prod.getProductImage());
		  //holder.UserImage.setImageResource(prod.getUser().getUserImg());
		  ImageUtil.displayImage(holder.ArticleImage, prod.getImage(), null);
		  ImageUtil.displayRoundImage(holder.ArticleAuthorImage, prod.getAuthorImg(), null);

		 // Picasso.with(mContext).load(article.getImage()).into(holder.image);

		  return rowView;
	  }

	}