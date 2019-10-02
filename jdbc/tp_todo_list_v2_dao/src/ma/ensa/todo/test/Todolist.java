package ma.ensa.todo.test;

import java.util.Scanner;

import ma.ensa.todo.beans.Utilisateur;
import ma.ensa.todo.business.TacheBusiness;
import ma.ensa.todo.business.UtilisateurBusiness;
import ma.ensa.todo.business.imp.TacheBusinessImp;
import ma.ensa.todo.business.imp.UtilisateurBusinessImp;
import ma.ensa.todo.dao.TacheDao;
import ma.ensa.todo.dao.UtilisateurDao;
import ma.ensa.todo.dao.imp.jdbc.TacheDaoImp;
import ma.ensa.todo.dao.imp.jdbc.UtilisateurDaoImp;

public class Todolist {

	public static void main(String[] args) {
		UtilisateurDao doaUtilisateur = new UtilisateurDaoImp();
		TacheDao daoTache = new TacheDaoImp();
		UtilisateurBusiness userBusiness = new UtilisateurBusinessImp(doaUtilisateur);
		TacheBusiness tacheBusiness = new TacheBusinessImp(daoTache);
		Utilisateur utilisateur = null;
		char option;
		String decision = "";
		do {

			System.out.println("____________________________________________________________");
			System.out.println("|--------- Bienvenue à l'application Todo-List v2.0 --------|");
			System.out.println("|___________________________________________________________|");
			System.out.println("|-----------Taper 1 : Pour se connecter --------------------|");
			System.out.println("|-----------Taper 2 : Pour créer un utilisateur ------------|");
			System.out.println("|-----------Taper 3 : pour afficher les taches -------------|");
			System.out.println("|-----------Taper 4 : pour supprimer une tache -------------|");
			System.out.println("|-----------Taper 5 : pour modifier une tache --------------|");
			System.out.println("|-----------Taper 6 : pour ajouter une tache ---------------|");
			System.out.println("|-----------Taper 0 : pour quitter l'application -----------|");
			System.out.println("|___________________________________________________________|");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Votre opération -------->");
			option = (scanner.nextLine()).charAt(0);

			switch (option) {
			case '0':
				System.out.println("----> Application fermée <----");
				break;
			case '1':
				utilisateur = userBusiness.connecterUtilisateur();
				break;
			case '2':
				userBusiness.ajouterUtilisateur();

				break;
			case '3':
				if (utilisateur != null) {
					tacheBusiness.afficherTaches(utilisateur);
				} else {
					System.out.println("Il faut se connecter");
				}
				break;
			case '4':
				if (utilisateur != null) {
					tacheBusiness.supprimerTache(utilisateur);
				} else {
					System.out.println("Il faut se connecter");
				}
				break;
			case '5':
				if (utilisateur != null) {
					tacheBusiness.modifierTache(utilisateur);
				} else {
					System.out.println("Il faut se connecter");
				}
				break;
			case '6':
				if (utilisateur != null) {
					tacheBusiness.ajouterTache(utilisateur);
				} else {
					System.out.println("Il faut se connecter");
				}
				break;
			default:
				System.err.println("Choix invalide");
				break;
			}
			System.out.println("Voulez vous continuez?(oui/non)");
			decision = scanner.nextLine();
		} while (decision.equals("oui"));
		System.out.println("******* Fin de programme *********");
	}
}
