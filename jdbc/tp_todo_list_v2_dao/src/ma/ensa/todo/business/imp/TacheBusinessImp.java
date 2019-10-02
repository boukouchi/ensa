package ma.ensa.todo.business.imp;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import ma.ensa.todo.beans.Tache;
import ma.ensa.todo.beans.Utilisateur;
import ma.ensa.todo.business.TacheBusiness;
import ma.ensa.todo.dao.TacheDao;

public class TacheBusinessImp implements TacheBusiness {
	private TacheDao dao;

	public TacheBusinessImp(TacheDao dao) {
		this.dao = dao;
	}

	@Override
	public void afficherTaches(Utilisateur utilisateur) {
		List<Tache> taches = utilisateur.getTaches();
		System.out.println(utilisateur.getLogin() + ": Liste des taches");
		for (Tache tache : taches) {
			String format = "%-4d%-30s%-4d%12s\n";
			System.out.format(format, tache.getCode(), tache.getLibelle(), tache.getVolume(), tache.getDatefin());
		}
	}

	@Override
	public void ajouterTache(Utilisateur utilisateur) {
		Tache tache = null;
		System.out.println("=======>> L'ajout d'une tache à : " + utilisateur.getLogin());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Libellé de la tâche             :");
		String libelleTache = scanner.nextLine();
		System.out.print("Volume horaire de la tache      :");
		int volumeTache = Integer.parseInt(scanner.nextLine());
		System.out.print("Date fin de la tache(yyyy-mm-dd):");
		Date datefinTache = Date.valueOf(scanner.nextLine());
		tache = dao.createTache(libelleTache, volumeTache, datefinTache, utilisateur.getId());
		utilisateur.ajouterTache(tache);
	}

	@Override
	public void supprimerTache(Utilisateur utilisateur) {
		Tache tache = null;
		System.out.println("=======>> La supprission d'une tache de : " + utilisateur.getLogin());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Code de la tâche à supprimer:");
		int codeTache = Integer.parseInt(scanner.nextLine());
		for (Tache t : utilisateur.getTaches()) {
			if (t.getCode() == codeTache) {
				tache = t;
			}
		}
		dao.deleteTache(codeTache);

		utilisateur.supprimerTache(tache);

	}

	@Override
	public void modifierTache(Utilisateur utilisateur) {

		System.out.println("=======>> La modification d'une tache de : " + utilisateur.getLogin());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Code de la tache      :");
		int codeTache = Integer.parseInt(scanner.nextLine());
		System.out.print("Libellé de la tâche             :");
		String libelleTache = scanner.nextLine();
		System.out.print("Volume horaire de la tache      :");
		int volumeTache = Integer.parseInt(scanner.nextLine());
		System.out.print("Date fin de la tache(yyyy-mm-dd):");
		Date datefinTache = Date.valueOf(scanner.nextLine());
		dao.updateTache(codeTache, libelleTache, volumeTache, datefinTache, utilisateur.getId());

	}

}
