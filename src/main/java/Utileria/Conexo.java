/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaYaExisteException;
import No_Pesados.Grafo;
import Pesados.GrafoPesado;

/**
 *
 * @author EQUIPO
 */
public class Conexo {
 private final Grafo elGrafo;
    
 public Conexo(Grafo unGrafo){ 
 this.elGrafo=unGrafo;    
 }
 
 public boolean esConexo(){
  DFS recorrido=new DFS(this.elGrafo,0);
  return recorrido.llegoATodos();
 }
 
 public boolean esFuertementeConexo(){
  for(int i=0;i<this.elGrafo.cantidadVertices();i++){
    DFS recorrido=new DFS(this.elGrafo,i);  
    if(!recorrido.llegoATodos()){
       return false;  
    }
  }
  return true;
 }
  
 public boolean esDebilmenteConexo(){
  Grafo grafoAuxiliar=new Grafo(this.elGrafo.cantidadVertices());
  this.convertirAGrafoNoDirigido(grafoAuxiliar);
  DFS recorrido=new DFS(grafoAuxiliar,0);
  return recorrido.llegoATodos();
 }
 
 private void convertirAGrafoNoDirigido(Grafo grafoAuxiliar){
  for(int i=0;i<this.elGrafo.cantidadVertices();i++){
   Iterable<Integer> adyacentesDelVerticeEnTurno=
                                           this.elGrafo.adyacentesDelVertice(i);
   
   for(Integer adyacente:adyacentesDelVerticeEnTurno){
    if(!grafoAuxiliar.existeAdyacencia(i,adyacente))   
    try{   
    grafoAuxiliar.insertarArista(i,adyacente);
    }catch(AristaYaExisteException exception){
    }
   }
  }   
 } 
}