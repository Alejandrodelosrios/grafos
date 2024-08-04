/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import Pesados.DiGrafoPesado;
import Pesados.GrafoPesado;

/**
 *
 * @author EQUIPO
 */
public class IslaPesado {
  private final DFSPesado recorrido;
  private final GrafoPesado elGrafoPesado;
  private final int tamañodelGrafo;
 
 public IslaPesado(GrafoPesado unGrafo){
  this.elGrafoPesado=unGrafo;
  recorrido=new DFSPesado(unGrafo,0);
    this.tamañodelGrafo=unGrafo.cantidadVertices();
 }
 
  public IslaPesado(DiGrafoPesado unGrafo){
  GrafoPesado grafoAuxiliar=new GrafoPesado(unGrafo.cantidadVertices());
  this.elGrafoPesado=unGrafo;
  this.tamañodelGrafo=unGrafo.cantidadVertices();
  this.convertir(grafoAuxiliar);
  recorrido=new DFSPesado(grafoAuxiliar,0);
 }
 
 private void convertir(GrafoPesado grafoConvertido){
  for(int i=0;i<this.tamañodelGrafo;i++){
   Iterable<Integer>adyacentesDelVertice=
                                     this.elGrafoPesado.adyacentesDelVertice(i);
   
   for(Integer adyacente:adyacentesDelVertice){
        double pesoDeLaArista=this.elGrafoPesado.peso(i,adyacente);
        if(!this.elGrafoPesado.existeAdyacencia(adyacente, i)){
         try{     
          grafoConvertido.insertarArista(i,adyacente,pesoDeLaArista);
         }catch(AristaYaExisteException exe){
         }
        }    
    }   
  }
 }
 
// este metodo  revisa si hay islas en el grafo
 public boolean hayIslas(){
  return (!this.recorrido.llegoATodos());
 }
 
 public int contadorDeIslas(){
  if (this.elGrafoPesado instanceof GrafoPesado){     
   return this.contadorDeIslasGrafos();
  }else{
  return this.contadorDeIslasDiGragrosPesados();
  }
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
 
  public int contadorDeIslasDiGragrosPesados(){
  int contador=1;
  for(int i=0;i<this.tamañodelGrafo;i++){
   if(!this.recorrido.llegoAVertice(i)){   
    Iterable<Integer> adyacentesDelVertice=
                                     this.elGrafoPesado.adyacentesDelVertice(i);
    if(this.tieneUnAdyacenteMarcadoPesado(adyacentesDelVertice)){                                                
      this.recorrido.ejecutarDFS(i);
    }else if(this.elverticeEsAdyacente(i)){
      int verticeConEseAdyacente=this.verticeQueTieneEseAdyacente(i);
      this.recorrido.ejecutarDFS(verticeConEseAdyacente);
    }else{   
     contador++;  
     this.recorrido.ejecutarDFS(i);
    }
   }
  }
  return contador;
 }
 
private int verticeQueTieneEseAdyacente(int adyacenteABuscar){
   for(int i=0;i<this.tamañodelGrafo;i++){
   Iterable<Integer> adyacentesDelVertice=
                                     this.elGrafoPesado.adyacentesDelVertice(i);
   for(Integer adyacente: adyacentesDelVertice){
     if(adyacente==adyacenteABuscar){
       return i;   
     }  
   } 
  }
  return -1;
 }        
  
 private boolean elverticeEsAdyacente(int posVertice){
  for(int i=0;i<this.tamañodelGrafo;i++){
   Iterable<Integer> adyacentesDelVertice=
                                     this.elGrafoPesado.adyacentesDelVertice(i);
   for(Integer adyacente: adyacentesDelVertice){
     if(adyacente==posVertice){
       return true;   
     }  
   } 
  }
  return false;
 }   
  
 private boolean tieneUnAdyacenteMarcadoPesado(Iterable<Integer> adyacentes){
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