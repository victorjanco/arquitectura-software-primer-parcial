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
public class MTipo {
    private int id;
    private String nombre;
    private Conexion conexion;

    public MTipo(String nombre) {
        this.nombre = nombre;
        this.conexion=new Conexion();
    }
    public MTipo(){
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
    
    
    public ArrayList<MTipo> obtenerTipos(){
        conexion.conectar();
        String sql = "SELECT id, nombre FROM tipo ORDER BY id";
        ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MTipo> lista = new ArrayList<>();
            while (rs.next()) {
                MTipo tipo = new MTipo();
                tipo.id = rs.getInt("id");
                tipo.nombre = rs.getString("nombre");
                lista.add(tipo);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MTipo - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
    public void getTipo(int id){
        conexion.conectar();
        String sql = "SELECT id, nombre FROM tipo WHERE id="+id;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.id = rs.getInt("id");
                this.nombre = rs.getString("nombre");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MTipo- Lista " + ex.getMessage());
        }       
    }
    public void getTipo(String nombre){
        conexion.conectar();
        nombre="'"+nombre+"'";
        String sql = "SELECT id, nombre FROM tipo WHERE nombre="+nombre;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.id = rs.getInt("id");
                this.nombre = rs.getString("nombre");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MTipo- Lista " + ex.getMessage());
        }       
    }
    public boolean insertar(){
        String dato ="insert into tipo (nombre) values ('"+this.getNombre()+"');";
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
        String sql = "UPDATE tipo SET nombre = '" +this.nombre+ "' WHERE id = " + this.id;
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
