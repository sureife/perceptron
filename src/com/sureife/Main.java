package com.sureife;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        HashMap<List<Double>,Double> iterations = new HashMap<>();


        List<Double> iter1 = new ArrayList<Double>();
        iter1.add(0.0);
        iter1.add(0.0);

        List<Double> iter2 = new ArrayList<Double>();
        iter2.add(0.0);
        iter2.add(1.0);

        List<Double> iter3 = new ArrayList<Double>();
        iter3.add(1.0);
        iter3.add(0.0);

        List<Double> iter4 = new ArrayList<Double>();
        iter4.add(1.0);
        iter4.add(1.0);

        iterations.put(iter1,0.0);
        iterations.put(iter2,0.0);
        iterations.put(iter3,0.0);
        iterations.put(iter4,1.0);

        int epoch = 1;

        // TRAINED TO PERFORM THE AND OPERATION

        System.out.println("EPOCH " + epoch);


        Neuron neuron = new Neuron(iter1,0.1);
        Neuron.setThreshold(0.2);

        System.out.println("-----ON NEW INPUT");
        neuron.showOutputBeforeActivation();

        neuron.activate(iterations.get(iter1));

        System.out.println("-----AFTER ACTIVATION");
        neuron.showOutputAfterActivation();

        while (epoch < 7){
            if(epoch > 1) System.out.println("EPOCH " + epoch);
            for(List<Double> iteration:iterations.keySet()){
                if(iteration == iter1 && epoch == 1){
                    continue;
                }else{
                    neuron.setInputs(iteration);
                    System.out.println("-----ON NEW INPUT");
                    neuron.showOutputBeforeActivation();

                    neuron.activate(iterations.get(iteration));
                    System.out.println("-----AFTER ACTIVATION");
                    neuron.showOutputAfterActivation();
                }
            }
            epoch++;
        }




    }
}
