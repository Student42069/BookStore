
/**
 * INF1120 H22 : TP3.
 * Classe de tests partiels pour la classe Livre
 * 
 * @author melanie lord
 * @version Hiver 2022
 */
public class TestsPartielsClasseLivre {
   
   private static Livre l1;
   private static Livre l2;
   
   /**
    * Executer la methode main pour executer les tests.
    * Les valeurs affichees entre parentheses sont les resultats attendus : 
    * Ce qui est affiche avant les parentheses doit etre egal a ce qui est
    * entre parentheses. Sinon, il y a une erreur dans votre code.
    * 
    * Si les tests plantent, le probleme est dans votre code.
    * 
    * @param args (non utilise)
    */
   public static void main (String [] args) {
      
      boolean test;
      
      l1 = new Livre("titre1", "auteur1", 19.45, 5);

      System.out.println(l1 + " (titre1 - auteur1 (19.45 $))");
      System.out.println();
      System.out.println(Livre.getNbrProduitsInventaire0() + " (0)");
      
      //-----------------------------------------------
      
      System.out.println(l1.getTitre() + " (titre1)");
      System.out.println(l1.getAuteur() + " (auteur1)");
      System.out.println(l1.getPrix() + " (19.45)");
      System.out.println(l1.getQteAchetee() + " (0)");
      System.out.println(l1.getQteEnInventaire() + " (5)");
      
      //-----------------------------------------------
      
      l1.setAuteur("auteur111");
      l1.setPrix(144.89);
      l1.setQteAchetee(13);
      l1.setTitre("titre111");
      
      System.out.println(l1.getTitre() + " (titre111)");
      System.out.println(l1.getAuteur() + " (auteur111)");
      System.out.println(l1.getPrix() + " (144.89)");
      System.out.println(l1.getQteAchetee() + " (13)");
      System.out.println(l1.getQteEnInventaire() + " (5)");
      
      //-----------------------------------------------
      
      l1.augmenterQteInventaire(10);
      
      l1.diminuerQteInventaire(5);
      
      System.out.println(l1.getQteEnInventaire() + " (10)");
      
      l1.diminuerQteInventaire(12);
      System.out.println(l1.getQteEnInventaire() + " (0)");
      
      System.out.println(Livre.getNbrProduitsInventaire0() + " (1)");
      
      //-----------------------------------------------
      
      test = l1.ajouterCategorie(0);
      System.out.println(test + " (true)");
      
      l1.ajouterCategorie(4);
      l1.ajouterCategorie(2);
      test = l1.ajouterCategorie(9);  //tentative d'ajouter une cat invalide
      System.out.println();
      
      System.out.println(l1.estClasseDansCategorie("SCIENCE fiction") + " (true)");
      System.out.println(l1.estClasseDansCategorie("THRILLER") + " (true)");
      System.out.println(l1.estClasseDansCategorie("humouR") + " (true)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_2) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_4) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_6) + " (false)");
      System.out.println(test + " (false)");
      
      l1.retirerCategorie(0);
      System.out.println();

      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_1) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_3) + " (true)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_5) + " (true)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_2) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_4) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_6) + " (false)");
      
      test = l1.retirerCategorie(2);
      l1.retirerCategorie(4);
      System.out.println();
      
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_1) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_3) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_5) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_2) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_4) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_6) + " (false)");
      System.out.println(test + " (true)");
      
      //tentative de retrait d'une categorie inexistante
      test = l1.retirerCategorie(9);
      System.out.println();
      
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_1) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_3) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_5) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_2) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_4) + " (false)");
      System.out.println(l1.estClasseDansCategorie(Livre.CATEGORIE_6) + " (false)");
      System.out.println(test + " (false)");
      
      //-----------------------------------------------
      
      l2 = l1.copier();
      System.out.println();
      
      System.out.println((l1 != l2) + " (true)");
      System.out.println(l1.getAuteur().equals(l2.getAuteur()) + " (true)");
      System.out.println(l1.getTitre().equals(l2.getTitre()) + " (true)");
      System.out.println((l1.getPrix() == l2.getPrix()) + " (true)");
      System.out.println((l1.getQteAchetee() == l2.getQteAchetee()) + " (true)");
      System.out.println((l1.getQteEnInventaire() == l2.getQteEnInventaire()) + " (true)");
      
      
      System.out.println(l2.estClasseDansCategorie(Livre.CATEGORIE_1) + " (false)");
      System.out.println(l2.estClasseDansCategorie(Livre.CATEGORIE_3) + " (false)");
      System.out.println(l2.estClasseDansCategorie(Livre.CATEGORIE_5) + " (false)");
      System.out.println(l2.estClasseDansCategorie(Livre.CATEGORIE_2) + " (false)");
      System.out.println(l2.estClasseDansCategorie(Livre.CATEGORIE_4) + " (false)");
      System.out.println(l2.estClasseDansCategorie(Livre.CATEGORIE_6) + " (false)");
      
      //-----------------------------------------------
      
      System.out.println(Livre.getNomCategorie(0) + " (Science fiction)");
      System.out.println(Livre.getNomCategorie(2) + " (Thriller)");
      System.out.println(Livre.getNomCategorie(9) + " (null)");  //code cat invalide

   }
   
}
