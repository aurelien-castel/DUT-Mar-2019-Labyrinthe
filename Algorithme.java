import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import javax.swing.ImageIcon;
import java.text.DecimalFormat;
import java.math.RoundingMode;

/**
 * <b>Algorithme qui traite l'algorithme d'Arianne.</b>
 * <p>
 * Dans cette classe on traite l'algorithme de differentes manieres
 * <li>Algorithme deterministe</li>
 * <li>Pas a pas deterministe</li>
 * <li>Aleatoire</li>
 * <li>Aleatoire pas a pas</li>
 * <li>Aleatoire x 100</li>
 * </p>
 */

class Algorithme extends JPanel {
    private JPanel buttonLeftPanel = new JPanel( new GridBagLayout() );

    private JButton PlayButton;
    private JButton PlayButtonAlea;
    private JButton NextButton;
    private JButton NextButtonAlea;
    private JButton Envoyer;
    private JButton Statut;

    private int i;
    private Boolean automatique;
    private static int MaxAleatoire;
    private Boolean hasRunNext = false;
    private Boolean hasRunNextAlea = false;

    private Stack<Carre> ListeOuverte;
    private Stack<NumberObject> PileNumber;
    private ArrayList<Integer> ListeFermee;
    private Stack<NumberObject> PileFermee;
    private Carre[] carrearray;

    private static int myRed = 255;
    private int NbIteration = 0;
    private Boolean success; //false = pas trouve sortie
    private int numEntree;
    private int numCarreElement;
    private int numSortie;
    private int gridSize;

    private static final int maxSizeofclassGrid = 15;

    /**
     * constructeur algorithme depuis les donnees suivantes :
     * @param carrearray le tableau d'objets
     * @param gridSize la taille de la grille
     * @param numEntree
     * @param numSortie
     * @param automatique si l'utilisateur veut faire sans composants graphiques
     */

    Algorithme ( Carre[] carrearray, int gridSize, int numEntree, int numSortie, Boolean automatique ) {
        this.carrearray = carrearray;
        this.gridSize = gridSize;
        this.numEntree = numEntree;
        this.numSortie = numSortie;
        this.automatique = automatique;

        MaxAleatoire = gridSize * 750;

        InitialiseGrid();

        System.out.println( "La taille de la grille est de:" + gridSize );

        for (int y = 0, i = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++, i++) {
                carrearray[i].setCoordonnees( x, y );
            }
        }


