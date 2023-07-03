/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.MCampeonato;
import Modelo.MEquipo;
import Modelo.MJugador;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author victor janco
 */
public class VJugador extends javax.swing.JFrame {
    private DefaultTableModel modeloJtable;
    private ArrayList<MJugador> listadoJugadores;
    /**
     * Creates new form VJugador
     */
    public VJugador() {
        initComponents();
        listadoJugadores=new ArrayList<>();
        cargarComboBoxCampeonato();
    }
    
    public final void actualizar(){
        MEquipo mEquipo=(MEquipo) cbEquipo.getSelectedItem();
        MJugador mJugador=new MJugador();
        mJugador.setIdEquipo(mEquipo.getId());
        
        String titulos[]={"Ci","Nombre","Apellido","Telefono","Nro"};
        modeloJtable=new DefaultTableModel(null, titulos);
        jTable1.setModel(modeloJtable);
        
        listadoJugadores =mJugador.obtenerJugadores();
        for (int i = 0; i < listadoJugadores.size(); i++) {
            String registro[]={String.valueOf(listadoJugadores.get(i).getCi()),listadoJugadores.get(i).getNombre(),
                               String.valueOf(listadoJugadores.get(i).getApellido()),String.valueOf(listadoJugadores.get(i).getTelefono()),
                               String.valueOf(listadoJugadores.get(i).getNroCamiseta())};
            modeloJtable.addRow(registro);
        }
        limpiar();
    }
    public void limpiar(){
        this.tfCi.setText("");
        this.tfNombre.setText("");
        this.tfApellido.setText("");
        this.tfTelefono.setText("");
        this.tfNroCamiseta.setText("");
    }
    
    public final void cargarComboBoxCampeonato(){
        MCampeonato mCampeonato=new MCampeonato();
        ArrayList<MCampeonato> listado=mCampeonato.obtenerCampeonatos();
        for (int i = 0; i < listado.size(); i++) {
            cbCampeonato.addItem(listado.get(i));
        }
    }
    
    public final void cargarComboBoxEquipo(){
        MCampeonato mCampeonato=(MCampeonato) cbCampeonato.getSelectedItem();
        MEquipo mEquipo=new MEquipo();
        mEquipo.setCodigoCamp(mCampeonato.getCodigo());
        cbEquipo.removeAllItems();
        ArrayList<MEquipo> listado=mEquipo.obtenerEquipos();
        for (int i = 0; i < listado.size(); i++) {
            cbEquipo.addItem(listado.get(i));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfCi = new javax.swing.JTextField();
        tfNombre = new javax.swing.JTextField();
        tfApellido = new javax.swing.JTextField();
        tfNroCamiseta = new javax.swing.JTextField();
        cbEquipo = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cbCampeonato = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfTelefono = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CI");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Nro Camiseta");

        cbEquipo.setActionCommand("comboBoxEquipo");

        btnRegistrar.setText("Registrar");
        btnRegistrar.setActionCommand("REGISTRAR");

        btnModificar.setText("Modificar");
        btnModificar.setActionCommand("MODIFICAR");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ci", "Nombre", "Apellido", "Telefono", "Nro"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        cbCampeonato.setActionCommand("comboBoxCampeonato");

        jLabel5.setText("Telefono");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfCi, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(tfNroCamiseta, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(tfTelefono))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrar))
                    .addComponent(cbEquipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCampeonato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tfCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCampeonato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNroCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar)
                        .addComponent(btnRegistrar)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int f=jTable1.rowAtPoint(evt.getPoint());
        int c=jTable1.columnAtPoint(evt.getPoint());
        
        MJugador mJugadorAux=listadoJugadores.get(f);
        this.tfCi.setText(mJugadorAux.getCi());
        this.tfNombre.setText(mJugadorAux.getNombre());
        this.tfApellido.setText(mJugadorAux.getApellido());
        this.tfTelefono.setText(mJugadorAux.getTelefono());
        this.tfNroCamiseta.setText(String.valueOf(mJugadorAux.getNroCamiseta()));
        
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JComboBox<MCampeonato> cbCampeonato;
    public javax.swing.JComboBox<MEquipo> cbEquipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField tfApellido;
    public javax.swing.JTextField tfCi;
    public javax.swing.JTextField tfNombre;
    public javax.swing.JTextField tfNroCamiseta;
    public javax.swing.JTextField tfTelefono;
    // End of variables declaration//GEN-END:variables
}