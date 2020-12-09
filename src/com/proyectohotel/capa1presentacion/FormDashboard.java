/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa1presentacion;

import com.proyectohotel.capa1_presentacion.fonts.font;
import com.proyectohotel.capa1_presentacion.util.Mensaje;
import com.proyectohotel.capa1_presentacion.util.CellRenderTable;
import com.proyectohotel.capa2_aplicacion.servicios.CierreDeEstadiaServicio;
import com.proyectohotel.capa2_aplicacion.servicios.RegistroClienteServicio;
import com.proyectohotel.capa2_aplicacion.servicios.RegistroHospedajeServicio;
import com.proyectohotel.capa2_aplicacion.servicios.ReporteHospedajeServicio;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa3_dominio.entidades.TipoDocumento;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author josel
 */
public class FormDashboard extends javax.swing.JFrame {
    
    private Cliente cliente;
    font tipoFuente;
    private RegistroDeHabitacion registroDeHabitacion;
    private Habitacion habitacion;
    private String numeroIdentidad=null;
    
    public FormDashboard() {
        initComponents();
       //  CERRAR ESTADIA CLIENTE :
      //  1)CODIGO DE RESEVA , DIFERENCIAR EL ULTIMO REGISTRO
     //   2)COSTO DE HABITACION TOMANDO EN CUENTA LA HORA , MES REFERENTE AL CLIENTE QUE CIERRA SU ESTADIA.
     //   OTROS:
     //   1)REFACTORIZACION

       
       itemTipoHabitacion.setModel(mostrarTipoHabitaciones());
       //DESCOMENTAR ESTA PARTE BRUNO CUANDO SOLUCIONAS EL POSTGRE
      refreshTable();    
       //-----------------------------------
         mostrarAcumuladoresDeEstado(null);
        setVisible(true);
        setLocationRelativeTo(null);
        fontsFamily();
        //mostrar tipos de documentos ..
        cbxTipoDeDocumentoCliente.setModel(mostrarTipoDocumento());
        
    }
    public void limpiarRegistroCliente(){
        txtNombreCliente.setText("");
        txtApellidoCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefonoCliente.setText("");
        txtNumeroIdentidadCliente.setText("");
        cbxNacionalidadCliente.setSelectedIndex(0);
        cbxTipoDeDocumentoCliente.setSelectedIndex(0);
    }
    public void limpiarReservaHabitacion(){
        txtDocumentoIdentidad.setText("");
        txtnombre.setText("");
        txtfono.setText("");
        txttipodocumento.setText("");
        txtcorreo.setText("");
        txtnacionalidad.setText("");
        itemTipoHabitacion.setSelectedIndex(0);
        txtCosto.setText("");
    }
    public void limpiarCierreDeEstadia(){
        labelNombreApellido.setText("");
        txtFechaHospedaje.setText("");
        labelNumeroIdentidad.setText("");
        txtPisoHabitacion.setText("");
        txtNumeroHabitacion.setText("");
        labelTipoHabitacion.setText("");
        labelCostoHabitacion.setText("");
    }
            
     public void fontsFamily(){
         tipoFuente=new font();
        jLabel2.setFont(tipoFuente.fuente(tipoFuente.MONTSERRAT,1,18));
        jLabel4.setFont(tipoFuente.fuente(tipoFuente.MONTSERRAT,1,18));
        jLabel5.setFont(tipoFuente.fuente(tipoFuente.MONTSERRAT,1,18));
        pp4.setFont(tipoFuente.fuente(tipoFuente.MONTSERRAT,1,18));
        labelUsuario.setFont(tipoFuente.fuente(tipoFuente.MONTSERRAT, 1, 20));
     }
     public void refreshTable(){
           listarHabitaciones(tableReservaHabitacion,null);
       tamañoColumnas(tableReservaHabitacion);
         labelCosto.setVisible(false);
         txtCosto.setVisible(false);
     }
     public void listarHabitaciones(JTable table,String tipoHabitacion){
          JButton btn1 = new JButton("Registrar Habitacion");
          String[] encabezado={"Nro","Habitacion","Estado",
                      "Piso","Accion"};
         RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
        JTableHeader tableHeader = tableReservaHabitacion.getTableHeader();
        tableHeader.setBackground(java.awt.Color.orange);
       tableHeader.setForeground(java.awt.Color.white);
        DefaultTableModel model = new DefaultTableModel(null,encabezado){
          @Override
          public boolean isCellEditable(int row,int column){
             return false; 
          }
        };
       table.setModel(model);
       tableReservaHabitacion.setDefaultRenderer(Object.class, new CellRenderTable());
       List<Habitacion> listadoHabitaciones;
     try {
       if(tipoHabitacion == null){
           listadoHabitaciones = registroHospedajeService.mostrarHabitaciones(null);
       }else{
          listadoHabitaciones = registroHospedajeService.mostrarHabitaciones(tipoHabitacion);
       }
            Object[] datos = new Object[5];
          for(int i=0;i<listadoHabitaciones.size();i++){
              datos[0] = i+1;
              datos[1] = listadoHabitaciones.get(i).getNumeroHabitacion();
              datos[2] = listadoHabitaciones.get(i).getEstado();
              datos[3]=  listadoHabitaciones.get(i).getNumeroDePiso();
              datos[4] =btn1;
              model.addRow(datos);
          }
        } catch (Exception ex) {
            System.out.println(ex);
        }
     }
     
