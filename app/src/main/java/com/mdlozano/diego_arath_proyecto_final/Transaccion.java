package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Transaccion {
    private int Costo;
    private String Fecha_Entrega;
    private String Tipo_Transaccion;

    public Transaccion() {
        this.Costo = 0;
        this.Fecha_Entrega = "";
        this.Tipo_Transaccion = "";
    }

    public Transaccion(int costo, String fecha_Entrega, String tipo_Transaccion) {
        Costo = costo;
        Fecha_Entrega = fecha_Entrega;
        Tipo_Transaccion = tipo_Transaccion;
    }

    public int getCosto() {
        return Costo;
    }

    public void setCosto(int costo) {
        Costo = costo;
    }

    public String getFecha_Entrega() {
        return Fecha_Entrega;
    }

    public void setFecha_Entrega(String fecha_Entrega) {
        Fecha_Entrega = fecha_Entrega;
    }

    public String getTipo_Transaccion() {
        return Tipo_Transaccion;
    }

    public void setTipo_Transaccion(String tipo_Transaccion) {
        Tipo_Transaccion = tipo_Transaccion;
    }

    public static class TransaccionBD {

        public ArrayList<Transaccion> getTransaccions() {
            ArrayList<Transaccion> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Transaccion";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Transaccion elemento;

                    while(resultado.next()){
                        elemento = new Transaccion(resultado.getInt("Costo"), resultado.getString("Fecha_Entrega"), resultado.getString("Tipo_Transaccion"));
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

        public Transaccion getTransaccion(int id) {
            Transaccion dato = new Transaccion();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Transaccion WHERE Costo = " + id);

                    if(resultado.next()){
                        dato.setCosto(resultado.getInt("Costo"));
                        dato.setFecha_Entrega(resultado.getString("Fecha_Entrega"));
                        dato.setTipo_Transaccion(resultado.getString("Tipo_Transaccion"));
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

        public boolean insertTransaccion(Transaccion dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Transaccion (Costo, Fecha_Entrega, Tipo_Transaccion) VALUES (?,?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setInt(1, dato.getCosto());
                    sentencia.setString(2, dato.getFecha_Entrega());
                    sentencia.setString(3, dato.getTipo_Transaccion());

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

        public boolean updateTransaccion(Transaccion dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Transaccion SET Fecha_Entrega = ?, Tipo_Transaccion = ? WHERE Costo = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getFecha_Entrega());
                    sentencia.setString(2, dato.getTipo_Transaccion());
                    sentencia.setInt(3, dato.getCosto());

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

        public boolean deleteTransaccion(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Transaccion WHERE Costo = ?";

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

