/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import No_Pesados.Grafo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class DFS {
 private final Grafo elGrafo; //copia la referencia de memoria del grafo
 private final ControlMarcado marcados; //marca los vertice que visite
 // devuelve una lista con los vertices que visito
 private final List<Integer> recorrido; 
 
 //constructor para grafos o digrafos no pesados
 public DFS(Grafo unGrafo,int posVerticeInicial){
  this.elGrafo=unGrafo; // copia la referencia de memoria
  this.recorrido=new ArrayList<>(); // instancia la lista
  this.marcados=new ControlMarcado(elGrafo.cantidadVertices());// instancia la lista de marcados
  this.ejecutarDFS(posVerticeInicial);//ejecuta el recorrido dfs con el posVertice que le pasamos     
 }
 
// este metodo ejectua el recorrido dfs con el posVertice que recibe del constructor 
 public void ejecutarDFS(int posDeVerticeActual){
  this.elGrafo.validarVertice(posDeVerticeActual);//validamos si el vertice esta en rango
  this.marcados.marcarVertice(posDeVerticeActual);// lo marcamos en la lista de marcados
  Iterable<Integer> adyacenteDeVerticeActual=
                          this.elGrafo.adyacentesDelVertice(posDeVerticeActual);// sacamos un iterable con los adyacentes del vertice
   recorrido.add(posDeVerticeActual);//lo anotamos en la lista de visitados
   for(Integer posDeAdyacente:adyacenteDeVerticeActual){//for each de los adyacentes
     //verifica si el adyacente no esta marcado  
    if(!this.marcados.estaMarcado(posDeAdyacente)){
      //haceso recursion a partir de ese adyacente  
      this.ejecutarDFS(posDeAdyacente);
    }   
   }//fin del for each
  }
 
 // este metodo devuelve la lista de los vertics visitados en forma de iterable 
 public Iterable<Integer> getRecorrido(){
  return this.recorrido;   
 }
 
 // este metodo devuelve un booleano si llego a todos los vertices del grafo
 public boolean llegoATodos(){
   // revisamos en la lista de marcados y lo devuelve  
  return this.marcados.estanTodosMarcados();
 }
 
 //este metodo revisa si un vertice fue visittado o no
 public boolean llegoAVertice(int posVertice){
   //valida si el vertice esta en rango y luego revisa en la lista de marcados  
  this.elGrafo.validarVertice(posVertice);
  return this.marcados.estaMarcado(posVertice);
 } 
}