package hr.java.vjezbe.baza.podataka;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.Zupanija;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BazaPodataka {
    private static final String DATABASE_FILE = "bazaPodataka.properties";


    private static Connection spajanjeNaBazuPodataka() throws SQLException, IOException{
        Connection veza = null;
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
        String korisnickoIme = svojstva.getProperty("korisnickoIme");
        String lozinka = svojstva.getProperty("lozinka");

        try {
            veza = DriverManager.getConnection(urlBazePodataka,korisnickoIme,lozinka);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veza;
    }

    private static void zatvaranjeVezeNaBazuPodataka(Connection veza) throws SQLException, IOException{
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
        String korisnickoIme = svojstva.getProperty("korisnickoIme");
        String lozinka = svojstva.getProperty("lozinka");
        try {
            veza = DriverManager.getConnection(urlBazePodataka,korisnickoIme,lozinka);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                veza.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void spremiDrzavu(Drzava drzava) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertDrzava = veza.prepareStatement("INSERT INTO POSTAJE.DRZAVA(NAZIV, POVRSINA) VALUES (?, ?);");
            insertDrzava.setString(1, drzava.getNaziv());
            insertDrzava.setBigDecimal(2, drzava.getPovrsina());
            insertDrzava.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<Drzava> dohvatiDrzave() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statementDrzave = veza.createStatement();
        ResultSet resultSetDrzave =
                statementDrzave.executeQuery("SELECT * FROM POSTAJE.DRZAVA");
        List< Drzava > listaDrzava = new ArrayList<>();
        while (resultSetDrzave.next()) {
            Integer drzavaId = resultSetDrzave.getInt("ID");
            String naziv = resultSetDrzave.getString("NAZIV");
            BigDecimal povrsina = resultSetDrzave.getBigDecimal("POVRSINA");
            Drzava drzava = new Drzava(naziv, povrsina);
            drzava.setId(drzavaId);
            listaDrzava.add(drzava);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaDrzava;
    }

    public static void spremiZupaniju(Zupanija zupanija) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertDrzava = veza.prepareStatement("INSERT INTO POSTAJE.ZUPANIJA(NAZIV, DRZAVA_ID) VALUES (?, ?);");
            insertDrzava.setString(1, zupanija.getNaziv());
            insertDrzava.setInt(2, zupanija.getDrzava().getId());
            insertDrzava.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }

    public static List<Zupanija> dohvatiZupanije() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statement = veza.createStatement();
        ResultSet resultZupanije = statement.executeQuery("SELECT zu.ID, zu.NAZIV, dr.NAZIV, dr.POVRSINA FROM POSTAJE.DRZAVA dr INNER JOIN POSTAJE.ZUPANIJA zu ON zu.DRZAVA_ID = dr.ID");

        List< Zupanija > listaZupanija = new ArrayList<>();
        while (resultZupanije.next()) {
            Integer zupanijaId = resultZupanije.getInt("ZUPANIJA.ID");
            String nazivZupanije = resultZupanije.getString("ZUPANIJA.NAZIV");
            String nazivDrzave = resultZupanije.getString("DRZAVA.NAZIV");
            BigDecimal povrsina = resultZupanije.getBigDecimal("POVRSINA");

            Drzava drzava = new Drzava(nazivDrzave, povrsina);
            Zupanija zupanija = new Zupanija(nazivZupanije, drzava);

            zupanija.setId(zupanijaId);
            listaZupanija.add(zupanija);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaZupanija;
    }

}
