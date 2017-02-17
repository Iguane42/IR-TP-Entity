package modeles;

import dao.*;
import outils.*;
import java.sql.*;
import java.util.*;

public class Reservation {

    private int id_oeuvre;
    private int id_adherent;
    private java.util.Date date_reservation;
    private String statut;
    private Adherent adherent;
    private Oeuvre oeuvre;

    public Reservation() {
        this.setAdherent(new Adherent());
        this.setOeuvre(new Oeuvre());
        this.setStatut("");
    }
    /**
     * Initialise l'Adhérent et l'Oeuvre de la Reservation
     * @param id_oeuvre Id de l'oeuvre réservée
     * @param id_adherent Id de l'adhérent réservant
     * @throws Exception
     */
    public Reservation(int id_oeuvre, int id_adherent) throws Exception {
        setId_oeuvre(id_oeuvre);
        setId_adherent(id_adherent);
        this.setAdherent(new Adherent().lire_Id(id_adherent));
        this.setOeuvre(new Oeuvre().lire_Id(id_oeuvre));
        this.setDate_reservation(date_reservation);
        this.setStatut(statut);
    }
    
    // <editor-fold desc="Propriétés"> 
    
    public Adherent getAdherent() {
        return adherent;
    }
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    public Oeuvre getOeuvre() {
        return oeuvre;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
    public int getId_oeuvre() {
        return id_oeuvre;
    }
    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }
    public int getId_adherent() {
        return id_adherent;
    }
    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }
    public java.util.Date getDate_reservation() {
        return date_reservation;
    }
    public void setDate_reservation(java.util.Date date_reservation) throws Exception {
        this.date_reservation = date_reservation;
    }
    // </editor-fold> 

    /**
     * liste des Réservations en Attente
     * @return List<Reservation> Collection de Réservations
     * @throws Exception
     */
    public List<Reservation> liste() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Reservation uneReservation;
        List<Reservation> lReservations = new ArrayList<Reservation>();
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareStatement("select * from reservation");
            rs = ps.executeQuery();
            while (rs.next()) {
                uneReservation = new Reservation(rs.getInt("id_oeuvre"), rs.getInt("id_adherent"));
                //String dateReservation = Utilitaire.DateToStr(rs.getDate("date_reservation"), "dd/MM/yyyy");
                uneReservation.setDate_reservation(rs.getDate("date_reservation"));
                uneReservation.setStatut(rs.getString("statut"));
                lReservations.add(uneReservation);
            }
            return (lReservations);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Met à jour une Réservation dans la base de données
     * @throws Exception
     */
    public void modifier() throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareStatement("update reservation set statut = 'Confirmée' where id_oeuvre = ? and date_reservation = ?");
            ps.setInt(1, getId_oeuvre());
            String strDate = Utilitaire.DateToStr(getDate_reservation(), "yyyy-MM-dd");
            ps.setDate(2, java.sql.Date.valueOf(strDate));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Insert une Réservation dans la base de données
     * @throws Exception
     */
    public void ajouter() throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            String requete = "insert into reservation (id_oeuvre, date_reservation, id_adherent, statut)";
            requete += " values (?, ?, ?, 'Attente')";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, getId_oeuvre());
            String strDate = Utilitaire.DateToStr(getDate_reservation(), "yyyy-MM-dd");
            ps.setDate(2, java.sql.Date.valueOf(strDate));
            ps.setInt(3, getId_adherent());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void supprimer() throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareStatement("delete from reservation where id_oeuvre = ? and date_reservation = ?");
            ps.setInt(1, getId_oeuvre());
            String strDate = Utilitaire.DateToStr(getDate_reservation(), "yyyy-MM-dd");
            ps.setDate(2, java.sql.Date.valueOf(strDate));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }    

}
