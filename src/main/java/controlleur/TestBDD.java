package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/TestBDD")
public class TestBDD extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url  = "jdbc:mysql://localhost:3306/listeimc?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";  // ⚠️ sous WAMP par défaut = vide

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<h3>✅ Driver MySQL chargé</h3>");

            Connection cn = DriverManager.getConnection(url, user, pass);
            out.println("<h3>✅ Connexion à la base 'listeimc' réussie</h3>");

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patient");

            out.println("<table border='1'>"
                + "<tr><th>Nom</th><th>Prénom</th><th>Masse</th>"
                + "<th>Taille</th><th>IMC</th><th>Interprétation</th></tr>");

            while (rs.next()) {
                out.println("<tr>"
                    + "<td>" + rs.getString("nom") + "</td>"
                    + "<td>" + rs.getString("prenom") + "</td>"
                    + "<td>" + rs.getDouble("masse") + "</td>"
                    + "<td>" + rs.getDouble("taille") + "</td>"
                    + "<td>" + rs.getDouble("imc") + "</td>"
                    + "<td>" + rs.getString("interpretation") + "</td>"
                    + "</tr>");
            }
            out.println("</table>");

            rs.close(); st.close(); cn.close();

        } catch (ClassNotFoundException e) {
            out.println("<h3>❌ Driver MySQL introuvable : " + e.getMessage() + "</h3>");
        } catch (SQLException e) {
            out.println("<h3>❌ Erreur SQL : " + e.getMessage() + "</h3>");
        } catch (Exception e) {
            out.println("<h3>❌ Erreur : " + e.getMessage() + "</h3>");
        }
    }
}