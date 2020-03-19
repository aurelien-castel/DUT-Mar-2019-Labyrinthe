import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * <b>Carre la classe qui permet de stocker les informations de chaques cases pour l'algorithme.</b>
 * <p>
 * Carre possède de nombres méthodes getters et setters qui permettent de créer différents objets Carre pour un tableau
 * Les objets de cette classes peuvent donc à la fois créés graphiquement ou non. 
 * Comme par exemple avec la méthode
 * <li>mouseClicked()</li>
 * </p>
 */

class Carre extends JButton implements MouseListener
{
    private Color couleur;
    private static Color myBrown = new Color (51,43,33); //bordures
    private static Color myWhite = new Color (255,255,255); //blanc
    private static Color myBlack = new Color (50,50,50); //noir
    
    private int numero;             //numero de la case
    private boolean videouplein;    //vide ou plein (plein=impossible à traverser: true)
    private static byte value=0;

    /**
     * constructeur carre
     * vp: vide ou plein
     * num: le numéro
     * @param vp
     * @param num
     */

          Carre(boolean vp, int num) { //constructeur si carre blanc ou noir
               
               value=0;
               this.videouplein = vp;
               if (videouplein==true) { 

                    this.couleur=myBlack; 

                    } else { 
                         
                         this.couleur=myWhite; 
                    
                    }
               this.numero = num;
               addMouseListener(this);
               setBackground(this.couleur);
               setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, myBrown));
               }

               private Grid parent;

/**
 * si interaction on pourra changer le parent (ici la grille)
 * @param vp vide ou plein
 * @param num numero
 * @param parent un composant graphique
 */

          Carre (boolean vp, int num, Grid parent) { //si interaction
               this(vp,num);
               this.parent=parent;
          }

     private boolean entree=false;   //si c'est une entree
     private boolean sortie=false;   //si c'est une sortie

     /**
      * Partie des évents. On prend en compte s'il y a une Sortie ou Entrée parmis tous les objets Carre grâce à l'attribut static value.
      */

     public void mouseClicked(MouseEvent e) {

          if (Enabled) {

          if (e.getButton() == MouseEvent.BUTTON1){ //left click

               if ((videouplein==false)&&(entree==false)&&(sortie==false)) //blanc -> noir
                    {
                    this.couleur=myBlack; //cette case deviendra noire
                    videouplein=true;
                    } 

                    else if ((videouplein==true)&&(entree==false)&&(sortie==false)) //noir -> blanc
                         {
                    this.couleur=myWhite; //cette case deviendra blanche
                    videouplein=false;
                         }
                    //value++;
                    System.out.println(videouplein+" num "+numero+" entree "+entree+" sortie "+sortie+" value "+value);
                    setBackground(this.couleur);
               
           } else if (e.getButton() == MouseEvent.BUTTON2){ 
  
               System.out.println(videouplein+" num "+numero+" entree "+entree+" sortie "+sortie+" value "+value);
               
           }
            else if (e.getButton() == MouseEvent.BUTTON3) { //right click

               
               switch(value){
                    case 0:
                    if ((videouplein==false)&&(entree==false)&&(sortie==false))  {
                         setEntree();
                         // parent.getReset().setEnabled(true);
                         // parent.getReset().setText("Reset all");
                         value++;
                         }
                    break;

                    case 1:
                    if ((videouplein==false)&&(entree==false)&&(sortie==false))  {
                         setSortie();
                         parent.getEnvoyer().setEnabled(true);
                         parent.getEnvoyer().setText("Envoyer");
                         //value++;
                         value++;
                         value%=3;
                         }
                    break;
                    case 2:
                    parent.ResetAll();
                    break;

                    case 3:
                    
                    break;
  
                    
           }  
           
          }

     }
  
     }
    //Invoked when the mouse has been clicked on a component.
  
    public void mousePressed(MouseEvent e) {}
    //Invoked when a mouse button has been pressed on a component.
  
    public void mouseReleased(MouseEvent e) {}
    //Invoked when a mouse button has been released on a component.
  
    public void mouseEntered(MouseEvent e) {}
    //Invoked when the mouse enters a component.
  
    public void mouseExited(MouseEvent e) {}
     //Invoked when the mouse exits a component.

