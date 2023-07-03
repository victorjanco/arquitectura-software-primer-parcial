/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author victor janco
 */
public class Celda {
    private int f;
    private int c;
    private int golEqF;
    private int golEqC;

    public Celda(){
        
    }

    public Celda(int f, int c, int golEqF, int golEqC) {
        this.f = f;
        this.c = c;
        this.golEqF = golEqF;
        this.golEqC = golEqC;
    }
    
    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getGolEqF() {
        return golEqF;
    }

    public void setGolEqF(int golEqF) {
        this.golEqF = golEqF;
    }

    public int getGolEqC() {
        return golEqC;
    }

    public void setGolEqC(int golEqC) {
        this.golEqC = golEqC;
    }

    @Override
    public String toString() {
        return "("+this.golEqF+"-"+this.golEqC+")";
    }
    
    
}
