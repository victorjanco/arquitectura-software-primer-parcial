/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MCampeonato;
import Modelo.MTipo;
import Vista.VCampeonato;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 *
 * @author victor janco
 */
public class CCampeonato implements ActionListener {
    private MCampeonato mCampeoanto;
    private VCampeonato vCampeonato;
    
    public CCampeonato(){
        mCampeoanto= new MCampeonato();
        vCampeonato= new VCampeonato();
        this.vCampeonato.btnRegistrar.addActionListener(this);
        this.vCampeonato.btnModificar.addActionListener(this);
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
            default:
                System.out.println(ae.getActionCommand());
                break;
        }
    }
    
    public void iniciar(){
        vCampeonato.setTitle("Gestionar Campeonato");
        vCampeonato.setLocationRelativeTo(null);
        vCampeonato.setVisible(true);
    }
    private void registrar(){
        MTipo mTipo=(MTipo) vCampeonato.cbTipo.getSelectedItem();
        Date dateInicial=new Date(vCampeonato.dcFechaInicio.getDate().getYear(), 
                                  vCampeonato.dcFechaInicio.getDate().getMonth(),
                                  vCampeonato.dcFechaInicio.getDate().getDate());
        Date dateFinal=new Date(vCampeonato.dcFechaFinal.getDate().getYear(), 
                                  vCampeonato.dcFechaFinal.getDate().getMonth(),
                                  vCampeonato.dcFechaFinal.getDate().getDate());
        
        mCampeoanto.setCodigo(Integer.parseInt(vCampeonato.tfCodigo.getText()));
        mCampeoanto.setNombre(vCampeonato.tfNombre.getText());
        mCampeoanto.setTemporada(Integer.parseInt(vCampeonato.tfTemporada.getText()));
        mCampeoanto.setFechaInicio(dateInicial);
        mCampeoanto.setFechaFinal(dateFinal);
        mCampeoanto.setIdTipo(mTipo.getId());
     

        mCampeoanto.insertar();
        vCampeonato.actualizar();
    }

    private void modificar(){
        MTipo mTipo=(MTipo) vCampeonato.cbTipo.getSelectedItem();
        Date dateInicial=new Date(vCampeonato.dcFechaInicio.getDate().getYear(), 
                                  vCampeonato.dcFechaInicio.getDate().getMonth(),
                                  vCampeonato.dcFechaInicio.getDate().getDate());
        Date dateFinal=new Date(vCampeonato.dcFechaFinal.getDate().getYear(), 
                                  vCampeonato.dcFechaFinal.getDate().getMonth(),
                                  vCampeonato.dcFechaFinal.getDate().getDate());
        
        mCampeoanto.setCodigo(Integer.parseInt(vCampeonato.tfCodigo.getText()));
        mCampeoanto.setNombre(vCampeonato.tfNombre.getText());
        mCampeoanto.setTemporada(Integer.parseInt(vCampeonato.tfTemporada.getText()));
        mCampeoanto.setFechaInicio(dateInicial);
        mCampeoanto.setFechaFinal(dateFinal);
        mCampeoanto.setIdTipo(mTipo.getId());
        
        mCampeoanto.modificar();
        vCampeonato.actualizar();
    }
    
    public static void main(String[] args) {
        CCampeonato cCampeonato= new CCampeonato();
        cCampeonato.iniciar();
    }
}
