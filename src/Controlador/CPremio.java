/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MCampeonato;
import Modelo.MPremio;
import Vista.VPremio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author victor janco
 */
public class CPremio implements ActionListener{
    private MPremio mPremio;
    private VPremio vPremio;
    
    public CPremio(){
        this.mPremio = new MPremio();
        vPremio= new VPremio();
        this.vPremio.btnRegistrar.addActionListener(this);
        this.vPremio.btnModificar.addActionListener(this);
        this.vPremio.cbCampeonato.addActionListener(this);
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
            case "comboBoxChanged":
                  vPremio.actualizar();
                break;
            default:
                System.out.println(ae.getActionCommand());
                break;
        }
    }
    
    
    public void iniciar(){
        vPremio.setTitle("Gestionar Premio");
        vPremio.setLocationRelativeTo(null);
        vPremio.setVisible(true);
    }
    private void registrar(){
        MCampeonato mCampeonato=(MCampeonato) vPremio.cbCampeonato.getSelectedItem();
        mPremio.setCodigoCamp(mCampeonato.getCodigo());
        mPremio.setNombre(vPremio.tfNombre.getText());
        mPremio.setDescripcion(vPremio.tfDescripcion.getText());
     
        mPremio.insertar();
        vPremio.actualizar();
    }

    private void modificar(){
        mPremio.setId(Integer.parseInt(vPremio.labeld.getText()));
        mPremio.setNombre(vPremio.tfNombre.getText());
        mPremio.setDescripcion(vPremio.tfDescripcion.getText());
        
        mPremio.modificar();
        vPremio.actualizar();
    }
    
    public static void main(String[] args) {
        CPremio cPremio =new CPremio();
        cPremio.iniciar();
    }
   
}
