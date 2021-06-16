package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sucursal {
    private int Id_Sucursal;
    private String Domicilio;
    private String Nombre;
    private int Id_Empleado;

    public Sucursal() {
        this.Id_Sucursal = 0;
        this.Domicilio = "";
        this.Nombre = "";
        this.Id_Empleado = 0;
    }

    public Sucursal(int id_Sucursal, String domicilio, String nombre, int id_Empleado) {
        Id_Sucursal = id_Sucursal;
        Domicilio = domicilio;
        Nombre = nombre;
        Id_Empleado = id_Empleado;
    }

    public int getId_Sucursal() {
        return Id_Sucursal;
    }

    public void setId_Sucursal(int id_Sucursal) {
        Id_Sucursal = id_Sucursal;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        Id_Empleado = id_Empleado;
    }

    public static class SucursalBD {

        public ArrayList<Sucursal> getSucursals() {
            ArrayList<Sucursal> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Sucursal";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Sucursal elemento;

                    while(resultado.next()){
                        elemento = new Sucursal(resultado.getInt("Id_Sucursal"), resultado.getString("Domicilio"), resultado.getString("Nombre"), resultado.getInt("Id_Empleado"));
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

        public Sucursal getSucursal(int id) {
            Sucursal dato = new Sucursal();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Sucursal WHERE Id_Sucursal = " + id);

                    if(resultado.next()){
                        dato.setId_Sucursal(resultado.getInt("Id_Sucursal"));
                        dato.setDomicilio(resultado.getString("Domicilio"));
                        dato.setNombre(resultado.getString("Nombre"));
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

        public boolean insertSucursal(Sucursal dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Sucursal (Domicilio, Nombre, Id_Empleado) VALUES (?,?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getDomicilio());
                    sentencia.setString(2, dato.getNombre());
                    sentencia.setInt(3, dato.getId_Empleado());

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

        public boolean updateSucursal(Sucursal dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Sucursal SET Domicilio = ?, Nombre = ?, Id_Empleado = ? WHERE Id_Sucursal = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getDomicilio());
                    sentencia.setString(2, dato.getNombre());
                    sentencia.setInt(3, dato.getId_Empleado());
                    sentencia.setInt(4, dato.getId_Sucursal());

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

        public boolean deleteSucursal(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Sucursal WHERE Id_Sucursal = ?";

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
