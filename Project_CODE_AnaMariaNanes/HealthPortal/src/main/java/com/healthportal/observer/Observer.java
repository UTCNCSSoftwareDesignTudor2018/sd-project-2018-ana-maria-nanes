package com.healthportal.observer;

import com.healthportal.entities.CartProduct;

public interface Observer {
   public void update(String command,int id);
}
