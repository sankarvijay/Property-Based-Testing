package fr.parisnanterre.miage.poa.pbt.impl;


/**
 * Created by vijay on 01-11-2018.
 */

public class PostalParcel {

    public static final double MAX_DELIVERY_COSTS = 3.99;
    public static final double MIN_DELIVERY_COSTS = 0.99;

    private int weight;
    private String uuid;

    public PostalParcel(String uuid, int weight) {
        this.uuid = uuid;
        if(weight>0){
            this.weight = weight;
        }else {
            System.out.println("Pas de poids inférieur à 0 !");
        }
    }

    public double deliveryCosts() {
        if (weight > 20) {
            return MAX_DELIVERY_COSTS;
        }
        return MIN_DELIVERY_COSTS;
    }
}
