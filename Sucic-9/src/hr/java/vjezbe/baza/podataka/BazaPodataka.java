package hr.java.vjezbe.baza.podataka;

import hr.java.vjezbe.entitet.*;

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

    //DRZAVE
    public static void spremiDrzavu(Drzava drzava) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insertDrzava = veza.prepareStatement("INSERT INTO POSTAJE.DRZAVA(ID, NAZIV, POVRSINA) VALUES (?, ?, ?);");
            insertDrzava.setInt(1, drzava.getId());
            insertDrzava.setString(2, drzava.getNaziv());
            insertDrzava.setBigDecimal(3, drzava.getPovrsina());
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



    //ZUPANIJE
    public static void spremiZupaniju(Zupanija zupanija) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insert = veza.prepareStatement("INSERT INTO POSTAJE.ZUPANIJA(ID, NAZIV, DRZAVA_ID) VALUES (?, ?, ?);");
            insert.setInt(1, zupanija.getId());
            insert.setString(2, zupanija.getNaziv());
            insert.setInt(3, zupanija.getDrzava().getId());
            insert.executeUpdate();
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
            Zupanija zupanija = new Zupanija(zupanijaId, nazivZupanije, drzava);

            listaZupanija.add(zupanija);
        }
        zatvaranjeVezeNaBazuPodataka(veza);
        return listaZupanija;
    }




    //MJESTA
    public static void spremiMjesto(Mjesto mjesto) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insert = veza.prepareStatement("INSERT INTO POSTAJE.MJESTO(ID, NAZIV, VRSTA, ZUPANIJA_ID) VALUES (?, ?, ?, ?);");
            insert.setInt(1, mjesto.getId());
            insert.setString(2, mjesto.getNaziv());
            insert.setString(3, mjesto.getVrstaMjesta().toString());
            insert.setInt(4, mjesto.getZupanija().getId());
            insert.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }
    public static List<Mjesto> dohvatiMjesta() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statement = veza.createStatement();
        ResultSet result = statement.executeQuery("SELECT mj.ID, mj.NAZIV, mj.VRSTA, zu.NAZIV FROM POSTAJE.MJESTO mj INNER JOIN POSTAJE.ZUPANIJA zu ON mj.ZUPANIJA_ID = zu.ID");


        List< Mjesto > lista = new ArrayList<>();
        while (result.next()) {
            Integer mjestoId = result.getInt("MJESTO.ID");
            String nazivMjesta = result.getString("MJESTO.NAZIV");
            String vrstaMjesta = result.getString("MJESTO.VRSTA");
            String nazivZupanije = result.getString("ZUPANIJA.NAZIV");

            Zupanija zupanija = new Zupanija(nazivZupanije, null);
            Mjesto mjesto = new Mjesto(mjestoId, nazivMjesta, zupanija);
            mjesto.setVrstaMjesta(VrstaMjesta.valueOf(vrstaMjesta));

            lista.add(mjesto);
        }
        zatvaranjeVezeNaBazuPodataka(veza);

        return lista;
    }


    //MJERNE POSTAJE
    public static void spremiMjernePostaje(MjernaPostaja mjernaPostaja) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insert = veza.prepareStatement("INSERT INTO POSTAJE.MJERNA_POSTAJA(ID, NAZIV, MJESTO_ID, LAT, LNG) VALUES (?, ?, ?, ?, ?);");
            insert.setInt(1, mjernaPostaja.getId());
            insert.setString(2, mjernaPostaja.getNaziv());
            insert.setInt(3, mjernaPostaja.getMjesto().getId());
            insert.setBigDecimal(4, mjernaPostaja.getGeografskaTocka().getX());
            insert.setBigDecimal(5, mjernaPostaja.getGeografskaTocka().getY());
            insert.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }
    public static List<MjernaPostaja> dohvatiMjernePostaje() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statement = veza.createStatement();
        ResultSet result = statement.executeQuery("SELECT mjp.ID, mjp.NAZIV, mj.NAZIV, mjp.LAT, mjp.LNG FROM POSTAJE.MJERNA_POSTAJA mjp INNER JOIN POSTAJE.MJESTO mj ON mjp.MJESTO_ID = mj.ID");


        List< MjernaPostaja > lista = new ArrayList<>();
        while (result.next()) {
            Integer postajaId = result.getInt("MJERNA_POSTAJA.ID");
            String nazivPostaje = result.getString("MJERNA_POSTAJA.NAZIV");
            String nazivMjesta = result.getString("MJESTO.NAZIV");
            BigDecimal lat = result.getBigDecimal("MJERNA_POSTAJA.LAT");
            BigDecimal lag = result.getBigDecimal("MJERNA_POSTAJA.LNG");

            GeografskaTocka geoTocka = new GeografskaTocka(lat, lag);
            Mjesto mjesto = new Mjesto(nazivMjesta, null);
            MjernaPostaja postaja = new MjernaPostaja(postajaId, nazivPostaje, mjesto, geoTocka);


            lista.add(postaja);
        }
        zatvaranjeVezeNaBazuPodataka(veza);

        return lista;
    }

    //MJERNE POSTAJE
    public static void spremiSenzor(SenzorTemperature senzor) throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        veza.setAutoCommit(true);
        try {
            PreparedStatement insert = veza.prepareStatement("INSERT INTO POSTAJE.SENZOR(MJERNA_JEDINICA, PRECIZNOST, VRIJEDNOST, RAD_SENZORA, MJERNA_POSTAJA_ID) VALUES (?, ?, ?, ?, ?);");
            insert.setString(1, senzor.getMjernaJedinica());
            insert.setDouble(2, senzor.getPreciznost());
            insert.setBigDecimal(3, senzor.getVrijednost());
            insert.setString(4, senzor.getRadSenzora().toString());
            insert.setInt(5, senzor.getMjernaPostaja().getId());
            insert.executeUpdate();

            Statement statement = veza.createStatement();
            ResultSet result = statement.executeQuery("SELECT TOP 1 * FROM POSTAJE.SENZOR ORDER BY ID DESC");
            Integer senzorId = null;
            while (result.next()){
                senzorId = result.getInt("ID");
            }
            PreparedStatement insertSenzorTemperature = veza.prepareStatement("INSERT INTO POSTAJE.SENZOR_TEMPERATURE(ID, ELEKTRONICKA_KOMPONENTA) VALUES (?, ?);");
            insertSenzorTemperature.setInt(1, senzorId);
            insertSenzorTemperature.setString(2, senzor.getElektronickaKomponenta());
            insertSenzorTemperature.executeUpdate();
        }
        catch(Throwable ex) {
            veza.rollback();
            throw ex;
        }
        zatvaranjeVezeNaBazuPodataka(veza);
    }
    public static List<SenzorTemperature> dohvatiSenzore() throws SQLException, IOException {
        Connection veza = spajanjeNaBazuPodataka();
        Statement statement = veza.createStatement();
        ResultSet result = statement.executeQuery("SELECT s.ID, s.MJERNA_JEDINICA, s.PRECIZNOST, s.VRIJEDNOST, s.RAD_SENZORA, mjp.NAZIV, st.ELEKTRONICKA_KOMPONENTA FROM POSTAJE.SENZOR s INNER JOIN POSTAJE.MJERNA_POSTAJA mjp ON s.MJERNA_POSTAJA_ID = mjp.ID INNER JOIN POSTAJE.SENZOR_TEMPERATURE st ON s.ID = st.ID");


        List< SenzorTemperature > lista = new ArrayList<>();
        while (result.next()) {
            Integer senzorId = result.getInt("SENZOR.ID");
            String mjernaJedinica = result.getString("SENZOR.MJERNA_JEDINICA");
            Double preciznost = result.getDouble("SENZOR.PRECIZNOST");
            BigDecimal vrijednost = result.getBigDecimal("SENZOR.VRIJEDNOST");
            RadSenzora radSenzora = RadSenzora.valueOf(result.getString("SENZOR.RAD_SENZORA"));
            String nazivPostaje = result.getString("MJERNA_POSTAJA.NAZIV");
            String elektronickaKomponenta = result.getString("SENZOR_TEMPERATURE.ELEKTRONICKA_KOMPONENTA");

            MjernaPostaja postaja = new MjernaPostaja(nazivPostaje, null, null);
            SenzorTemperature senzor = new SenzorTemperature(senzorId, elektronickaKomponenta, mjernaJedinica, preciznost, vrijednost, radSenzora, postaja);

            lista.add(senzor);
        }
        zatvaranjeVezeNaBazuPodataka(veza);

        return lista;
    }
}
