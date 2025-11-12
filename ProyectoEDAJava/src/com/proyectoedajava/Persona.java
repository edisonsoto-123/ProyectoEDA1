package com.proyectoedajava;

public class Persona implements Comparable<Persona> {
    private double peso;
    private double altura;
    private double imc;

    public Persona(double peso, double altura, double imc) {
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
    }

    public double getImc() {
        return imc;
    }

    @Override
    public int compareTo(Persona otra) {
        return Double.compare(this.imc, otra.imc);
    }
}
