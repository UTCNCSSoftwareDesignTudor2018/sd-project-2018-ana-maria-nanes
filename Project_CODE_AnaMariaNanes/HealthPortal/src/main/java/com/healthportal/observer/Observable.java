package com.healthportal.observer;

import com.healthportal.entities.CartProduct;

public interface Observable {

        public void addObserver(Observer o);
        public void removeObserver(Observer o);
        public void notifyObservers(String command,int id,int quantity, float total);

}
