/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesados;

/**
 *
 * @author EQUIPO
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{
  private int indiceDeVertice;
  private double peso;

  public AdyacenteConPeso() {
  }

  public AdyacenteConPeso(int indiceDeVertice) {
   this.indiceDeVertice = indiceDeVertice;
  }

  public AdyacenteConPeso(int indiceDeVertice, double peso) {
    this.indiceDeVertice = indiceDeVertice;
    this.peso = peso;
  }

  public int getIndiceDeVertice() {
   return indiceDeVertice;
  }

  public void setIndiceDeVertice(int indiceDeVertice) {
   this.indiceDeVertice = indiceDeVertice;
  }

  public double getPeso() {
   return peso;
  }

  public void setPeso(double peso) {
   this.peso = peso;
  }

  @Override
  public int compareTo(AdyacenteConPeso elOtroAdyacenteConPeso) {
    if(elOtroAdyacenteConPeso==null){
     return -1;  
    }   
   return this.indiceDeVertice-elOtroAdyacenteConPeso.getIndiceDeVertice();
  }

  @Override
  public boolean equals(Object elOtroObjecto) {
   if(elOtroObjecto==null){
     return false;  
   }
   AdyacenteConPeso elOtroAdyacente=(AdyacenteConPeso) elOtroObjecto;
   return this.compareTo(elOtroAdyacente)==0;
  }  
}