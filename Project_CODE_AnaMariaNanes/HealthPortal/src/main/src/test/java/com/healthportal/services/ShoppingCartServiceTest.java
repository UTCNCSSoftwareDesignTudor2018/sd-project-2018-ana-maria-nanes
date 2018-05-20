package com.healthportal.services;

import com.healthportal.dto.ShoppingCartDTO;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ShoppingCartServiceTest {
    @Test
    public void findByCartlId() throws Exception {
        ShoppingCartService test = Mockito.mock(ShoppingCartService.class);

        ShoppingCartDTO shoppingCartDTO = test.findByCartlId(1);
        int productNo = shoppingCartDTO.getProductNo();

        assertEquals(productNo,5);

    }

    @Test
    public void findByUser() throws Exception {
        ShoppingCartService test = Mockito.mock(ShoppingCartService.class);

        ShoppingCartDTO shoppingCartDTO = test.findByUser(2);
        Integer cartId = 1;

        assertEquals(shoppingCartDTO.getCartId(), cartId);

    }


}