/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Pesados.GrafoPesado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class Floyd {
 private final double[][] matrizDeAdyacencia;
 private final double[][] matrizDeCosto;
 private final int[][] matrizDePredecesores;
 public static final double INFINITO=Double.POSITIVE_INFINITY;   

 public Floyd(GrafoPesado unGrafo) {
  int tamañoDelGrafo=unGrafo.cantidadVertices();
  this.matrizDeAdyacencia=new double[tamañoDelGrafo][tamañoDelGrafo];
  this.matrizDeCosto=new double[tamañoDelGrafo][tamañoDelGrafo];
  this.matrizDePredecesores=new int [tamañoDelGrafo][tamañoDelGrafo];
  this.verificarLosCostos(unGrafo);
  this.cargarMatrizDeAdyacencia(unGrafo);
  this.cargarMatrizDePredecesores();
  this.ejecutarFloyd();
 }
 
 public  void verificarLosCostos(GrafoPesado unGrafo){
  for(int i=0;i<unGrafo.cantidadVertices();i++){
    Iterable<Integer> adyacentesDelVertice =unGrafo.adyacentesDelVertice(i);
   for (Integer adyacentes:adyacentesDelVertice){
     double peso=unGrafo.peso(i,adyacentes);
     if(peso<0){
      throw new IllegalArgumentException("tiene costos negativos");  
     }   
   } 
  }
 }

 public  void cargarMatrizDeAdyacencia(GrafoPesado unGrafo){
  for(int i=0;i<unGrafo.cantidadVertices();i++){
   Iterable<Integer> adyacentesDelVertice=unGrafo.adyacentesDelVertice(i);
   for(Integer adyacentes:adyacentesDelVertice){
    this.matrizDeAdyacencia[i][adyacentes]=unGrafo.peso(i,adyacentes);
   }
   this.matrizDeAdyacencia[i][i]=0.0;
  }
 }
 
 public  void cargarMatrizDePredecesores(){
  for(int i=0;i<this.matrizDePredecesores.length;i++){
   for(int j=0;j<this.matrizDePredecesores.length;j++){
    if(j!=i){
      this.matrizDePredecesores[i][j]=j;   
    }else{
     this.matrizDePredecesores[i][j]= -1;  
    }   
   }   
  }
 }
  
 public void cargarMatrizDeCosto(){
  for(int i=0;i<this.matrizDeCosto.length;i++){
   for(int j=0;j<this.matrizDeCosto.length;j++){
    if(j!=i){
      if(this.matrizDeAdyacencia[i][j]!=0){   
       this.matrizDeCosto[i][j]=this.matrizDeAdyacencia[i][j];
      }else{
       this.matrizDeCosto[i][j]=Floyd.INFINITO;   
      }
    }else{
     this.matrizDeCosto[i][j]=0;  
    }   
   }   
  }
 }
 
 public void ejecutarFloyd(){
  this.cargarMatrizDeCosto();
  for(int k=0;k<this.matrizDeCosto.length;k++){
   for(int i=0;i<this.matrizDeCosto.length;i++){
    for(int j=0;j<this.matrizDeCosto.length;j++){ 
     if(j!=k && i!=k){
      double minimo=this.matrizDeCosto[i][k]+this.matrizDeCosto[k][j];
      if(this.matrizDeCosto[i][j]>minimo){
        this.matrizDeCosto[i][j]=minimo;
        this.matrizDePredecesores[i][j]=k; 
      }   
     }    
    }  
   }   
  }   
 }
 
 public boolean hayCamino(int posVerticeOrigen,int posVerticeDestino){
  return this.mostrarCosto(posVerticeOrigen, posVerticeDestino)!=Floyd.INFINITO;   
 } 

 public double[][] getMatrizDeCosto(){
  return this.matrizDeCosto;   
 } 
 
 public int[][] getMatrizDePredecesores(){
   return this.matrizDePredecesores;  
 }
 
 public String imprimirCamino(int posVerticeOrigen,int posVerticeDestino){   
  String camino="";
  if(this.hayCamino(posVerticeOrigen,posVerticeDestino)){    
    List<Integer> listaCamino = new ArrayList<>();
    listaCamino.add(posVerticeOrigen);
    getCamino(posVerticeOrigen,posVerticeDestino,listaCamino);
    for(int i=0;i<listaCamino.size();i++){
     camino+=listaCamino.get(i)+"->";
    }   
  }
  return camino+" costo total: "+this.mostrarCosto(posVerticeOrigen,posVerticeDestino);
 } 
  
  private void getCamino
         (int posVerticeOrigen,int posVerticeDestino,List<Integer> listaCamino){
   int intermedio=
                 this.matrizDePredecesores[posVerticeOrigen][posVerticeDestino];
   if(intermedio!=posVerticeDestino){
     this.getCamino(posVerticeOrigen,intermedio,listaCamino);
     this.getCamino(intermedio,posVerticeDestino,listaCamino);
   }else{
    listaCamino.add(posVerticeDestino);
   } 
  }
  
  public double mostrarCosto(int posVerticeOrigen,int posVerticeDestino){   
   return this.matrizDeCosto[posVerticeOrigen][posVerticeDestino];   
  }
  
  public String imprimirCamino(int posVerticeOrigen){
   String camino="";
   for(int i=0;i<this.matrizDePredecesores.length;i++){
    if(this.hayCamino(posVerticeOrigen,i)&&posVerticeOrigen!=i){   
     camino+=this.imprimirCamino(posVerticeOrigen,i)+"\n";
    }
   }
   return camino; 
  }
}