/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.util;

import java.util.Date;
import mysql.entity.Balconista;
import mysql.entity.Item;

/**
 *
 * @author Gaara-X
 */
public class Reports {

    private Balconista user;
    private String accao;
    private Balconista user1;
    private Item item;
    private Date data;

    public Reports() {
    }

    public Reports(Balconista user, String accao, Balconista user1, Date data) {
        this.user = user;
        this.accao = accao;
        this.user1 = user1;
        this.data = data;
    }

    public Reports(Balconista user, String accao, Item item, Date data) {
        this.user = user;
        this.accao = accao;
        this.item = item;
        this.data = data;
    }

    public Reports(Balconista user, String accao, Date data) {
        this.user = user;
        this.accao = accao;
        this.data = data;
    }

    public Balconista getUser() {
        return user;
    }

    public void setUser(Balconista user) {
        this.user = user;
    }

    public String getAccao() {
        return accao;
    }

    public void setAccao(String accao) {
        this.accao = accao;
    }

    public Balconista getUser1() {
        return user1;
    }

    public void setUser1(Balconista user1) {
        this.user1 = user1;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
