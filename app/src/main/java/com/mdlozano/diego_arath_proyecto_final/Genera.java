package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Genera {
    private int Id_Recibo;
    private String Tipo_Servicio;
    private int Costo;

    public Genera() {
        this.Id_Recibo = 0;
        this.Tipo_Servicio = "";
        this.Costo = 0;
    }

    public Genera(int id_Recibo, String tipo_Servicio, int costo) {
        Id_Recibo = id_Recibo;
        Tipo_Servicio = tipo_Servicio;
        Costo = costo;
    }

    public int getId_Recibo() {
        return Id_Recibo;
    }

    public void setId_Recibo(int id_Recibo) {
        Id_Recibo = id_Recibo;
    }

    public String getTipo_Servicio() {
        return Tipo_Servicio;
    }

    public void setTipo_Servicio(String tipo_Servicio) {
        Tipo_Servicio = tipo_Servicio;
    }

    public int getCosto() {
        return Costo;
    }

    public void setCosto(int costo) {
        Costo = costo;
    }

    public static class AtiendeBD {

        public ArrayList<Atiende> getAtiendes() {
            ArrayList<Atiende> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Atiende";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Atiende elemento;

                    while(resultado.next()){
                        elemento = new Atiende(resultado.getInt("Id_Nota"), resultado.getInt("Id_Automovil"), resultado.getInt("Id_Empleado"));
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

        public Genera getGenera(int id) {
            Genera dato = new Genera();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Genera WHERE Id_Recibo = " + id);

                    if(resultado.next()){
                        dato.setId_Recibo(resultado.getInt("Id_Recibo"));
                        dato.setTipo_Servicio(resultado.getString("Tipo_Servicio"));
                        dato.setCosto(resultado.getInt("Costo"));
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

        public boolean insertGenera(Genera dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Genera (Tipo_Servicio, Costo) VALUES (?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getTipo_Servicio());
                    sentencia.setInt(2, dato.getCosto());

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

        public boolean updateGenera(Genera dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Genera SET Tipo_Servicio = ?, Costos = ? WHERE Id_Recibo = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getTipo_Servicio());
                    sentencia.setInt(2, dato.getCosto());
                    sentencia.setInt(3, dato.getId_Recibo());

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

        public boolean deleteGenera(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Genera WHERE Id_Recibo = ?";

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
