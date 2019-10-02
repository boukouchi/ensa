package ma.ensa.todo.dao;

import java.sql.Date;
import java.util.List;

import ma.ensa.todo.beans.Tache;
import ma.ensa.todo.beans.Utilisateur;

public interface TacheDao {
	public List<Tache> findByUtilisateur(Utilisateur utilisateur);

	public Tache createTache(String libelle, int volume, Date dateFin, int id);

	public void deleteTache(int code);

	public void updateTache(int code, String libelle, int volume, Date dateFin, int id);

}
