/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

/**
 *
 * @author EQUIPO
 */
public class CostoMinimo implements Comparable<CostoMinimo>{
 int indiceOrigen;
 int indiceDestino;
 double peso;

 public CostoMinimo(int indiceOrigen, int indiceDestino) {
  this.indiceOrigen = indiceOrigen;
  this.indiceDestino = indiceDestino;
 }
 
 public CostoMinimo(int indiceOrigen, int indiceDestino, double peso) {
  this.indiceOrigen = indiceOrigen;
  this.indiceDestino = indiceDestino;
  this.peso = peso;
 }

    public int getIndiceOrigen() {
        return indiceOrigen;
    }

    public void setIndiceOrigen(int indiceOrigen) {
        this.indiceOrigen = indiceOrigen;
    }

    public int getIndiceDestino() {
        return indiceDestino;
    }

    public void setIndiceDestino(int indiceDestino) {
        this.indiceDestino = indiceDestino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
 
 
    @Override
    public int compareTo(CostoMinimo otroCostoMinimo) {
     if(otroCostoMinimo==null){
      return -1;   
    }   
     if(this.getPeso()>otroCostoMinimo.getPeso()){
       return 1;  
     }else if(this.getPeso()<otroCostoMinimo.getPeso()){
       return -1;  
     }else
        return 0; 
  }

  @Override
  public boolean equals(Object elOtroObjecto) {
   if(elOtroObjecto==null){
     return false;  
   }
   CostoMinimo elOtroAdyacente=(CostoMinimo) elOtroObjecto;
   return this.compareTo(elOtroAdyacente)==0;
  }
}
