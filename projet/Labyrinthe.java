import java.io.*;
import java.time.Year;
import java.util.Random;

/**
 * <b>Labyrinthe est la classe qui représentent les information d'une grille de l'algorithme d'Arianne.</b>
 * <p>
 * Dans cette objet on a les informations suivantes:
 * <li>la taille de la grille</li>
 * <li>la position X et Y de départ</li>
 * <li>la position X et Y d'arrivée</li>
 * <li>un tableau donnant la position des obstacle</li>
 * </p>
 *
 * @author Alex Maizeroi
 * @see
 */

public class Labyrinthe {
	private int taille;
	private int arriveeX;
	private int arriveeY;
	private int departX;
	private int departY;
	private byte[] map;

	/**
	 * crée un <code>Labyrinthe</code> à partir d'informations restreintes c'est-à-dire
	 * sans l'emplacement connues des obstacles.
	 *
	 * @param taille la taille de la grille.
	 * @param aX     la coordonnée su l'axe X de la sortie.
	 * @param aY     la coordonnée sur l'axe Y de la sortie.
	 * @param dX     la coordonnée sur l'axe X de l'entrée.
	 * @param dY     la coordonnée sur l'axe Y de l'entrée.
	 */

	public Labyrinthe ( int taille, int aX, int aY, int dX, int dY ) {
		this( taille, aX, aY, dX, dY, new byte[(int) Math.ceil( taille * taille / 8.0 )] );
	}

	/**
	 * crée un <code>Labyrinthe</code> à partir d'un ensemble d'informations que l'on donne en paramètre.
	 * Elle permet aussi d'obtenir une map aléatoire.
	 *
	 * @param taille  la taille de la grille (taille*taille).
	 * @param aX      la coordonnée su l'axe X de la sortie.
	 * @param aY      la coordonnée sur l'axe Y de la sortie.
	 * @param dX      la coordonnée sur l'axe X de l'entrée.
	 * @param dY      la coordonnée sur l'axe Y de l'entrée.
	 * @param densite permet de controlée le nombre d'obstacle.
	 */
	public Labyrinthe ( int taille, int aX, int aY, int dX, int dY, float densite ) {
		this( taille, aX, aY, dX, dY );
		Random random = new Random();

		for (int x = 0; x < taille; x++) {
			for (int y = 0; y < taille; y++) {
				if( !(x == aX && y == aY) && !(x == dX && y == dY) && random.nextFloat() < densite )
					this.set( x, y, true );
			}
		}
	}

	/**
	 * crée un <code>Labyrinthe</code> à partir d'un ensemble d'informations que l'on donne en paramètre.
	 * on peut lui donnée une map des obstacles que l'on a produite au préalable.
	 *
	 * @param taille la taille de la grille (taille*taille).
	 * @param aX     la coordonnée su l'axe X de la sortie.
	 * @param aY     la coordonnée sur l'axe Y de la sortie.
	 * @param dX     la coordonnée sur l'axe X de l'entrée.
	 * @param dY     la coordonnée sur l'axe Y de l'entrée.
	 * @param map    la position de tout les obstacle de la grilles.
	 */

	public Labyrinthe ( int taille, int aX, int aY, int dX, int dY, byte[] map ) {
		this.taille = taille;
		this.arriveeX = aX;
		this.arriveeY = aY;
		this.departX = dX;
		this.departY = dY;
		this.map = map;
	}

	/**
	 * crée un <code>Labyrinthe</code> à partir d'un ensemble d'informations que l'on donne en paramètre.
	 * on peut lui donnée une map des obstacles que l'on a produite au préalable.
	 *
	 * @param taille la taille de la grille (taille*taille).
	 * @param carrearray[] tableau d'objets Carre.
	 */

	private int i;

	public Labyrinthe ( int taille, int entree, int sortie, Carre carrearray[] ) {

		int tempi = 0;
		int num = 0;

		int aX = 0, aY = 0, dX = 0, dY = 0;

		int nbrByte = (int) Math.ceil( taille * taille / 8.0 );

		byte map[] = new byte[nbrByte];

		for (int x = 0, i = 0; x < taille; x++, i++) {
			tempi = i;
			for (int y = 0; y < taille; y++, i = i + taille) {

				if( carrearray[i].returnvideouplein() ) {
					map[num / 8] |= 1 << (7 - num % 8);
				} else {
					map[num / 8] &= ~(1 << (7 - num % 8));
				}

				num++;

			}
			i = tempi;
		}

		for (int x = 0, i = 0; x < taille; x++) {

			for (int y = 0; y < taille; y++, i++) {

				if( entree == i ) {
					System.out.println( "Entree " + i );
					dX = x;
					dY = y;
					Depart = i;
				}

				if( sortie == i ) {
					System.out.println( "Sortie " + i );
					aX = x;
					aY = y;
					Arrivee = i;
				}
/*
				if(carrearray[i].returnifES("Entree")==true) {
					System.out.println("Entree "+i);

					dX=x;
					dY=y;
					Depart=i;
				}

				if (carrearray[i].returnifES("Sortie")==true) {
					System.out.println("Sortie "+i);

					aX=x;
					aY=y;
					Arrivee=i;
				}*/

			}
		}

		this.taille = taille;
		this.arriveeX = aX;
		this.arriveeY = aY;
		this.departX = dX;
		this.departY = dY;
		this.map = map;
	}

