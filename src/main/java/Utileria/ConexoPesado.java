/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaYaExisteException;
import No_Pesados.Grafo;
import Pesados.GrafoPesado;
import Pesados.GrafoPesado;

/**
 *
 * @author EQUIPO
 */
public class ConexoPesado {
 private final GrafoPesado elGrafo;
    
 public ConexoPesado(GrafoPesado unGrafo){ 
 this.elGrafo=unGrafo;    
 }
 
 public boolean esConexo(){
  DFSPesado recorrido=new DFSPesado(this.elGrafo,0);
  return recorrido.llegoATodos();
 }
 
 public boolean esFuertementeConexo(){
  for(int i=0;i<this.elGrafo.cantidadVertices();i++){
    DFSPesado recorrido=new DFSPesado(this.elGrafo,i);  
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
    try{   
    grafoAuxiliar.insertarArista(i,adyacente);
    }catch(AristaYaExisteException exception){
    }
   }
  }   
 }
 
}