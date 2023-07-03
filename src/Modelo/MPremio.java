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
public class MPremio {
    private int id;
    private int codigoCamp;
    private String nombre;
    private String descripcion;
    private Conexion conexion;
    
    public MPremio(){
        conexion=new Conexion();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoCamp() {
        return codigoCamp;
    }

    public void setCodigoCamp(int codigoCamp) {
        this.codigoCamp = codigoCamp;
    }
    
    
    public ArrayList<MPremio> obtenerPremios(){
        conexion.conectar();
        String sql = "SELECT id, codigoCamp, nombre, descripcion FROM premio WHERE codigoCamp="+this.codigoCamp;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MPremio> lista = new ArrayList<>();
            while (rs.next()) {
                MPremio premio = new MPremio();
                premio.id = rs.getInt("id");
                premio.nombre = rs.getString("nombre");
                premio.descripcion = rs.getString("descripcion");
                lista.add(premio);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MPremio - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
    public void getPremio(int id){
        conexion.conectar();
        String sql = "SELECT id, nombre, descripcion FROM premio WHERE id="+id;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.id = rs.getInt("id");
                this.nombre = rs.getString("nombre");
                this.descripcion = rs.getString("descripcion");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MPremio" + ex.getMessage());
        }       
    }
    
    public boolean insertar(){
        String dato ="insert into premio (codigoCamp, nombre, descripcion) values ("+this.codigoCamp+", '"+this.getNombre()+"', '"+this.descripcion+"');";
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
        String sql = "UPDATE premio SET nombre= '" +this.nombre+ "', descripcion = '"+this.descripcion+"' WHERE id = " + this.id;
        if (conexion.conectar()) {
            System.out.println("Se conecto");
            conexion.actualizar(sql);
            conexion.desconectar();
            return true;
        }else{
            return false;
        }
    }
    
}
