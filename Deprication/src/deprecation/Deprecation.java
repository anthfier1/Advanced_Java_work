package deprecation;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony Fierro
 */
public class Deprecation implements Serializable {

    public Deprecation() {
    }

    
    
    public void getSLD(double cost, double scrap, double life, double[] accDep) {
        double accDeprecitation = (cost - scrap) / life;
        for (int i = 0; i < (int) life; i++) {
            accDep[i] = accDeprecitation;
            if (accDeprecitation > cost) {
                accDeprecitation += (accDeprecitation - cost);
            } else {
                accDeprecitation += accDeprecitation;
            }
        }
    }

    public void getSOYD(double cost, double scrap, double life, double[] dep, double [] accDep) {
       int totalYears =(int) (life * (life + 1))/2;
        double accDeprecation = 0;
        for (int i = 0; i < (int) life; i++){
            double depDouble = ((life - i)/totalYears) * (cost - scrap);
            accDeprecation += ((life - i)/totalYears) * (cost - scrap);
            dep[i] = depDouble;
            accDep[i] = accDeprecation;
        }
    }
    
    public void getUOP(double cost, double scrap, double life, double[] dep, double [] accDep){
     
        
        
    }
    
    public void getDDB(double cost, double scrap, double life, double[] dep, double [] accDep){
    
    
    }
}
