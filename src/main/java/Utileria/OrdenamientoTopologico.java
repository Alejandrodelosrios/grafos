/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;


import No_Pesados.DiGrafo;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author EQUIPO
 */
public class OrdenamientoTopologico {
 private final DiGrafo elDigrafo;//copia del digrafo
 //lista con todos los grados de entradas  
 private final List<Integer> gradosDeEntradas;
 private final List<Integer> resultado;
 //lista para marcar los vertices que entran a la cola
 private final ControlMarcado marcados;
 private final Ciclo ciclo;//para ver que n tenga ciclos
 private final Conexo esConexo; //para ver si es debilmente conexo
 
 //constructor para grafos no pesados
 public OrdenamientoTopologico(DiGrafo unDiGrafo){
  this.elDigrafo=unDiGrafo; //copia el digrafo 
  this.gradosDeEntradas=new LinkedList<>();//instancia la lista
  this.resultado=new LinkedList<>();//instancia la lista
  //obtiene la cantidad de vertice
  int cantidadDeVertices=elDigrafo.cantidadVertices();
  marcados=new ControlMarcado(cantidadDeVertices);//instancia la lista de marcado
  this.ciclo=new Ciclo(unDiGrafo);//instancia una clase que ve si hay ciclos
  this.esConexo=new Conexo(this.elDigrafo);//instancia una clase que ve si es conexo
  //hace un for de 0 hasta la cantidad de vertice
  for(int i=0;i<cantidadDeVertices;i++){
     //y va poniendo en la lista segun la posicion del vertice su grado de entrada 
    this.gradosDeEntradas.add(elDigrafo.gradoDeEntrada(i));
  }
  this.ejecutarOrdenamientoTopologico();
 }
 
 //este metodo  devuelve una lista con el ordenamiento topologico
 public void ejecutarOrdenamientoTopologico(){
  //verifica que no tenga ciclos y que sea debilmente conexo
  if(!this.ciclo.haycicloEnDiGrafo()&& esConexo.esDebilmenteConexo()){
   //crea la cola que guardara la posiciones de los grados de entrada igual a 0   
   Queue<Integer> colaDeVertices=new LinkedList<>(); 
   do{
    //hace un for de 0 hasta la cantidad de vertices para buscar otras posiciones con grado 0    
    for(int i=0;i<this.gradosDeEntradas.size();i++){
      //verifica si hay una posicion de vertice que tenga grado 0 y no este marcado   
     if(this.gradosDeEntradas.get(i)==0 && !marcados.estaMarcado(i)){
      //si pilla uno lo mete a la cola y lo marca   
      colaDeVertices.offer(i);
      marcados.marcarVertice(i);
     }   
    }//fin del for
    int verticeEnTurno=colaDeVertices.poll();//sacamos la posicion del vertice con grado 0
    this.resultado.add(verticeEnTurno);//lo metemos a la lista
    //buscamos sus adyacentes del vertice que sacamos
   Iterable<Integer> adyacentesDelVertice=this.elDigrafo.adyacentesDelVertice(verticeEnTurno);
    //hacemos un for each
    for(Integer adyacentes:adyacentesDelVertice){
      //verificamos si el grado de entrada del adyacente es mayor a 0  
     if(!this.marcados.estaMarcado(adyacentes)){   
      //le restamos uno al grado del adyacente y lo actualizamos en la lista   
      int nuevoGrado=this.gradosDeEntradas.get(adyacentes)-1;
      this.gradosDeEntradas.set(adyacentes,nuevoGrado);
     }
    }//fin del for each
    //iteramos hasta que los dos de 0
   }while(!colaDeVertices.isEmpty()||!this.marcados.estanTodosMarcados());
  }
 }
 
 // devuelve el resultado del ordenamiento topologico en forma de iterable
 public Iterable<Integer> getOrdenamientoTopologico(){
  return (Iterable) this.resultado;    
 }
 
} 