import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

/**
 * classe pour choisir l'algorithme
 */

class ChoixAutomate extends JPanel
{
    private JComboBox <String> combo=new JComboBox<>();
    private JButton Envoyer;
    
    private static Boolean automatique=false;

    /**
     * constructeur choix algorithme
     */

    ChoixAutomate ()
    {

        JPanel leftPanel = new JPanel();
            leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    
            JPanel labelPanel = new JPanel();
                JLabel parametersLabel = new JLabel("Choix Algorithme", JLabel.CENTER);
                labelPanel.add(parametersLabel); 

            JPanel buttonLeftPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JPanel instructionsPanel = new JPanel();
                    JLabel  instructionsLabel = new JLabel("<html><p>Choisissez votre Algorithme<br>et confirmez avec le bouton.</p></html>");
                    instructionsPanel.add(instructionsLabel); 
                    buttonLeftPanel.add(instructionsPanel, gbc);
                        
                        combo.addItem("manuel");
                        combo.addItem("automatique");

                    Envoyer = new JButton("Envoyer");
                    Envoyer.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent ae)
                    {          
                        String comboresult;               
                        comboresult = (String)combo.getSelectedItem(); 
                        automatique = (comboresult=="automatique") ? true : false;  
                        System.out.println(automatique);

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
     * @return the automatique
     */
    public static Boolean getAutomatique() {
        return automatique;
    }

}
