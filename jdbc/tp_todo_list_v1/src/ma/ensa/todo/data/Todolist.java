package ma.ensa.todo.data;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Todolist {

	public static void main(String[] args) {
		UserManager manager=new UserManager();
		//String user="";
		int id=-1;
		int option;
		String decision="";
		do{
			
			System.out.println("____________________________________________________________");
			System.out.println("|----------- Bienvenue à l'application Todo-List -----------|");
			System.out.println("|___________________________________________________________|");
			System.out.println("|-----------Taper 1 : Pour se connecter --------------------|");
			System.out.println("|-----------Taper 2 : Pour créer un utilisateur ------------|");
			System.out.println("|-----------Taper 3 : pour afficher les taches -------------|");
			System.out.println("|-----------Taper 4 : pour supprimer une tache -------------|");
			System.out.println("|-----------Taper 5 : pour modifier une tache --------------|");
			System.out.println("|-----------Taper 6 : pour ajouter une tache ---------------|");
			System.out.println("|-----------Taper 0 : pour quitter l'application -----------|");
			System.out.println("|___________________________________________________________|");
			Scanner scanner=new Scanner(System.in);
			System.out.println("Votre opération -------->");
			option =Integer.parseInt(scanner.nextLine());
			
			switch (option) {
			case 0:System.out.println("----> Application fermée <----");
				break;
			case 1:id=manager.connecterUtilisateur();
				break;
			case 2:manager.ajouterUtilisateur();
				
				break;
			case 3:
				if(id!=-1){
					manager.afficherTaches(id);
				}else{
					System.out.println("Il faut se connecter");
					}
			break;
			case 4:
				if(id!=-1){
					manager.supprimerTache(id);
				}else{
					System.out.println("Il faut se connecter");
					}
			break;
			case 5:
				if(id!=-1){
					manager.modifierTache(id);
				}else{
					System.out.println("Il faut se connecter");
					}
			break;
			case 6:
				if(id!=-1){
					manager.ajouterTache(id);
				}else{
					System.out.println("Il faut se connecter");
					}
			break;
			default:System.err.println("Choix invalide");
				break;
			}
			System.out.println("Voulez vous continuez?(oui/non)");
			decision =scanner.nextLine();
		}while(decision.equals("oui"));

	}
}
