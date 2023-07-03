/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author victor janco
 */
public class MEquipo {
    private int id;
    private String nombre;
    private int puntos;
    private int goles;
    private int codigoCamp;
    private Conexion conexion;
    
    public MEquipo(){
        this.conexion=new Conexion();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getCodigoCamp() {
        return codigoCamp;
    }

    public void setCodigoCamp(int codigoCamp) {
        this.codigoCamp = codigoCamp;
    }
    
    public ArrayList<MEquipo> obtenerEquipos(){
        conexion.conectar();
        String sql = "SELECT id, nombre, puntos, goles, codigoCamp  FROM equipo WHERE codigoCamp="+this.codigoCamp;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MEquipo> lista = new ArrayList<>();
            while (rs.next()) {
                MEquipo equipo = new MEquipo();
                equipo.id = rs.getInt("id");
                equipo.nombre = rs.getString("nombre");
                equipo.puntos = rs.getInt("puntos");
                equipo.goles = rs.getInt("goles");
                equipo.codigoCamp = rs.getInt("codigoCamp");
                lista.add(equipo);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MEquipo - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
    public void getEquipo(int id){
        conexion.conectar();
        String sql = "SELECT id, nombre, puntos, goles, codigoCamp FROM equipo WHERE id="+id;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.id = rs.getInt("id");
                this.nombre = rs.getString("nombre");
                this.puntos = rs.getInt("puntos");
                this.goles = rs.getInt("goles");
                this.codigoCamp = rs.getInt("codigoCamp");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MEquipo" + ex.getMessage());
        }       
    }
    public void getEquipo(String nombre){
        conexion.conectar();
        nombre="'"+nombre+"'";
        String sql = "SELECT id, nombre, puntos FROM equipo WHERE nombre="+nombre;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.id = rs.getInt("id");
                this.nombre = rs.getString("nombre");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MEquipo" + ex.getMessage());
        }      
    }
    public boolean insertar(){
        String dato ="insert into equipo (nombre, codigoCamp) values ('"+this.nombre+"', "+this.codigoCamp+");";
        if (conexion.conectar()) {
            System.out.println("Se conecto");
            conexion.insertar(dato);
            conexion.desconectar();
            return true;
        }else{
            return false;
        }
    }
    public boolean modificar(){
        String sql = "UPDATE equipo SET nombre= '" +this.nombre+ "' WHERE id = " + this.id;
        if (conexion.conectar()) {
            System.out.println("Se conecto");
            conexion.actualizar(sql);
            conexion.desconectar();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}
