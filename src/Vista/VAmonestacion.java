/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.MAmonestacion;
import Modelo.MCampeonato;
import Modelo.MJugador;
import Modelo.MPartido;
import Modelo.MVersus;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author victor janco
 */
public class VAmonestacion extends javax.swing.JFrame {
    private DefaultTableModel modeloAmonestados;

    private ArrayList<ArrayList<MVersus>> listaPartidos;
    private ArrayList<MJugador> listaJugadoresEquipoA;
    private ArrayList<MJugador> listaJugadoresEquipoB;
    /**
     * Creates new form VAmonestacio
     */
    public VAmonestacion() {
        initComponents();
        this.listaPartidos=new ArrayList<>();
        this.listaJugadoresEquipoA=new ArrayList<>();
        this.listaJugadoresEquipoB=new ArrayList<>();
        
        actualizarJtable();
        cargarComboBoxCampaonatos();
    }
    public final void actualizarJtable(){
        String titulos[]={"Ci","Nombre","TA","TR","Gol","Equipo"};
        modeloAmonestados=new DefaultTableModel(null, titulos);
        jTable1.setModel(modeloAmonestados);
    }
    public final void cargarComboBoxCampaonatos(){
        MCampeonato mCampeonato =new MCampeonato();
        ArrayList<MCampeonato> listaCamp=mCampeonato.obtenerCampeonatos();
        for (int i = 0; i < listaCamp.size(); i++) {
            cbCampeonato.addItem(listaCamp.get(i));
        }
    }
    
    public void cargarComboBoxPartidos(){
        MCampeonato mCampeonato=(MCampeonato) cbCampeonato.getSelectedItem();
        MPartido mPartido=new MPartido();
        mPartido.setCodigoCamp(mCampeonato.getCodigo());
        
        cbPartido.removeAllItems();
        ArrayList<MPartido> listado=mPartido.obtenerPartidos();
        
        for (int i = 0; i < listado.size(); i++) {
            MVersus mVersus=new MVersus();
            mVersus.setIdPartido(listado.get(i).getId());
            ArrayList<MVersus> listaVersus= mVersus.obtenerVersus();
            String partido=listado.get(i).getId()+": ("+listaVersus.get(0).getNombreEquipo()+")vs("+listaVersus.get(1).getNombreEquipo()+")";
            cbPartido.addItem(partido);
            listaPartidos.add(listaVersus);
        }
        System.out.println(listaPartidos.size());
    }
    
    public void cargarComboBoxJugadores(){
        
     if(!listaPartidos.isEmpty()){
        ArrayList<MVersus> listaVersus= listaPartidos.get(cbPartido.getSelectedIndex());
        lbEquipoA.setText(listaVersus.get(0).getNombreEquipo());
        lbEquipoB.setText(listaVersus.get(1).getNombreEquipo());
        lbIdPartido.setText(String.valueOf(listaVersus.get(0).getIdPartido()));
        
        MJugador mJugadorA=new MJugador();
        mJugadorA.setIdEquipo(listaVersus.get(0).getIdEquipo());
        listaJugadoresEquipoA=mJugadorA.obtenerJugadores();
        
        MJugador mJugadorB=new MJugador();
        mJugadorB.setIdEquipo(listaVersus.get(1).getIdEquipo());
        listaJugadoresEquipoB=mJugadorB.obtenerJugadores();
        
        for (int i = 0; i < listaJugadoresEquipoA.size(); i++) {
            cbJugadoresA.addItem(listaJugadoresEquipoA.get(i).toString());
            cbJugadoresB.addItem(listaJugadoresEquipoB.get(i).toString());
        }
     }
     actualizarJtable();
    }
    
    public void amonestarJugadorA(){
        if(!listaJugadoresEquipoA.isEmpty()){
        MJugador mJugadorA=listaJugadoresEquipoA.get(cbJugadoresA.getSelectedIndex());
            String registro[]={mJugadorA.getCi(),mJugadorA.getNombre(),"0","0","0",lbEquipoA.getText()};
            modeloAmonestados.addRow(registro);
        }
    }
    public void amonestarJugadorB(){
        if(!listaJugadoresEquipoB.isEmpty()){
        MJugador mJugadorA=listaJugadoresEquipoB.get(cbJugadoresB.getSelectedIndex());
            String registro[]={mJugadorA.getCi(),mJugadorA.getNombre(),"0","0","0",lbEquipoB.getText()};
            modeloAmonestados.addRow(registro);
        }
    }
    
    public ArrayList<MAmonestacion> listAmonestaciones(){
        ArrayList<MAmonestacion> listaAmonestados=new ArrayList<>();
        
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            MAmonestacion mAmonestacion=new MAmonestacion();
            mAmonestacion.setIdPartido(Integer.parseInt(lbIdPartido.getText()));
            mAmonestacion.setCiJugador((String) jTable1.getValueAt(i, 0));
            mAmonestacion.setTa(Integer.parseInt(jTable1.getValueAt(i, 2).toString()));
            mAmonestacion.setTr(Integer.parseInt(jTable1.getValueAt(i, 3).toString()));
            mAmonestacion.setGol(Integer.parseInt(jTable1.getValueAt(i, 4).toString()));
            listaAmonestados.add(mAmonestacion);
        }
        return listaAmonestados;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbCampeonato = new javax.swing.JComboBox<>();
        cbPartido = new javax.swing.JComboBox<>();
        lbEquipoA = new javax.swing.JLabel();
        lbEquipoB = new javax.swing.JLabel();
        tfGolEquipoA = new javax.swing.JTextField();
        tfGolEquipoB = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbJugadoresA = new javax.swing.JComboBox<>();
        cbJugadoresB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbIdPartido = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbCampeonato.setActionCommand("comboBoxCampeonato");

        cbPartido.setActionCommand("comboBoxPartidos");

        lbEquipoA.setText("EquipoA");

        lbEquipoB.setText("EquipoB");

        jLabel3.setText("vs");

        cbJugadoresA.setActionCommand("comboBoxJugadoresA");

        cbJugadoresB.setActionCommand("comboBoxJugadoresB");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Ci", "Nombre", "TA", "TR", "Gol", "Equipo"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        lbIdPartido.setText("id");

        btnGuardar.setText("Guardar");
        btnGuardar.setActionCommand("GUARDAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbIdPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbEquipoA)
                                        .addGap(29, 29, 29)
                                        .addComponent(tfGolEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbJugadoresA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfGolEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbEquipoB))
                                    .addComponent(cbJugadoresB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(142, 142, 142))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbCampeonato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29)
                                .addComponent(cbPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)))))
                .addComponent(btnGuardar)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCampeonato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEquipoA)
                            .addComponent(lbEquipoB)
                            .addComponent(tfGolEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGolEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lbIdPartido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbJugadoresA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbJugadoresB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox<MCampeonato> cbCampeonato;
    public javax.swing.JComboBox<String> cbJugadoresA;
    public javax.swing.JComboBox<String> cbJugadoresB;
    public javax.swing.JComboBox<String> cbPartido;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JLabel lbEquipoA;
    public javax.swing.JLabel lbEquipoB;
    public javax.swing.JLabel lbIdPartido;
    public javax.swing.JTextField tfGolEquipoA;
    public javax.swing.JTextField tfGolEquipoB;
    // End of variables declaration//GEN-END:variables
}
