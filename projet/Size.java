import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

/**
 * <b>Sise la classe pour choisir sa taille.</b>
 * <p>
 * Dans cette classe on va renvoyer la valeur que l'utilisateur souhaite avoir pour sa grille.
 * </p>
 */

class Size extends JPanel
{
    private JComboBox <String> combo=new JComboBox<>();
    private JButton Envoyer;
    private int i;
    
    private static final int minimal=3; //pour initialiser le minimum (pour eviter null pointer)
    private static int size=minimal;

    /**
     * constructeur taille
     */

    Size ()
    {

        JPanel leftPanel = new JPanel();
            leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    
            JPanel labelPanel = new JPanel();
                JLabel parametersLabel = new JLabel("Choix taille", JLabel.CENTER);
                labelPanel.add(parametersLabel); 

            JPanel buttonLeftPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JPanel instructionsPanel = new JPanel();
                    JLabel  instructionsLabel = new JLabel("<html><p>Choisissez votre taille<br>et confirmez avec le bouton.<br>Si vous avez une grande taille<br>selon votre ecran mettez en plein<br>ecran pour visualiser le tableau<br>s'il n'est pas apparent.</html>");
                    instructionsPanel.add(instructionsLabel); 
                    buttonLeftPanel.add(instructionsPanel, gbc);

                    for(i = minimal; i<=30 ; i++) {
                        combo.addItem(""+i);
                    }

                    Envoyer = new JButton("Envoyer");
                    Envoyer.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent ae)
                    {                         
                        size=Integer.parseInt((String)combo.getSelectedItem());                       
                    }
                });

                    buttonLeftPanel.add(combo, gbc);
                    buttonLeftPanel.add(Envoyer, gbc);

                gbc.weighty = 1;
               
            leftPanel.add(labelPanel);
            leftPanel.add(buttonLeftPanel);

        add(leftPanel);
    }

    /**
     * @return the size
     */
    public static int returnSize() {
        return size;
    }

}
