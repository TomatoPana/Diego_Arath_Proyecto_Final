package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Atiende {
    private int Id_Nota;
    private int Id_Automovil;
    private int Id_Empleado;

    public Atiende() {
        this.Id_Nota = 0;
        this.Id_Automovil = 0;
        this.Id_Empleado = 0;
    }

    public Atiende(int id_Nota, int id_Automovil, int id_Empleado) {
        Id_Nota = id_Nota;
        Id_Automovil = id_Automovil;
        Id_Empleado = id_Empleado;
    }

    public int getId_Nota() {
        return Id_Nota;
    }

    public void setId_Nota(int id_Nota) {
        Id_Nota = id_Nota;
    }

    public int getId_Automovil() {
        return Id_Automovil;
    }

    public void setId_Automovil(int id_Automovil) {
        Id_Automovil = id_Automovil;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        Id_Empleado = id_Empleado;
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

        public Atiende getAtiende(int id) {
            Atiende dato = new Atiende();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Atiende WHERE Id_Nota = " + id);

                    if(resultado.next()){
                        dato.setId_Nota(resultado.getInt("Id_Nota"));
                        dato.setId_Automovil(resultado.getInt("Id_Automovil"));
                        dato.setId_Empleado(resultado.getInt("Id_Empleado"));
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

        public boolean insertAtiende(Atiende dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Atiende (Id_Automovil, Id_Empleado) VALUES (?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setInt(1, dato.getId_Automovil());
                    sentencia.setInt(2, dato.getId_Empleado());

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

        public boolean updateAtiende(Atiende dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Atiende SET Id_Automovil = ?, Id_Empleado = ? WHERE Id_Nota = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setInt(1, dato.getId_Automovil());
                    sentencia.setInt(2, dato.getId_Empleado());
                    sentencia.setInt(3, dato.getId_Nota());

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

        public boolean deleteAtiende(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Atiende WHERE Id_Nota = ?";

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
