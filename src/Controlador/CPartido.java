/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.MCampeonato;
import Modelo.MEquipo;
import Modelo.MPartido;
import Modelo.MVersus;
import Vista.VPartido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author victor janco
 */
public class CPartido implements ActionListener {
    private MPartido mPartido;
    private VPartido vPartido;
    private MVersus mVersus;
    
    public CPartido(){
        this.mPartido= new MPartido();
        this.vPartido= new VPartido();
        this.mVersus= new MVersus();
        this.vPartido.btnRegistrar.addActionListener(this);
        this.vPartido.cbCampeonato.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch (e.getActionCommand())
        {
            case "REGISTRAR":
                 registrar();
                 vPartido.actualizar();
                break;
            case "comboBoxCampeonato":
                  vPartido.cargarComboBoxEquipos();
                  vPartido.actualizar();
                  vPartido.cargarModelPartidoJugados();
                break;
            default:
                System.out.println(e.getActionCommand());
                break;
        }
    }
    
     public void iniciar(){
        vPartido.setTitle("Generar Partidos");
        vPartido.setLocationRelativeTo(null);
        vPartido.setVisible(true);
    }
  private void registrar(){
        MCampeonato mCampeonato=(MCampeonato) vPartido.cbCampeonato.getSelectedItem();
        System.out.println(mCampeonato.getCodigo());
        
        Date date=new Date(vPartido.dcFecha.getDate().getYear(), 
                                  vPartido.dcFecha.getDate().getMonth(),
                                  vPartido.dcFecha.getDate().getDate());
        Time hora=new Time(Integer.parseInt(vPartido.spHora.getValue().toString()),Integer.parseInt(vPartido.spMinuto.getValue().toString()), 0);
        
        mPartido.setId(Integer.parseInt(vPartido.tfId.getText()));
        mPartido.setFecha(date);
        mPartido.setHora(hora);
        mPartido.setEstado("espera");
        mPartido.setCodigoCamp(mCampeonato.getCodigo());
        mPartido.insertar();
        
        MEquipo mEquipo=(MEquipo) vPartido.cbEquipoA.getSelectedItem();
        mVersus=new MVersus();
        mVersus.setIdPartido(Integer.parseInt(vPartido.tfId.getText()));
        mVersus.setIdEquipo(mEquipo.getId());
        mVersus.insertar();
        
        mEquipo=(MEquipo) vPartido.cbEquipoB.getSelectedItem();
        mVersus=new MVersus();
        mVersus.setIdPartido(Integer.parseInt(vPartido.tfId.getText()));
        mVersus.setIdEquipo(mEquipo.getId());
        mVersus.insertar();
    }
 
    public static void main(String[] args) {
        CPartido cPartido=new CPartido();
        cPartido.iniciar();
    }
}
