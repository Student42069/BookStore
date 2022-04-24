/**
* Cette classe represente la classe Livre po
*
* @author -
* Code permanent :-
* Courriel : -
* Cours : -
* @version 2022-04-04
*/

public class Livre
{
	public final static String CATEGORIE_1 = "Science fiction";
	public final static String CATEGORIE_2 = "Romance";
	public final static String CATEGORIE_3 = "Thriller";
	public final static String CATEGORIE_4 = "Policier";
	public final static String CATEGORIE_5 = "Humour";
	public final static String CATEGORIE_6 = "Drame";
	public static final String[] NOMS_CATEGORIES = {CATEGORIE_1, CATEGORIE_2,
			CATEGORIE_3, CATEGORIE_4, CATEGORIE_5, CATEGORIE_6};
	
	static int nbrProduitsInventaire0 = 0;
	
	private String titre;
	private String auteur;
	private double prix;
	private int[] codesCategories;
	private int qteEnInventaire;
	private int qteAchetee;
	
    Livre(String titre, String auteur, double prix, int qteEnInventaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.prix = prix;
        this.qteEnInventaire = qteEnInventaire;
		if (qteEnInventaire ==0) {
			nbrProduitsInventaire0++;
		}
        this.codesCategories = new int[0];
        this.qteAchetee = 0;
        
    }

	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public double getPrix() {
		return prix;
	}
	
	public int getQteAchetee() {
		return qteAchetee;
	}

	public int getQteEnInventaire() {
		return qteEnInventaire;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public void setQteAchetee(int qteAchetee) {
		this.qteAchetee = qteAchetee;
	}
    
	public void diminuerQteInventaire(int qteDeMoins) {
		if (!(this.qteEnInventaire <= 0)) {
			this.qteEnInventaire -= qteDeMoins;
			if (this.qteEnInventaire <= 0) {
				this.qteEnInventaire = 0;
				nbrProduitsInventaire0 += 1;
			}
		}
	}

	public void augmenterQteInventaire(int qteDePlus) {
		if (this.qteEnInventaire == 0) {
			nbrProduitsInventaire0 -= 1;
		}
		this.qteEnInventaire += qteDePlus;
	}
	
	public boolean estClasseDansCategorie(String categorie) {
		for (int i = 0; i < this.codesCategories.length; i++) {
			if (NOMS_CATEGORIES[this.codesCategories[i]].toLowerCase()
					.equals(categorie.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean ajouterCategorie(int codeCat) {
		if (livreALeCode(codeCat) || codeCat > 5 || codeCat < 0) {
				return false;
		}

		//Un tableau d'une unite plus grand est cree
		int[] temp = new int[this.codesCategories.length + 1];
		for (int i = 0; i < this.codesCategories.length; i++) {
			temp[i] = this.codesCategories[i];
		}
		
		//Le tableau original est agrandi et les valeur recopie
		this.codesCategories = new int[temp.length];
		for (int i = 0; i < temp.length - 1; i++) {
			this.codesCategories[i] = temp[i];
		}
		
		//Le code peut maintenant etre rajoute au tableau
		this.codesCategories[temp.length - 1] = codeCat;

		return true;
	}
	
	public boolean retirerCategorie(int codeCat) {
		if (!livreALeCode(codeCat)) {
			return false;
		}
		//Un tableau temporaire de une unite plus petite est cree
		int[] temp = new int[this.codesCategories.length - 1];
		
		for (int i = 0; i < this.codesCategories.length; i++) {
            if(this.codesCategories[i] == codeCat) {
                for(int index = 0; index < i; index++) {
                    temp[index] = this.codesCategories[index];
                }
                for(int j = i; j < this.codesCategories.length - 1; j++) {
                    temp[j] = this.codesCategories[j+1];
                }
                //Pour briser la boucle sans break
                this.codesCategories = new int[0];
            }
        }
		
		//Les valeur sont recopie dans le tableau original
		this.codesCategories = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			this.codesCategories[i] = temp[i];
		}

		return true;
		
	}
	
	private boolean livreALeCode(int codeCat) {
		for (int code : this.codesCategories) {
			if (code == codeCat) {
				return true;
			}
		}
		return false;
	}
	
	public Livre copier() {
		Livre livre = new Livre(this.titre, this.auteur, this.prix, this.qteEnInventaire);
		livre.setQteAchetee(this.qteAchetee);
		livre.augmenterQteInventaire(this.getQteEnInventaire());
		for (int code : this.codesCategories) {
			livre.ajouterCategorie(code);
		}
		return livre;
	}
	
	public String toString() {
		return this.titre+ " - " + this.auteur + " (" + this.prix + " $)";
	}
	
	public static String getNomCategorie(int codeCat) {
		if (codeCat > NOMS_CATEGORIES.length - 1) {
			return null;
		}
		return NOMS_CATEGORIES[codeCat];
	}
	//Je l'ai ajoute pour eviter l'erreur dans la classe de test
	public static int getNbrProduitsInventaire0() {
		return nbrProduitsInventaire0;
	}
}
