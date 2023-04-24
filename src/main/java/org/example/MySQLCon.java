package org.example;

import java.sql.*;

public class MySQLCon {
    private Connection connection;
    public void connectDatabase(String url, String user, String password) {
        try {
            // Registramos el driver de MySQL
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }
            connection = null;
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(url, user, password);
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de MySQL (" + url + "): " + sqle);
        }
    }
    public void cerrar()throws SQLException {
        if(connection!=null)
            connection.close();
    }
    public void executeUpdate(String sql)throws SQLException{
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        System.out.println(result + " records affected");
    }
    public ResultSet executeQuery(String sql)throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
