/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mysql.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
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
public class Listas {
    public Listas(){
        
    }
    
    public ArrayList<Balconista> listaBalconista(){
        ArrayList<Balconista> balconistas = new ArrayList<Balconista>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Balconista");
        balconistas = (ArrayList) query.list();
        session.getTransaction().commit();
        session.close();
        return balconistas;
    }
    
    public Vector<Tipoitem> vectorTipoItem(){
        Vector<Tipoitem> tipoItem = new Vector<Tipoitem>();
        for(Tipoitem aux: listaTipoItem())
            tipoItem.add(aux);
        return tipoItem;
    }
    
    
    public Vector<Item> vectorItem(){
        Vector<Item> item = new Vector<Item>();
        for(Item aux: listaItem())
            item.add(aux);
        return item;
    }
    
    public Vector<Item> vectorItems(Tipoitem tI){
        Vector<Item> itens = new Vector<Item>();  
        for(Item i:listaItem()){
            if(i.getTipoitem().getId()==tI.getId()){
                itens.add(i);
            }
        } 
        return itens;
    }
    
    public Double totalPorUsuario(Balconista b){
        Double total=0.0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date());
        for(ItemPedido iP: listaItensPedidos()){
            cal2.setTime(iP.getPedido().getData());
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(iP.getPedido().isPago() && iP.getPedido().getBalconista().getId()==b.getId()&& sameDay)
                total+=iP.getItem().getPreco();
            
    }
        return total;
    }
    
    public int totalPorUsuarioBebida(Balconista b){
        int total=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date());
        for(ItemPedido iP: listaItensPedidos()){
            cal2.setTime(iP.getPedido().getData());
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(iP.getPedido().isPago() && iP.getPedido().getBalconista().getId()==b.getId()&& iP.getItem().getTipoitem().getId()!=3&& sameDay)
            total=total+iP.getQtd();
            
    }
        return total;
    }
    
    public int totalPorUsuarioComida(Balconista b){
        int total=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date());
        for(ItemPedido iP: listaItensPedidos()){
            cal2.setTime(iP.getPedido().getData());
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(iP.getPedido().isPago() && iP.getPedido().getBalconista().getId()==b.getId()&& iP.getItem().getTipoitem().getId()==3&& sameDay)
                total=total+iP.getQtd();
            
    }
        return total;
    }
    
    public Vector<Tipousuario> vectorTipoUsuario(){
        Vector<Tipousuario> tipoUsuario = new Vector<Tipousuario>();
        for(Tipousuario aux: listaTipoUsuario())
         tipoUsuario.add(aux);
        return tipoUsuario;
    }
    
    public Vector<Mesa> vectorMesasLivres(){
        Vector<Mesa> mesas = new Vector<Mesa>();
        for(Mesa aux: listaMesas())
          if(aux.isMesalivre())
            mesas.add(aux);
        return mesas;
    }
    
    public Vector<Mesa> vectorMesasOcupadas(){
        Vector<Mesa> mesas = new Vector<Mesa>();
        for(Mesa aux: listaMesas())
          if(!aux.isMesalivre())  
            mesas.add(aux);
        return mesas;
    }
     
    
    public ArrayList<Mesa> listaMesas(){
        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Mesa");
        mesas = (ArrayList) query.list();
        session.getTransaction().commit();
        session.close();
        return mesas;    
                }
    
    
    public ArrayList<Logs> listaLogs(){
        ArrayList<Logs> logs = new ArrayList<Logs>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Logs");
        logs = (ArrayList) query.list();
        session.getTransaction().commit();
        //session.close();
        return logs;    
                }
    
    public ArrayList<Tipousuario> listaTipoUsuario(){
        ArrayList<Tipousuario> tipoUsuario = new ArrayList<Tipousuario>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Tipousuario");
        tipoUsuario = (ArrayList) query.list();
        session.getTransaction().commit();
        session.close();
        return tipoUsuario;
    }
    
     public ArrayList<Tipoitem> listaTipoItem(){
        ArrayList<Tipoitem> tipoItem = new ArrayList<Tipoitem>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Tipoitem");
        tipoItem = (ArrayList) query.list();
        session.getTransaction().commit();
        session.close();
        return tipoItem;
    }
    
    public ArrayList<Item> listaItem(){
        ArrayList<Item> item = new ArrayList<Item>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item");
        item = (ArrayList) query.list();
        session.getTransaction().commit();
        //session.close();
        return item;
    }
    
    public ArrayList<Pedido> listaPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Pedido");
        pedidos = (ArrayList) query.list();
        session.getTransaction().commit();
        session.close();
        return pedidos;
    }
    
    public ArrayList<ItemPedido> listaItensPedidos(){
        ArrayList<ItemPedido> itensPedidos = new ArrayList<ItemPedido>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemPedido");
        itensPedidos = (ArrayList) query.list();
        session.getTransaction().commit();
        //session.close();
        return itensPedidos;
    }
    //semanal
    public int nrPedidosVendidosSemana(int diaSemana){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfWeek(refDate, Calendar.getInstance().getFirstDayOfWeek());
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago()){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(days[diaSemana]);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                nrPedidos=nrPedidos+p.getQtd();
        }
        System.out.println(days[diaSemana]+" "+nrPedidos+" pedidos feitos");
        
        return nrPedidos;
    }
    
    public Double facturamentoSemanal(int diaSemana){ //Resolver bug de retorno
        Double facturamento=0.0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfWeek(refDate, Calendar.getInstance().getFirstDayOfWeek());
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago()){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(days[diaSemana]);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                facturamento+=p.getItem().getPreco()*p.getQtd(); //Bug to solve
            }
        System.out.println(days[diaSemana]+" "+facturamento+" facturado");
        
        return facturamento;
    }
    
     public int nrPratosVendidosSemana(int diaSemana){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfWeek(refDate, Calendar.getInstance().getFirstDayOfWeek());
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago() && p.getItem().getTipoitem().getId()==3){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(days[diaSemana]);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                nrPedidos=nrPedidos+p.getQtd();
        }
        System.out.println(days[diaSemana]+" "+nrPedidos+" pedidos feitos");
        
        return nrPedidos;
    }
     
     public int nrBebidasVendidosSemana(int diaSemana){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfWeek(refDate, Calendar.getInstance().getFirstDayOfWeek());
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago() && p.getItem().getTipoitem().getId()!=3){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(days[diaSemana]);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                nrPedidos=nrPedidos+p.getQtd();
        }
        System.out.println(days[diaSemana]+" "+nrPedidos+" pedidos feitos");
        
        return nrPedidos;
    } 
    //mensal
    public int nrPedidosVendidosMes(int diaMes){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date(); //data corrente
        Date[] days = getDaysOfMonth(refDate); //extraindo os dias do mes corrente
        ArrayList<ItemPedido> pedidos = listaItensPedidos(); //adquirindo lista de todos os pedidos
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago()){
            cal1.setTime(p.getPedido().getData()); //data de um pedido
            cal2.setTime(days[diaMes]); //uma data do mes corrente
            //verificando se o pedido foi feito numa data no mes corrente
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                nrPedidos=nrPedidos+p.getQtd();
        }
        System.out.println(days[diaMes]+" "+nrPedidos+" pedidos feitos");
        
        return nrPedidos;
    }
    
    public Double facturamentoMensal(int diaMes){ //Resolver bug de retorno
        Double facturamento=0.0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfMonth(refDate);
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago()){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(days[diaMes]);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                facturamento+=(p.getItem().getPreco()*p.getQtd()); //Bug de retorno
        }
        System.out.println(days[diaMes]+" "+facturamento+" facturado");
        
        return facturamento;
    }
    
       public int nrPratosVendidosMes(int diaMes){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfMonth(refDate);
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago() && p.getItem().getTipoitem().getId()==3){
            cal1.setTime(p.getPedido().getData()); //data de um pedido
            cal2.setTime(days[diaMes]); //uma data do mes corrente
            //verificando se o pedido foi feito numa data no mes corrente
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                nrPedidos=nrPedidos+p.getQtd();
        }
        System.out.println(days[diaMes]+" "+nrPedidos+" pedidos feitos");
        
        return nrPedidos;
    }
 
          public int nrBebidasVendidosMes(int diaMes){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        Date refDate = new Date();
        Date[] days = getDaysOfMonth(refDate);
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago() && p.getItem().getTipoitem().getId()!=3){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(days[diaMes]);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                nrPedidos=nrPedidos+p.getQtd();
        }
        System.out.println(days[diaMes]+" "+nrPedidos+" pedidos feitos");
        
        return nrPedidos;
    }
   
          //anual
          
          public int nrPratosVendidosAno(int mesAno){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        Date refDate = new Date();
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago() && p.getItem().getTipoitem().getId()==3){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(refDate);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == mesAno)
                nrPedidos=nrPedidos+p.getQtd();
        }
        
        return nrPedidos;
    }
   
              public int nrBebidasVendidosAno(int mesAno){
        int nrPedidos=0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        Date refDate = new Date();
        ArrayList<ItemPedido> pedidos = listaItensPedidos();
        
        for(ItemPedido p:pedidos)
            if(p.getPedido().isPago() && p.getItem().getTipoitem().getId()!=3){
            cal1.setTime(p.getPedido().getData());
            cal2.setTime(refDate);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == mesAno)
                nrPedidos=nrPedidos+p.getQtd();
        }
        
        return nrPedidos;
    }
          
 
    //-------------------------------//
    private Date[] getDaysOfWeek(Date refDate, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        Date[] daysOfWeek = new Date[7];
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysOfWeek;
    }
    
    private Date[] getDaysOfMonth(Date refDate) {
        int i=0;
        Date[] daysMonth = new Date[nrDaysOfMonth(refDate)];
        Calendar cal = Calendar.getInstance();
        cal.setTime(refDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth = cal.get(Calendar.MONTH);

        while (myMonth == cal.get(Calendar.MONTH)) {
            daysMonth[i]=cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            i++;
        }
        return daysMonth;
}        

    public int nrDaysOfMonth(Date refDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(refDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    
}
