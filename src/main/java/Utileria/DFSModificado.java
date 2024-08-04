/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaYaExisteException;
import No_Pesados .Grafo;
/**
 *
 * @author EQUIPO
 */
public class DFSModificado {
 private final Grafo elGrafo;
 private final Grafo grafoAuxiliar;
 private final ControlMarcado marcados;
 
 public DFSModificado(Grafo unGrafo){
  this.elGrafo=unGrafo;
  this.grafoAuxiliar=new Grafo(this.elGrafo.cantidadVertices());
  this.marcados=new ControlMarcado(unGrafo.cantidadVertices());     
 }
 public boolean ejecutarDFS(int posDeVerticeActual){
  this.elGrafo.validarVertice(posDeVerticeActual);
  this.marcados.marcarVertice(posDeVerticeActual);
  Iterable<Integer> adyacenteDeVerticeActual=
                          this.elGrafo.adyacentesDelVertice(posDeVerticeActual);
   for(Integer posDeAdyacente:adyacenteDeVerticeActual){  
    if(!this.marcados.estaMarcado(posDeAdyacente)){
        try {
         this.grafoAuxiliar.insertarArista(posDeVerticeActual,posDeAdyacente);
         if(this.ejecutarDFS(posDeAdyacente)){
            return true; 
         }
        } catch (AristaYaExisteException ex) {
         }
    }else{
      if(!grafoAuxiliar.existeAdyacencia(posDeVerticeActual,posDeAdyacente)){
        return true;  
      }  
    }   
   }//fin del for each
   return false;
  }
 
 public boolean llegoATodos(){
  return this.marcados.estanTodosMarcados();
 }
 
 public boolean llegoAVertice(int posVertice){
  this.elGrafo.validarVertice(posVertice);
  return this.marcados.estaMarcado(posVertice);
 }
}