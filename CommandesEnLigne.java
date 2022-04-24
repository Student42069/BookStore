/**
* Cette classe represente le programme de commande en ligne,
*	en lien avec la classe Livre pour le TP3
*
* @author -
* Code permanent : -
* Courriel : -
* Cours : -
* @version 2022-04-04
*/

public class CommandesEnLigne
{
	public static final String LIBRAIRIE = "L I B R A I R I E   A   L I V R E   O U V E R T\n\n";
	public static final String MENU_PRINCIPALE = "-----------------\r\n"
			+ "COMMANDE EN LIGNE\r\n-----------------\r\n"
			+ "1. Magasiner\r\n2. Voir panier\r\n"
			+ "3. Quitter\r\n\r\nEntrez votre choix au menu : ";
	public static final String ERR_MENU_PRINCIPALE = "\nErreur, choix invalide! Recommencez...\n";
	public static final String ERR_CHOIX_FACTURE = "Erreur, choix invalide! Recommencez...";
	public static final String MERCI = "\n\nMerci et a la prochaine !";
	public static final String CHOIX_CATEGORIE = "\r\n----------\r\n"
			+ "CATEGORIES\r\n----------\r\n1. Science fiction\r\n"
			+ "2. Romance\r\n3. Thriller\r\n"
			+ "4. Policier\r\n5. Humour\r\n6. Drame\r\n\r\n"
			+ "Entrez une categorie (ENTREE pour annuler): ";
	public static final String CATEGORIE_INVALIDE = "\nCategorie invalide ! Recommencez...";
	public static final String CHOIX_LIVRE = "     (S)uivant, (P)recedent, (A)jouter au panier ou (T)erminer : ";
	public static final String ERR_CHOIX_LIVRE = "     Erreur, choix invalide! Recommencez...\n";
	public static final String ERR_QUANTITE = "\n     Erreur, quatite invalide";
	public static final String RUPTURE = "\n     *** Ce livre est en rupture de stock.";
	public static final String PANIER_VIDE = "\n*** Votre panier est vide\r\n"
			+ "\nAppuyez sur <ENTREE> pour revenir au menu principal...";
	 public static final String CONTENU = "\n-----------------\r\n"
	 		+ "CONTENU DU PANIER\r\n-----------------\n";
	 public static final String LIGNE = "------------------------------------------------------------------------------------";
	public static final String LIGNE_EGAL = "====================================================================================";
	public static final String CHOIX_FACTURE = "\n(R)etirer item, (P)asser la commande, ou (T)erminer : ";
	public static final String ITEM_RETIRER = "Entrez le numero de l'item a retirer (0 pour annuler): ";
	public static final String ERR_ITEM = "\nErreur, item invalide ! Recommencez...";
	public static final String EXPEDIE = "\n************************************************************\r\n"
			+ "* Merci d'avoir magasine chez A livre ouvert !             *\r\n"
			+ "* Votre commande sera expediee dans les plus brefs delais. *\r\n"
			+ "************************************************************\n\n"
			+ "Appuyez sur <ENTREE> pour revenir au menu principal...";

	public static final double TAUX_TPS = 5/100f;
	public static final double TAUX_TVQ = 9.975/100f;

    public static void main(String[] args) {
		int choixMenu;
		String categorie;
		Livre[] livresDeLaCat;
		Livre[] panier = {};

		System.out.println(LIBRAIRIE);
    	Livre[] livres = initialiserLivres();

		do {
    		choixMenu = choixMenu();
			if (choixMenu == 1) {
				do {
					categorie = choixCategorie();
					if (!categorie.equals("")) {
						livresDeLaCat = livresDeLaCat(categorie, livres);
						panier = gererLesAchats(livresDeLaCat, panier);
					}
				} while (!categorie.equals(""));
			} 
			else if (choixMenu == 2) {
				if (panier.length == 0) {
					System.out.println(PANIER_VIDE);
					Clavier.lireFinLigne();
				} else {
					panier = facture(panier);
				}
			} 
			else {
				System.out.println(MERCI);
			}
		} while (choixMenu != 3);

    }

