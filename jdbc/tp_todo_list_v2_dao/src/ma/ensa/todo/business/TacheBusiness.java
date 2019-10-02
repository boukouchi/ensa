package ma.ensa.todo.business;

import ma.ensa.todo.beans.Utilisateur;

public interface TacheBusiness {

	public void afficherTaches(Utilisateur utilisateur);

	public void ajouterTache(Utilisateur utilisateur);

	public void supprimerTache(Utilisateur utilisateur);

	public void modifierTache(Utilisateur utilisateur);

}
