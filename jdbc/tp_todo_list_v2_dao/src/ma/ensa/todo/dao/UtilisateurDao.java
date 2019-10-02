package ma.ensa.todo.dao;

import ma.ensa.todo.beans.Utilisateur;

public interface UtilisateurDao {
	public Utilisateur findByLogin(String login, String motpasse);

	public void createUtilisateur(String login, String motpasse, String email);

	// public List<Utilisateur> findAllUtilisateurs();

}
