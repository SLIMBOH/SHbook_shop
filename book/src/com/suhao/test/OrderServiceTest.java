package com.suhao.test;

import com.suhao.pojo.Cart;
import com.suhao.pojo.CartItem;
import com.suhao.pojo.OrderItem;
import com.suhao.service.OrderService;
import com.suhao.service.impl.OrderServiceImpl;
import org.junit.Test;

import javax.smartcardio.CardTerminal;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    OrderService os = new OrderServiceImpl();

//    @Test
//    public void createOrder(){
//        Cart c = new Cart();
//
//        c.addItem(new CartItem( 1,1,"java从入门到放弃", new BigDecimal(80), new BigDecimal(80)));
//        c.addItem(new CartItem( 1,2,"java从入门到放弃", new BigDecimal(80), new BigDecimal(80)));
//        c.addItem(new CartItem( 5,4,"C++编程思想", new BigDecimal(80), new BigDecimal(80)));
//
//        OrderService os = new OrderServiceImpl();
//
//        System.out.println(os.createOrder(c, 2));
//    }

}