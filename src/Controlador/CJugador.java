/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MEquipo;
import Modelo.MJugador;
import Vista.VJugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author victor janco
 */
public class CJugador implements ActionListener{
    private MJugador mJugador;
    private MEquipo mEquipo;
    private VJugador vJugador;
    
    public CJugador(){
        this.mJugador=new MJugador();
        this.vJugador=new VJugador();
        this.mEquipo=new MEquipo();
        
        this.vJugador.btnRegistrar.addActionListener(this);
        this.vJugador.btnModificar.addActionListener(this);
        this.vJugador.cbEquipo.addActionListener(this);
        this.vJugador.cbCampeonato.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       switch (ae.getActionCommand())
        {
            case "REGISTRAR":
                  registrar();
                break;
            case "MODIFICAR":
                   modificar();
                break;
            case "comboBoxCampeonato":
                  vJugador.cargarComboBoxEquipo();
                break;
            case "comboBoxEquipo":
                  vJugador.actualizar();
                break;
            default:
                System.out.println(ae.getActionCommand());
                break;
        }
    }
    
    public void iniciar(){
        vJugador.setTitle("Gestionar Jugadores");
        vJugador.setLocationRelativeTo(null);
        vJugador.setVisible(true);
    }
    private void registrar(){
        
        mEquipo=(MEquipo) vJugador.cbEquipo.getSelectedItem();
        
        mJugador.setCi(vJugador.tfCi.getText());
        mJugador.setNombre(vJugador.tfNombre.getText());
        mJugador.setApellido(vJugador.tfApellido.getText());
        mJugador.setTelefono(vJugador.tfTelefono.getText());
        mJugador.setNroCamiseta(Integer.parseInt(vJugador.tfNroCamiseta.getText()));
        mJugador.setIdEquipo(mEquipo.getId());
     
        mJugador.insertar();
        vJugador.actualizar();
    }

    private void modificar(){
        mEquipo=(MEquipo) vJugador.cbEquipo.getSelectedItem();
        
        mJugador.setCi(vJugador.tfCi.getText());
        mJugador.setNombre(vJugador.tfNombre.getText());
        mJugador.setApellido(vJugador.tfApellido.getText());
        mJugador.setTelefono(vJugador.tfTelefono.getText());
        mJugador.setNroCamiseta(Integer.parseInt(vJugador.tfNroCamiseta.getText()));
        mJugador.setIdEquipo(mEquipo.getId());
        
        mJugador.modificar();
        vJugador.actualizar();
    }
    public static void main(String[] args) {
        CJugador cJugador=new CJugador();
        cJugador.iniciar();
    }
}
