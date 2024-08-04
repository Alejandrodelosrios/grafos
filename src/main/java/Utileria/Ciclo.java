/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaYaExisteException;
import No_Pesados.DiGrafo;
import No_Pesados.Grafo;
import Pesados.DiGrafoPesado;
import Pesados.GrafoPesado;
/**
 *
 * @author EQUIPO
 */
public class Ciclo {
private final Grafo elGrafo;

 public Ciclo(Grafo unGrafo){
  this.elGrafo=unGrafo;
 }   
 
 public Ciclo(GrafoPesado unGrafo){
  this.elGrafo=new Grafo(unGrafo.cantidadVertices());
  for(int i=0;i<unGrafo.cantidadVertices();i++){
   Iterable<Integer> adyacentesDelVertice=unGrafo.adyacentesDelVertice(i);
   for(Integer adyacente:adyacentesDelVertice){
       try {
           this.elGrafo.insertarArista(i,adyacente);
       } catch (AristaYaExisteException ex) {
       }
    }
  }
 }   
 
 public Ciclo(DiGrafoPesado unDiGrafo){
  this.elGrafo=new DiGrafo(unDiGrafo.cantidadVertices());
  for(int i=0;i<unDiGrafo.cantidadVertices();i++){
   Iterable<Integer> adyacentesDelVertice=unDiGrafo.adyacentesDelVertice(i);
   for(Integer adyacente:adyacentesDelVertice){
       try {
           this.elGrafo.insertarArista(i,adyacente);
       } catch (AristaYaExisteException ex) {
       }
    }
  }
 }   
 
  //ciclo para digrafos
 public boolean haycicloEnDiGrafo(){
  Warshall algortimo=new Warshall(this.elGrafo);   
  boolean[][] matrizDeCamino=algortimo.getMatrizDeCaminos();
  int tamañoDeLaMatriz =matrizDeCamino.length;
  for(int i=0;i<tamañoDeLaMatriz;i++){
   if(matrizDeCamino[i][i]){
     return true;  
   }   
  }
  return false;
 }
 
 public boolean hayCiclosEnGrafo(){    
  DFSModificado recorrido= new DFSModificado(this.elGrafo);
  boolean resultado=recorrido.ejecutarDFS(0);
  if(!resultado){
   for(int i=0;i<this.elGrafo.cantidadVertices();i++){
    if(!recorrido.llegoAVertice(i)){ 
     if(recorrido.ejecutarDFS(i)){
       return true;     
     }  
    } 
  }
 }
 return resultado;  
}

public int contadorDeCiclosEnDiGrafo(Grafo unDiGrafo){
 int contadorDeCiclos=0;
  Warshall algortimo=new Warshall(unDiGrafo);   
  boolean[][] matrizDeCamino=algortimo.getMatrizDeCaminos();
  int tamañoDeLaMatriz =matrizDeCamino.length;
  for(int i=0;i<tamañoDeLaMatriz;i++){
   if(matrizDeCamino[i][i]){
     contadorDeCiclos++;  
   }   
  }
  return contadorDeCiclos;
}

 public int contadorDeCiclosEnGrafos(Grafo unGrafo){
  int contadorDeCiclos=0;
  DFSModificado recorrido= new DFSModificado(unGrafo);
    for(int i=0;i<unGrafo.cantidadVertices();i++){
     if(!recorrido.llegoAVertice(i)){ 
      if(recorrido.ejecutarDFS(i)){
        contadorDeCiclos++;     
      }  
     } 
   }
  return contadorDeCiclos;  
 }
 
}