package com.company;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Tensor<T> {



    private int[] dimensions;
    private ArrayList<T> values;

    Tensor(int ... dims){
        dimensions = new int[dims.length];
        values = new ArrayList<>();

        int k = 0;
        for(int i = 0; i < dims.length; i++){
            dimensions[i] = dims[i];
        }

        for(int i = 0; i < size(); i++){
            values.add(null);
        }
    }

    private int getIndex(int[] coords){
        int index = 0;
        for (int i = 0; i < coords.length; i++){
            index = index*dimensions[i] + coords[i];
        }
        return index;
    }

    public void set(T val, int ... coords){
        int index = getIndex(coords);
        values.set(index, val);
    }

    public T get(int ... coords){
        int index = getIndex(coords);
        return values.get(index);
    }

    public void fillBy(T val){
        int i = 0;
        while (i < values.size()){
            values.set(i, val);
            i++;
        }
    }

    public void fillBy(T val, Function<T, T> inc){
        int i = 0;
        while (i < values.size()){
            values.set(i, val);
            val = inc.apply(val);
            i++;
        }
    }

    public void fillIn(InputStream in){
        Scanner sc = new Scanner(in);
        Pointer p = new Pointer(dimensions);
        T input;
        while(p.getLast() != p.getDims().length){
            this.set(null, p.getCurr());
            System.out.println(this);
            input = (T) sc.nextLine();
            this.set(input, p.getCurr());
            p.inc();
        }
        System.out.println(this);

    }

    public void fillBy(T val, Function<T, T> inc, Function<T, T> func){
        int i = 0;
        while (i < values.size()){
            values.set(i, func.apply(val));
            val = inc.apply(val);
            i++;
        }
    }

    void setName(int d, String name){

    }

    public int size(){
        int i = 0;
        int counter = 1;
        while(i < dimensions.length){
            counter *= dimensions[i];
            i++;
        }
        return counter;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Pointer p = new Pointer(dimensions);

        int j = 0;
        while (j < p.getDims().length){
            builder.append("[");
            j++;
        }
        for(int i = 0; i < size(); i++){
            p.inc();
            builder.append(values.get(i));
            if (p.getLast() == 0){
                builder.append(",");
            } else {
                j = 0;
                while (j < p.getLast()){
                    builder.append("]");
                    j++;
                }
                builder.append(",");
                if (p.getLast() < 2){
                    builder.append("\n");
                } else {
                    builder.append("\n\n");
                }
                j = 0;
                while (j < p.getLast()){
                    builder.append("[");
                    j++;
                }
            }
        }
        return builder.toString().substring(0, builder.length() - p.getLast() -3);
    }

}
