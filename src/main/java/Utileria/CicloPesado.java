/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Pesados.GrafoPesado;
/**
 *
 * @author EQUIPO
 */
public class CicloPesado {
private final GrafoPesado elGrafo;

 public CicloPesado(GrafoPesado unGrafo){
  this.elGrafo=unGrafo;
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
  DFSModificadoPesado recorrido= new DFSModificadoPesado(this.elGrafo);
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

public int contadorDeCiclosEnDiGrafo(GrafoPesado unDiGrafo){
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

 public int contadorDeCiclosEnGrafos(GrafoPesado unGrafo){
  int contadorDeCiclos=0;
  DFSModificadoPesado recorrido= new DFSModificadoPesado(unGrafo);
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