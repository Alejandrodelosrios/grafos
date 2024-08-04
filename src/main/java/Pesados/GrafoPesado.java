/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesados;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class GrafoPesado {
 protected final List<List<AdyacenteConPeso>> listaDeAdayacencia; // lista de lista  
 
 //constructores
 public GrafoPesado(){
  this.listaDeAdayacencia=new ArrayList<>();         
 }
 
 public GrafoPesado(int nroVertices){
  if(nroVertices<0){
    throw new IllegalArgumentException("la cantidad de vertices no puede ser"+
             "negativo");  
  }
  this.listaDeAdayacencia=new ArrayList<>();
  for(int i=0;i<nroVertices;i++){
    this.insertarVertice();   
  }
 }
 /*mtodo que inserta vertices con su lista de adyacentes con peso */
 public void insertarVertice(){
  List<AdyacenteConPeso> adayacenciaDelNuevoVertice= new ArrayList<>();
  this.listaDeAdayacencia.add(adayacenciaDelNuevoVertice);
 }
 
 //este metodo valida el vertice del parametro si esta en rango 
 public void validarVertice(int posVertice){
   //si esta fuera del rango lanza una excepcion  
  if(posVertice<0 || posVertice>=this.listaDeAdayacencia.size()){
   throw new IllegalArgumentException("posicion de vertice invalido");
  }
 }
 
 // este metodo verifica si hay adyacencia entre dos vertices
 public boolean existeAdyacencia(int posVerticeOrigen,int posVerticeDestino){
  //validamos los vertices   
  this.validarVertice(posVerticeOrigen);
  this.validarVertice(posVerticeDestino);
  //sacamos la lista de adyacentes con peso del vertice origen
  List<AdyacenteConPeso> adyacentesDelOrigen =
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
  //y creamos una variable adycente con peso que tendra el vertice destino
  AdyacenteConPeso adyacenteDestino=new AdyacenteConPeso(posVerticeDestino);
  //usamos el contains y verificara si hay el vertice destino en la lista del origen 
  return adyacentesDelOrigen.contains(adyacenteDestino);
 }
 
 //este metodo inserta una arista 
 public void insertarArista(int posVerticeOrigen,int posVerticeDestino,
                                     double peso)throws AristaYaExisteException{
   //verifica si existe una arista entre los vertices  
  if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
    throw new AristaYaExisteException();  
  }
  //sacamos la lista de adyacentes con peso del origen
  List<AdyacenteConPeso>adyacentesDelOrigen =
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
  //y lo insertamos en la lista  pero lo mandamos creado como adyacente con peso
  adyacentesDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,peso));
  //y usando el collection sort lo ordena
  Collections.sort(adyacentesDelOrigen);
  //verificamos que son diferentes los vertice origen y el destino
  if(posVerticeOrigen!=posVerticeDestino){
    //si son diferentes hacemos un lo mismo pero en vez de origen sera destino  
    List<AdyacenteConPeso> adyacentesDelDestino=
                                 this.listaDeAdayacencia.get(posVerticeDestino);
    adyacentesDelDestino.add(new AdyacenteConPeso(posVerticeOrigen,peso));
    Collections.sort(adyacentesDelDestino);
  }
 }
 
 //este metodo devuelve la cantidad de vertices que hay en el grafo
 public int cantidadVertices(){
  //retoramos el size de la lista   
  return this.listaDeAdayacencia.size();
 }
 
 //este metodo cuenta cuantas aristas hay en el grafo
 public int cantidadDeAristas(){
  int contadorDeArista=0;  
  int contadorDeLazo=0;  // contador de aristas que salen y entran al mismo vertice
  //haceos un for
  for(int posVerticeEnTurno=0;posVerticeEnTurno<this.listaDeAdayacencia.size();
                                                           posVerticeEnTurno++){
    //sacamos los adyacentes  del vertice en turno con un iterable 
   Iterable<Integer> adyacentesDelVertice=
                                   this.adyacentesDelVertice(posVerticeEnTurno);
   // hacemos un for each
   for(Integer adyacentes: adyacentesDelVertice){
       //verificamos si la arista es lazo o no
      if(adyacentes==posVerticeEnTurno){
         contadorDeLazo++;  
      }else{
       contadorDeArista++;   
      } 
   }//fin del for each
  } // fin del for
  //sumamos ambos contadores y dividimos entre 2 y eso retornamos
  return (contadorDeArista/2)+contadorDeLazo; 
 }
 
 // este metodo devuelve la cantidad de aristas que salen de un vertce
 public int gradoDeVertice(int posVertice){
    // validamos el vertice 
   this.validarVertice(posVertice);
   //sacamos la lista de adyacentes con peso del vertice
   List<AdyacenteConPeso> adyacenteDelVertice=this.listaDeAdayacencia.get(posVertice);
   return adyacenteDelVertice.size(); // y retornamos el size de esa lista
 }
 //este metodo elimina un vertice
 public void eliminarVertice(int posVertice){
   //validamos el vertice  y removemos de la lista de listas
   this.validarVertice(posVertice);
   this.listaDeAdayacencia.remove(posVertice);
   //jacemos un for each
   for(List<AdyacenteConPeso> adyacentesDeUnVertice:this.listaDeAdayacencia){
      //buscamos en las listas de adyacentes con peso si tienen el vertice que eliminamos 
     int posDeLaAdyacencia=adyacentesDeUnVertice.indexOf(new AdyacenteConPeso(posVertice));
     //si la variable es mayor igual a 0 quiere decir que la pillo y removemos
     if(posDeLaAdyacencia>=0){
       adyacentesDeUnVertice.remove(posDeLaAdyacencia);
     }
   }//fin del for each
    //hacemos un for 
     for(int i=0;i<this.listaDeAdayacencia.size();i++){
      //sacamos la lista de adyacentes con peso de cada vertice    
      List<AdyacenteConPeso> adyacentesDeUnVertice =
                                                 this.listaDeAdayacencia.get(i);
      //y hacemos un for each
      for(AdyacenteConPeso elementoEnTurno:adyacentesDeUnVertice){
       //revisamos el indecie de vertice del adyacente con peso si es mayor al 
       //vertie que queremos eliminar   
      if(elementoEnTurno.getIndiceDeVertice()>posVertice){
        //si es mayor le restamos uno y lo reescribimos  
        elementoEnTurno.setIndiceDeVertice
                                       (elementoEnTurno.getIndiceDeVertice()-1);
        adyacentesDeUnVertice.set(i,elementoEnTurno);
      }
     }//fin del for each
   }//fin del for
 }
 
 //este metodo elimina una arista entre dos vertices
 public void eliminarArista(int posVerticeOrigen,int posVerticeDestino)
                                                 throws AristaNoExisteException{
   // validamos los vertices de origen y destino    
  this.validarVertice(posVerticeOrigen);
  this.validarVertice(posVerticeDestino);
  //verificamos si no existe adyacencia entre estos vertice y lanzaos una excepcion
  if(!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
    throw new AristaNoExisteException();   
  }
  // sacamos la lista de adyacentes con peso del vertice origen
  List<AdyacenteConPeso> adyacentesDelOrigen=
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
  //y borramos el adyacente con peso que tiene el destino
  adyacentesDelOrigen.remove(new AdyacenteConPeso(posVerticeDestino));
  Collections.sort(adyacentesDelOrigen); // opcional
  // verificamos si el destino es diferente al origen
  if(posVerticeOrigen!=posVerticeDestino){
    //hacemos lo mismo que el origen pero desde el destino  
    List<AdyacenteConPeso> adyacentesDelDestino=
                                 this.listaDeAdayacencia.get(posVerticeDestino);
    adyacentesDelDestino.remove(new AdyacenteConPeso(posVerticeOrigen));
    Collections.sort(adyacentesDelDestino); 
  }
 }
 
 //este metodo devuelve un iterable con los adyacentes que tiene un vertice
 public Iterable<Integer> adyacentesDelVertice(int posDeVertice){
  //validmaos el vertice   
  this.validarVertice(posDeVertice);
  //sacamos su lista de adyacentes con peso y creamos una lista de enteros
  List<AdyacenteConPeso> adyacenteDelVertice=this.listaDeAdayacencia.get(posDeVertice);
  List<Integer> copiaDeAdyacencia=new ArrayList<>();
  //hacemos un for each
  for(AdyacenteConPeso adyacente:adyacenteDelVertice){
     //y en la lista de enteros vamos copiando los indices destinos 
   copiaDeAdyacencia.add(adyacente.getIndiceDeVertice());
  }
  //retornamos la lista casteada como iterable
  return (Iterable) copiaDeAdyacencia;
 }
 
 public double peso (int posVerticeOrigen,int posVerticeDestino){
   if(!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
    throw new IllegalArgumentException("no existe esa arista");
   }
   List<AdyacenteConPeso> adyacenteDelOrigen = 
                                  this.listaDeAdayacencia.get(posVerticeOrigen);
   AdyacenteConPeso adyacenteDestino=new AdyacenteConPeso(posVerticeDestino);
   int posicionDelAdyacente=adyacenteDelOrigen.indexOf(adyacenteDestino);
   AdyacenteConPeso adyacenteDestinoConPeso=
                                   adyacenteDelOrigen.get(posicionDelAdyacente);
   return adyacenteDestinoConPeso.getPeso();
 }
}