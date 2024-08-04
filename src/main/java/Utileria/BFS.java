/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import No_Pesados.Grafo;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author EQUIPO
 */
public class BFS {
 private final Grafo elGrafo;//copia la referencia de memoria del grafo
 private final ControlMarcado marcados;// una lista para marcar los vertices 
 private final List<Integer> recorrido;// una lista que tiene los vertices que visita
 
 //constructor para grafos y digrafos no pesados
 public BFS(Grafo unGrafo,int posVerticeInicial){
  this.elGrafo=unGrafo; //copia la referencia
  this.recorrido=new LinkedList<>(); //instancia la lista
  this.marcados=new ControlMarcado(elGrafo.cantidadVertices());//instancia la lista de marcados
  this.ejecutarBFS(posVerticeInicial);//llamam al metodo que hace el recorrido     
 }
  
 //este metodo ejecuta el recorrido con el posVertice que recibe en el constructor
 public void ejecutarBFS(int posDeVertice){
  this.elGrafo.validarVertice(posDeVertice);//validamos si el vertice esta en rango
  Queue<Integer> colaDeVertices=new LinkedList<>();//creamos una cola para los vertices
  colaDeVertices.offer(posDeVertice);//metemos el vertice que recibimos a la cola
  this.marcados.marcarVertice(posDeVertice);//marcamos ese vertice en la lista de marcados
  do{
   int posVerticeActual=colaDeVertices.poll();//saca el vertice de la cola
   //obtiene un iterable con todos los adyacentes del vertice 
   Iterable<Integer> adyacenteDeVerticeActual=
                            this.elGrafo.adyacentesDelVertice(posVerticeActual);
   recorrido.add(posVerticeActual);//lo anota en la lista
   //hace un for each con los adyacentes del vertice
   for(Integer posDeAdyacente:adyacenteDeVerticeActual){
    // verifica si el adyacente no esta marcado   
    if(!this.marcados.estaMarcado(posDeAdyacente)){
      // lo mete en la cola  y lo marca
      colaDeVertices.offer(posDeAdyacente);
      this.marcados.marcarVertice(posDeAdyacente);
    }   
   }//fin del for each
  }while(!colaDeVertices.isEmpty()); //hace este ciclo hasta que la cola este vacia
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