package beauty.android.com.paradisebeaity.Utils;

import beauty.android.com.paradisebeaity.R;
import beauty.android.com.paradisebeaity.Models.DummyModel;


import java.util.ArrayList;
import java.util.Random;

public class DummyContent {
	
	/* This method gives us just a dummy content - array list
	 * of ImageGalleryCategoryModels. Every model has id that is
	 * need for some classes (e.g. DefaultAdapter.java).
	 * Favourites are randomly chosen to be true or false.
	 * Last category is randomly added to the list so you could
	 * see when there are even or odd numbers of categories in
	 * ImageGalleryActivity.
	 */

	public static ArrayList<DummyModel> getSocialDummyListImage() {
		ArrayList<DummyModel> list = new ArrayList<>();
		list.add(new DummyModel(0, R.drawable.article_menu,"Nourriture","Légumes,Fruits,Viandes,A Manger,Oeufs,Miel,Céréales,Poissons,Huiles...",1));
		list.add(new DummyModel(1, R.drawable.article_menu,"Artisanat","Tapisserie,Broderie,Poterie,Peinture,Couture,Menuiserie,Céramique...",1));
		list.add(new DummyModel(2, R.drawable.article_menu,"Services","Ménage,plomberie,Eduquation,Babysitting,Cuisine...",1));
		list.add(new DummyModel(3, R.drawable.article_menu,"Multimedia","Ordinateur,Téléphones,TV,Son...",1));
		list.add(new DummyModel(4, R.drawable.article_menu,"Immobilier","Appartement,Maison,Garage,Locale,A louer,A vendre...",1));
		list.add(new DummyModel(5, R.drawable.article_menu,"Vetements","Pulls,Chemise,Pantalons,Chaussure,Bijoux...",1));
		list.add(new DummyModel(6, R.drawable.article_menu,"Recherche","Rechercher votre produit ou service par ville , par catégorie ou par prix",1));
		list.add(new DummyModel(7, R.drawable.article_menu,"Votre Compte","Mettre a jour votre compte , vos produits et vos services",1));
		list.add(new DummyModel(8, R.drawable.article_menu,"Déconnexion","Au revoir et a la prochaine connexion...",1));
		return list;
	}


}
