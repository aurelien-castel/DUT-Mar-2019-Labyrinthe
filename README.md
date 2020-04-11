# Mode d'emploi
- [Mode d'emploi](#mode-demploi)
  - [Utilisation](#utilisation)
  - [Résumé du projet](#r%c3%a9sum%c3%a9-du-projet)
  - [Images du projet](#images-du-projet)

## Utilisation
Allez dans le répertoire projet<br /> 
```bash
javac Main.java 
```
```bash
java Main
```
Pour relancer une nouvelle fois il faut supprimer les .class
| Sous winsdows | Sous linux |
| :--: | :--: |
| del /s *.class | find . -name \`*.class\` -exec rm -f {} \; |

## Résumé du projet
Projet qui avait pour but de résoudre des labyrinthes personalisables et sauvegardables. Après avoir créé son labyrinthe, l'utilisateur peut le résoudre grâce à un algorithme déterministe. Pour mieux comprendre le fonctionnement de l'algorithme l'utilisateur peut dérouler l'algorithme pas à pas ou sinon le dérouler entièrement. L'algorithme peut aussi détecter si ce labyrinthe est solvable ou non. Après avoir terminé de résoudre son labyrinthe, l'utilisateur peut sauvegarder son labyrinthe. Grâce à un système d'encodage et de décodage le labyrinthe est sauvegardé dans un fichier et est chargeable la prochaine fois que l'utilisateur ouvrira le projet.

## Images du projet
| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/1.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/1.png?raw=true) | 
| :--: | 
| Choix taille de la grille |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/2.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/2.png?raw=true) | 
| :--: | 
| Grille vide à personaliser |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/3.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/3.png?raw=true) | 
| :--: | 
| Personalisation aléatoire de la grille |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/4.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/4.png?raw=true) | 
| :--: | 
| Début d'une recherche |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/5.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/5.png?raw=true) | 
| :--: | 
| Fin d'une recherche |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/6.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/6.png?raw=true) | 
| :--: | 
| Recherche dans un labyrinthe impossible |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/ClassDiagram.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/ClassDiagram.png?raw=true) | 
| :--: | 
| Diagramme de classes du projet |

| ![https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/EncodeDecode.png?raw=true](https://github.com/aurelien-castel/DUT-Mar-2019-Labyrinthe/blob/master/images/EncodeDecode.png?raw=true) | 
| :--: | 
| Système d'encodage et décodage des fichiers |