        JPanel leftPanel = new JPanel();
        leftPanel.setBorder( BorderFactory.createLineBorder( Color.GRAY, 2 ) );
        leftPanel.setLayout( new BoxLayout( leftPanel, BoxLayout.Y_AXIS ) );
        JPanel labelPanel = new JPanel();
        JLabel parametersLabel = new JLabel( "Parametres", JLabel.CENTER );
        labelPanel.add( parametersLabel );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel instructionsPanel = new JPanel();
        JLabel instructionsLabel = new JLabel( "<html><p>Lancez votre algorithme. Verifiez<br>le statut grace au statut en gris</p></html>" );
        instructionsPanel.add( instructionsLabel );
        buttonLeftPanel.add( instructionsPanel, gbc );

/**
 * Partie PlayButton
 */
        if( automatique == false ) {

            PlayButton = new JButton();
            PlayButton.setText( "Play deterministe" );
            PlayButton.addActionListener( new ActionListener()  {
                public void actionPerformed ( ActionEvent ae ) {
                    InitialiseGrid();
                    NextButton.setEnabled( false );
                    NextButtonAlea.setEnabled( false );
                    PlayButton.setEnabled( false );
                    PlayButtonAlea.setEnabled( false );
                    Recherche();
                    NextButton.setEnabled( true );
                    NextButtonAlea.setEnabled( true );
                    PlayButton.setEnabled( true );
                    PlayButtonAlea.setEnabled( true );
                }
            } );
            buttonLeftPanel.add( PlayButton, gbc );


            PlayButtonAlea = new JButton();
            PlayButtonAlea.setText( "Play aleatoire" );
            PlayButtonAlea.addActionListener( new ActionListener() {
                public void actionPerformed ( ActionEvent ae ) {
                    NextButton.setEnabled( false );
                    NextButtonAlea.setEnabled( false );
                    PlayButton.setEnabled( false );
                    PlayButtonAlea.setEnabled( false );
                    RechercheAlea();
                    NextButton.setEnabled( true );
                    NextButtonAlea.setEnabled( true );
                    PlayButton.setEnabled( true );
                    PlayButtonAlea.setEnabled( true );
                }
            } );
            buttonLeftPanel.add( PlayButtonAlea, gbc );

        } else {

            PlayButton = new JButton();
            PlayButton.setText( "Play deterministe" );
            PlayButton.addActionListener( new ActionListener() {
                public void actionPerformed ( ActionEvent ae ) {
                    PlayButton.setEnabled( false );
                    PlayButtonAlea.setEnabled( false );
                    Recherche();
                    PlayButton.setEnabled( true );
                    PlayButtonAlea.setEnabled( true );
                }
            } );
            buttonLeftPanel.add( PlayButton, gbc );

            PlayButtonAlea = new JButton();
            PlayButtonAlea.setText( "Play aleatoire x 100" );
            PlayButtonAlea.addActionListener( new ActionListener() {
                public void actionPerformed ( ActionEvent ae ) {
                    PlayButton.setEnabled( false );
                    PlayButtonAlea.setEnabled( false );
                    RechercheAleaAutomatique();
                    PlayButton.setEnabled( true );
                    PlayButtonAlea.setEnabled( true );
                }
            } );
            buttonLeftPanel.add( PlayButtonAlea, gbc );
        }

/**
 * Partie NextButton
 */
        if( automatique == false ) {
            NextButton = new JButton();
            NextButton.setText( "Pas a pas deterministe" );
            NextButton.setEnabled( true );
            NextButton.addActionListener( new ActionListener() {
                public void actionPerformed ( ActionEvent ae ) {
                    PasaPas();
                }
            } );
            buttonLeftPanel.add( NextButton, gbc );

            NextButtonAlea = new JButton();
            NextButtonAlea.setText( "Pas a pas aleatoire" );
            NextButtonAlea.setEnabled( true );
            NextButtonAlea.addActionListener( new ActionListener() {
                public void actionPerformed ( ActionEvent ae ) {
                    PasaPasAlea();
                }
            } );
            buttonLeftPanel.add( NextButtonAlea, gbc );
        }

/**
 * Statut
 */

        Statut = new JButton();
        Statut.setText( "Pas encore lance" );
        Statut.setBackground( Color.LIGHT_GRAY );
        buttonLeftPanel.add( Statut, gbc );

        gbc.weighty = 1;

        leftPanel.add( labelPanel );
        leftPanel.add( buttonLeftPanel );

