/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package No_Pesados;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class Grafo {
 protected final List<List<Integer>> listaDeAdayacencia;//lista de listas 
 
 //constructor 
 public Grafo(){
  this.listaDeAdayacencia=new ArrayList<>();         
 }
 //constructor que recibe la cantidad de vertice que tendra el grafo
 public Grafo(int nroVertices){
  //verificamos si el nrodevertices es menor a 0   
  if(nroVertices<0){
    throw new IllegalArgumentException("la cantidad de vertices no puede ser"+
             "negativo");  
  }
  //caso contrario hacemos que la lista tenga todos los vertices 
  this.listaDeAdayacencia=new ArrayList<>();
  for(int i=0;i<nroVertices;i++){
    this.insertarVertice();   
  }
 }
 /*metodo que inserta vertices en la lista original*/
 public void insertarVertice(){
  //crea la lista de adyacencia que tiene cada vertice    
  List<Integer> adayacenciaDelNuevoVertice= new ArrayList<>();
  //y la añadimos en la lista de adyacencia
  this.listaDeAdayacencia.add(adayacenciaDelNuevoVertice);
 }
 //este metodo valida si el vertice esta en rango   
 public void validarVertice(int posVertice){
   //verificamos si posVertice no es nemor de 0 y no es mayor igual al tamaño de
                                                                      //la lista   
  if(posVertice<0 || posVertice>=this.listaDeAdayacencia.size()){
   // si es manda una excepcion   
   throw new IllegalArgumentException("posicion de vertice invalido");
  }
 }
 // este metodo devuelve un boolean si hay o no una adyacencia entre 
                                                               // dos posVertice
 public boolean existeAdyacencia(int posVerticeOrigen,int posVerticeDestino){
  //validamos ambos vertices si estan en rango   
  this.validarVertice(posVerticeOrigen);
  this.validarVertice(posVerticeDestino);
  //sacamos la lista de adyacencia del posVerticeOrigen 
  List<Integer> adyacentesDelOrigen =
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
  // y usamos el contains para revisar si hay ese elemento o no en la lista de
                                           // la adyacencia del posVeritceOrigen 
  return adyacentesDelOrigen.contains(posVerticeDestino);
 }
 // este metodo inserta una arista desde un posVerticeOrigen a un 
                                                             //posVerticeDestino 
 public void insertarArista(int posVerticeOrigen,int posVerticeDestino)
                                                 throws AristaYaExisteException{
  //verificamos si existe ya una adyacencia entre estos vertices   
  if(this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
    //mandamos una excepcion  
    throw new AristaYaExisteException();  
  }
  //en caso de no haber una adyacencia entre estos vertices sacamos la lista de 
  // adyacencia del posVerticeOrigen
  List<Integer>adyacentesDelOrigen =
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
  //y añadimos en esa lista la posicion del vertice destino
  adyacentesDelOrigen.add(posVerticeDestino);
  // usamos el Collections para ordenar la lista
  Collections.sort(adyacentesDelOrigen);
  // verificamos que sean distintos los vertices
  if(posVerticeOrigen!=posVerticeDestino){
    //y hacemos los msmos pasos solo que con el vertice destino en su lugar 
    List<Integer> adyacentesDelDestino=
                                 this.listaDeAdayacencia.get(posVerticeDestino);
    adyacentesDelDestino.add(posVerticeOrigen);
    Collections.sort(adyacentesDelDestino);
  }
 }
 //este metodo devuelve la cantidad de vertice que tiene el grafo
 public int cantidadVertices(){   
  return this.listaDeAdayacencia.size();
 }
 //este metodo devuelve la cantidad de aristas que tiene el grafo
 public int cantidadDeAristas(){
  //creamos dos dos contadores uno para aristas y otro pára lazos(ciclos)    
  int contadorDeArista=0;
  int contadorDeLazo=0;
  //hacemos un for de 0 hasta el tamaño de la lista de adyacencia
  for(int posVerticeEnTurno=0;posVerticeEnTurno<this.listaDeAdayacencia.size();
                                                           posVerticeEnTurno++){
   //sacamos la lista de adyacencias de cada posVerticeEnTurno   
   List<Integer>adyacentesDelVertice=
                                 this.listaDeAdayacencia.get(posVerticeEnTurno);
   //y hacemos un for each con esa lista de adyacecia
   for(Integer adyacentes: adyacentesDelVertice){
      // verificamos si el adyacente es igual al posVerticeEnTurno 
      if(adyacentes==posVerticeEnTurno){
         //si son iguales crece el contador de lazos(ciclos) 
         contadorDeLazo++;  
      }else{
        //caso contrario crece el contador de aristas   
       contadorDeArista++;   
      } 
   }
  }
  // al final dividimos entre dos el contador de aristas y sumamos el contador 
                                                                      // de lazo
  return (contadorDeArista/2)+contadorDeLazo;
 }
 //este metodo retorna el grado que tiene un vertice
 public int gradoDeVertice(int posVertice){
   //validamos el vertice  
   this.validarVertice(posVertice);
   //sacamos la lista de adyacencia de ese vertice 
   List<Integer> adyacenteDelVertice=this.listaDeAdayacencia.get(posVertice);
   // y retornamos el size de dicha lista 
   return adyacenteDelVertice.size();
 }
 //este metodo elimina un vertice que se le pase 
 public void eliminarVertice(int posVertice){
    //validamos el vertice 
   this.validarVertice(posVertice);
   // lo borramos de la lista de adyacecia
   this.listaDeAdayacencia.remove(posVertice);
    //hacemos un for each 
   for(List<Integer> adyacentesDeUnVertice:this.listaDeAdayacencia){
     //sacamos las listas de adyacecia de cada posicion y revisamos si tenian
     //una arista con ese vertice 
     int posDeLaAdyacencia=adyacentesDeUnVertice.indexOf(posVertice);
     //verificamos posDeLaAdyacencia es mayor o igual a 0
     if(posDeLaAdyacencia>=0){
        //si es eliminamos  
       adyacentesDeUnVertice.remove(posDeLaAdyacencia);
     }
   }
    //terminado eso hacemos un for de 0 hasta el tamaño de la lista de 
                                                                   //adyacencia 
     for(int i=0;i<this.listaDeAdayacencia.size();i++){
      //sacamos las listas de adyacencias de cada vertice   
      List<Integer> adyacentesDeUnVertice=this.listaDeAdayacencia.get(i);
      //y hacemos un for each a cada lista de adyacencia que saquemos
      for(Integer elementoEnTurno:adyacentesDeUnVertice){
      //verificamos si el elementoenTurno es mayor al vertice que eliminamos    
      if(elementoEnTurno>posVertice){
        //si es asi le restamos uno   
        elementoEnTurno--;
        //y lo seteamos en la misma posicion
        adyacentesDeUnVertice.set(i,elementoEnTurno);
      }
     }//fin del for each
   }//fin del for
 }
 //este metodo elimina una arista de un vertice origen y un vertice destino
 public void eliminarArista(int posVerticeOrigen,int posVerticeDestino)
                                                 throws AristaNoExisteException{
   //validamos los vertices  
  this.validarVertice(posVerticeOrigen);
  this.validarVertice(posVerticeDestino);
  //verificamos si existe la adyacencia de ambos vertices
  if(!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
     //en caso de no haber mandamos una excepcion 
    throw new AristaNoExisteException();   
  }
  //sacamos la lista de adyacencia del vertice origen
  List<Integer> adyacentesDelOrigen=
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
  //y lo removemos el vertice destino (haciendo un casting)
  adyacentesDelOrigen.remove((Integer) posVerticeDestino);
  //podemos usar el collections sort (opcional)
  Collections.sort(adyacentesDelOrigen);
  //volvemos a hacer la pregunta que en el insertar
  if(posVerticeOrigen!=posVerticeDestino){
    //en caso de ser distintos sacamos la lista de adyacencia del destino
    // y hacemos lo mismo que en el vertice origen
    List<Integer> adyacentesDelDestino=
                                 this.listaDeAdayacencia.get(posVerticeDestino);
    adyacentesDelDestino.remove((Integer)posVerticeOrigen);
    Collections.sort(adyacentesDelDestino); 
  }
 }
 //este metod hace un iterable que es como una lista para los for each
 public Iterable<Integer> adyacentesDelVertice(int posDeVertice){
   //validamos el vertice  
  this.validarVertice(posDeVertice);
  //y sacamos la lista de adyacencia del vertice
  List<Integer> adyacenteDelVertice=this.listaDeAdayacencia.get(posDeVertice);
  //creamos una lista que sera la copia 
  List<Integer> copiaDeAdyacencia=new ArrayList<>();
  //hacemos un for each de los adyacente del vertice
  for(Integer adyacente:adyacenteDelVertice){
   //lo vamos añadiendo en la copia   
   copiaDeAdyacencia.add(adyacente);
  }
  //retornamos la copia pero con un casting de iterable
  return (Iterable) copiaDeAdyacencia;
 }
}