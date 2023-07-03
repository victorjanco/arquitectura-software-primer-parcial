/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MCampeonato;
import Modelo.MEquipo;
import Vista.VEquipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author victor janco
 */
public class CEquipo implements ActionListener{
   private MEquipo mEquipo;
   private VEquipo vEquipo;
   
   public CEquipo(){
        this.mEquipo = new MEquipo();
        this.vEquipo= new VEquipo();
        this.vEquipo.btnRegistrar.addActionListener(this);
        this.vEquipo.btnModificar.addActionListener(this);
        this.vEquipo.cbCampeonato.addActionListener(this);
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
                  vEquipo.actualizar();
                break;
            default:
                System.out.println(ae.getActionCommand());
                break;
        }
    }
    
   
    public void iniciar(){
        vEquipo.setTitle("Gestionar Equipox");
        vEquipo.setLocationRelativeTo(null);
        vEquipo.setVisible(true);
    }
    private void registrar(){
        
        MCampeonato mCampeonato=(MCampeonato) vEquipo.cbCampeonato.getSelectedItem();
        mEquipo.setCodigoCamp(mCampeonato.getCodigo());
        mEquipo.setNombre(vEquipo.tfNombre.getText());
     
        mEquipo.insertar();
        vEquipo.actualizar();
    }

    private void modificar(){
        mEquipo.setId(Integer.parseInt(vEquipo.labelId.getText()));
        mEquipo.setNombre(vEquipo.tfNombre.getText());
        
        mEquipo.modificar();
        vEquipo.actualizar();
    }

    public static void main(String[] args) {
        CEquipo cEquipo =new CEquipo();
        cEquipo.iniciar();
    }
}