     /* 
       Author :  Jose
     */
    
     public DefaultComboBoxModel mostrarTipoHabitaciones(){
            DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
         try{
              RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
          
             comboBox.addElement("Seleccione Habitacion:");
             for(int i=0;i<registroHospedajeService.mostrarTiposHabitaciones().size();i++){
                 comboBox.addElement(registroHospedajeService.mostrarTiposHabitaciones().get(i).getDescripcion());
             }
         }catch(Exception ex){
             System.out.println(ex);
         }
         return comboBox;
     }
     
     
     public void mostrarCostoHabitacion(String tipoHabitacion){
         labelCosto.setVisible(true);
         txtCosto.setVisible(true);
         try{
              RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
              double costo = registroHospedajeService.mostrarCostoHabitacion(tipoHabitacion);
              txtCosto.setText(String.valueOf(costo));
         }catch(Exception ex){
               System.out.println(ex);
         }
     }
    /* 
        Author :  Jose
    */
   public void mostrarAcumuladoresDeEstado(String tipoHabitacion){
      try{
          RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
           Map dataHabitaciones = registroHospedajeService.mostrarTotalDeHabitacionesDeEstado(tipoHabitacion);
           labelHabitacionesDisponibles.setText(dataHabitaciones.get("disponibles").toString());
          labelHabitacionesOcupadas.setText(dataHabitaciones.get("ocupadas").toString());
      }catch(Exception ex){
           System.out.println(ex);
      }
    }
   /* 
            Author :  Jose
        */
   public void tamañoColumnas(JTable table){
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(50);  
        columnModel.getColumn(4).setPreferredWidth(200);  

    }
public String getFecha(java.util.Date fecha)
    {
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(fecha);
    }

    public DefaultComboBoxModel mostrarTipoDocumento(){
          DefaultComboBoxModel item = new DefaultComboBoxModel();
        try{
               RegistroClienteServicio registroClienteServicio = new RegistroClienteServicio();
             
               for(int i=0;i<registroClienteServicio.listarTipoDocumento().size();i++){
                   item.addElement(registroClienteServicio.listarTipoDocumento().get(i).getDocumentoId()+"-"+registroClienteServicio.listarTipoDocumento().get(i).getDescripcion());
               }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return item;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pp1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pp2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pp3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        pp4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel_centre = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JLabel();
        txtfono = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JLabel();
        txttipodocumento = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtnacionalidad = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        labelHabitacionesDisponibles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableReservaHabitacion = new javax.swing.JTable();
        txtDocumentoIdentidad = new javax.swing.JTextField();
        itemTipoHabitacion = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        labelHabitacionesOcupadas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JLabel();
        labelCosto = new javax.swing.JLabel();
        txtCosto = new javax.swing.JLabel();
        p2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnReporteHospedaje = new javax.swing.JButton();
        btnReporteCliente = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        fechaInicial = new com.toedter.calendar.JDateChooser();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        jLabel45 = new javax.swing.JLabel();
        itemAnyo = new javax.swing.JComboBox<>();
        p3 = new javax.swing.JPanel();
        btnCierreEstadia = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        labelPisoHabitacion = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtFechaHospedaje = new javax.swing.JLabel();
        txtPisoHabitacion = new javax.swing.JLabel();
        labelTipoHabitacion = new javax.swing.JLabel();
        txtNumeroHabitacion = new javax.swing.JLabel();
        labelCostoHabitacion = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        labelNombreApellido = new javax.swing.JLabel();
        jlabelidentidad = new javax.swing.JLabel();
        labelNumeroIdentidad = new javax.swing.JLabel();
        btnGenerarBoleta = new javax.swing.JLabel();
        btnLimpiarCierreEstadia = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        cbxTipoDeDocumentoCliente = new javax.swing.JComboBox<>();
        txtNumeroIdentidadCliente = new javax.swing.JTextField();
        cbxNacionalidadCliente = new javax.swing.JComboBox<>();
        btnRegistrarCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pp1.setBackground(new java.awt.Color(153, 95, 32));
        pp1.setMinimumSize(new java.awt.Dimension(300, 50));
        pp1.setPreferredSize(new java.awt.Dimension(300, 50));
        pp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REGISTRO DE HABITACIONES");

        javax.swing.GroupLayout pp1Layout = new javax.swing.GroupLayout(pp1);
        pp1.setLayout(pp1Layout);
        pp1Layout.setHorizontalGroup(
            pp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pp1Layout.setVerticalGroup(
            pp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(pp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, -1));

        pp2.setBackground(new java.awt.Color(153, 95, 32));
        pp2.setMinimumSize(new java.awt.Dimension(300, 50));
        pp2.setPreferredSize(new java.awt.Dimension(300, 50));
        pp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("REPORTES DE HOSPEDAJES");

        javax.swing.GroupLayout pp2Layout = new javax.swing.GroupLayout(pp2);
        pp2.setLayout(pp2Layout);
        pp2Layout.setHorizontalGroup(
            pp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pp2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pp2Layout.setVerticalGroup(
            pp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pp2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(pp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));

        pp3.setBackground(new java.awt.Color(153, 95, 32));
        pp3.setMinimumSize(new java.awt.Dimension(300, 50));
        pp3.setPreferredSize(new java.awt.Dimension(300, 50));
        pp3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CERRAR ESTADIA");

        javax.swing.GroupLayout pp3Layout = new javax.swing.GroupLayout(pp3);
        pp3.setLayout(pp3Layout);
        pp3Layout.setHorizontalGroup(
            pp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pp3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel5)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        pp3Layout.setVerticalGroup(
            pp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pp3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(pp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        jPanel7.setBackground(new java.awt.Color(153, 95, 32));
        jPanel7.setMaximumSize(new java.awt.Dimension(300, 50));
        jPanel7.setMinimumSize(new java.awt.Dimension(300, 50));
        jPanel7.setName(""); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(300, 50));

        pp4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        pp4.setForeground(new java.awt.Color(255, 255, 255));
        pp4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pp4.setText("REGISTRO DE CLIENTES");
        pp4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pp4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pp4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pp4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/proyectohotel/capa1_presentacion/icons/pruebita.gif"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(124, 117));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 160, 140));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/proyectohotel/capa1_presentacion/icons/boton-salir-a-la-aplicacion.png"))); // NOI18N
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setText("LOREM IMPSUN");
        jPanel1.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 190, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/proyectohotel/capa1_presentacion/image/hotel.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 640));

