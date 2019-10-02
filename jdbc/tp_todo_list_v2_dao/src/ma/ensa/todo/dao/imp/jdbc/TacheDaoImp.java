package ma.ensa.todo.dao.imp.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.ensa.todo.beans.Tache;
import ma.ensa.todo.beans.Utilisateur;
import ma.ensa.todo.dao.TacheDao;

public class TacheDaoImp implements TacheDao {
	private Connection con = null;
	private PreparedStatement pstm = null;
	private Statement stm = null;
	private ResultSet rs = null;

	public TacheDaoImp() {

	}

	@Override
	public List<Tache> findByUtilisateur(Utilisateur utilisateur) {
		con = DBConnection.getConnection();

		List<Tache> taches = new ArrayList<Tache>();
		try {
			String sqlSelect = "select * from tache where id=?";
			pstm = con.prepareStatement(sqlSelect);
			pstm.setInt(1, utilisateur.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Tache tache = new Tache();
				tache.setCode(rs.getInt(1));
				tache.setLibelle(rs.getString(2));
				tache.setVolume(rs.getInt(3));
				tache.setDatefin(rs.getDate(4));
				taches.add(tache);
			}
		} catch (SQLException e) {
			DBConnection.getErrorException(e);
		} finally {
			DBConnection.close(con, stm, pstm, rs);
		}

		return taches;
	}

	@Override
	public Tache createTache(String libelle, int volume, Date dateFin, int id) {
		Tache tache = new Tache();
		tache.setLibelle(libelle);
		tache.setVolume(volume);
		tache.setDatefin(dateFin);
		con = DBConnection.getConnection();
		try {
			String sqlInsertTache = "insert into tache(libelle,volume,datefin,id)value(?,?,?,?)";
			pstm = con.prepareStatement(sqlInsertTache, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, libelle);
			pstm.setInt(2, volume);
			pstm.setDate(3, dateFin);
			pstm.setInt(4, id);
			int resultat = pstm.executeUpdate();
			if (resultat == 1) {
				System.out.println("Tâche ajoutée");
				ResultSet primary = pstm.getGeneratedKeys();
				while (primary.next()) {
					tache.setCode(primary.getInt(1));
				}
				primary.close();

			} else {
				System.err.println("Probléme d'ajout de la tache tâche");
			}
		} catch (SQLException e) {
			DBConnection.getErrorException(e);
		} finally {
			DBConnection.close(con, null, pstm, rs);
		}
		return tache;

	}

	@Override
	public void deleteTache(int code) {
		con = DBConnection.getConnection();

		try {
			String sqlDeleteTache = "delete from tache where code=?";
			pstm = con.prepareStatement(sqlDeleteTache);
			pstm.setInt(1, code);
			int resultat = pstm.executeUpdate();
			if (resultat == 1) {
				System.out.println("Tâche supprimée");
			} else {
				System.err.println("Probléme de suppression de la tâche");
			}
		} catch (SQLException e) {
			DBConnection.getErrorException(e);
		} finally {
			DBConnection.close(con, null, pstm, rs);
		}
	}

	@Override
	public void updateTache(int code, String libelle, int volume, Date dateFin, int id) {

		con = DBConnection.getConnection();
		try {
			String sqlUpdatetTache = "Update tache set libelle=?, volume=?, datefin=? where code=? and id=?";
			pstm = con.prepareStatement(sqlUpdatetTache);
			pstm.setString(1, libelle);
			pstm.setInt(2, volume);
			pstm.setDate(3, dateFin);
			pstm.setInt(4, id);
			int resultat = pstm.executeUpdate();
			if (resultat == 1) {
				System.out.println("Tâche ajoutée");
			} else {
				System.err.println("Probléme d'ajout de la tache");
			}
		} catch (SQLException e) {
			DBConnection.getErrorException(e);
		} finally {
			DBConnection.close(con, null, pstm, rs);
		}
	}

}
