package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Realiza {
    private int Id_Registro;
    private int Id_Sucursal;
    private String Tipo_Servicio;

    public Realiza() {
        this.Id_Registro = 0;
        this.Id_Sucursal = 0;
        this.Tipo_Servicio = "";
    }

    public Realiza(int id_Registro, int id_Sucursal, String tipo_Servicio) {
        Id_Registro = id_Registro;
        Id_Sucursal = id_Sucursal;
        Tipo_Servicio = tipo_Servicio;
    }

    public int getId_Registro() {
        return Id_Registro;
    }

    public void setId_Registro(int id_Registro) {
        Id_Registro = id_Registro;
    }

    public int getId_Sucursal() {
        return Id_Sucursal;
    }

    public void setId_Sucursal(int id_Sucursal) {
        Id_Sucursal = id_Sucursal;
    }

    public String getTipo_Servicio() {
        return Tipo_Servicio;
    }

    public void setTipo_Servicio(String tipo_Servicio) {
        Tipo_Servicio = tipo_Servicio;
    }

    public static class RealizaBD {

        public ArrayList<Realiza> getRealizas() {
            ArrayList<Realiza> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Realiza";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Realiza elemento;

                    while(resultado.next()){
                        elemento = new Realiza(resultado.getInt("Id_Registro"), resultado.getInt("Id_Sucursal"), resultado.getString("Tipo_Servicio"));
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

        public Realiza getRealiza(int id) {
            Realiza dato = new Realiza();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Realiza WHERE Id_Registro = " + id);

                    if(resultado.next()){
                        dato.setId_Registro(resultado.getInt("Id_Registro"));
                        dato.setId_Sucursal(resultado.getInt("Id_Sucursal"));
                        dato.setTipo_Servicio(resultado.getString("Tipo_Servicio"));
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

        public boolean insertRealiza(Realiza dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Realiza (Id_Sucursal, Tipo_Servicio) VALUES (?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setInt(1, dato.getId_Sucursal());
                    sentencia.setString(2, dato.getTipo_Servicio());

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

        public boolean updateRealiza(Realiza dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Realiza SET Id_Sucursal = ?, Tipo_Servicio = ? WHERE Id_Registro = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setInt(1, dato.getId_Sucursal());
                    sentencia.setString(2, dato.getTipo_Servicio());
                    sentencia.setInt(3, dato.getId_Registro());

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

        public boolean deleteRealiza(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Realiza WHERE Id_Registro = ?";

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