        add( leftPanel );

/**
 * Grid
 */
        if( automatique == false ) {
            JPanel grid = new JPanel( new GridBagLayout() );
            GridLayout gestionnaire = new GridLayout( gridSize, gridSize );
            grid.setLayout( gestionnaire );

            for (i = 0; i < carrearray.length; i++) {
                if( gridSize <= maxSizeofclassGrid ) {

                    carrearray[i].setPreferredSize( new Dimension( 50, 50 ) );
                } else {

                    carrearray[i].setPreferredSize( new Dimension( 30, 30 ) );
                }

                grid.add( carrearray[i] );
            }

            add( grid );
        }
    }

    /**
     * L'algorithme déterministe
     */

    private void Recherche () {

        try{

            InitialiseRecherche();

            while ((success = ! PileNumber.empty()) && (numCarreElement != numSortie)) {

                NbIteration++;

                NumberObject numberElement = PileNumber.peek();

                PileNumber.pop();

                if( ! numberElement.getAbort() ) {

                    Carre carreElement = carrearray[numberElement.getValue()];

                    //on ne travaille plus que a partir de Carre maintenant

                    numCarreElement = carreElement.getNumero();

                    ListeFermee.add( numCarreElement );

                    if( (carreElement.returnvideouplein() == false) ) { //on ne vérifie que les chemins

                        PileFermee.add( numberElement ); //pile fermee n'a que des chemins

                    }

                    System.out.println( "element" + numCarreElement );

                    carrearray[numCarreElement].changeLabel( NbIteration + "" );
                    Statut.setText( "Thesee est sur la case " + NbIteration );

                    //NumberObject nextNumberElement = PileNumber.pop();

                    if( myRed != 0 ) {
                        myRed = myRed - 1;
                    }

                    // oblige sinon on fait rajoute une case apres la sortie
                    if( numCarreElement != numSortie ) { //si cet element est plein alors on fait rien

                        Boolean temph = false;
                        Boolean tempb = false;
                        Boolean tempg = false;
                        Boolean tempd = false;

                        // cas si on a deja parcouru:

                        for (int ElementFerme : ListeFermee) { //verifier si on a deja parcouru la case
                            System.out.println( "ele" + ElementFerme );
                            if( ElementFerme == (numCarreElement - gridSize) ) {
                                temph = true;  // on ne doit pas la reparcourir
                            }
                            if( ElementFerme == (numCarreElement + gridSize) ) {
                                tempb = true;
                            }
                            if( ElementFerme == (numCarreElement - 1) ) {
                                tempg = true;
                            }
                            if( ElementFerme == (numCarreElement + 1) ) {
                                tempd = true;
                            }
                        }


                        int haut = - 1;
                        int bas = - 1;
                        int gauche = - 1;
                        int droite = - 1;

                        int x = carreElement.getCoordonneeX();
                        int y = carreElement.getCoordonneeY();

                        haut = numCarreElement - gridSize;
                        bas = numCarreElement + gridSize;
                        gauche = numCarreElement - 1;
                        droite = numCarreElement + 1;

                        // System.out.println("x"+x);

                        if( ! temph ) {

                            if( (haut >= 0) && (temph == false) && (carrearray[haut].getColorie() == false) && (! carrearray[haut].returnvideouplein()) ) { //case d'en haut


                                carrearray[haut].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( haut, false, NbIteration, numCarreElement ) );

                            } else if( haut < 0 ) {

                                PileNumber.push( new NumberObject( haut, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[haut].returnvideouplein()) && (carrearray[haut].getColorie() == false) ) {

                                carrearray[haut].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( haut, true, NbIteration, numCarreElement, true ) );
                            }

                        }

                        if( ! tempd ) {

                            if( ((x + 1) < gridSize) && (tempd == false) && (carrearray[droite].getColorie() == false) && (! carrearray[droite].returnvideouplein()) ) {


                                carrearray[droite].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( droite, false, NbIteration, numCarreElement ) );

                            } else if( (x + 1) >= gridSize ) {

                                PileNumber.push( new NumberObject( droite, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[droite].returnvideouplein()) && (carrearray[droite].getColorie() == false) ) {

                                carrearray[droite].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( droite, true, NbIteration, numCarreElement, true ) );

                            }

                        }

                        if( ! tempb ) {

                            if( (bas < carrearray.length) && (tempb == false) && (carrearray[bas].getColorie() == false) && (! carrearray[bas].returnvideouplein()) ) {


                                carrearray[bas].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( bas, false, NbIteration, numCarreElement ) );

                            } else if( bas >= carrearray.length ) {

                                PileNumber.push( new NumberObject( bas, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[bas].returnvideouplein()) && (carrearray[bas].getColorie() == false) ) {

                                carrearray[bas].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( bas, true, NbIteration, numCarreElement, true ) );

                            }

                        }

                        if( ! tempg ) {

                            if( ((x - 1) >= 0) && (tempg == false) && (carrearray[gauche].getColorie() == false) && (! carrearray[gauche].returnvideouplein()) ) {


                                carrearray[gauche].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( gauche, false, NbIteration, numCarreElement ) );

                            } else if( ((x - 1) < 0) ) {

                                PileNumber.push( new NumberObject( gauche, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[gauche].returnvideouplein()) && (carrearray[gauche].getColorie() == false) ) {

                                carrearray[gauche].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( gauche, true, NbIteration, numCarreElement, true ) );

                            }

                        }

                    }

                } else {

                    NumberObject nextNumberElement = PileNumber.peek();

                    System.out.println( "nbIteParent: " + numberElement.getNbIterationparent() + " nextparent: " + nextNumberElement.getNbIterationparent() );

                    if( numberElement.getVp() ) {

                        System.out.println( "Mur " + NbIteration );
                        carrearray[numberElement.getValue()].changeLabel( "" + NbIteration );

                    } //vp

                }

                if( numCarreElement != numSortie ) { //si cet element est plein alors on fait rien

                    NumberObject nextNumberElement = PileNumber.peek();

                    NumberObject numberElementFermee = PileFermee.peek();
                    //Boolean vp=false;

                    while (numberElementFermee.getNbIterationparent() >= nextNumberElement.getNbIterationparent()) {


                        System.out.println( "cul de sac" );

                        numberElementFermee = PileFermee.peek();

                        if( numberElementFermee.getNbIterationparent() >= nextNumberElement.getNbIterationparent() ) {

                            if( numberElementFermee.getNbIterationparent() >= nextNumberElement.getNbIterationparent() ) {
                                NbIteration++;
                            }

                            carrearray[numberElementFermee.getParent()].setColoriage( myRed, "cds " + NbIteration + "" );

                            numberElementFermee = PileFermee.pop();


                        }

                    }

                }

            }
            if( success == true ) { //sortie

                FinishRecherche( "Termine " + NbIteration + " etapes" );

            } else if( numCarreElement == numSortie ) {

                // pour le seul cas ou l'entree est collee a la sortie
                FinishRecherche( "Termine " + NbIteration + " etapes" );

            } else {

                FinishRecherche( "Impossible" );

            }

            carrearray[numSortie].changeLabel( "Sortie" );

        } catch (java.util.EmptyStackException e) {
            FinishRecherche( "Impossible" );
            //si on teste alors que NumberObject nextNumberElement = PileNumber.peek(); est vide
        }

    }

    Boolean CdS=false;

    /**
     * Pas à pas déterministe
     */

    private void PasaPas () {

        try{

            if( (! hasRunNext) || (hasRunNextAlea) ) {
                hasRunNext = true;
                hasRunNextAlea = false;
                InitialiseNext();
                CdS=false;
            }

            if( (success = ! PileNumber.empty()) && (numCarreElement != numSortie) ) {

if (!CdS) {

                NbIteration++;

                NumberObject numberElement = PileNumber.peek();

                PileNumber.pop();

                if( ! numberElement.getAbort() ) {

                    Carre carreElement = carrearray[numberElement.getValue()];

                    //on ne travaille plus que a partir de Carre maintenant

                    numCarreElement = carreElement.getNumero();

                    ListeFermee.add( numCarreElement );

                    if( (carreElement.returnvideouplein() == false) ) { //on ne vérifie que les chemins

                        PileFermee.add( numberElement ); //pile fermee n'a que des chemins

                    }

                    System.out.println( "element" + numCarreElement );

                    carrearray[numCarreElement].changeLabel( NbIteration + "" );
                    Statut.setText( "Thesee est sur la case " + NbIteration );

                    //NumberObject nextNumberElement = PileNumber.pop();

                    if( myRed != 0 ) {
                        myRed = myRed - 1;
                    }

                    // oblige sinon on fait rajoute une case apres la sortie
                    if( numCarreElement != numSortie ) { //si cet element est plein alors on fait rien

                        Boolean temph = false;
                        Boolean tempb = false;
                        Boolean tempg = false;
                        Boolean tempd = false;

                        // cas si on a deja parcouru:

                        for (int ElementFerme : ListeFermee) { //verifier si on a deja parcouru la case
                            System.out.println( "ele" + ElementFerme );
                            if( ElementFerme == (numCarreElement - gridSize) ) {
                                temph = true;  // on ne doit pas la reparcourir
                            }
                            if( ElementFerme == (numCarreElement + gridSize) ) {
                                tempb = true;
                            }
                            if( ElementFerme == (numCarreElement - 1) ) {
                                tempg = true;
                            }
                            if( ElementFerme == (numCarreElement + 1) ) {
                                tempd = true;
                            }
                        }


                        int haut = - 1;
                        int bas = - 1;
                        int gauche = - 1;
                        int droite = - 1;

                        int x = carreElement.getCoordonneeX();
                        int y = carreElement.getCoordonneeY();

                        haut = numCarreElement - gridSize;
                        bas = numCarreElement + gridSize;
                        gauche = numCarreElement - 1;
                        droite = numCarreElement + 1;

                        // System.out.println("x"+x);

                        if( ! temph ) {

                            if( (haut >= 0) && (temph == false) && (carrearray[haut].getColorie() == false) && (! carrearray[haut].returnvideouplein()) ) { //case d'en haut


                                carrearray[haut].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( haut, false, NbIteration, numCarreElement ) );

                            } else if( haut < 0 ) {

                                PileNumber.push( new NumberObject( haut, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[haut].returnvideouplein()) && (carrearray[haut].getColorie() == false) ) {

                                carrearray[haut].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( haut, true, NbIteration, numCarreElement, true ) );
                            }

                        }

                        if( ! tempd ) {

                            if( ((x + 1) < gridSize) && (tempd == false) && (carrearray[droite].getColorie() == false) && (! carrearray[droite].returnvideouplein()) ) {


                                carrearray[droite].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( droite, false, NbIteration, numCarreElement ) );

                            } else if( (x + 1) >= gridSize ) {

                                PileNumber.push( new NumberObject( droite, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[droite].returnvideouplein()) && (carrearray[droite].getColorie() == false) ) {

                                carrearray[droite].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( droite, true, NbIteration, numCarreElement, true ) );

                            }

                        }

                        if( ! tempb ) {

                            if( (bas < carrearray.length) && (tempb == false) && (carrearray[bas].getColorie() == false) && (! carrearray[bas].returnvideouplein()) ) {


                                carrearray[bas].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( bas, false, NbIteration, numCarreElement ) );

                            } else if( bas >= carrearray.length ) {

                                PileNumber.push( new NumberObject( bas, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[bas].returnvideouplein()) && (carrearray[bas].getColorie() == false) ) {

                                carrearray[bas].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( bas, true, NbIteration, numCarreElement, true ) );

                            }

                        }

                        if( ! tempg ) {

                            if( ((x - 1) >= 0) && (tempg == false) && (carrearray[gauche].getColorie() == false) && (! carrearray[gauche].returnvideouplein()) ) {


                                carrearray[gauche].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( gauche, false, NbIteration, numCarreElement ) );

                            } else if( ((x - 1) < 0) ) {

                                PileNumber.push( new NumberObject( gauche, true, NbIteration, numCarreElement ) );

                            } else if( (carrearray[gauche].returnvideouplein()) && (carrearray[gauche].getColorie() == false) ) {

                                carrearray[gauche].setColoriage( myRed, "" );
                                PileNumber.push( new NumberObject( gauche, true, NbIteration, numCarreElement, true ) );

                            }

                        }

                    }

                } else {

                    NumberObject nextNumberElement = PileNumber.peek();

                    System.out.println( "nbIteParent: " + numberElement.getNbIterationparent() + " nextparent: " + nextNumberElement.getNbIterationparent() );

                    if( numberElement.getVp() ) {

                        System.out.println( "Mur " + NbIteration );
                        carrearray[numberElement.getValue()].changeLabel( "" + NbIteration );

                    } //vp

                }

            }

                if( numCarreElement != numSortie ) { //si cet element est plein alors on fait rien

                    NumberObject nextNumberElement = PileNumber.peek();

                    NumberObject numberElementFermee = PileFermee.peek();

                    CdS=true;

                    //Boolean vp=false;

                    if (numberElementFermee.getNbIterationparent() >= nextNumberElement.getNbIterationparent()) {


                        System.out.println( "cul de sac" );

                        numberElementFermee = PileFermee.peek();

                        if( numberElementFermee.getNbIterationparent() >= nextNumberElement.getNbIterationparent() ) {

                            if( numberElementFermee.getNbIterationparent() >= nextNumberElement.getNbIterationparent() ) {
                                NbIteration++;
                            }

                            carrearray[numberElementFermee.getParent()].setColoriage( myRed, "cds " + NbIteration + "" );

                            numberElementFermee = PileFermee.pop();


                        }

                    } else { CdS=false; }

                }

                //abort

            } else if( success == true ) { //sortie

                FinishNext( "Termine " + NbIteration + " etapes" );


            } else if( numCarreElement == numSortie ) {

                FinishNext( "Termine " + NbIteration + " etapes" );


            } else {

                FinishNext( "Impossible" );

            }

            carrearray[numSortie].changeLabel( "Sortie" );

        } catch (java.util.EmptyStackException e) { //si on teste alors que NumberObject nextNumberElement = PileNumber.peek(); est vide
        }

    }

    /**
     * Recherche aléatoire
     */

    private void RechercheAlea () {

        InitialiseRecherche();

        while ((success = ! ListeOuverte.empty()) && (numCarreElement != numSortie) && (NbIteration < MaxAleatoire)) {

            Carre carreElement = ListeOuverte.peek();
            numCarreElement = carreElement.getNumero();

            System.out.println( "element" + numCarreElement );

            NbIteration++;
            carrearray[numCarreElement].changeLabel( NbIteration + "" );
            Statut.setText( "On fait l'element " + NbIteration );

            if( myRed != 0 ) {
                myRed = myRed - 1;
            }

            // oblige sinon on fait rajoute une case apres la sortie
            if( (numCarreElement != numSortie) && (carreElement.returnvideouplein() == false) ) { //si cet element est plein alors on fait rien

                int haut = - 1;
                int bas = - 1;
                int gauche = - 1;
                int droite = - 1;

                int x = carreElement.getCoordonneeX();
                int y = carreElement.getCoordonneeY();

                haut = numCarreElement - gridSize;
                bas = numCarreElement + gridSize;
                gauche = numCarreElement - 1;
                droite = numCarreElement + 1;

                // System.out.println("x"+x);

                int value = (int) (Math.random() * ((3 - 0) + 1));

                switch (value) {

                    case 0:

                        if( haut >= 0 ) { //case d'en haut

                            carrearray[haut].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[haut] );
                        }
                        break;

                    case 1:

                        if( (x + 1) < gridSize ) {


                            carrearray[droite].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[droite] );
                        }
                        break;

                    case 2:

                        if( bas < carrearray.length ) {


                            carrearray[bas].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[bas] );
                        }
                        break;

                    case 3:

                        if( (x - 1) >= 0 ) {


                            carrearray[gauche].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[gauche] );
                        }
                        break;

                }

            } else {
                ListeOuverte.pop();
            }

        }
        if( success == true ) { //sortie

            FinishRecherche( "Termine " + NbIteration + " etapes" );

        }

        if( NbIteration >= MaxAleatoire ) {
            FinishRecherche( "Impossible" );
        }

        carrearray[numSortie].changeLabel( "Sortie" );

    }

    /**
     * Aléatoire x 100
     */

    private void RechercheAleaAutomatique () {

        int MaxRecherAuto = 0;
        int intArray[] = new int[100];

        Recherche();

        if( ! Statut.getText().equals( "Impossible" ) ) {

            while ((MaxRecherAuto < 100)) {

                NbIteration = 0;
                numCarreElement = numEntree;
                ListeOuverte = new Stack<Carre>();
                ListeOuverte.push( carrearray[numEntree] );

                while ((success = ! ListeOuverte.empty()) && (numCarreElement != numSortie)) {

                    Carre carreElement = ListeOuverte.peek();
                    numCarreElement = carreElement.getNumero();

                    System.out.println( "element" + numCarreElement );
                    System.out.println( "num ite" + NbIteration );

                    NbIteration++;


                    if( myRed != 0 ) {
                        myRed = myRed - 1;
                    }

                    // oblige sinon on fait rajoute une case apres la sortie
                    if( (numCarreElement != numSortie) && (carreElement.returnvideouplein() == false) ) { //si cet element est plein alors on fait rien

                        int haut = - 1;
                        int bas = - 1;
                        int gauche = - 1;
                        int droite = - 1;

                        int x = carreElement.getCoordonneeX();
                        int y = carreElement.getCoordonneeY();

                        haut = numCarreElement - gridSize;
                        bas = numCarreElement + gridSize;
                        gauche = numCarreElement - 1;
                        droite = numCarreElement + 1;

                        // System.out.println("x"+x);

                        int value = (int) (Math.random() * ((3 - 0) + 1));

                        switch (value) {

                            case 0:

                                if( haut >= 0 ) { //case d'en haut


                                    ListeOuverte.push( carrearray[haut] );
                                }
                                break;

                            case 1:

                                if( (x + 1) < gridSize ) {


                                    ListeOuverte.push( carrearray[droite] );
                                }
                                break;

                            case 2:

                                if( bas < carrearray.length ) {


                                    ListeOuverte.push( carrearray[bas] );
                                }
                                break;

                            case 3:

                                if( (x - 1) >= 0 ) {


                                    ListeOuverte.push( carrearray[gauche] );
                                }
                                break;

                        }

                    } else {
                        ListeOuverte.pop();
                    }

                }
                if( success == true ) { //sortie

                    intArray[MaxRecherAuto] = NbIteration;

                    MaxRecherAuto = MaxRecherAuto + 1;

                }

            }


            int somme = 0;
            for (int i = 0; i < intArray.length; i++) {
                somme += intArray[i];
            }
            float moyenne = (float) somme / intArray.length;

            DecimalFormat df = new DecimalFormat( "#.##" );
            df.setRoundingMode( RoundingMode.CEILING );

            Statut.setText( "Termine en moyenne " + moyenne + " etapes" );
        }

    }

    /**
     * Pas a pas aléatoire
     */

    private void PasaPasAlea () {

        if( (! hasRunNextAlea) || (hasRunNext) ) {
            hasRunNextAlea = true;
            hasRunNext = false;
            InitialiseNext();
        }

        if( (success = ! ListeOuverte.empty()) && (numCarreElement != numSortie) && (NbIteration < MaxAleatoire) ) {

            Carre carreElement = ListeOuverte.peek();
            numCarreElement = carreElement.getNumero();

            ListeFermee.add( numCarreElement );
            System.out.println( "element" + numCarreElement );

            NbIteration++;
            carrearray[numCarreElement].changeLabel( NbIteration + "" );
            Statut.setText( "On fait l'element " + NbIteration );

            if( myRed != 0 ) {
                myRed = myRed - 1;
            }

            // oblige sinon on fait rajoute une case apres la sortie
            if( (numCarreElement != numSortie) && (carreElement.returnvideouplein() == false) ) { //si cet element est plein alors on fait rien

                int haut = - 1;
                int bas = - 1;
                int gauche = - 1;
                int droite = - 1;

                int x = carreElement.getCoordonneeX();
                int y = carreElement.getCoordonneeY();

                haut = numCarreElement - gridSize;
                bas = numCarreElement + gridSize;
                gauche = numCarreElement - 1;
                droite = numCarreElement + 1;

                // System.out.println("x"+x);

                int value = (int) (Math.random() * ((3 - 0) + 1));

                switch (value) {

                    case 0:

                        if( haut >= 0 ) { //case d'en haut

                            carrearray[haut].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[haut] );
                        }
                        break;

                    case 1:

                        if( (x + 1) < gridSize ) {


                            carrearray[droite].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[droite] );
                        }
                        break;

                    case 2:

                        if( bas < carrearray.length ) {


                            carrearray[bas].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[bas] );
                        }
                        break;

                    case 3:

                        if( (x - 1) >= 0 ) {


                            carrearray[gauche].setColoriage( myRed, "" );
                            ListeOuverte.push( carrearray[gauche] );
                        }
                        break;

                }

            } else {
                ListeOuverte.pop();
            }

        } else if( success == true ) { //sortie

            FinishNext( "Termine " + NbIteration + " etapes" );

        } else {

            FinishNext( "Impossible" );

        }

        carrearray[numSortie].changeLabel( "Sortie" );

    }

    /**
     * Initialiser la grille graphiquement
     */

    private void InitialiseGrid () {
        for (i = 0; i < carrearray.length; i++) {
            carrearray[i].cleanColoriage();
        }
        myRed = 255;
        carrearray[numEntree].setColoriage( myRed, "Entree" );
        carrearray[numSortie].changeLabel( "Sortie" );
    }

    /**
     * Initialiser recherche (pas les algorithmes pas a pas)
     */

    private void InitialiseRecherche () {
        hasRunNext = false;
        hasRunNextAlea = false;

        InitialiseGrid();
        NbIteration = 0;

        ListeOuverte = new Stack<Carre>();
        ListeFermee = new ArrayList<Integer>();

        PileNumber = new Stack<NumberObject>();
        PileNumber.push( new NumberObject( numEntree, false ) );

        PileFermee = new Stack<NumberObject>();

        numCarreElement = numEntree;
        ListeOuverte.push( carrearray[numEntree] );
    }

    /**
     * Finir recherche (pas les algorithmes pas a pas)
     * @param text
     */

    private void FinishRecherche ( String text ) {
        Statut.setText( text );
        carrearray[numEntree].changeLabel( "Entree" );
        carrearray[numSortie].changeLabel( "Sortie" );
    }

    /**
     * Initialiser pas a pas
     */

    private void InitialiseNext () {
        InitialiseGrid();
        NbIteration = 0;

        ListeOuverte = new Stack<Carre>();
        ListeFermee = new ArrayList<Integer>();

        PileNumber = new Stack<NumberObject>();
        PileNumber.push( new NumberObject( numEntree, false ) );

        PileFermee = new Stack<NumberObject>();

        numCarreElement = numEntree;
        ListeOuverte.push( carrearray[numEntree] );

    }

    /**
     * Finir pas a pas
     */

    private void FinishNext ( String text ) {
        Statut.setText( text );
        hasRunNext = false;
        hasRunNextAlea = false;
        carrearray[numEntree].changeLabel( "Entree" );
        carrearray[numSortie].changeLabel( "Sortie" );
    }

}
