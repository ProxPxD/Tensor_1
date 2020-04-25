package com.company;

public class Pointer {

    private int[] maxes;
    private int[] current;
    private int max;
    private int last;

    Pointer(int ... dims){
        max = dims.length;
        maxes = new int[max];
        current = new int[max];
        for(int i = 0; i < max; i++){
            maxes[i] = dims[i];
            current[i] = 0;
        }
    }

    public int getLast(){
        return last;
    }

    public int[] getDims(){
        return maxes;
    }

    public int[] getCurr(){
        return current;
    }

    public int getCurrMax(){
        return maxes[last];
    }

    public int dec(){
        int i = max-1;
        while(--current[i] == 0){
            current[i] = maxes[i]-1;
            i--;
        }
        last = max-i -1;
        return last;
    }

    public int inc(){
        int i = max-1;
        while(++current[i] == maxes[i]){
            current[i] = 0;
            i--;
            if (i < 0){
                break;
            }
        }
        last = max-i -1;
        return last;
    }
}
