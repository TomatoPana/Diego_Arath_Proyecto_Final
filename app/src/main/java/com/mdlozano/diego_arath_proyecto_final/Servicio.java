package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Servicio {
    private int Id_Servicio;
    private String Tipo_Servicio;
    private String Fecha_Inicio;

    public Servicio() {
        this.Id_Servicio = 0;
        this.Tipo_Servicio = "";
        this.Fecha_Inicio = "";
    }

    public Servicio(int Id_Servicio, String tipo_Servicio, String fecha_Inicio) {
        this.Id_Servicio = Id_Servicio;
        Tipo_Servicio = tipo_Servicio;
        Fecha_Inicio = fecha_Inicio;
    }

    public int getId_Servicio() {
        return Id_Servicio;
    }

    public void setId_Servicio(int id_Servicio) {
        Id_Servicio = id_Servicio;
    }

    public String getTipo_Servicio() {
        return Tipo_Servicio;
    }

    public void setTipo_Servicio(String tipo_Servicio) {
        Tipo_Servicio = tipo_Servicio;
    }

    public String getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(String fecha_Inicio) {
        Fecha_Inicio = fecha_Inicio;
    }

    public static class ServicioBD {

        public ArrayList<Servicio> getServicios() {
            ArrayList<Servicio> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Servicio";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Servicio elemento;

                    while(resultado.next()){
                        elemento = new Servicio(resultado.getInt("Id_Servicio"), resultado.getString("Tipo_Servicio"), resultado.getString("Fecha_Inicio"));
                        datos.add(elemento);
                    }


                    conexion.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            };

            Thread newThread = new Thread(task);

            newThread.start();

            while(newThread.isAlive()) { }

            return datos;
        }

        public Servicio getServicio(int id) {
            Servicio dato = new Servicio();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Servicio WHERE Id_Servicio = " + id);

                    if(resultado.next()){
                        dato.setId_Servicio(id);
                        dato.setTipo_Servicio(resultado.getString("Tipo_Servicio"));
                        dato.setFecha_Inicio(resultado.getString("Fecha_Inicio"));
                    } else {
                        throw new Exception();
                    }

                    conexion.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            };

            Thread newThread = new Thread(task);

            newThread.start();

            while(newThread.isAlive()) { }

            return dato;
        }

        public boolean insertServicio(Servicio dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Servicio (Tipo_Servicio, Fecha_Inicio) VALUES (?, ?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getTipo_Servicio());
                    sentencia.setString(2, dato.getFecha_Inicio());

                    sentencia.execute();

                    resultado.set(true);

                    conexion.close();

                } catch (Exception exception) {
                    exception.printStackTrace();
                    resultado.set(false);
                }
            };

            Thread newThread = new Thread(task);

            newThread.start();

            while(newThread.isAlive()) { }

            return resultado.get();
        }

        public boolean updateServicio(Servicio dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Tipo_Servicio = ?, Servicio SET Fecha_Inicio = ? WHERE ID_Servicio = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getTipo_Servicio());
                    sentencia.setString(2, dato.getFecha_Inicio());
                    sentencia.setInt(3, dato.getId_Servicio());

                    sentencia.execute();

                    resultado.set(true);

                    conexion.close();

                } catch (Exception exception) {
                    exception.printStackTrace();
                    resultado.set(false);
                }
            };

            Thread newThread = new Thread(task);

            newThread.start();

            while(newThread.isAlive()) { }

            return resultado.get();
        }

        public boolean deleteServicio(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Servicio WHERE Id_Servicio = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setInt(1, id);

                    sentencia.execute();

                    resultado.set(true);

                    conexion.close();

                } catch (Exception exception) {
                    exception.printStackTrace();
                    resultado.set(false);
                }

            };

            Thread newThread = new Thread(task);

            newThread.start();

            while(newThread.isAlive()) { }

            return resultado.get();
        }

    }
}
