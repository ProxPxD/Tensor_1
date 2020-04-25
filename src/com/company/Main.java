package com.company;


public class Main {

    public static void main(String[] args) {

        int [] dims = {2, 2, 2};
        Tensor<Integer> l = new Tensor<>(dims);
        l.fillBy(0);
        l.fillIn(System.in);

        System.out.println("\n" + l);


    }

}
