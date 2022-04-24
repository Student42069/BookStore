
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TP3Utils {
   
   //nom du fichier dans lequel on lit la liste des livres en inventaire
   //Doit se trouver a la racine du projet.
   public final static String CHEMIN_FIC = "inventaire.txt";
   
   //separateur des infos sur un livre dans une ligne du fichier
   public final static String SEPARATEUR = "\t";

   /**
    * Lit le fichier texte donne par CHEMIN_FIC, et retourne son contenu sous 
    * la forme d'un tableau à 2 dimensions (tableau de tableaux de String).
    * 
    * Si le fichier donne par CHEMIN_FIC n'existe pas, la methode retourne 
    * un tableau vide.
    * 
    * @return le contenu du fichier texte donne par CHEMIN_FIC sous forme 
    * d'un tableau de String a 2 dimensions.
    */
   public static String[][] lireFichierInventaire() {
   
      ArrayList<String[]> liste = new ArrayList<>();
      String ligne = "";
      BufferedReader in;
      String[] tabLigne;
            
      try {
         in = new BufferedReader(new FileReader(CHEMIN_FIC));

         while (in.ready()) {
            ligne = in.readLine().trim();
            
            if (!ligne.isEmpty() && ligne.charAt(0) != '#') {
               tabLigne = ligne.split(SEPARATEUR);
               liste.add(tabLigne);
            }
         }
         
      } catch (IOException e) {
         //ne rien faire, ne devrait pas se produire
      }      
   
      return liste.toArray(new String[0][0]);
   }

   
   /**
    * Valide un entier entre min et max inclusivement.
    * 
    * ANTÉCÉDENT : ON SUPPOSE QUE MIN <= MAX.
    * 
    * @param msgSol le message de sollicitation a afficher
    * @param msgErr le message d'erreur a afficher
    * @param min la borne minimum valide
    * @param max la borne maximum valide
    * @return un entier entre min et max inclusivement.
    */
   public static int validerEntier(String msgSol, String msgErr, 
                                   int min, int max) {
      int entier = 0;
      boolean valide;
      
      do {
         try {
            System.out.print(msgSol);
            entier = Clavier.lireInt();
            valide = entier >= min && entier <= max;
            
         } catch (NumberFormatException e) {
            valide = false;
         }
         
         if (!valide) {
            System.out.println(msgErr);
         }

      } while (!valide);

      return entier;
   }
 
}