	/**
	 * crée un <code>Labyrinthe</code> à partir d'un fichier (*.lab) qui contient l'ensemble des donnée requise
	 * elle en extrait les donnée afin qu'elle puisse être utiliser.
	 *
	 * @param file
	 */
	public Labyrinthe ( File file ) {
		FileInputStream stream = null;

		try{
			stream = new FileInputStream( file );

			this.taille = stream.read();
			this.departX = stream.read();
			this.departY = stream.read();
			this.arriveeX = stream.read();
			this.arriveeY = stream.read();
			int nbrByte = (int) Math.ceil( this.taille * this.taille / 8.0 );

			this.map = new byte[nbrByte];

			for (int i = 0; i < nbrByte; i++)
				this.map[i] = (byte) stream.read();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( stream != null ) {
				try{
					stream.close();
				} catch (IOException ignored) {
				}
			}
		}
	}

	/**
	 * methode qui permet de sauvegarder l'ensemble des paramètres pertinents de la grille dans un fichier
	 * fournie en Paramètre de la methode.
	 *
	 * @param filename
	 */
	public void saveTo ( String filename ) {
		//Fichier de sauvegardes
		File dir = new File( "Sauvegardes_lab" );
		if( !dir.exists() ) {
			dir.mkdir();
		}

		File file = new File( filename );

		if( file.exists() )
			file.delete();

		FileOutputStream stream = null;

		try{
			stream = new FileOutputStream( file );

			stream.write( this.taille );
			stream.write( this.departX );

			stream.write( this.departY );
			System.out.println( "Depart " + departX + " " + departY );
			stream.write( this.arriveeX );
			stream.write( this.arriveeY );

			for (int i = 0; i < map.length; i++) {
				System.out.println( "map[" + i + "]=" + map[i] );
				stream.write( this.map[i] );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( stream != null ) {
				try{
					stream.close();
				} catch (IOException ignored) {
				}
			}
		}
	}

	/**
	 * Méthode qui renvoie la taille de de la grille(taille*taille).
	 *
	 * @return
	 */
	public int getTaille () {
		return taille;
	}

	/**
	 * Méthode qui permet de mettre à jour la taille de la grille(taille*taille).
	 *
	 * @param taille
	 */
	public void setTaille ( int taille ) {
		this.taille = taille;
	}

	/**
	 * Méthode qui renvoie la coordonnée X de l'arrivée.
	 *
	 * @return
	 */
	public int getArriveeX () {
		return arriveeX;
	}

	/**
	 * Méthode qui renvoie la coordonnée Y de l'arrivée.
	 *
	 * @return
	 */
	public int getArriveeY () {
		return arriveeY;
	}

	/**
	 * Méthode qui met à jour les coordonnée d'arrivée.
	 *
	 * @param x
	 * @param y
	 */
	public void setArrivee ( int x, int y ) {
		this.arriveeX = x;
		this.arriveeY = y;
	}

	/**
	 * Méthode qui renvoie la coordonnée X de départ.
	 *
	 * @return
	 */
	public int getDepartX () {
		return departX;
	}

	/**
	 * Méthode qui renvoie la coordonnée Y de départ.
	 *
	 * @return
	 */
	public int getDepartY () {
		return departY;
	}

	/**
	 * Méthode qui met à jour la coordonnée Y de départ.
	 *
	 * @param x
	 * @param y
	 */
	public void setDepart ( int x, int y ) {
		this.departX = x;
		this.departY = y;
	}

	/**
	 * Méthode qui renvoie le statut mur de la position a une dimensions fournie en paramètre.
	 *
	 * @param pos
	 * @return
	 */
	public boolean get ( int pos ) {
		return ((this.map[pos / 8] >> (7 - pos % 8)) & 1) == 1;
	}

	/**
	 * Méthode qui renvoie le statut mur de la position a deux dimensions fournie en paramètre.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean get ( int x, int y ) {
		return this.get( x + y * this.taille );
	}

	/**
	 * Méthode qui met à jour le statut de mur a une position dans un tableau a une dimension.
	 *
	 * @param pos
	 * @param mur
	 */
	public void set ( int pos, boolean mur ) {
		if( mur ) {
			this.map[pos / 8] |= 1 << (7 - pos % 8);
		} else {
			this.map[pos / 8] &= ~(1 << (7 - pos % 8));
		}
	}

	/**
	 * Méthode qui met à jour le statut de mur a une position dans un tableau a deux dimension.
	 *
	 * @param x
	 * @param y
	 * @param mur
	 */
	public void set ( int x, int y, boolean mur ) {
		this.set( y + x * this.taille, mur );
	}

	private Carre[] carrearray;
	private int Depart;
	private int Arrivee;

	public Carre[] getCarrearray () {
		carrearray = new Carre[getTaille() * getTaille()];

		System.out.println( "La taille est de: " + getTaille() );

		int tempi = 0;
		int num = 0;

		for (int x = 0, i = 0; x < getTaille(); x++, i++) {
			tempi = i;
			for (int y = 0; y < getTaille(); y++, i = i + getTaille()) {

				carrearray[num] = new Carre( get( i ), num );

				carrearray[num].setEnabledfalse();
				num++;

			}
			i = tempi;
		}

		for (int x = 0, i = 0; x < getTaille(); x++) {

			for (int y = 0; y < getTaille(); y++, i++) {

				if( (getDepartX() == x) && (getDepartY() == y) ) {

					System.out.println( "Entree " + i );

					carrearray[i].setEntree();
					Depart = i;

				} else if( (getArriveeX() == x) && (getArriveeY() == y) ) {

					System.out.println( "Sortie " + i );

					carrearray[i].setSortie();
					Arrivee = i;

				}

			}
		}

		return carrearray;

	}

	/**
	 * @return the depart
	 */
	public int getDepart () {
		return Depart;
	}

	/**
	 * @return the arrivee
	 */
	public int getArrivee () {
		return Arrivee;
	}
}