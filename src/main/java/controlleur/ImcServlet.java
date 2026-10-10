package controlleur;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import modele.MonIMC;

public class ImcServlet extends HttpServlet {

    private static final String URL  = "jdbc:mysql://localhost:3306/listeimc?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    // ---------- init() : charge la liste depuis la BDD ----------
    @Override
    public void init() throws ServletException {
        chargerListe();
    }

    private void chargerListe() {
        List<MonIMC> listeIMC = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection cn = DriverManager.getConnection(URL, USER, PASS);
                 Statement st = cn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM patient")) {

                while (rs.next()) {
                    MonIMC p = new MonIMC();
                    p.setId(rs.getInt("id"));
                    p.setNom(rs.getString("nom"));
                    p.setPrenom(rs.getString("prenom"));
                    p.setMasse(rs.getDouble("masse"));
                    p.setTaille(rs.getDouble("taille"));
                    p.setImc(rs.getDouble("imc"));
                    p.setInterpretation(rs.getString("interpretation"));
                    listeIMC.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServletContext().setAttribute("listeIMC", listeIMC);
    }

    // ---------- doGet : suppression ----------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("supprimer".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection cn = DriverManager.getConnection(URL, USER, PASS);
                     PreparedStatement ps = cn.prepareStatement("DELETE FROM patient WHERE id=?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
                chargerListe();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("accueil.jsp");
    }

    // ---------- doPost : création OU modification ----------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String nom    = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        double masse  = Double.parseDouble(request.getParameter("masse"));
        double taille = Double.parseDouble(request.getParameter("taille"));

        MonIMC p = new MonIMC(nom, prenom, masse, taille);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection cn = DriverManager.getConnection(URL, USER, PASS)) {

                if ("modifier".equals(action)) {
                    // ---- UPDATE ----
                    int id = Integer.parseInt(request.getParameter("id"));
                    String sql = "UPDATE patient SET nom=?, prenom=?, masse=?, taille=?, imc=?, interpretation=? WHERE id=?";
                    try (PreparedStatement ps = cn.prepareStatement(sql)) {
                        ps.setString(1, p.getNom());
                        ps.setString(2, p.getPrenom());
                        ps.setDouble(3, p.getMasse());
                        ps.setDouble(4, p.getTaille());
                        ps.setDouble(5, p.getImc());
                        ps.setString(6, p.getInterpretation());
                        ps.setInt(7, id);
                        ps.executeUpdate();
                    }
                } else {
                    // ---- INSERT ----
                    String sql = "INSERT INTO patient(nom, prenom, masse, taille, imc, interpretation) VALUES (?,?,?,?,?,?)";
                    try (PreparedStatement ps = cn.prepareStatement(sql)) {
                        ps.setString(1, p.getNom());
                        ps.setString(2, p.getPrenom());
                        ps.setDouble(3, p.getMasse());
                        ps.setDouble(4, p.getTaille());
                        ps.setDouble(5, p.getImc());
                        ps.setString(6, p.getInterpretation());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        chargerListe();
        response.sendRedirect("accueil.jsp");
    }
}