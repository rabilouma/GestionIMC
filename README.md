# GestionIMC — Application JEE

Application web de calcul et gestion d'IMC (Indice de Masse Corporelle).

## 🛠 Technologies
- Jakarta EE 10
- Apache Tomcat 10.1
- Java (JDK 21)
- JSP / JSTL 3.0 / EL
- MySQL (WAMP) + Connector/J 9.7.0
- Bootstrap 5.0.2

## 📁 Structure
- `src/main/java/modele/MonIMC.java` — Bean patient (calcul IMC)
- `src/main/java/controlleur/ImcServlet.java` — Contrôleur (MVC)
- `src/main/webapp/accueil.jsp` — Liste des patients
- `src/main/webapp/new_patient.jsp` — Création patient
- `src/main/webapp/modifier_patient.jsp` — Modification patient

## 🗄 Base de données

```sql
CREATE DATABASE IF NOT EXISTS listeimc;
USE listeimc;

CREATE TABLE patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    masse DOUBLE NOT NULL,
    taille DOUBLE NOT NULL,
    imc DOUBLE NOT NULL,
    interpretation VARCHAR(50) NOT NULL
);

INSERT INTO patient (nom, prenom, masse, taille, imc, interpretation)
VALUES ('DIOP', 'Daouda', 70, 1.70, 24.22, 'Corpulence normale');
