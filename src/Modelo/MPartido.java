/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author victor janco
 */
public class MPartido {
    private int id;
    private Date fecha;
    private Time hora;
    private String estado;
    private int codigoCamp;
    
    
    private Conexion conexion;
    
    public MPartido(){
     this.conexion=new Conexion();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoCamp() {
        return codigoCamp;
    }

    public void setCodigoCamp(int codigoCamp) {
        this.codigoCamp = codigoCamp;
    }
    
    
    public ArrayList<MPartido> obtenerPartidos(){
        conexion.conectar();
          String sql = "SELECT id, fecha, hora, estado FROM partido "
               + "WHERE codigoCamp="+this.codigoCamp;
       // String sql = "SELECT p.id, p.fecha, p.hora, p.estado, v.idEquipo, v.goles, v.puntos, FROM equipo as e, versus as v, partido as p "
         //       + "WHERE e.equipo=v.idEquipo AND v.idPartido=p.id AND codigoCamp="+this.codigoCamp;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MPartido> lista = new ArrayList<>();
            while (rs.next()) {
                MPartido partido = new MPartido();
                partido.id = rs.getInt("id");
                partido.fecha = rs.getDate("fecha");
                partido.hora = rs.getTime("hora");
                partido.estado = rs.getString("estado");
                lista.add(partido);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("Mpartido - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
    public MPartido getPartido(int id){
        conexion.conectar();
        String sql = "SELECT id, fecha, hora, estado, codigoCamp FROM partido WHERE id="+id;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.id = rs.getInt("id");
                this.fecha = rs.getDate("fecha");
                this.hora = rs.getTime("hora");
                this.estado = rs.getString("estado");
                this.codigoCamp = rs.getInt("codigoCamp");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MPartido" + ex.getMessage());
        }  
        return this;
    }
    
    public boolean insertar(){
        String dato ="INSERT INTO partido (id, fecha, hora, estado, codigoCamp) values ("+this.id+",'"+this.fecha+"','"+this.hora+"','"+this.estado+"',"+this.codigoCamp+");";
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
        String sql = "UPDATE partido SET fecha= '" +this.fecha+ "', "
                                       +"hora= '" +this.hora+ "', "
                                       +"estado= '" +this.estado+ "' "
                                       +"WHERE id = " +this.id;
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