	/**
     * Methode qui gere l'affichage de la facture et les les options qui y 
	 * sont associees
     *
     * @param  panier est le tableau des livre dans le panier
     * 
     * @return  Livre[] le tableau represantant le panier mis-a-jour
     */
	public static Livre[] facture(Livre[] panier) {
		String choixPanier;
		String choixItem;

		do {
			imprimerFacture(panier);
			choixPanier = choixPanier();
			if (choixPanier.equals("R")) {
				choixItem = choixItem(panier.length);
				if (!choixItem.equals("0")) {
					panier = retirerLivre(panier, Integer.parseInt(choixItem));
				}
			} else if (choixPanier.equals("P")) {
				panier = new Livre[0];
				System.out.print(EXPEDIE);
				Clavier.lireFinLigne();
			}
		} while(!choixPanier.equals("T") && !choixPanier.equals("P"));
		System.out.println();
		return panier;
	}

	/**
     * Methode qui gere le retrait d'un livre dans le panier
     *
     * @param  panier est le tableau des livre dans le panier
     * @param  choix est le choix du numero de livre(sur la facture) a retirer
     * 
     * @return  Livre[] le tableau represantant le panier mis-a-jour
     */
	public static Livre[] retirerLivre(Livre[] panier, int choix) {
		choix--;
		//Un tableau de une unite plus petite est cree
		Livre[] temp = new Livre[panier.length - 1];
		
		for (int i = 0; i < panier.length; i++) {
            if(i == choix) {
                for(int index = 0; index < i; index++) {
                    temp[index] = panier[index];
                }
                for(int j = i; j < panier.length - 1; j++) {
                    temp[j] = panier[j+1];
                }
                //Pour briser la boucle sans break
                panier = new Livre[0];
            }
        }
		return temp;
	}
	
	/**
     * Methode qui gere le choix d'un item a retirer du panier
     *
     * @param  len pour length est la longueur du tableau panier
     * 
     * @return  String un chaine de caractere de 1 caractere numerique
	 * 			entre 0 et la longueur du tableau panier
     */
	public static String choixItem(int len) {
		String choixItem;
		System.out.print(ITEM_RETIRER);
		choixItem = Clavier.lireString().toUpperCase();
		while (!itemValide(len, choixItem)) {
			System.out.println(ERR_ITEM);
			System.out.print(ITEM_RETIRER);
			choixItem = Clavier.lireString().toUpperCase();
		}
		return choixItem;
	}

