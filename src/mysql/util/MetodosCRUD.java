/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mysql.util;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import mysql.entity.Balconista;
import mysql.entity.Item;
import mysql.entity.ItemPedido;
import mysql.entity.Logs;
import mysql.entity.Mesa;
import mysql.entity.Pedido;
import mysql.entity.Tipoitem;
import mysql.entity.Tipousuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gaara-X
 */
public class MetodosCRUD {
    
    Listas listas = new Listas();
    public static Double total=0.0;
    public MetodosCRUD(){
        
    }
    
    public void editarDadosUsuario(String name,Date dataNasc,int nuit, String morada, Balconista balconista){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        balconista.setNome(name);
        balconista.setMorada(morada);
        balconista.setNuit(nuit);
        balconista.setDataNascimento(dataNasc);
        session.update(balconista);
        session.getTransaction().commit();
        session.close();
    }
    
    public void editarCredenciaisUsuario(String username,String password, Balconista balconista){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        balconista.setUsername(username);
        balconista.setPassword(password);
        session.update(balconista);
        session.getTransaction().commit();
        session.close();
    }
    
    
 
    
    public void salvarUsuario(String name,Date dataNasc,int nuit, String morada, Tipousuario tipoUsuario){
        Balconista balconista = new Balconista();
        balconista.setNome(name);
        balconista.setMorada(morada);
        balconista.setNuit(nuit);
        balconista.setDataNascimento(dataNasc);
        balconista.setUsername("user"+(listas.listaBalconista().get(listas.listaBalconista().size()-1).getId()+1));
        balconista.setPassword("password"+(listas.listaBalconista().get(listas.listaBalconista().size()-1).getId()+1));
        balconista.setTipousuario(tipoUsuario);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(balconista);
        session.getTransaction().commit();
        session.close();
    }
    
    public void salvarLogs(Balconista b, String a, Date d){
        Logs log = new Logs();
        log.setBalconista(b);
        log.setAccao(a);
        log.setData(d);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(log);
        session.getTransaction().commit();
        session.close();
    }
    
    public DefaultTableModel listarUsuario(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"ID","Nome","Username","NUIT"});
         for(Balconista balconista:listas.listaBalconista())
             dtm.addRow(new Object[]{" "+balconista.getId(),balconista.getNome(),balconista.getUsername(),balconista.getNuit()});
        return dtm;
    }
    
    public DefaultTableModel listarLogs(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"ID","Nome","Accao","Momento"});
         for(Logs log:listas.listaLogs())
             dtm.addRow(new Object[]{" "+log.getId(),log.getBalconista().getNome(),log.getAccao(),log.getData()});
        return dtm;
    }
    
   
    
    public DefaultTableModel listarItens(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"ID","Nome","Preco","Tipo"});
         for(Item item:listas.listaItem())
             dtm.addRow(new Object[]{" "+item.getId(),item.getNome(),item.getPreco(),item.getTipoitem()});
        return dtm;
    }
    
    public DefaultTableModel facturamentoDia(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"Usuario","Facturamento"});
         for(Balconista user:listas.listaBalconista())
             dtm.addRow(new Object[]{" "+user.getNome(),listas.totalPorUsuario(user)});
        return dtm;
    }
    
    public DefaultTableModel facturamentoDiaComida(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"Usuario","Facturamento"});
         for(Balconista user:listas.listaBalconista())
             dtm.addRow(new Object[]{" "+user.getNome(),listas.totalPorUsuarioComida(user)});
        return dtm;
    }
    
    public DefaultTableModel facturamentoDiaBebida(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"Usuario","Facturamento"});
         for(Balconista user:listas.listaBalconista())
             dtm.addRow(new Object[]{" "+user.getNome(),listas.totalPorUsuarioBebida(user)});
        return dtm;
    }
    
    
    public void salvarPedido(Balconista balconista,Mesa mesa,Date data){
        Pedido pedido = new Pedido(balconista,mesa,data);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pedido);
        session.getTransaction().commit();
        session.close();
    }
    
    public void salvarItemPedido(ItemPedido iP){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(iP);
        session.getTransaction().commit();
        session.close();
    }
    
    
    public void salvarItem(String name,double preco,Tipoitem tipoItem){
        Item item = new Item();
        item.setNome(name);
        item.setPreco(preco);
        item.setTipoitem(tipoItem);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }
    
    //Devolvera lista de itens que estao numa determinada mesa
    public DefaultTableModel itensMesa(Mesa mesa){
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        ArrayList<ItemPedido> itensPedidos = new ArrayList<ItemPedido>();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new Object[] {"Item","Quantidade","Preco Unitario","Preco Total"});
        for(Pedido p:listas.listaPedidos()) //Pedidos existentes
            if(p.getMesa().getId()==mesa.getId()&& !p.isPago()) //Filtrar pedidos de uma determinada mesa
                pedidos.add(p);
        
        for(ItemPedido iP: listas.listaItensPedidos()) //Recuperar itens dos pedidos filtrados
            for(Pedido p:pedidos)
                if(iP.getPedido().getId()==p.getId()){ //Compor dados da tabela
                     dtm.addRow(new Object[]{" "+iP.getItem().getNome(),iP.getQtd(),iP.getItem().getPreco(),(iP.getQtd()*iP.getItem().getPreco())});
                     total+=iP.getQtd()*iP.getItem().getPreco();
                     }
        dtm.addRow(new Object[] {"","","",""});
        dtm.addRow(new Object[] {"Total","","",total});
        return dtm;
    }
    
    public void pagarPedido(Mesa mesa){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(Pedido p: listas.listaPedidos())
            if(p.getMesa().getId()==mesa.getId() && !p.isPago()){
                Pedido pedido = (Pedido) session.get(Pedido.class, p.getId());
                pedido.setPago(true);
                pedido.setData(new Date());
                session.update(pedido);
            }
        
        session.getTransaction().commit();
        session.close();
    }
    
    public void actualizaMesas(){ //Actualizar mesas ocupadas ou nao ocupadas consoante os pedidos pagos ou nao
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(Pedido p: listas.listaPedidos())
            for(Mesa m:listas.listaMesas())
            if(p.getMesa().getId()==m.getId()){
                if(p.isPago()){
                Mesa mesa = (Mesa) session.get(Mesa.class, m.getId());
                mesa.setMesalivre(true);
                session.update(mesa);
                } else {
                Mesa mesa = (Mesa) session.get(Mesa.class, m.getId());
                mesa.setMesalivre(false);
                session.update(mesa);
            }
            }
        session.getTransaction().commit();
        session.close();
    }
    
    
}
