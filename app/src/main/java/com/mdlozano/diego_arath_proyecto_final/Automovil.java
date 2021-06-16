package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Automovil {
    private int Id_Automovil;
    private String Placas;
    private String Marca;
    private String Modelo;
    private int Id_Cliente;

    public Automovil() {
        this.Id_Automovil = 0;
        this.Placas = "";
        this.Marca = "";
        this.Modelo = "";
        this.Id_Cliente = 0;
    }

    public Automovil(int id_Automovil, String placas, String marca, String modelo, int id_Cliente) {
        Id_Automovil = id_Automovil;
        Placas = placas;
        Marca = marca;
        Modelo = modelo;
        Id_Cliente = id_Cliente;
    }

    public int getId_Automovil() {
        return Id_Automovil;
    }

    public void setId_Automovil(int id_Automovil) {
        Id_Automovil = id_Automovil;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String placas) {
        Placas = placas;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public static class AutomovilBD {

        public ArrayList<Automovil> getAutomovils() {
            ArrayList<Automovil> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Automovil";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Automovil elemento;

                    while(resultado.next()){
                        elemento = new Automovil(resultado.getInt("Id_Automovil"), resultado.getString("Placas"), resultado.getString("Marca"), resultado.getString("Modelo"), resultado.getInt("Id_Cliente"));
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

        public Automovil getAutomovil(int id) {
            Automovil dato = new Automovil();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Automovil WHERE Id_Automovil = " + id);

                    if(resultado.next()){
                        dato.setId_Automovil(resultado.getInt("Id_Automovil"));
                        dato.setPlacas(resultado.getString("Placas"));
                        dato.setMarca(resultado.getString("Marca"));
                        dato.setModelo(resultado.getString("Modelo"));
                        dato.setId_Cliente(resultado.getInt("Id_Cliente"));
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

        public boolean insertAutomovil(Automovil dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Automovil (Placas, Marca, Modelo, Id_Cliente) VALUES (?,?,?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getPlacas());
                    sentencia.setString(2, dato.getMarca());
                    sentencia.setString(3, dato.getModelo());
                    sentencia.setInt(4, dato.getId_Cliente());

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

        public boolean updateAutomovil(Automovil dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Automovil SET Placas = ?, Marca = ?, Modelo = ?, Id_Cliente WHERE Id_Automovil = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getPlacas());
                    sentencia.setString(2, dato.getMarca());
                    sentencia.setString(3, dato.getModelo());
                    sentencia.setInt(4, dato.getId_Cliente());
                    sentencia.setInt(5, dato.getId_Automovil());

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

        public boolean deleteAutomovil(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Automovil WHERE Id_Automovil = ?";

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