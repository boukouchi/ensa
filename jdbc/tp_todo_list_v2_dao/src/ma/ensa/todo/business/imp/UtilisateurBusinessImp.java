package ma.ensa.todo.business.imp;

import java.util.Scanner;

import ma.ensa.todo.beans.Utilisateur;
import ma.ensa.todo.business.UtilisateurBusiness;
import ma.ensa.todo.dao.UtilisateurDao;

public class UtilisateurBusinessImp implements UtilisateurBusiness {
	private UtilisateurDao dao;

	public UtilisateurBusinessImp(UtilisateurDao dao) {
		this.dao = dao;
	}

	@Override
	public void ajouterUtilisateur() {
		System.out.println("=======>> L'ajout d'un utilisateur");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez saisir le Login :");
		String loginUser = scanner.nextLine();
		System.out.print("Veuillez saisir le mot de passe :");
		String pwUser = scanner.nextLine();
		System.out.print("Veuillez saisir l'email :");
		String emailUser = scanner.nextLine();
		dao.createUtilisateur(loginUser, pwUser, emailUser);
	}

	@Override
	public Utilisateur connecterUtilisateur() {
		System.out.println("=======>> Connexion d'un utilisateur");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez saisir le Login        :");
		String loginUser = scanner.nextLine();
		System.out.print("Veuillez saisir le mot de passe :");
		String pwUser = scanner.nextLine();
		Utilisateur utilisateur = dao.findByLogin(loginUser, pwUser);
		if (utilisateur != null) {
			System.out.println("Connexion réussie de : " + utilisateur.getLogin());

		} else {
			System.err.println("Login ou mot de passe incorrecte");
		}
		return utilisateur;
	}
}