/*
          public void actionPerformed(ActionEvent e) {

		     switch(value){
               case 0:
               if ((videouplein==false)&&(entree==false)&&(sortie==false))  {
                    setText("Entree");
                    entree=true;
                    value++;
				break; }
               case 1:
               if ((videouplein==false)&&(entree==false)&&(sortie==false))  {
                    setText("Sortie");
                    sortie=true;
                    value++;
                    break; }
                    
			default:
                    if ((videouplein==false)&&(entree==false)&&(sortie==false)) //blanc -> noir
                    {
                    this.couleur=myBlack; //cette case deviendra noire
                    videouplein=true;
                    } 

                    else if ((videouplein==true)&&(entree==false)&&(sortie==false)) //noir -> blanc
                         {
                    this.couleur=myWhite; //cette case deviendra blanche
                    videouplein=false;
                         }
                    value++;
                    System.out.println(videouplein);
                    setBackground(this.couleur);
				break;
               }     
          }*/


           /**
            * pour set l'entrée
            */

          public void setEntree() {
               if ((videouplein==false)&&(entree==false)&&(sortie==false)) {
                    setText("Entree");
                    entree=true;
                    //value++;
               }
          }
/**
 * pour set la sortie
 */

          public void setSortie() {
               if ((videouplein==false)&&(entree==false)&&(sortie==false)) {
                    setText("Sortie");
                    sortie=true;
                    //value++;
               }
          }
/**
 * Partie aléatoire
 */

          public void setAleatoire () {
               Random random = new Random();

               this.videouplein=random.nextBoolean();

               if ((videouplein==true)&&((entree==true)||(sortie==true))) { 
                    videouplein=false;
               } //cas où on est tombe sur true et que la case a deja une sortie ou une entree

               if ((videouplein==true)) { 

                    this.couleur=myBlack; 

                    } else { 
                         
                         this.couleur=myWhite; 
                    
                    }

               System.out.println(videouplein);
               setBackground(this.couleur);
               value=2;
               repaint();
          }

          /**
           * Partie Reinstialisation
           */
          
          public void setReintialise () {
               this.videouplein=false;
               this.couleur=myWhite;
               this.entree=false;
               this.sortie=false;
               System.out.println(videouplein);
               setBackground(this.couleur);
               value=0;
               setText("");
               repaint();
          }

          private static Boolean Enabled=true; 

/**
 * prêt pour l'envoi !
 */

          public void setEnabledfalse () { 
               setEnabled(false);
               Enabled=false;
          }

          /**
           * Partie return
           */

           /**
            * retourne si c'est une entree ou sortie
            * @param ES
            * @return
            */

          public Boolean returnifES (String ES) {

               /*Boolean EouS = false;

               if (getText().equals(ES)) { EouS = true; }

               return EouS;*/
               Boolean EouS = false;

               String Entree = "Entree";
               String Sortie = "Sortie";

               if ((Entree.equals(ES))&&(entree==true)&&(sortie==false)) { EouS = true; } 
               
               else if ((Sortie.equals(ES))&&(sortie==true)&&(entree==false)) { EouS=true; }

               return EouS;
          }

          public Boolean returnvideouplein () {
               return videouplein;
          }

          /**
           * Partie Algorithme
           */

          private Boolean Colorie=false;

          /**
           * @return the colorie
           */
          public Boolean getColorie() {
               return Colorie;
          }

          /**
           * permet de colorier
           * @param red
           * @param NbIteration
           */
          void setColoriage(int red, String NbIteration) { //si le carre a été colorié: Algorithme

               if (this.videouplein==false) { //on peut que colorier si la case est blanche

                    Color myColor=new Color (red,240,200);
                    this.couleur=myColor;
                    this.Colorie=true;
                    setText(NbIteration);
                    setBackground(this.couleur);

               } else {
                    this.Colorie=true;
                    setText(NbIteration); 
               }
               
          }
/**
 * nettoyeur
 */
          void cleanColoriage () {
               this.Colorie=false;
               setText("");
               if (videouplein==false) {
                    setBackground(myWhite);
               }
          }

          /**
           * pour changer le texte
           * @param label
           */

          void changeLabel (String label) {
               setText(label);
          }

          /**
           * Coordonnees
           */

           private int x;
           private int y;

/**
 * set les coordonnées
 * @param x
 * @param y
 */

          void setCoordonnees(int x, int y) {
               this.x=x;
               this.y=y;
               System.out.println(x);
          }

          /**
           * @return the x
           */
          public int getCoordonneeX() {
               return x;
          }

          /**
           * @return the y
           */
          public int getCoordonneeY() {
               return y;
          }

          /**
           * @return the numero
           */
          public int getNumero() {
               return numero;
          }
          
}