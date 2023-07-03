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
public class MJugador {
    private String ci;
    private String nombre;
    private String apellido;
    private String telefono;
    private int nroCamiseta;
    private int idEquipo;
    private Conexion conexion;
    
    public MJugador(){
        this.conexion=new Conexion();
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public int getNroCamiseta() {
        return nroCamiseta;
    }

    public void setNroCamiseta(int nroCamiseta) {
        this.nroCamiseta = nroCamiseta;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    
     public ArrayList<MJugador> obtenerJugadores(){
        conexion.conectar();
        String sql = "SELECT ci, nombre, apellido, telefono, nroCamiseta  FROM jugador WHERE idEquipo="+this.idEquipo;
         ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MJugador> lista = new ArrayList<>();
            while (rs.next()) {
                MJugador jugador = new MJugador();
                jugador.ci = rs.getString("ci");
                jugador.nombre = rs.getString("nombre");
                jugador.apellido = rs.getString("apellido");
                jugador.telefono = rs.getString("telefono");
                jugador.nroCamiseta = rs.getInt("nroCamiseta");
                lista.add(jugador);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MJugador - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    
    public void getJugador(String ci){
        ci="'"+ci+"'";
        conexion.conectar();
        String sql = "SELECT ci, nombre, apellido, telefono, nroCamiseta, idEquipo FROM jugador WHERE ci="+ci;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.ci = rs.getString("ci");
                this.nombre = rs.getString("nombre");
                this.apellido = rs.getString("apellido");
                this.telefono = rs.getString("telefono");
                this.nroCamiseta = rs.getInt("nroCamiseta");
                this.idEquipo = rs.getInt("idEquipo");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MJugador" + ex.getMessage());
        }       
    }
    
    public boolean insertar(){
        String dato ="INSERT INTO jugador (ci, nombre, apellido, telefono, nroCamiseta, idEquipo) values ('"+this.ci+"','"+this.nombre+"','"+this.apellido+"', '"+this.telefono+"',"+this.nroCamiseta+","+this.idEquipo+");";
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
        String cii="'"+this.ci+"'";
        String sql = "UPDATE jugador SET nombre= '" +this.nombre+ "', "
                                       +"apellido= '" +this.apellido+ "', "
                                       +"telefono= '" +this.telefono+ "', "
                                       +"nroCamiseta= '" +this.nroCamiseta+ "', "
                                       +"idEquipo= " +this.idEquipo+ " "
                                       +"WHERE ci = " +cii;
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
        return this.nroCamiseta+"-"+this.nombre;
    }
    
    
}
