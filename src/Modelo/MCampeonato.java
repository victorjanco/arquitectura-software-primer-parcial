/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author victor janco
 */
public class MCampeonato {
    private int codigo;
    private String nombre;
    private int temporada;
    private Date fechaInicio;
    private Date fechaFinal;
    private int idTipo;
    private Conexion conexion;
    
    public MCampeonato() {
       this.conexion=new Conexion();
    }

    public MCampeonato(int codigo, String nombre, int temporada, Date fechaInicio, Date fechaFinal, int idTipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.temporada = temporada;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.idTipo = idTipo;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    public String getTipoCampeonato(){
        MTipo mTipo= new MTipo();
        mTipo.getTipo(this.idTipo);
        return mTipo.getNombre();
    }
    
  /*  public void getCampeonato(int codigo){
        conexion.conectar();
        String sql = "SELECT codigo, nombre FROM campeonato WHERE codigo="+codigo;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.codigo = rs.getInt("codigo");
                this.nombre = rs.getString("nombre");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MCampeonato- Lista " + ex.getMessage());
        }       
    }
    public void getCampeonato(String nombre){
        conexion.conectar();
        nombre="'"+nombre+"'";
        String sql = "SELECT codigo, nombre FROM campeonato WHERE nombre="+nombre;
        ResultSet rs = conexion.consultaSelect(sql);
        try {
           
            while (rs.next()) {
                this.codigo = rs.getInt("codigo");
                this.nombre = rs.getString("nombre");
            }
            conexion.desconectar();
        } catch (Exception ex) {
            System.err.println("MCampeonato- Lista " + ex.getMessage());
        }       
    }*/
     public ArrayList<MCampeonato> obtenerCampeonatos(){
        conexion.conectar();
        String sql = "SELECT * FROM campeonato ORDER BY codigo";
        ResultSet rs = conexion.consultaSelect(sql);
        try {
            ArrayList<MCampeonato> lista = new ArrayList<>();
            while (rs.next()) {
                MCampeonato campeonato = new MCampeonato();
                campeonato.codigo = rs.getInt("codigo");
                campeonato.nombre = rs.getString("nombre");
                campeonato.temporada = rs.getInt("temporada");
                campeonato.fechaInicio = rs.getDate("fechaInicio");
                campeonato.fechaFinal = rs.getDate("fechaFin");
                campeonato.idTipo = rs.getInt("idTipo");
                lista.add(campeonato);
            }
            conexion.desconectar();
            return lista;
        } catch (Exception ex) {
            System.err.println("MCampeonato - Lista " + ex.getMessage());
            return new ArrayList<>();
        }        
    } 
    public boolean insertar(){
        String dato ="insert into campeonato (codigo, nombre, temporada, fechaInicio, fechaFin, idTipo) values "
                + "('"+this.getCodigo()+"','"+this.getNombre()+"','"+this.getTemporada()+"','"+this.getFechaInicio()+"','"+this.getFechaFinal()+"','"+this.getIdTipo()+"');";
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
        String sql = "UPDATE campeonato SET nombre = '" +this.nombre+ "', "
                                        +" temporada = "+this.temporada+", "
                                        +" fechaInicio = '"+this.fechaInicio+"',"
                                        +" fechaFin = '"+this.fechaFinal+"',"
                                        +" idTipo = "+this.idTipo+" "
                                        +" WHERE codigo = " + this.codigo;
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
