package com.healthportal.observer;

public interface Observer {
   void update(String command,int id,int quantity,float total);
}
