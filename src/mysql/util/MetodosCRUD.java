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
    
    public void salvarUsuario(String name, String username, String password,Date dataNasc,int nuit, String morada, Tipousuario tipoUsuario){
        Balconista balconista = new Balconista();
        balconista.setNome(name);
        balconista.setMorada(morada);
        balconista.setNuit(nuit);
        balconista.setDataNascimento(dataNasc);
        balconista.setUsername(username);
        balconista.setPassword(password);
        balconista.setTipousuario(tipoUsuario);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(balconista);
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
    
   
    
    public DefaultTableModel listarItens(){
        DefaultTableModel dtm = new DefaultTableModel();
         dtm.setColumnIdentifiers(new Object[] {"ID","Nome","Preco","Tipo"});
         for(Item item:listas.listaItem())
             dtm.addRow(new Object[]{" "+item.getId(),item.getNome(),item.getPreco(),item.getTipoitem()});
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
    
    
}
