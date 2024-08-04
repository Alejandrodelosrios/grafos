/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Pesados.GrafoPesado;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author EQUIPO
 */
public class Dijkstra{
  private final GrafoPesado elGrafo;
  private final ControlMarcado marcados;
  private final List<Double> listaDeCostos;
  private final List<Integer> listaDePredecesores;
  public static final double INFINITO=Double.POSITIVE_INFINITY;
  
  public Dijkstra(GrafoPesado elGrafo) {
    this.elGrafo = elGrafo;
    this.marcados=new ControlMarcado(this.elGrafo.cantidadVertices());
    this.listaDeCostos=new ArrayList<>();  
    this.listaDePredecesores=new ArrayList<>();
    this.validarCostos();
    this.cargarListas();
  }
   
  private void  validarCostos(){
   for(int i=0;i<this.elGrafo.cantidadVertices();i++){
    Iterable<Integer> adyacentesDelVertice =
                                           this.elGrafo.adyacentesDelVertice(i);
   for (Integer adyacentes:adyacentesDelVertice){
     double peso=this.elGrafo.peso(i,adyacentes);
     if(peso<0){
      throw new IllegalArgumentException("tiene costos negativos");  
     }   
   } 
  }
 }    
 
  private void cargarListas(){
   for(int i=0; i<this.elGrafo.cantidadVertices();i++){
    this.listaDeCostos.add(Dijkstra.INFINITO);
    this.listaDePredecesores.add(-1);
   }   
  }
  
  public void ejecutarDijkstra(int posVerticeOrigen,int posVerticeDestino){
    this.listaDeCostos.set(posVerticeOrigen,0.0);
    do{
     int posVerticeDeCostoMinimo=this.verticeDeMenorCosto();
     this.marcados.marcarVertice(posVerticeDeCostoMinimo);   
     Iterable<Integer> adyacentesDelVertice=
                     this.elGrafo.adyacentesDelVertice(posVerticeDeCostoMinimo);
      for (Integer adyacentes:adyacentesDelVertice){
       double minimo=this.listaDeCostos.get(posVerticeDeCostoMinimo)+
              this.elGrafo.peso(posVerticeDeCostoMinimo,adyacentes);
     if(this.listaDeCostos.get(adyacentes)>minimo){
        this.listaDeCostos.set(adyacentes, minimo);
        this.listaDePredecesores.set(adyacentes,posVerticeDeCostoMinimo);
     }
     }
    }while(!this.marcados.estaMarcado(posVerticeDestino)&&
                                              costoMinimo()!=Dijkstra.INFINITO); 
   }
  
  private int verticeDeMenorCosto(){
    double costoMenor=this.costoMinimo();
   for(int i=0;i<this.elGrafo.cantidadVertices();i++){
     if(this.listaDeCostos.get(i)==costoMenor && !this.marcados.estaMarcado(i)){
      return i;   
     } 
   }
   return -1;  
  }
  
  private Iterable<Integer> verticesNoMarcados(){
   List<Integer> listaDePosNoMarcados=new ArrayList<>();
   for(int i=0;i<this.elGrafo.cantidadVertices();i++){
      if(!this.marcados.estaMarcado(i)){
        listaDePosNoMarcados.add(i);
      }  
   }
   return (Iterable)listaDePosNoMarcados;   
  }  
    
  private double costoMinimo(){
   Iterable<Integer>verticesNoMarcados=this.verticesNoMarcados();
    double costo=Dijkstra.INFINITO; 
    for(Integer vertices:verticesNoMarcados){
     if(costo>this.listaDeCostos.get(vertices)){
       costo=this.listaDeCostos.get(vertices);  
     }   
    }
    return costo; 
  }
   
  public String imprimirCamino(int posVerticeOrigen,int posVerticeDestino){
   String camino="";
  if(this.hayCamino(posVerticeOrigen, posVerticeDestino)){
    Stack<Integer> pilaDeCaminos=new Stack<>();
    pilaDeCaminos.push(posVerticeDestino);
    do{
     int vertice=pilaDeCaminos.pop();
      camino=vertice+camino;
      if (this.listaDePredecesores.get(vertice)!= -1){
        pilaDeCaminos.push(this.listaDePredecesores.get(vertice));
        camino="->"+camino;
      }   
    }while(!pilaDeCaminos.isEmpty());
  }
  return camino;   
  } 
  
   public double mostrarCosto(int posVerticeDestino){      
    return this.listaDeCostos.get(posVerticeDestino);
   }
   
  public boolean hayCamino(int posVerticeOrigen,int posVerticeDestino){
   return this.listaDeCostos.get(posVerticeDestino)!=Dijkstra.INFINITO &&
                                            posVerticeOrigen!=posVerticeDestino;
  }
 
}