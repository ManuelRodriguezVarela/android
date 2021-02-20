package net.ies.san.clemente.tarea_pmdm02.model;

import java.util.ArrayList;

public class PrimeNumber {
    private ArrayList<Integer> primeNumbers;

    public PrimeNumber(ArrayList<Integer> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    public PrimeNumber() {
        this.primeNumbers = new ArrayList<>();
    }

    public ArrayList<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(ArrayList<Integer> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    public void addPrimeNumber(Integer primeNumber) {
        this.primeNumbers.add(primeNumber);
    }

    public Integer getPrimeNumber(Integer position) {
        return primeNumbers.get(position - 1);
    }

    public void addPrimesNumbersIfNotExistPosition(Integer position) {
        Integer arraySize = primeNumbers.size();
        Integer prime = 1;
        if (arraySize > 0) {
            prime = getPrimeNumber(arraySize);
        }
        Integer flag = position - arraySize;
        for(int i=0; i < flag; i++) {
            prime++;
            while(!Utils.isPrimeNumber(prime)) {
                prime++;
            };
            addPrimeNumber(prime);
        }
    }

}
