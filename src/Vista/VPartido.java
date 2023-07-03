/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Celda;
import Modelo.MCampeonato;
import Modelo.MEquipo;
import Modelo.MPartido;
import Modelo.MVersus;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author victor  janco
 */
public class VPartido extends javax.swing.JFrame {
    private DefaultTableModel modeloEquipos;
    private DefaultTableModel modeloPartidosJugados;
    private DefaultTableModel modeloPartidos;
    
    private ArrayList<MPartido> listaPartidos;
    private ArrayList<MEquipo> listaEquipos;
    ArrayList<Celda> celdas;
    /**
     * Creates new form VPartido
     */
    public VPartido() {
        initComponents();
        celdas=new ArrayList<>();
        actualizarModelJtable();
        cargarComboBoxCampaonatos();
    }
    
    public final void actualizarModelJtable() {
        String titulos[]={"Id","Nombre"};
        modeloEquipos=new DefaultTableModel(null, titulos);
        tableEquipo.setModel(modeloEquipos);
        tableEquipo.getColumnModel().getColumn(0).setPreferredWidth(20);
        
       String titulos2[]={"0","1","2","3","4","5","6","7","8","Puntos"};
       String dato[][]={{"1"," "," "," "," "," "," "," "," "," "},
                        {"2"," "," "," "," "," "," "," "," "," "},
                        {"3"," "," "," "," "," "," "," "," "," "},
                        {"4"," "," "," "," "," "," "," "," "," "},
                        {"5"," "," "," "," "," "," "," "," "," "},
                        {"6"," "," "," "," "," "," "," "," "," "},
                        {"7"," "," "," "," "," "," "," "," "," "},
                        {"8"," "," "," "," "," "," "," "," "," "}};
       modeloPartidosJugados=new DefaultTableModel(dato, titulos2);
       tablePartidoJugados.setModel(modeloPartidosJugados);
    }
    
    public final void cargarComboBoxCampaonatos(){
        MCampeonato mCampeonato=new MCampeonato();
        ArrayList<MCampeonato> listaCamp=mCampeonato.obtenerCampeonatos();
        for (int i = 0; i < listaCamp.size(); i++) {
            cbCampeonato.addItem(listaCamp.get(i));
        }
    }
    
    /**
     * Este metodo cargara los 2 combobox
     * Tambien cargara listara los equipos en un jtable
     */
    public void cargarComboBoxEquipos(){
        MCampeonato mCampeonato= (MCampeonato) cbCampeonato.getSelectedItem();
        MEquipo mEquipo=new MEquipo();
        mEquipo.setCodigoCamp(mCampeonato.getCodigo());
        cbEquipoA.removeAllItems();
        cbEquipoB.removeAllItems();
        ArrayList<MEquipo> listado=mEquipo.obtenerEquipos();
        listaEquipos=listado;  //---------
        
        for (int i = 0; i < listado.size(); i++) {
            cbEquipoA.addItem(listado.get(i));
            cbEquipoB.addItem(listado.get(i));
            String registro[]={String.valueOf(listado.get(i).getId()),listado.get(i).getNombre()};
            modeloEquipos.addRow(registro);
        }
       // Actualizar();
        //cargarModelPartidoJugados();
    }
    
    
    public void cargarModelPartidoJugados(){
       for (int i = 0; i < celdas.size(); i++) {
            modeloPartidosJugados.setValueAt(celdas.get(i).toString(), celdas.get(i).getF(), celdas.get(i).getC());
       }
    }
    
    public final void actualizar(){
        MCampeonato mCampeonato=(MCampeonato) cbCampeonato.getSelectedItem();
        MPartido mPartido=new MPartido();
        mPartido.setCodigoCamp(mCampeonato.getCodigo());
        
        String titulos[]={"Id","Equipo A","vs","Equipo B","Fecha","Estado"};
        modeloPartidos=new DefaultTableModel(null, titulos);
        tablePartido.setModel(modeloPartidos);
        tablePartido.getColumnModel().getColumn(0).setPreferredWidth(20);
        tablePartido.getColumnModel().getColumn(2).setPreferredWidth(20);
        
        celdas=new ArrayList<>();
        listaPartidos =mPartido.obtenerPartidos();
        for (int i = 0; i < listaPartidos.size(); i++) {
            MVersus mVersus1=new MVersus();
                    mVersus1.setIdPartido(listaPartidos.get(i).getId());
            ArrayList<MVersus> listadoVersus=mVersus1.obtenerVersus();
            if("espera".equals(listaPartidos.get(i).getEstado())){
              String registro[]={String.valueOf(listaPartidos.get(i).getId()),listadoVersus.get(0).getNombreEquipo(),
                               "vs",listadoVersus.get(1).getNombreEquipo(),
                               listaPartidos.get(i).getFecha().toString(),listaPartidos.get(i).getEstado()};
               modeloPartidos.addRow(registro);
            }else{
            Celda celda =new Celda( getPosionJtable(listadoVersus.get(0).getNombreEquipo()),
                                    getPosionJtable(listadoVersus.get(1).getNombreEquipo())+1,
                                    listadoVersus.get(0).getGol(),
                                    listadoVersus.get(1).getGol());
            celdas.add(celda);
            }
           // System.out.println(celda.getF()+"-"+celda.getC());
        }
        limpiar();
    }
    public void limpiar(){
        this.tfId.setText("");
        this.dcFecha.setDate(null);
        this.spHora.setValue(0);
        this.spHora.setValue(0);
    }
    
    public int getPosionJtable(String nombreEquipo){
        for (int i = 0; i < listaEquipos.size(); i++) {
            System.out.println(i+"-"+listaEquipos.get(i).getNombre());
            if (listaEquipos.get(i).getNombre().equals(nombreEquipo)) {
                return i;
            }
        }
        return -1;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        cbEquipoA = new javax.swing.JComboBox<>();
        cbEquipoB = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEquipo = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePartidoJugados = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePartido = new javax.swing.JTable();
        spHora = new javax.swing.JSpinner();
        spMinuto = new javax.swing.JSpinner();
        tfId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbCampeonato.setActionCommand("comboBoxCampeonato");

        jLabel1.setText("Id");

        jLabel2.setText("Fecha");

        jLabel3.setText("Hora");

        btnRegistrar.setText("Registrar");
        btnRegistrar.setActionCommand("REGISTRAR");

        tableEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Equipo"
            }
        ));
        jScrollPane1.setViewportView(tableEquipo);

        tablePartidoJugados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "", null, null, null, null, null, null, null, null},
                {"1", null, null, null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "punto"
            }
        ));
        jScrollPane2.setViewportView(tablePartidoJugados);

        tablePartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Equipo", "vs", "Equipo", "Fecha", "Estado"
            }
        ));
        tablePartido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePartidoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablePartido);

        jLabel4.setText("vs");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbCampeonato, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spHora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(spMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCampeonato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRegistrar))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(spHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablePartidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePartidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePartidoMouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JComboBox<MCampeonato> cbCampeonato;
    public javax.swing.JComboBox<MEquipo> cbEquipoA;
    public javax.swing.JComboBox<MEquipo> cbEquipoB;
    public com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JSpinner spHora;
    public javax.swing.JSpinner spMinuto;
    public javax.swing.JTable tableEquipo;
    public javax.swing.JTable tablePartido;
    public javax.swing.JTable tablePartidoJugados;
    public javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables

    
}
