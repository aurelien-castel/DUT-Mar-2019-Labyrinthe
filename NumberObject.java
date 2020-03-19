/**
 * <b>La classe qui permet sauvegarder les position du personnage sous forme d'objets pour l'algorithme déterministe.</b>
 * <p>
 * Dans cette classe chaque déplacement est sauvé sous forme d'objet de type NumberObject
 * On peut savoir le parent de l'objet, son numéro et s'il est n'est pas un mur (abort) 
 * et aussi si c'est un mur (vp).
 * </p>
 */

class NumberObject
{
    private int Number;
    private Boolean abort=false;
    private int parent;
    private Boolean vp=false;
    private int NbIterationparent;

    /**
     * si en dehors
     * @param num
     * @param abort
     */

    NumberObject (int num, Boolean abort) {
     this.Number=num;
     this.abort=abort;
    }
    
    /**
     * si en dehors + parent
     * @param num
     * @param abort
     * @param NbIterationparent
     * @param parent
     */

    NumberObject (int num, Boolean abort, int NbIterationparent ,int parent) {
         this(num,abort);
     this.NbIterationparent=NbIterationparent;
     this.parent=parent;
    }

    /**
     * si mur + parent
     * @param num
     * @param abort
     * @param NbIterationparent
     * @param parent
     * @param videplein
     */

    NumberObject (int num, Boolean abort, int NbIterationparent ,int parent, Boolean videplein) {
     this(num,abort);
     this.NbIterationparent=NbIterationparent;
     this.parent=parent;
     this.vp=videplein;
     }

     /**
      * @return the nbIterationparent
      */
     public int getNbIterationparent() {
          return NbIterationparent;
     }

     /**
      * @return the vp
      */
     public Boolean getVp() {
          return vp;
     }

    /**
     * @return the number
     */
    public int getValue() {
         return Number;
    }

    /**
     * @return the abort
     */
    public Boolean getAbort() {
         return abort;
    }

    /**
     * @return the parent
     */
    public int getParent() {
         return parent;
    }

    /**
     * @param abort the abort to set
     */
    public void setAbort(Boolean abort) {
         this.abort = abort;
    }
}