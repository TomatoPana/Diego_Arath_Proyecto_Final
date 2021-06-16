package com.mdlozano.diego_arath_proyecto_final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Cliente {
    private int Id_Cliente;
    private String Nombre;
    private String Telefono;
    private String Apellidos;
    private String Domicilio;

    public Cliente() {
        this.Id_Cliente = 0;
        this.Nombre = "";
        this.Telefono = "";
        this.Apellidos = "";
        this.Domicilio = "";
    }

    public Cliente(int id_Cliente, String nombre, String telefono, String apellidos, String domicilio) {
        Id_Cliente = id_Cliente;
        Nombre = nombre;
        Telefono = telefono;
        Apellidos = apellidos;
        Domicilio = domicilio;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public static class ClienteBD {

        public ArrayList<Cliente> getClientes() {
            ArrayList<Cliente> datos = new ArrayList<>();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "SELECT * FROM Cliente";

                    Statement sentencia = conexion.createStatement();

                    ResultSet resultado = sentencia.executeQuery(SQL);

                    Cliente elemento;

                    while(resultado.next()){
                        elemento = new Cliente(resultado.getInt("Id_Cliente"), resultado.getString("Nombre"), resultado.getString("Telefono"), resultado.getString("Apellidos"), resultado.getString("Domicilio"));
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

        public Cliente getCliente(int id) {
            Cliente dato = new Cliente();

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM Cliente WHERE Id_Cliente = " + id);

                    if(resultado.next()){
                        dato.setId_Cliente(resultado.getInt("Id_Cliente"));
                        dato.setNombre(resultado.getString("Nombre"));
                        dato.setTelefono(resultado.getString("Telefono"));
                        dato.setApellidos(resultado.getString("Apellidos"));
                        dato.setDomicilio(resultado.getString("Domicilio"));
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

        public boolean insertCliente(Cliente dato){
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "INSERT INTO Cliente (Nombre, Telefono, Apellidos, Domicilio) VALUES (?,?,?,?)";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getNombre());
                    sentencia.setString(2, dato.getTelefono());
                    sentencia.setString(3, dato.getApellidos());
                    sentencia.setString(4, dato.getDomicilio());
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

        public boolean updateCliente(Cliente dato) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "UPDATE Cliente SET Nombre = ?, Telefono = ?, Apellidos = ?, Domicilio = ? WHERE Id_Cliente = ?";

                    PreparedStatement sentencia = conexion.prepareStatement(SQL);

                    sentencia.setString(1, dato.getNombre());
                    sentencia.setString(2, dato.getTelefono());
                    sentencia.setString(3, dato.getApellidos());
                    sentencia.setString(4, dato.getDomicilio());
                    sentencia.setInt(5, dato.getId_Cliente());

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

        public boolean deleteCliente(int id) {
            AtomicBoolean resultado = new AtomicBoolean(false);

            Runnable task = () -> {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://ao9moanwus0rjiex.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/lbdb0ii09q1suhsu","gwd5dexb49hi9may","o1uums0swvxzeuv9");

                    String SQL = "DELETE FROM Cliente WHERE Id_Cliente = ?";

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

