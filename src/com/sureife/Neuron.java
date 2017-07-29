package com.sureife;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by sureife on 21/07/2017.
 */
public class Neuron {
    private HashMap<Integer,Input> inputs =  new HashMap<>();
    private HashMap<Integer,Double> weights =  new HashMap<>();

    private double output = 0.0;
    private static double threshold;
    private final double learningRate;

    Neuron(List<Double> inputs,double learningRate){
        Random random = new Random();

//        threshold = adjustRange(random.nextDouble());

        int i = 0;
        for (double input : inputs) {
//            double rand = random.nextDouble();
//            double weight = adjustRange(rand);
//            this.inputs.put(i,new Input(input));
//            this.weights.put(i,weight);
            if(i == 0) {
                this.inputs.put(i,new Input(input));
                this.weights.put(i,0.3);
            }
            else{
                this.inputs.put(i,new Input(input));
                this.weights.put(i,-0.1);
            }
            i++;
        }

        this.learningRate = learningRate;
    }

    public static void setThreshold(double threshold){
        Neuron.threshold = threshold;
    }

    public void setInputs(List<Double> inputs){
        if(inputs.size() == this.inputs.size()){
            int i = 0;
            for(Double input:inputs){
                this.inputs.put(i,new Input(input));
                i++;
            }

        }
        output = 0.0;
    }

    private double adjustRange(double rand) {
        if(rand == 0.5) {
            rand = 0;
        } else {
            rand = rand - 0.5;
        }
        return rand;
    }

    public void activate(double desiredOutput){
        output = step(getActivationLevel());

        double error = desiredOutput - output;
        trainWeights(error);
    }

    private void trainWeights(double error){
        for(Integer index:weights.keySet()){
            double weight = weights.get(index);
            weights.put(index,to1dp(weight + computeWeightCorrection(inputs.get(index).getValue(),error)));
        }
    }

    private double computeWeightCorrection(double value,double error){
        return this.learningRate * value * error;
    }

    private double getActivationLevel(){
        double activationLevel = 0.0;

        for(Integer index:inputs.keySet()){
            activationLevel += inputs.get(index).getValue() * weights.get(index);
        }

        activationLevel = to1dp(activationLevel);
        return activationLevel;
    }

    private double to1dp(double dec){
        return Double.valueOf(new DecimalFormat("#.#").format(dec));
    }

    private double sign(double activationLevel){
        return activationLevel >= threshold ? 1 : -1;
    }

    private double step(double activationLevel){
        return activationLevel >= threshold ? 1 : 0;
    }

    @Override
    public String toString() {

        String out = "INPUT-WEIGHT\n";
        for(Integer index:inputs.keySet()){
            out += "\n" + inputs.get(index).getValue() + " - " + weights.get(index);
        }

        out += "\nOUTPUT = " + output + "\n";
        return out;
    }

    public void showOutputAfterActivation(){
        System.out.println("THRESHOLD = " + threshold);
        System.out.println("LEARNING RATE = " + learningRate  + "\n");
        String out = "INPUT - WEIGHT";
        for(Integer index:inputs.keySet()){
            out += "\n" + inputs.get(index).getValue() + " - " + weights.get(index);
        }

        out += "\nOUTPUT = " + output + "\n";
        System.out.println(out);
    }


    public void showOutputBeforeActivation(){
        System.out.println("THRESHOLD = " + threshold);
        System.out.println("LEARNING RATE = " + learningRate + "\n");
        String out = "INPUT - WEIGHT";
        for(Integer index:inputs.keySet()){
            out += "\n" + inputs.get(index).getValue() + " - " + weights.get(index);
        }

        System.out.println(out+ "\n");
    }
}
