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
public class MAmonestacion {
    private int idPartido;
    private String ciJugador;
    private int ta;
    private int tr;
    private int gol;
    private Conexion conexion;
    
    public MAmonestacion(){
        this.conexion=new Conexion();
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getCiJugador() {
        return ciJugador;
    }

    public void setCiJugador(String ciJugador) {
        this.ciJugador = ciJugador;
    }

    public int getTa() {
        return ta;
    }

    public void setTa(int ta) {
        this.ta = ta;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }
    
    
     public ArrayList<MAmonestacion> obtenerAmonestaciones(){
        conexion.conectar();
        String sql = "SELECT v.idEquipo, v.idPartido, v.gol, e.nombre FROM jugador as j, amonestacion a, partido as p "
                + "WHERE j.ci=a.ciJugador AND a.idPartido=p.id AND p.id="+this.idPartido;
         ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MAmonestacion> lista = new ArrayList<>();
            while (rs.next()) {
                MAmonestacion amonestacion = new MAmonestacion();
                amonestacion.idPartido = rs.getInt("idPartido");
                amonestacion.ciJugador= rs.getString("ciJugador");
                amonestacion.ta= rs.getInt("ta");
                amonestacion.tr= rs.getInt("tr");
                amonestacion.gol = rs.getInt("gol");
                lista.add(amonestacion);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MVersus - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
   public boolean insertar(){
        String dato ="INSERT INTO amonestacion (idPartido, ciJugador, ta, tr, gol) values ("+this.idPartido+", '" +this.ciJugador+"', "+this.ta+", "+this.tr+", "+this.gol+");";
        if (conexion.conectar()) {
            System.out.println("Se conecto");
            conexion.insertar(dato);
            conexion.desconectar();
            return true;
        }else{
            return false;
        }
    }
}
