import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <b>Classe pour l'emplacement de l'enregistrement ainsi que le nom du fichier
 * à sauver.</b>
 * <p>
 * Elle sert pour récupéer un emplacement sous forme de String et le donner à un
 * writer
 * </p>
 */

class SauvegarderJTextArea extends JPanel {
	private JButton Envoyer;
	private JButton Directory;
	private JFileChooser chooser = new JFileChooser();
	private String dir = "./Sauvegardes_lab";

	private JTextField field;
	private String text;

	private final String nospecial = "[a-zA-Z0-9]+";

	/**
	 * on donne un enregistreur: "labyrinthe writer" pour écrire celui-ci s'occupe
	 * après de créer le fichier Le constructeur s'occupe de savoir si le nom du
	 * fichier est valable ou non, et donner un emplacement. Par défaut, on
	 * sauvegardera dans un fichier nommé "Sauvegardes_lab".
	 * 
	 * @param writer
	 */

	SauvegarderJTextArea(Labyrinthe writer) {

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JPanel labelPanel = new JPanel();
		JLabel parametersLabel = new JLabel("Writer", JLabel.CENTER);
		labelPanel.add(parametersLabel);

		JPanel buttonLeftPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel instructionsPanel = new JPanel();
		JLabel instructionsLabel = new JLabel(
				"<html><p>Si vous entrez le nom d'un fichier<br>existant l'ancien fichier sera supprime.<br>Les fichiers seront sauvegardes par<br>defaut dans le fichier Sauvegardes_lab</p></html>");
		instructionsPanel.add(instructionsLabel);
		buttonLeftPanel.add(instructionsPanel, gbc);

		field = new JTextField();
		buttonLeftPanel.add(field, gbc);

		Directory = new JButton("change directory");
		Directory.setEnabled(true);
		Directory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//
				if (chooser.showOpenDialog(SauvegarderJTextArea.this) == JFileChooser.APPROVE_OPTION) {
					dir = "" + chooser.getSelectedFile();
					Directory.setEnabled(false);
				}

			}
		});

		buttonLeftPanel.add(Directory, gbc);

		Envoyer = new JButton("Envoyer");
		Envoyer.setEnabled(true);
		Envoyer.addActionListener(new ActionListener() {
			/**
			 * Sortie
			 */
			public void actionPerformed(ActionEvent ae) {
				text = field.getText().trim();
				if (!text.equals("") && (text.matches(nospecial))) {
					field.setEnabled(false);
					Envoyer.setEnabled(false);
					writer.saveTo(dir + "/" + text + ".lab");
					System.out.println("Fichier enregistre: " + dir + "/" + text + ".lab");
				}
			}
		});
		buttonLeftPanel.add(Envoyer, gbc);

		gbc.weighty = 1;

		leftPanel.add(labelPanel);
		leftPanel.add(buttonLeftPanel);

		add(leftPanel);
	}

}
