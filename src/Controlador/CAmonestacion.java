/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MAmonestacion;
import Modelo.MPartido;
import Modelo.MVersus;
import Vista.VAmonestacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author victor janco
 */
public class CAmonestacion implements ActionListener{
    private VAmonestacion vAmonestacion;
    private MPartido mPartido;
    
     public CAmonestacion(){
        this.mPartido= new MPartido();
        this.vAmonestacion= new VAmonestacion();
        this.vAmonestacion.btnGuardar.addActionListener(this);
        this.vAmonestacion.cbCampeonato.addActionListener(this);
        this.vAmonestacion.cbPartido.addActionListener(this);
        this.vAmonestacion.cbJugadoresA.addActionListener(this);
        this.vAmonestacion.cbJugadoresB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch (e.getActionCommand())
        {
            case "GUARDAR":
                 guardar();
                break;
            case "comboBoxCampeonato":
                  vAmonestacion.cargarComboBoxPartidos();
                break;
            case "comboBoxPartidos":
                System.out.println(e.getActionCommand());
                vAmonestacion.cargarComboBoxJugadores();
                break;
            case "comboBoxJugadoresA":
                System.out.println(e.getActionCommand());
                vAmonestacion.amonestarJugadorA();
                break;
            case "comboBoxJugadoresB":
               System.out.println(e.getActionCommand());
               vAmonestacion.amonestarJugadorB();
                break;
            default:
                System.out.println(e.getActionCommand());
                break;
        }
    }
    
     public void iniciar(){
        vAmonestacion.setTitle("Gestionar Partidos");
        vAmonestacion.setLocationRelativeTo(null);
        vAmonestacion.setVisible(true);
    }
    
    private void guardar(){
        mPartido=new  MPartido().getPartido(Integer.parseInt(vAmonestacion.lbIdPartido.getText()));
        mPartido.setEstado("jugado");
        mPartido.modificar();
        
        MVersus mVersus=new MVersus();
        mVersus.setIdPartido(Integer.parseInt(vAmonestacion.lbIdPartido.getText()));
        ArrayList<MVersus> listaVersus=mVersus.obtenerVersus();
        
        elegirGanador(listaVersus);
        registrarAmonestaciones();
    }
    
    private void elegirGanador(ArrayList<MVersus> listaVersusu){
       MVersus mVersusA=listaVersusu.get(0);
       MVersus mVersusB=listaVersusu.get(1);
       
       mVersusA.setGol(Integer.parseInt(vAmonestacion.tfGolEquipoA.getText()));
       mVersusB.setGol(Integer.parseInt(vAmonestacion.tfGolEquipoB.getText()));
       
       if(mVersusA.getGol() > mVersusB.getGol()){
           mVersusA.setPunto(3);
           mVersusB.setPunto(0);
       } else if(mVersusA.getGol() < mVersusB.getGol()){
           mVersusA.setPunto(0);
           mVersusB.setPunto(3);
       }else{ //es igual    
           mVersusA.setPunto(1);
           mVersusB.setPunto(1);
       }
       
       mVersusA.update();
       mVersusB.update();
    }

    private void registrarAmonestaciones() {
        ArrayList<MAmonestacion> listaAmonestacions=vAmonestacion.listAmonestaciones();
        for (int i = 0; i < listaAmonestacions.size(); i++) {
            listaAmonestacions.get(i).insertar();
        }
    }
 
    public static void main(String[] args) {
        CAmonestacion cAmonestacion=new CAmonestacion();
        cAmonestacion.iniciar();
    }
}
