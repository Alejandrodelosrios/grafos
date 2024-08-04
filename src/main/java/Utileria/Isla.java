/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaYaExisteException;
import No_Pesados.DiGrafo;
import No_Pesados.Grafo;

/**
 *
 * @author EQUIPO
 */
public class Isla {
  private final DFS recorrido;
  private final Grafo elGrafo;
  private final int tamañodelGrafo;
 
 public Isla(Grafo unGrafo){
  recorrido=new DFS(unGrafo,0);
  this.elGrafo=unGrafo;
  this.tamañodelGrafo=unGrafo.cantidadVertices();
 }    
 
 public Isla(DiGrafo unGrafo){
  Grafo grafoAuxiliar=new Grafo(unGrafo.cantidadVertices());
  this.elGrafo=unGrafo;
  this.convertir(grafoAuxiliar);
  recorrido=new DFS(grafoAuxiliar,0);
  this.tamañodelGrafo=unGrafo.cantidadVertices();
 }
 
 private void convertir(Grafo grafoConvertido){
  for(int i=0;i<this.tamañodelGrafo;i++){
   Iterable<Integer> adyacentesDelVertice=this.elGrafo.adyacentesDelVertice(i);
   for(Integer adyacentes:adyacentesDelVertice){
    if(!this.elGrafo.existeAdyacencia(adyacentes, i)){
      try{  
      grafoConvertido.insertarArista(i,adyacentes);
      }catch(AristaYaExisteException ex){          
      }
    }   
   }
  }   
 }
// este metodo  revisa si hay islas en el grafo
 public boolean hayIslas(){
  return !this.recorrido.llegoATodos();
 }
 
 public int contadorDeIslas(){
  if(this.elGrafo instanceof Grafo){
    return this.contadorDeIslasGrafos();
  }else
   return this.contadorDeIslasDiGragros();
 }
 
 public int contadorDeIslasGrafos(){
  int contador=1;   
  for(int i=0;i<this.tamañodelGrafo;i++){
    if(!this.recorrido.llegoAVertice(i)){
      contador++;
      this.recorrido.ejecutarDFS(i);
    }   
  }
  return contador;
 }
 
 public int contadorDeIslasDiGragros(){
  int contador=1;
  for(int i=0;i<this.tamañodelGrafo;i++){
   if(!this.recorrido.llegoAVertice(i)){   
    Iterable<Integer> adyacentesDelVertice=this.elGrafo.adyacentesDelVertice(i);
    if(this.tieneUnAdyacenteMarcado(adyacentesDelVertice)){
      this.recorrido.ejecutarDFS(i);
    }else{
     contador++;   
     this.recorrido.ejecutarDFS(i);
    }
   }
  }
  return contador;
 }
 
 private boolean tieneUnAdyacenteMarcado(Iterable<Integer> adyacentes){
   for (Integer posDeAdyacentes:adyacentes){
     if(this.recorrido.llegoAVertice(posDeAdyacentes)){
       return true;   
     }  
   }
  return false;   
 }
 
 public int getVerticeNoMarcado (){
   for(int i=0;i<this.tamañodelGrafo;i++){
    if(!this.recorrido.llegoAVertice(i)){
      return i;  
    }  
   }
   return -1;
 } 
}