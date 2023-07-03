/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MTipo;
import Vista.VTipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author victor janco
 */
public class CTipo implements ActionListener{
    private MTipo mTipo;
    private VTipo vTipo;
    
    public CTipo(){
        mTipo= new MTipo();
        vTipo= new VTipo();
        this.vTipo.btnRegistrar.addActionListener(this);
        this.vTipo.btnModificar.addActionListener(this);
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
        vTipo.setTitle("Gestionar Tipo");
        vTipo.setLocationRelativeTo(null);
        vTipo.setVisible(true);
    }
    private void registrar(){
        mTipo.setNombre(vTipo.tfNombre.getText());
        mTipo.insertar();
        vTipo.actualizar();
    }

    private void modificar(){
        mTipo.setId(Integer.valueOf(vTipo.labelId.getText()));
        mTipo.setNombre(vTipo.tfNombre.getText());
        mTipo.modificar();
        vTipo.actualizar();
    }
    
    public static void main(String[] args) {
        CTipo cTipo =new CTipo();
        cTipo.iniciar();
    }
}