	/**
     * Methode qui verifie que le numero d'item entre est un chiffre valide
	 * donc entre 0 et la longueur du tableau panier. Sert a resoudre le probleme
	 * que le nombre de livre dans le panier est variable est donc 
	 * les choix de numeros aussi
     *
     * @param  len est la longueur du tableau panier
     * @param  choix est le choix du numero de livre(sur la facture) a retirer
     * 
     * @return  Livre[] le tableau represantant le panier mis-a-jour
     */
	public static boolean itemValide(int len, String choix) {
		char[] valides = new char[len + 1];
		char caractere = '0';
		if (choix.length() != 0) {
			for (int i = 0; i <= len; i++) {
				valides[i] = caractere++;
			}
			for (char chiffre : valides) {
				if (chiffre == choix.charAt(0)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
     * Methode qui gere le choix a faire apres l'affichage du panier
     * 
     * @return  String une lettre majuscule du choix fait par l'utilisateur
     */
	public static String choixPanier(){
		String choix;
		System.out.print(CHOIX_FACTURE);
		choix = Clavier.lireString().toUpperCase();
		while (!choix.equals("R") && !choix.equals("P") && !choix.equals("T")) {
			System.out.println(ERR_CHOIX_FACTURE);
			System.out.print(CHOIX_FACTURE);
			choix = Clavier.lireString().toUpperCase();
		}
		return choix.toUpperCase();
	}

	/**
     * Methode qui affiche la facture a la console
     *
     * @param  panier pour length est la longueur du tableau panier
     */
	public static void imprimerFacture(Livre[] panier){
		double sousTotal = 0;
		double total;
		double prix;
		double tps;
		double tvq;
		double livraison = 10.00;

		System.out.println(CONTENU);
		System.out.printf("%-64s | %5s | %7s %n", "ITEM", "QTE", "PRIX");
		System.out.println(LIGNE);
		for (int i = 0; i< panier.length; i++) {
			prix = panier[i].getPrix() * panier[i].getQteAchetee();
			sousTotal += prix;
			System.out.printf("%-64s | %5d | %7.2f $ %n", i+1 + ". " + panier[i],
								panier[i].getQteAchetee(), prix);
		}
		System.out.println(LIGNE);

		tps = sousTotal * TAUX_TPS;
		tvq = sousTotal * TAUX_TVQ;
		total = sousTotal + tps + tvq + livraison;

		System.out.printf("%-64s %17.2f $ %n", "Sous-total", sousTotal);
		System.out.printf("%-64s %17.2f $ %n", "TPS", tps);
		System.out.printf("%-64s %17.2f $ %n", "TVQ", tvq);
		System.out.printf("%-64s %17.2f $ %n", "LIVRAISON", livraison);
		System.out.println(LIGNE_EGAL);
		System.out.printf("%-64s %17.2f $ %n", "TOTAL", total);
	}

	/**
     * Methode qui gere la navigation dans la librairie et l'affichage des
	 * livres dans celle ci
     *
     * @param  livresDeLaCat est un tableau des livres dans la categorie choisi
	 * par l'utilisateur
	 * 
     * @param  panier est le panier de livres
     * 
     * @return  Livre[]  le panier de livre mis-a-jour
     */
	public static Livre[] gererLesAchats(Livre[] livresDeLaCat, Livre[] panier) {
		String choix;
		int quantite;
		int max = livresDeLaCat.length;
		System.out.println("* " + max + " LIVRE(S) DANS CETTE CATEGORIE *");
		int noDeLivre = 1;
		Livre livre;

		do {
			livre = livresDeLaCat[noDeLivre - 1];
			System.out.println("\n" + noDeLivre + ". " + livre.toString());
			imprimerLigne(livre.toString().length() + 3);
			choix = choixLivre();
			if (choix.equals("P")) {
				noDeLivre -= 1;
				//Pour ne pas naviguer en dessous du premier livre
				if (noDeLivre < 1) {noDeLivre = 1;}
			} else if (choix.equals("S")) {
				noDeLivre += 1;
				//Pour ne pas naviguer au dessus du dernier livre
				if (noDeLivre > max) {noDeLivre = max;}
			} else if (choix.equals("A")) {
				if (livre.getQteEnInventaire() == 0) {
					System.out.println(RUPTURE);
				} else {
					quantite = TP3Utils.validerEntier("\n     Quantite (entre 0 et " 
									+ livre.getQteEnInventaire() + ") : ", 
									ERR_QUANTITE, 0,
									livre.getQteEnInventaire());
					if (quantite != 0) {
						panier = ajoutPanier(livre, panier, quantite);
						System.out.println("\n     *** " + quantite 
											+ " livre(s) ajoute(s) au panier.");
					}
				}
				
			}
		} while(!choix.equals("T"));
		return panier;
	}

	/**
     * Methode qui imprime un ligne de tiret (-) de la longuer donne en parametre
     *
     * @param  len longueur desire (ici la longueur du titre du livre)
	 * 
     */
	public static void imprimerLigne(int len) {
		for (int i = 0; i < len; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	/**
     * Methode qui gere l'ajout d'un livre au tableau de livre (panier)
     *
     * @param  livre etant le livre a ajouter au panier
     * @param  panier est le panier de livres
     * @param  quantite  est la quantite de ce livre achete
     * 
     * @return  Livre[]  le panier de livre mis-a-jour
     */
	public static Livre[] ajoutPanier(Livre livre, Livre[] panier, int quantite) {
		Livre copie = livre.copier();
		livre.diminuerQteInventaire(quantite);
		copie.setQteAchetee(quantite);

		//Un nouveau tableau de Livre est cree de une unite plus grande
		Livre[] temp = new Livre[panier.length + 1];

		//Les valeur de l'ancien panier recopie
		for (int i = 0; i < panier.length; i++) {
			temp[i] = panier[i];
		}
		
		//Le nouveau livre ajoute au panier
		temp[temp.length - 1] = copie;
		return temp;
	}

	/**
     * Methode qui gere le choix fait par l'utilisateur sur un livre 
	 * donne dans la librairie
	 * (S) suivant, (P) precedant, (A) ajouter au panier et 
	 * (T) terminer/retourner en arriere
     * 
     * @return  String etant le choix de l'utilisateur
     */
	public static String choixLivre() {
		String choix;
		System.out.print(CHOIX_LIVRE);
		choix = Clavier.lireString().toUpperCase();
		while (!choix.equals("S") && !choix.equals("P") && !choix.equals("A") 
				&& !choix.equals("T")) {
			System.out.println(ERR_CHOIX_LIVRE);
			System.out.print(CHOIX_LIVRE);
			choix = Clavier.lireString().toUpperCase();
		}
		return choix.toUpperCase();
	}

	/**
     * Methode qui sert a construire le tableau des livres dans un categorie donne
     *
     * @param  categorie est la categorie qui nous interesse
     * @param  livres est le panier de livres
     * 
     * @return  Livre[]  un tableau des livres qui ont cette categorie
     */
	public static Livre[] livresDeLaCat(String categorie, Livre[] livres) {
		Livre[] livreAvecLaCat;
		//Count pour longueur de tableau
		int count = 0;

		//Un premier parcourt pour connaitre de quel longueur initialiser le tableau
		for (Livre livre : livres) {
			if (livre.estClasseDansCategorie(Livre.NOMS_CATEGORIES[Integer
														.parseInt(categorie)-1])){
				count++;
			}
		}

		livreAvecLaCat = new Livre[count];

		//Count pour garder les index de panier et livresDeLaCat separe
		count = 0;

		//Un second parcourt pour ajouter les dits livres dans le tableau
		for (int i = 0; i < livres.length; i++) {
			if (livres[i].estClasseDansCategorie(Livre.NOMS_CATEGORIES[Integer
														.parseInt(categorie)-1])){
				livreAvecLaCat[count] = livres[i];
				count++;
			}
		}
		return livreAvecLaCat;
	}

	/**
     * Methode qui gere de demander a l'utilisateur un choix de categorie valide
     * 
     * @return  String est le choix de categorie a voir dans la librairie
     */
	public static String choixCategorie() {
		String choix;
		System.out.print(CHOIX_CATEGORIE);
		choix = Clavier.lireString();
		System.out.println();
		while ((!choix.equals("1") && !choix.equals("2") && !choix.equals("3") 
				&& !choix.equals("4") && !choix.equals("5") && !choix.equals("6")
				&& !choix.equals(""))) {
			System.out.println(CATEGORIE_INVALIDE);
			System.out.print(CHOIX_CATEGORIE);
			choix = Clavier.lireString();
		}
		return choix;
	}

	/**
     * Methode qui gere de demander a l'utilisateur un choix de menu valide
     * 
     * @return  String est le choix fait par l'utilisateur
     */
	public static int choixMenu() {
		System.out.print(MENU_PRINCIPALE);
		String choix = Clavier.lireString();
		while (!choix.equals("1") && !choix.equals("2") && !choix.equals("3")) {
			System.out.println(ERR_MENU_PRINCIPALE);
			System.out.print(MENU_PRINCIPALE);
			choix = Clavier.lireString();
		}
		return Integer.parseInt(choix);
	}

	/**
     * Methode qui construit le tableau de Livre[] a partir du tableau a 
	 * 2 dimensions tiree de la lecture d'un fichier texte
     * 
     * @return  Livre[] est le tableau de tout les livres dans la librairie
     */
	public static Livre[] initialiserLivres() {
		String[][] livresTxt = TP3Utils.lireFichierInventaire();
    	Livre[] livres = new Livre[livresTxt.length];
    	
    	for (int i = 0; i < livresTxt.length; i++) {
    		livres[i] = new Livre(livresTxt[i][0], livresTxt[i][1],
									Double.parseDouble(livresTxt[i][2].trim()), 
									Integer.parseInt(livresTxt[i][3].trim()));
			for (int j = 4; j < livresTxt[i].length; j++) {
				livres[i].ajouterCategorie(Integer.parseInt(livresTxt[i][j].trim()));
			}
    	}
		return livres;
	}
}