        panel_centre.setLayout(new java.awt.CardLayout());

        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.setMinimumSize(new java.awt.Dimension(600, 650));
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mouseclick(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(208, 145, 94));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel25.setText("NOMBRE Y APELLIDO:");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel11.setText("TELEFONO:");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel12.setText("CORREO:");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel13.setText("TIPO_DOCUMENTO:");

        txtnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtnombre.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtnombre.setForeground(new java.awt.Color(255, 255, 255));
        txtnombre.setText("------");

        txtfono.setBackground(new java.awt.Color(255, 255, 255));
        txtfono.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtfono.setForeground(new java.awt.Color(255, 255, 255));
        txtfono.setText("--------");

        txtcorreo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtcorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtcorreo.setText("------");

        txttipodocumento.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txttipodocumento.setForeground(new java.awt.Color(255, 255, 255));
        txttipodocumento.setText("---");

        jLabel18.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel18.setText("NACIONALIDAD:");

        txtnacionalidad.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtnacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        txtnacionalidad.setText("---");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttipodocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtnacionalidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(txtfono)
                    .addComponent(txttipodocumento))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcorreo)
                    .addComponent(jLabel18)
                    .addComponent(txtnacionalidad))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnBuscarCliente.setBackground(new java.awt.Color(149, 95, 32));
        btnBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("BUSCAR CLIENTES");

        javax.swing.GroupLayout btnBuscarClienteLayout = new javax.swing.GroupLayout(btnBuscarCliente);
        btnBuscarCliente.setLayout(btnBuscarClienteLayout);
        btnBuscarClienteLayout.setHorizontalGroup(
            btnBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnBuscarClienteLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        btnBuscarClienteLayout.setVerticalGroup(
            btnBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBuscarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel22.setText("Tipo Habitacion:");

        jLabel23.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel23.setText("<html>\n<p>Total de Habitaciones</br> Disponibles</p>\n</html>");

        labelHabitacionesDisponibles.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        labelHabitacionesDisponibles.setText("--");

        tableReservaHabitacion.setBackground(new java.awt.Color(208, 145, 94));
        tableReservaHabitacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tableReservaHabitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Habitacion", "Estado", "  "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableReservaHabitacion.setGridColor(new java.awt.Color(153, 153, 153));
        tableReservaHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableReservaHabitacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableReservaHabitacion);

        txtDocumentoIdentidad.setBackground(new java.awt.Color(163, 151, 123));
        txtDocumentoIdentidad.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtDocumentoIdentidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoIdentidadActionPerformed(evt);
            }
        });
        txtDocumentoIdentidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoIdentidadKeyTyped(evt);
            }
        });

        itemTipoHabitacion.setBackground(new java.awt.Color(194, 101, 101));
        itemTipoHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE TIPO HABITACION:", "Habitacion indivual", "Habitacion presidencial", "Habitacion de 3 personas", " " }));
        itemTipoHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemTipoHabitacionMouseClicked(evt);
            }
        });

        jLabel43.setText("<html> <p>Total de Habitaciones</br> Ocupadas</p> </html>");

        labelHabitacionesOcupadas.setText("--");

        jPanel3.setBackground(new java.awt.Color(149, 95, 32));

        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscar.setText("Buscar");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(149, 95, 32));

        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setText("Refrescar");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLimpiarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        labelCosto.setText("Costo de habitacion:");

        txtCosto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCosto.setText("--");

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p1Layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(p1Layout.createSequentialGroup()
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(58, 58, 58)
                                .addComponent(txtDocumentoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(p1Layout.createSequentialGroup()
                                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(p1Layout.createSequentialGroup()
                                        .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(itemTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(p1Layout.createSequentialGroup()
                                                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(p1Layout.createSequentialGroup()
                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(labelHabitacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(p1Layout.createSequentialGroup()
                                                        .addComponent(labelCosto)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(42, 42, 42)
                                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelHabitacionesOcupadas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDocumentoIdentidad))
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCosto)
                    .addComponent(labelCosto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                        .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelHabitacionesDisponibles)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelHabitacionesOcupadas))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        panel_centre.add(p1, "card2");
        p1.getAccessibleContext().setAccessibleName("");

        p2.setBackground(new java.awt.Color(255, 255, 255));
        p2.setMinimumSize(new java.awt.Dimension(600, 650));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Reportes de clientes de las habitaciones ocupadas y disponibles");
        jLabel6.setPreferredSize(new java.awt.Dimension(480, 18));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("Reporte de Clientes Hospedados ");
        jLabel21.setPreferredSize(new java.awt.Dimension(271, 21));

        btnReporteHospedaje.setBackground(new java.awt.Color(208, 145, 94));
        btnReporteHospedaje.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnReporteHospedaje.setText("REPORTE DE  HOSPEDAJES");
        btnReporteHospedaje.setPreferredSize(new java.awt.Dimension(226, 51));
        btnReporteHospedaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteHospedajeActionPerformed(evt);
            }
        });

        btnReporteCliente.setBackground(new java.awt.Color(208, 145, 94));
        btnReporteCliente.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnReporteCliente.setText("REPORTE DE CLIENTES");
        btnReporteCliente.setPreferredSize(new java.awt.Dimension(226, 51));
        btnReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteClienteActionPerformed(evt);
            }
        });
        btnReporteCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnReporteClienteKeyPressed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(163, 151, 123));
        jPanel5.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.setMaximumSize(new java.awt.Dimension(524, 68));
        jPanel5.setMinimumSize(new java.awt.Dimension(524, 68));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("Fecha 1:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Hasta");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setText("Fecha 2:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(fechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel10)
                .addGap(32, 32, 32)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel26)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel45.setText("Seleccione Año de Reporte");

        itemAnyo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCION DE AÑO", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010" }));

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p2Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p2Layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(jLabel45)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p2Layout.createSequentialGroup()
                            .addGap(192, 192, 192)
                            .addComponent(btnReporteHospedaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 201, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2Layout.createSequentialGroup()
                        .addComponent(btnReporteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))))
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(itemAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(btnReporteHospedaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(btnReporteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        panel_centre.add(p2, "card3");

        p3.setBackground(new java.awt.Color(255, 255, 255));
        p3.setMinimumSize(new java.awt.Dimension(600, 650));
        p3.setPreferredSize(new java.awt.Dimension(600, 650));

        btnCierreEstadia.setBackground(new java.awt.Color(187, 115, 115));
        btnCierreEstadia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCierreEstadiaMousePressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("CERRAR ESTADIA DE CLIENTE");

        javax.swing.GroupLayout btnCierreEstadiaLayout = new javax.swing.GroupLayout(btnCierreEstadia);
        btnCierreEstadia.setLayout(btnCierreEstadiaLayout);
        btnCierreEstadiaLayout.setHorizontalGroup(
            btnCierreEstadiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCierreEstadiaLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(56, 56, 56))
        );
        btnCierreEstadiaLayout.setVerticalGroup(
            btnCierreEstadiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCierreEstadiaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel27)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(208, 145, 94));

        jLabel28.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Fecha de Hospedaje");

        labelPisoHabitacion.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        labelPisoHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        labelPisoHabitacion.setText("Piso de Habitacion");

        jLabel31.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Numero Habitacion");

        jLabel32.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Tipo de Habitacion");

        jLabel33.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Costo");

        txtFechaHospedaje.setForeground(new java.awt.Color(51, 0, 204));
        txtFechaHospedaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFechaHospedaje.setText("----");

        txtPisoHabitacion.setForeground(new java.awt.Color(51, 0, 204));
        txtPisoHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPisoHabitacion.setText("----");

        labelTipoHabitacion.setForeground(new java.awt.Color(51, 0, 204));
        labelTipoHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoHabitacion.setText("---");

        txtNumeroHabitacion.setForeground(new java.awt.Color(51, 0, 204));
        txtNumeroHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumeroHabitacion.setText("---");

        labelCostoHabitacion.setForeground(new java.awt.Color(51, 0, 204));
        labelCostoHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCostoHabitacion.setText("---");

        jLabel14.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nombre y Apellido:");

        labelNombreApellido.setForeground(new java.awt.Color(0, 51, 255));
        labelNombreApellido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNombreApellido.setText("----");

        jlabelidentidad.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jlabelidentidad.setForeground(new java.awt.Color(255, 255, 255));
        jlabelidentidad.setText("Numero de Identidad:");

        labelNumeroIdentidad.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelNumeroIdentidad.setForeground(new java.awt.Color(255, 0, 0));
        labelNumeroIdentidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNumeroIdentidad.setText("--");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(32, 32, 32)
                        .addComponent(labelTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCostoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelPisoHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombreApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPisoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaHospedaje, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jlabelidentidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelNumeroIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumeroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(labelNombreApellido))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelidentidad)
                    .addComponent(labelNumeroIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtFechaHospedaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPisoHabitacion)
                    .addComponent(txtPisoHabitacion)
                    .addComponent(jLabel31)
                    .addComponent(txtNumeroHabitacion))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(labelTipoHabitacion)
                    .addComponent(jLabel33)
                    .addComponent(labelCostoHabitacion))
                .addGap(51, 51, 51))
        );

        btnGenerarBoleta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGenerarBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/proyectohotel/capa1_presentacion/icons/portapapeles.png"))); // NOI18N
        btnGenerarBoleta.setText("Generar Boleta");
        btnGenerarBoleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGenerarBoletaMousePressed(evt);
            }
        });

        btnLimpiarCierreEstadia.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnLimpiarCierreEstadia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/proyectohotel/capa1_presentacion/icons/escoba.png"))); // NOI18N
        btnLimpiarCierreEstadia.setText("Limpiar campos");
        btnLimpiarCierreEstadia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarCierreEstadiaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addGroup(p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(p3Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(btnGenerarBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(p3Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btnCierreEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnLimpiarCierreEstadia)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnCierreEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerarBoleta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnLimpiarCierreEstadia)
                .addGap(60, 60, 60))
        );

        panel_centre.add(p3, "card4");

        p4.setBackground(new java.awt.Color(255, 255, 255));
        p4.setMinimumSize(new java.awt.Dimension(600, 650));
        p4.setPreferredSize(new java.awt.Dimension(600, 650));

        jPanel8.setBackground(new java.awt.Color(149, 95, 32));
        jPanel8.setMinimumSize(new java.awt.Dimension(530, 575));
        jPanel8.setPreferredSize(new java.awt.Dimension(530, 575));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 2, 36)); // NOI18N
        jLabel9.setText("REGISTRO DE CLIENTES");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nombre");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Apelido");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Correo");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tipo de Documento");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Nacionalidad");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Numero de Identidad");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Telefono");

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtApellidoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoClienteKeyTyped(evt);
            }
        });

        txtCorreoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyTyped(evt);
            }
        });

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyTyped(evt);
            }
        });

        cbxTipoDeDocumentoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CARNET DE EXTRANJERIA" }));

        txtNumeroIdentidadCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroIdentidadClienteKeyTyped(evt);
            }
        });

        cbxNacionalidadCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PERUANO", "CHILENO", "URUGUAYO", "ARGENTINO", "BOLIVIANO", "COLOMBIANO", "ITALIANO" }));

        btnRegistrarCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/proyectohotel/capa1_presentacion/icons/disquete.png"))); // NOI18N
        btnRegistrarCliente.setText("Registrar cliente");
        btnRegistrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel9))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel17)
                                                .addComponent(jLabel16)))
                                        .addGap(70, 70, 70))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(74, 74, 74))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel19)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroIdentidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombreCliente)
                                .addComponent(txtCorreoCliente)
                                .addComponent(txtTelefonoCliente)
                                .addComponent(txtApellidoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                .addComponent(cbxTipoDeDocumentoCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnRegistrarCliente))
                            .addComponent(cbxNacionalidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addGap(32, 32, 32)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel17))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel30))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(cbxTipoDeDocumentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxNacionalidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(43, 43, 43)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroIdentidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(56, 56, 56)
                .addComponent(btnRegistrarCliente)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panel_centre.add(p4, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_centre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panel_centre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseclicked
        if(evt.getSource()==pp1){
            p1.setVisible(true);
            p2.setVisible(false);
            p3.setVisible(false);
             p4.setVisible(false);
        }
        if(evt.getSource()==pp2){
            p1.setVisible(false);
            p2.setVisible(true);
            p3.setVisible(false);
             p4.setVisible(false);
        }
        if(evt.getSource()==pp3){
            p1.setVisible(false);
            p2.setVisible(false);
            p3.setVisible(true);
             p4.setVisible(false);
        }
        if(evt.getSource()==pp4){
            p1.setVisible(false);
            p2.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(true);
            
        }
    }//GEN-LAST:event_mouseclicked

    private void Mouseclick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mouseclick
        // TODO add your handling code here:
    }//GEN-LAST:event_Mouseclick

    private void txtDocumentoIdentidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoIdentidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoIdentidadActionPerformed

    private void btnReporteHospedajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteHospedajeActionPerformed

                 try {
                       int anio = Integer.parseInt(itemAnyo.getSelectedItem().toString());
                     System.out.println("data "+ anio);
                     ReporteHospedajeServicio rhs = new ReporteHospedajeServicio();
                     rhs.reporteHospedaje(anio);
                 } catch (Exception ex) {
                     System.out.println("error" + ex);
                   Mensaje.mostrarError(null,"Seleccione el año para realizar el reporte grafico");
                 }
    }//GEN-LAST:event_btnReporteHospedajeActionPerformed

    private void btnBuscarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMousePressed
        String documentoIdentidad = txtDocumentoIdentidad.getText();
            try {
            RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
            cliente= registroHospedajeService.buscarCliente(documentoIdentidad); 
//            System.out.println(cliente);
            if(cliente != null){
                numeroIdentidad=cliente.getCodigocliente();
                txtnombre.setText(cliente.getNombre()+ " "+cliente.getApellido());
                txtfono.setText(cliente.getTelefono());
                txtcorreo.setText(cliente.getCorreo());
                System.out.println(cliente.getNacionalidad());
         
                txttipodocumento.setText(cliente.getTipoDocumento().getDescripcion());
                txtnacionalidad.setText(cliente.getNacionalidad());
            } else {
                Mensaje.mostrarAdvertencia(null,"No se ha encontrado el numero de identidad");
            }
        } catch (Exception e) {
             Mensaje.mostrarAdvertencia(null,"ERROR " + e);
        } 
    
    
    
    }//GEN-LAST:event_btnBuscarClienteMousePressed

    private void btnReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteClienteActionPerformed
     
       try {
      Date fechaI=fechaInicial.getDate();
      Date fechaF=fechaFinal.getDate();
//       if(fechaI.compareTo(fechaF) == 1)
//        //   fechaI.com
//             System.out.println("asd");
//       else LA EXPO ES LA OTRA SEMANA
//           System.out.println("asd");
       ReporteHospedajeServicio reporteHospedajeService=new ReporteHospedajeServicio();
            reporteHospedajeService.reporteCliente(fechaI,fechaF);
       
       } catch (Exception e) {
           Mensaje.mostrarError(null,"Establecer el rango de fechas para realizar el reporte correctamente");
       }

        
        
        /* 
         Author :  Bruno
        */
        //NOTA : RECUERDA QUE  POR SALIDA ES UN REPORTE => USANDO JASPER REPORTS
    }//GEN-LAST:event_btnReporteClienteActionPerformed
    public void actualizarEstadia(){
         CierreDeEstadiaServicio cierreEstadia = new CierreDeEstadiaServicio();
        try{
            System.out.println(registroDeHabitacion.getCliente().getCodigocliente());
            int resultado =  cierreEstadia.actualizarDatosEstadia(registroDeHabitacion);
            if(resultado == 1){
                System.out.println("actualizando");
            }else{
                System.out.println("probelmas al actualizar");
            }
        }catch(Exception ex){
            System.out.println("problema" + ex);
        }
    }
    private void btnCierreEstadiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCierreEstadiaMousePressed
        // TODO add your handling code here:
        int documentoIdentidad = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el documento de identidad"));
        try{
                 try{
                     System.out.println(documentoIdentidad);
                     CierreDeEstadiaServicio cierreEstadia = new CierreDeEstadiaServicio();
                     registroDeHabitacion =  cierreEstadia.listadoEstadiaCliente(documentoIdentidad);
                     System.out.print(registroDeHabitacion);
                     if(registroDeHabitacion != null){
                        System.out.println(registroDeHabitacion.getCliente().getNombre());
                      txtFechaHospedaje.setText(getFecha(registroDeHabitacion.getFechaIngreso()));
                      txtPisoHabitacion.setText(String.valueOf(registroDeHabitacion.getHabitacion().getNumeroDePiso()));
                       labelNombreApellido.setText(registroDeHabitacion.getCliente().getNombre()+ 
                               " " + registroDeHabitacion.getCliente().getApellido());
                       labelTipoHabitacion.setText(registroDeHabitacion.getHabitacion().getTipoHabitacion().getDescripcion());
                       labelNumeroIdentidad.setText(String.valueOf(documentoIdentidad));
                       txtNumeroHabitacion.setText(registroDeHabitacion.getHabitacion().getNumeroHabitacion());
                       labelCostoHabitacion.setText(String.valueOf(registroDeHabitacion.getHabitacion().getTipoHabitacion().getCosto()));
                       actualizarEstadia();
                     }else{
                         System.out.println("no existe el documento de identidad");
                     }
                 }catch(Exception ex){
                       System.out.println("problema" + ex);
                 }
        }catch(NumberFormatException ex){
            System.out.println("ingresar entero" + ex);
        }
    }//GEN-LAST:event_btnCierreEstadiaMousePressed

    private void itemTipoHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTipoHabitacionMouseClicked
    }//GEN-LAST:event_itemTipoHabitacionMouseClicked

    private void btnBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMousePressed
        // TODO add your handling code here:
        /* 
         Author :  Wilmer
        */
        String tipoHabitacion = itemTipoHabitacion.getSelectedItem().toString();
        listarHabitaciones(tableReservaHabitacion,tipoHabitacion);
        mostrarAcumuladoresDeEstado(tipoHabitacion);
        mostrarCostoHabitacion(tipoHabitacion);
    }//GEN-LAST:event_btnBuscarMousePressed

    private void btnLimpiarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMousePressed
       //  listarHabitacioneableReservaHabitacions(null);
        /* 
         Author :  Jose
        */
         refreshTable();  
           mostrarAcumuladoresDeEstado(null);
    }//GEN-LAST:event_btnLimpiarMousePressed

    private void btnGenerarBoletaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarBoletaMousePressed
        // TODO add your handling code here:--
       if(labelNumeroIdentidad.getText().equals("--")){
           Mensaje.mostrarAdvertencia(null, "Debe cerrar estadia para generar boleta");
       }else{
            try{
               String numeroReserva = registroDeHabitacion.getNumeroReserva();
                CierreDeEstadiaServicio cierreDeEstadia = new CierreDeEstadiaServicio();
                cierreDeEstadia.boletaDeCierreEstadia(numeroReserva);
           }catch(Exception ex){
               System.out.println("problema " + ex);
           }

       }
    }//GEN-LAST:event_btnGenerarBoletaMousePressed

    private void tableReservaHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableReservaHabitacionMouseClicked
        // TODO add your handling code here:
        if(numeroIdentidad == null){
                   Mensaje.mostrarAdvertencia(null, "Ingresar numero de identidad");
             }else{
                int fila = tableReservaHabitacion.rowAtPoint(evt.getPoint());
                       int col = tableReservaHabitacion.columnAtPoint(evt.getPoint());
                       if(fila >-1 && col==4){
                            String  estado=String.valueOf(tableReservaHabitacion.getValueAt(fila, 2));
                            String  numeroHabitacion=String.valueOf(tableReservaHabitacion.getValueAt(fila, 1));
                                if(estado.equals("OCUPADO")){
                                     Mensaje.mostrarAdvertencia(null, "Habitacion ocupada");
                                }else{
                                  try {
                                      String codigoCliente = cliente.getCodigocliente();
                                    RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
                                    cliente = new Cliente(codigoCliente);
                                    habitacion = new Habitacion(numeroHabitacion);
                                     registroDeHabitacion = new RegistroDeHabitacion(cliente,habitacion);
                                    int res = registroHospedajeService.registrarHabitacion(registroDeHabitacion);
                                    if(res == 1){
                                        mostrarDetalleDeReserva(codigoCliente);
                                         refreshTable();
                                         limpiarReservaHabitacion();
                                           mostrarAcumuladoresDeEstado(null);
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(FormDashboard.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                }
                       }//siii morii
             }
    }//GEN-LAST:event_tableReservaHabitacionMouseClicked

    private void pp4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pp4MouseClicked
        if(evt.getSource()==pp1){
            p1.setVisible(true);
            p2.setVisible(false);
            p3.setVisible(false);
             p4.setVisible(false);
        }
        if(evt.getSource()==pp2){
            p1.setVisible(false);
            p2.setVisible(true);
            p3.setVisible(false);
             p4.setVisible(false);
        }
        if(evt.getSource()==pp3){
            p1.setVisible(false);
            p2.setVisible(false);
            p3.setVisible(true);
             p4.setVisible(false);
        }
        if(evt.getSource()==pp4){
            p1.setVisible(false);
            p2.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(true);
            
        }
    }//GEN-LAST:event_pp4MouseClicked
public int validarNumeroIdentidad(String numeroIdentidad){
    int existeNumeroIdentidad=0;
   try{
        RegistroClienteServicio registroCliente=new RegistroClienteServicio();
        existeNumeroIdentidad = registroCliente.existeDocumentoidentidad(numeroIdentidad);
        return existeNumeroIdentidad;
   }catch(Exception ex){
       System.out.println(ex);
   }
   return existeNumeroIdentidad;
}
    private void btnReporteClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnReporteClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteClienteKeyPressed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
       FormLogin login = new FormLogin();
       login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnRegistrarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarClienteMouseClicked
        String nombre=txtNombreCliente.getText();
        String apellido=txtApellidoCliente.getText();
        String telefono=txtTelefonoCliente.getText();
        String correo=txtCorreoCliente.getText();
        String documento=cbxTipoDeDocumentoCliente.getSelectedItem().toString();
        String arrayDocumento[] = documento.split("-");
        String numeroIdentidad=txtNumeroIdentidadCliente.getText();
        String nacionalidad=cbxNacionalidadCliente.getSelectedItem().toString();
        int idDocumento = Integer.parseInt(arrayDocumento[0]);
//        if(nombre.equals("") || apellido.equals("") || telefono.equals("") || correo.equals("") ||
//                documento.equals() || numeroIdentidad.equals("") || nacionalidad.equals(""){
//        
//        }else{
             TipoDocumento tipoDocumento=new TipoDocumento(idDocumento);
        Cliente cliente=new Cliente(apellido, documento, correo, nacionalidad, nombre, numeroIdentidad, telefono, tipoDocumento);
        if(validarNumeroIdentidad(numeroIdentidad) == 1){
                Mensaje.mostrarAdvertencia(null, "el numero de identidad existe");
        }else{
            try {
                RegistroClienteServicio registroCliente=new RegistroClienteServicio();
                int registro=registroCliente.registrarCliente(cliente);
                if(registro==1){
                    Mensaje.mostrarAfirmacion(null,"Resgistrado correctamente");
                    limpiarRegistroCliente();
                }else{
                     Mensaje.mostrarError(null,"problema al registrar");
                }
            } catch (Exception e) {
                 Mensaje.mostrarError(null,"Llene todos los campos correctamente");
            }
//        }
        }
       
    }//GEN-LAST:event_btnRegistrarClienteMouseClicked

    private void btnLimpiarCierreEstadiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarCierreEstadiaMouseClicked
    limpiarCierreDeEstadia();
    }//GEN-LAST:event_btnLimpiarCierreEstadiaMouseClicked

    private void txtNumeroIdentidadClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroIdentidadClienteKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtNumeroIdentidadCliente.getText().length() == 8 || 
                !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))
            evt.consume(); 
    }//GEN-LAST:event_txtNumeroIdentidadClienteKeyTyped

    private void txtTelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyTyped
     String patron_de_entrada = "0123456789";
        if (txtTelefonoCliente.getText().length() == 9 || 
                !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))
            evt.consume();
    }//GEN-LAST:event_txtTelefonoClienteKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
    final char keyChar = evt.getKeyChar();
    if (!(Character.isAlphabetic(keyChar) || (keyChar == KeyEvent.VK_SPACE) || keyChar == KeyEvent.VK_DELETE)) {
        evt.consume();
    } else if (Character.isLowerCase(evt.getKeyChar())) {
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtApellidoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoClienteKeyTyped
    final char keyChar = evt.getKeyChar();
    if (!(Character.isAlphabetic(keyChar) || (keyChar == KeyEvent.VK_SPACE) || keyChar == KeyEvent.VK_DELETE)) {
        evt.consume();
    } else if (Character.isLowerCase(evt.getKeyChar())) {
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    } 
    }//GEN-LAST:event_txtApellidoClienteKeyTyped

    private void txtCorreoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyTyped
      final char keyChar = evt.getKeyChar();
    if ( (keyChar == KeyEvent.VK_SPACE)) {
        evt.consume();
    } else if (Character.isLowerCase(evt.getKeyChar())) {
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }  
    }//GEN-LAST:event_txtCorreoClienteKeyTyped

    private void txtDocumentoIdentidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoIdentidadKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtDocumentoIdentidad.getText().length() == 8 || 
                !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))
            evt.consume(); 
    }//GEN-LAST:event_txtDocumentoIdentidadKeyTyped
    public void mostrarDetalleDeReserva(String codigoCliente){
       
      try{
          //nombre,apellido,cost,nivel,tipo
             RegistroHospedajeServicio registroHospedajeService = new RegistroHospedajeServicio();
         registroDeHabitacion = registroHospedajeService.detalleReservaHabitacion(codigoCliente);
         if(registroDeHabitacion != null){
            Mensaje.mostrarAfirmacion(null, "N° Habitacion: "+ registroDeHabitacion.getHabitacion().getNumeroHabitacion()
                    +"\n Nombre y Apellido del hospedado: " + registroDeHabitacion.getCliente().getNombre() + " "+registroDeHabitacion.getCliente().getApellido()
                    +"\n Tipo de Habitacion:" + registroDeHabitacion.getHabitacion().getTipoHabitacion().getDescripcion()
                    +"\n Costo de Habitacion: " + registroDeHabitacion.getHabitacion().getTipoHabitacion().getCosto() 
                     +"\n Piso de Habitacion: " + registroDeHabitacion.getHabitacion().getNumeroDePiso()
                   +"\n Fecha de Estadia: " + getFecha(registroDeHabitacion.getFechaIngreso()));
         }else{
             System.out.println("problem ");
         }
  }catch(Exception ex){
          Mensaje.mostrarError(null, "error " + ex);
      }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JPanel btnBuscarCliente;
    private javax.swing.JPanel btnCierreEstadia;
    private javax.swing.JLabel btnGenerarBoleta;
    private javax.swing.JLabel btnLimpiar;
    private javax.swing.JLabel btnLimpiarCierreEstadia;
    private javax.swing.JLabel btnRegistrarCliente;
    private javax.swing.JButton btnReporteCliente;
    private javax.swing.JButton btnReporteHospedaje;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JComboBox<String> cbxNacionalidadCliente;
    private javax.swing.JComboBox<String> cbxTipoDeDocumentoCliente;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicial;
    private javax.swing.JComboBox<String> itemAnyo;
    private javax.swing.JComboBox<String> itemTipoHabitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabelidentidad;
    private javax.swing.JLabel labelCosto;
    private javax.swing.JLabel labelCostoHabitacion;
    private javax.swing.JLabel labelHabitacionesDisponibles;
    private javax.swing.JLabel labelHabitacionesOcupadas;
    private javax.swing.JLabel labelNombreApellido;
    private javax.swing.JLabel labelNumeroIdentidad;
    private javax.swing.JLabel labelPisoHabitacion;
    private javax.swing.JLabel labelTipoHabitacion;
    public static javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel panel_centre;
    private javax.swing.JPanel pp1;
    public static javax.swing.JPanel pp2;
    private javax.swing.JPanel pp3;
    private javax.swing.JLabel pp4;
    private javax.swing.JTable tableReservaHabitacion;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JLabel txtCosto;
    private javax.swing.JTextField txtDocumentoIdentidad;
    private javax.swing.JLabel txtFechaHospedaje;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JLabel txtNumeroHabitacion;
    private javax.swing.JTextField txtNumeroIdentidadCliente;
    private javax.swing.JLabel txtPisoHabitacion;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JLabel txtcorreo;
    private javax.swing.JLabel txtfono;
    private javax.swing.JLabel txtnacionalidad;
    private javax.swing.JLabel txtnombre;
    private javax.swing.JLabel txttipodocumento;
    // End of variables declaration//GEN-END:variables
}
