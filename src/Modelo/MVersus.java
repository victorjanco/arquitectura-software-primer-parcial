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
public class MVersus {
   private int idPartido;
   private int idEquipo;
   private int gol;
   private int punto;
   private String nombreEquipo;
   
   private Conexion conexion;
   
   public MVersus(){
       this.conexion=new Conexion();
   }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int iPartido) {
        this.idPartido = iPartido;
    }

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    
    public ArrayList<MVersus> obtenerVersus(){
        conexion.conectar();
        String sql = "SELECT  v.idPartido, v.idEquipo, v.gol, v.punto, e.nombre FROM equipo as e, versus v, partido as p "
                + "WHERE  v.idPartido=p.id AND e.id=v.idEquipo AND p.id="+this.idPartido;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MVersus> lista = new ArrayList<>();
            while (rs.next()) {
                MVersus versus = new MVersus();
                versus.idEquipo = rs.getInt("idEquipo");
                versus.idPartido = rs.getInt("idPartido");
                versus.gol = rs.getInt("gol");
                versus.punto = rs.getInt("punto");
                versus.nombreEquipo = rs.getString("nombre");
                lista.add(versus);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MVersus - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
   public boolean insertar(){
        String dato ="INSERT INTO versus (idPartido, idEquipo) values ("+this.idPartido+"," +this.idEquipo+");";
        if (conexion.conectar()) {
            System.out.println("Se conecto");
            conexion.insertar(dato);
            conexion.desconectar();
            return true;
        }else{
            return false;
        }
    }
   
    public boolean update(){
        String sql = "UPDATE versus SET gol = " +this.gol+ ", punto="+ this.punto +" WHERE idPartido = " + this.idPartido+" AND idEquipo=" + this.idEquipo;
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
