package ma.ensa.todo.dao.imp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ma.ensa.todo.beans.Tache;
import ma.ensa.todo.beans.Utilisateur;
import ma.ensa.todo.dao.UtilisateurDao;

public class UtilisateurDaoImp implements UtilisateurDao {
	private Connection con = null;
	private PreparedStatement pstm = null;
	private Statement stm = null;
	private ResultSet rs = null;

	public UtilisateurDaoImp() {

	}

	@Override
	public Utilisateur findByLogin(String login, String motpasse) {

		con = DBConnection.getConnection();

		Utilisateur utilisateur = null;
		try {
			System.out.println("Etat connexion :" + con.isClosed() + " [ " + con + "]");
			String sqlConnect = "select * from utilisateur where login=? and motpasse=?";
			pstm = con.prepareStatement(sqlConnect);
			pstm.setString(1, login);
			pstm.setString(2, motpasse);
			rs = pstm.executeQuery();
			rs.first();
			if (rs.getRow() == 1) {
				utilisateur = new Utilisateur();
				utilisateur.setLogin(rs.getString("login"));
				utilisateur.setMotpasse(rs.getString("motpasse"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setId(rs.getInt("id"));
				String sqlSelect = "select * from tache where id=?";
				pstm = con.prepareStatement(sqlSelect);
				pstm.setInt(1, utilisateur.getId());
				ResultSet rsTaches = pstm.executeQuery();
				while (rsTaches.next()) {
					Tache tache = new Tache();
					tache.setCode(rsTaches.getInt(1));
					tache.setLibelle(rsTaches.getString(2));
					tache.setVolume(rsTaches.getInt(3));
					tache.setDatefin(rsTaches.getDate(4));

					utilisateur.ajouterTache(tache);
				}
				rsTaches.close();

			}
		} catch (SQLException e) {
			DBConnection.getErrorException(e);
		} finally {
			DBConnection.close(con, stm, pstm, rs);
		}
		return utilisateur;

	}

	@Override
	public void createUtilisateur(String login, String motpasse, String email) {

		con = DBConnection.getConnection();
		try {
			String sqlInsert = "insert into utilisateur(login,motpasse,email)value(?, ?,?)";
			pstm = con.prepareStatement(sqlInsert);
			pstm.setString(1, login);
			pstm.setString(2, motpasse);
			pstm.setString(3, email);
			int resultat = pstm.executeUpdate();
			if (resultat == 1) {
				System.out.println("Utilisateur bien enregistré");
			} else {
				System.err.println("Probléme d'enregistrement");
			}
		} catch (SQLException e) {
			DBConnection.getErrorException(e);
		} finally {
			DBConnection.close(con, null, pstm, rs);
		}

	}

	/*
	 * @Override public List<Utilisateur> findAllUtilisateurs() {
	 * 
	 * con = DBConnection.getConnection(); List<Utilisateur> utilisateurs = new
	 * ArrayList<Utilisateur>();
	 * 
	 * try { String sqlConnect = "select * from utilisateur"; stm =
	 * con.createStatement(); rs = pstm.executeQuery(sqlConnect);
	 * 
	 * while (rs.next()) { Utilisateur utilisateur = new Utilisateur();
	 * utilisateur.setLogin(rs.getString("login"));
	 * utilisateur.setMotpasse(rs.getString("motpasse"));
	 * utilisateur.setEmail(rs.getString("email"));
	 * utilisateur.setId(rs.getInt("id")); String sqlSelect =
	 * "select * from tache where id=?"; pstm = con.prepareStatement(sqlSelect);
	 * pstm.setInt(1, utilisateur.getId()); ResultSet rsTaches =
	 * pstm.executeQuery(); while (rsTaches.next()) { Tache tache = new Tache();
	 * tache.setCode(rsTaches.getInt(1));
	 * tache.setLibelle(rsTaches.getString(2));
	 * tache.setVolume(rsTaches.getInt(3));
	 * tache.setDatefin(rsTaches.getDate(4)); utilisateur.ajouterTache(tache); }
	 * rsTaches.close(); utilisateurs.add(utilisateur);
	 * 
	 * } } catch (SQLException e) { DBConnection.getErrorException(e); } finally
	 * { DBConnection.close(con, stm, pstm, rs); }
	 * 
	 * return utilisateurs; }
	 */

}
