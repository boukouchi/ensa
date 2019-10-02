package ma.ensa.todo.beans;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int id;
	private String login;
	private String motpasse;
	private String email;
	private List<Tache> taches;

	public Utilisateur() {
		// TODO Auto-generated constructor stub
		taches = new ArrayList<Tache>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the motpasse
	 */
	public String getMotpasse() {
		return motpasse;
	}

	/**
	 * @param motpasse
	 *            the motpasse to set
	 */
	public void setMotpasse(String motpasse) {
		this.motpasse = motpasse;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the taches
	 */
	public List<Tache> getTaches() {
		return taches;
	}

	/**
	 * @param taches
	 *            the taches to set
	 */
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public void ajouterTache(Tache tache) {
		this.taches.add(tache);
	}

	public void supprimerTache(Tache tache) {
		this.taches.remove(tache);
	}

}
