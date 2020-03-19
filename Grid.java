import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.Arrays;
import java.util.Random;

/**
 * <b>Classe pour créer sa grille personalisée.</b>
 * <p>
 * Dans cette classe on traite on affiche graphiquement une grille que l'utilisateur peut modifier
 * Les modifications sont enregistrées dans des objets de type Carre
 * Après avoir terminé sa grille il peut l'envoyer si celle-ci est valable il retournera plusieurs données
 * <li>la taille</li>
 * <li>l'emplacement de la sortie</li>
 * <li>l'emplacement de l'entree</li>
 * <li>le tableau d'objets de type Carre</li>
 * </p>
 */

class Grid extends JPanel
{

    private String NoPressed = "Nothing Pressed";
    private JButton Aleatoire;
    private JButton Reset;
    private JButton Envoyer;
    private int i;

    private Carre[] carrearray;
    private int gridSize;

    private static final int maxSizeofclassGrid=15;

    /**
     * @return the carrearray
     */
    public Carre[] getCarrearray() {
        return carrearray;
    }

    /**
     * selected: la taille
     * parent: la fenetre
     * @param selected
     * @param parent
     */

    Grid(int selected, Frame parent)
    {        
        
        this.gridSize = selected;
        carrearray = new Carre [gridSize*gridSize];

        for (i=0 ; i < carrearray.length ; i++) {  
    
            carrearray[i] = new Carre(false,i,this);
    
            }

        JPanel leftPanel = new JPanel();
            leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    
            JPanel labelPanel = new JPanel();
                JLabel parametersLabel = new JLabel("Parametres", JLabel.CENTER);
                labelPanel.add(parametersLabel); 

            JPanel buttonLeftPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JPanel instructionsPanel = new JPanel();
                    JLabel  instructionsLabel = new JLabel("<html><p>Clique gauche: blanc->noir<br>Clique droite: entree->sortie->Reset all<br>(sur cases differentes blanches pour entr. sort.)</p></html>");
                    instructionsPanel.add(instructionsLabel); 
                    buttonLeftPanel.add(instructionsPanel, gbc);

/**
 * Partie Aleatoire
 */

                Aleatoire = new JButton("Aleatoire");
                Aleatoire.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {          
                        Random random = new Random();

                        for (i=0 ; i < carrearray.length ; i++) 
                        {  
                            carrearray[i].setReintialise();
                        }

                        int randomNum=random.nextInt(carrearray.length);
                        int temprandomNum=randomNum;
                        System.out.println("Entree "+randomNum);
                        carrearray[randomNum].setEntree();

                        randomNum=random.nextInt(carrearray.length);
                        if ( randomNum==temprandomNum ) { 
                            while ( randomNum==temprandomNum ) {
                                randomNum=random.nextInt(carrearray.length);
                                
                            }
                        }
                        System.out.println("Sortie "+randomNum);
                        carrearray[randomNum].setSortie();

                        for (i=0 ; i < carrearray.length ; i++) {  
                            carrearray[i].setAleatoire();
                            }
          
                        // Reset.setEnabled(true);
                        // Reset.setText("Reset all");
                        Envoyer.setEnabled(true);
                        Envoyer.setText("Envoyer");
                    }
                });
                    buttonLeftPanel.add(Aleatoire, gbc);

/**
 * Partie Reset
 */
/*
                Reset = new JButton(NoPressed);
                Reset.setEnabled(false);
                Reset.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {            
                        for (i=0 ; i < carrearray.length ; i++) {
                            carrearray[i].setReintialise();
                            }         
                        Reset.setEnabled(false);
                        Reset.setText(NoPressed);
                        Envoyer.setEnabled(false);
                        Envoyer.setText(NoPressed);
                    }
                });
                buttonLeftPanel.add(Reset, gbc);
*/
/**
 * Envoyer
 */

                Envoyer = new JButton(NoPressed);
                Envoyer.setEnabled(false);
                Envoyer.addActionListener(new ActionListener()
                {
                    /** 
                     * Sortie
                     */
                    public void actionPerformed(ActionEvent ae)
                    {
                        for (i=0 ; i < carrearray.length ; i++) { //pret a l'envoi !
                            carrearray[i].setEnabledfalse();
                            } 
                        Aleatoire.setEnabled(false);
                        //Reset.setEnabled(false);
                        Envoyer.setEnabled(false);
                        parent.getb1Button().setEnabled(false);
                        parent.getb2Button().setEnabled(false);
                        parent.getb3Button().setEnabled(true);
                        parent.getb4Button().setEnabled(true);
                        parent.getb5Button().setEnabled(true);
                    }
                });
                buttonLeftPanel.add(Envoyer, gbc);

                gbc.weighty = 1;
               
            leftPanel.add(labelPanel);
            leftPanel.add(buttonLeftPanel);

        add(leftPanel);

/**
 * Grid
 */

        JPanel grid = new JPanel(new GridBagLayout());
        GridLayout gestionnaire = new GridLayout(gridSize,gridSize);
        grid.setLayout(gestionnaire);

        for (i=0 ; i< carrearray.length ; i++) {
            
            if (gridSize<=maxSizeofclassGrid) {
                
            carrearray[i].setPreferredSize(new Dimension(50, 50)); } 

            else {

                carrearray[i].setPreferredSize(new Dimension(30, 30));
            }
/*
            carrearray[i].addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent ae)
                    {  
                        if (Carre.getValue()>0) {
                            Reset.setEnabled(true);
                            Reset.setText(
                                "Reset all"); }
                        if (Carre.getValue()>=1) {
                            Envoyer.setEnabled(true);
                            Envoyer.setText(
                                "Envoyer"); }         
                    }
                });*/ 
            

            grid.add(carrearray[i]);
        }

        /*for (i=0 ; i< carrearray.length ; i++) {
            carrearray[i].setPreferredSize(new Dimension(50, 50)); 
            carrearray[i].addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent ae)
                    {  
                        if (Carre.getValue()>0) {
                            Reset.setEnabled(true);
                            Reset.setText(
                                "Reset all"); }
                        if (Carre.getValue()>=1) {
                            Envoyer.setEnabled(true);
                            Envoyer.setText(
                                "Envoyer"); }         
                    }
                }); 
                grid.add(carrearray[i]);
        }*/

        add(grid); 
}

/**
 * Permet de tout remettre en cases blanches
 */

public void ResetAll () {

    for (i=0 ; i < carrearray.length ; i++) {
        carrearray[i].setReintialise();
        }         
    // Reset.setEnabled(false);
    // Reset.setText(NoPressed);
    Envoyer.setEnabled(false);
    Envoyer.setText(NoPressed);

}

/**
 * @return the reset
 */
public JButton getReset() {
    return Reset;
}

/**
 * @return the envoyer
 */
public JButton getEnvoyer() {
    return Envoyer;
}
    
/**
 * methodes qui convertissent les resultats pour convenir au Writer
 */

    public int getTaille() {
    return gridSize;
    }

    public int getDepart() {
        int g = 0;
        i=0;

    for (i=0; i<carrearray.length ;i++) {

            if ( carrearray[i].returnifES("Entree")==true ) {
            g=i;
            }

        }
    
    return g;
    }

    public int getArrivee() {
        int g = 0;
        i=0;

        for (i=0; i<carrearray.length ;i++) {

            if ( carrearray[i].returnifES("Sortie")==true ) {
            g=i;
            }
        }
    
    return g;
    }

    
    
}
