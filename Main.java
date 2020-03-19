import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <b>Frame la classe qui appelle tous les composants graphiques.</b>
 * <p>
 * Frame sert au transfert de données et créé des objets de différentes classes selon les choix de l'utilisateur
 * Dans cette classe selon les choix retournés par Menu, on affichera les objets des classes suivantes : 
 * <li>Menu</li>
 * <li>Labyrinthe</li>
 * <li>Size</li>
 * <li>Grid</li>
 * <li>ChoixAutomate</li>
 * <li>Algorithme</li>
 * <li>SauvegarderJTextArea</li>
 * Frame est affiché en cardlayout ce qui permet facilement de montrer les objets classes souhaitées
 * </p>
 */

class Frame extends JFrame
{
    private Menu menu;

    private Labyrinthe readwrite;

    private Size size = new Size();
    private Grid grid = new Grid(Size.returnSize(), this);

    private ChoixAutomate choixautomate = new ChoixAutomate();
    private Algorithme algorithme;

    private SauvegarderJTextArea jtextarea;

    private JPanel buttonPanel = new JPanel();
    private JButton b1 = new JButton("Taille");
    private JButton b2 = new JButton("Tableau personalise");
    private JButton b3 = new JButton("Choix Algorithme");
    private JButton b4 = new JButton("Algorithme");
    private JButton b5 = new JButton("Sauvegarde");

    /**
     * constructeur de la fenètre
     */

    Frame ()
    {
    super("L'algorithme d'Ariane");

    setLayout(new BorderLayout());

    final CardLayout cardLayout = new CardLayout();
    final JPanel centeredPanel = new JPanel(new GridBagLayout());
    final JPanel cardPanel = new JPanel(cardLayout);
    //setLayout(new GridBagLayout());

    // panneau par defaut
    menu = new Menu(this);

    cardPanel.add(menu,"0Card");

    if (Menu.getCharger()==false) {

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                cardPanel.add(size,"1Card");
                cardLayout.show(cardPanel, "1Card");
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                grid= new Grid(Size.returnSize(), Frame.this); //pour le mettre à jour
                cardPanel.add(grid,"2Card");
                cardLayout.show(cardPanel, "2Card");
        }
        });}

        b5.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent event ) {


                if ( Menu.getCharger() == false ) {
                    readwrite = new Labyrinthe( grid.getTaille(), grid.getDepart(), grid.getArrivee(), grid.getCarrearray() );
                    jtextarea = new SauvegarderJTextArea( readwrite );
                }

                b5.setEnabled( false );
                cardPanel.add( jtextarea, "5Card" );
                cardLayout.show( cardPanel, "5Card" );
            }
        } );


    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {

        b5.setEnabled(false);
        cardPanel.add(choixautomate,"3Card");
        cardLayout.show(cardPanel, "3Card");
    }
    });


    b4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {

            if (Menu.getCharger()==false) {
        algorithme = new Algorithme(grid.getCarrearray(), grid.getTaille(), grid.getDepart(), grid.getArrivee(), ChoixAutomate.getAutomatique());
            } else {
        readwrite = new Labyrinthe(menu.getSelection());
        algorithme = new Algorithme(readwrite.getCarrearray(), readwrite.getTaille(), readwrite.getDepart(), readwrite.getArrivee(), ChoixAutomate.getAutomatique());


    }

        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        cardPanel.add(algorithme,"4Card");
        cardLayout.show(cardPanel, "4Card");
    }
    });

      // ajouter card et button à la fenetre
      centeredPanel.add(cardPanel);
      add(centeredPanel,BorderLayout.CENTER);
      add(buttonPanel,BorderLayout.SOUTH);


    setSize(1100,850);

    setDefaultLookAndFeelDecorated(true);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * pour modifier les boutons a partir d'une autre classe:
     */

    public JPanel getbuttonPanel () {
        return buttonPanel;
    }

    /**
     * pour modifier les boutons a partir d'une autre classe: taille
     */

    public JButton getb1Button () {
        return b1;
    }

    /**
     * pour modifier les boutons a partir d'une autre classe: grille perso
     */

    public JButton getb2Button () {
        return b2;
    }

    /**
     * pour modifier les boutons a partir d'une autre classe: choix algo
     */

    public JButton getb3Button () {
        return b3;
    }

    /**
     * pour modifier les boutons a partir d'une autre classe: algorithme
     */

    public JButton getb4Button () {
        return b4;
    }

    /**
     * pour modifier les boutons a partir d'une autre classe: sauver
     */

    public JButton getb5Button () {
        return b5;
    }

}

/**
 * <b>Le Main</b>
 * <p>
 * Classe qui sert à commencer le programme, elle appelle ensuite Frame ();
 * </p>
 */

class Main
{
    public static void main(String[] args)
    {
	new Frame();
    }
}
