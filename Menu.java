import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <b>Menu est la classe qui génère une fenêtre intéractive pour l'utilisateur afin qu'il puisse utilisée l'application</b>
 * <p>
 * Dans cette objet on a les informations suivantes:
 * <li>un boolean caractérisant le chargement d'un fichier</li>
 * <li>un bouton menant a une grille aléatoire</li>
 * <li>un bouton menant au chooser pour choisir le fichier</li>
 * <li>un fichier qui correspond a celui choisit par le chooser</li>
 * </p>
 *
 *
 * @author Alex Maizeroi
 */

public class Menu extends JPanel
{
	private static Boolean charger=false;
	private JButton random;
	private JButton load;
	private JFileChooser chooser = new JFileChooser();
	private File selection = chooser.getSelectedFile();
	
	/**
	 * le premier menu
	 * @param parent la Jframe qui contient l'application
	 */
	
	public Menu(Frame parent)
	{
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JPanel labelPanel = new JPanel();
		JLabel parametersLabel = new JLabel("Menu principal", JLabel.CENTER);
		labelPanel.add(parametersLabel);
		
		JPanel buttonLeftPanel = new JPanel(new FlowLayout());
		
		random = new JButton("Grille aleatoire");
		random.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				random.setEnabled(false);
				load.setEnabled(false);
				
				parent.getb1Button().setEnabled(true);
				parent.getb2Button().setEnabled(true);
				parent.getbuttonPanel().add(parent.getb1Button());
				parent.getbuttonPanel().add(parent.getb2Button());
				
				parent.getb5Button().setEnabled(false);
				parent.getbuttonPanel().add(parent.getb5Button());
				
				parent.getb3Button().setEnabled(false);
				parent.getb4Button().setEnabled(false);
				parent.getbuttonPanel().add(parent.getb3Button());
				parent.getbuttonPanel().add(parent.getb4Button());
				
				parent.add(parent.getbuttonPanel(),BorderLayout.SOUTH);
				parent.setVisible(true);
			}
		});
		buttonLeftPanel.add(random);
		
		load = new JButton("Charger une grille");
		load.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				random.setEnabled(false);
				
				chooser.setName("Choisir une grille");
				chooser.setFileFilter(new FileFilter()
				{
					@Override
					public boolean accept(File f)
					{
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".lab");
					}
					
					@Override
					public String getDescription()
					{
						return "Grille (*.lab)";
					}
				});
				chooser.showOpenDialog(Menu.this);
				selection = chooser.getSelectedFile();
				
				if(selection != null) //Si un fichier est sélectionné, continuer. Sinon, ne rien faire
				{
					System.out.println("Fichier sélectionné: " + selection.getAbsolutePath());
					//TODO transférer le fichier au readwrite
					selection=chooser.getSelectedFile();
					
					charger=true;
					
					parent.getb3Button().setEnabled(true);
					parent.getb4Button().setEnabled(true);
					parent.getbuttonPanel().add(parent.getb3Button());
					parent.getbuttonPanel().add(parent.getb4Button());
					
					parent.add(parent.getbuttonPanel(),BorderLayout.SOUTH);
					parent.setVisible(true);
				}
			}
		});
		buttonLeftPanel.add(load);
		
		
		// on ajoute le composant dans la fenetre, au milieu
		leftPanel.add(labelPanel);
		leftPanel.add(buttonLeftPanel);
		
		add(leftPanel);
	}
	
	/**
	 * @return  renvoie true si un fichier a été sélectionnée
	 */
	public static Boolean getCharger() {
		return charger;
	}
	
	/**
	 * @return selection charger le fichier choisit pour le chargement. 
	 */
	public File getSelection() {
		return selection;
	}
	
}