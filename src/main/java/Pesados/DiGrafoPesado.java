/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesados;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class DiGrafoPesado extends GrafoPesado{
 
 //costructores   
 public DiGrafoPesado(){
  super();   
 }   
 public DiGrafoPesado(int nroDeVertice){
  super(nroDeVertice);   
 }

// este metodo elimina una arista que hay en diGrafo 
 @Override
 public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)
                                                throws AristaNoExisteException {
 //valida los vertices de origen y destino    
 super.validarVertice(posVerticeOrigen);
 super.validarVertice(posVerticeDestino);
 //verifica si no existe adyacencia y la lanza una excepcion si no hay
 if(!super.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
   throw new AristaNoExisteException();  
 }
 //saca la lista de adyacentee con peso del origen
 List<AdyacenteConPeso> adyacenciaDelOrigen =
                                 super.listaDeAdayacencia.get(posVerticeOrigen);
 //y lo borra de esta lista pero para que lo borre le pasamos un adyacente con 
 // peso que creamos ahi mismo para que lo pille y lo borre
 adyacenciaDelOrigen.remove(new AdyacenteConPeso(posVerticeDestino));
 //lo ordenamos  (opcional)
 Collections.sort(adyacenciaDelOrigen);
 }

 // este metodo deberia devolver el grado de un vertice pero en los grafos dirigidos
 // tienen por entrada y por salida por lo que tiene una excepcion 
 @Override
 public int gradoDeVertice(int posVertice) {
  throw new UnsupportedOperationException("este metodo no soporta grafos "
                                                                 + "dirigidos");
 }

 //este metodo inserta una arista con su peso 
 @Override
 public void insertarArista(int posVerticeOrigen,int posVerticeDestino,
                                     double peso)throws AristaYaExisteException{
  //validamos los vertices de origen y destino   
  super.validarVertice(posVerticeOrigen);
  super.validarVertice(posVerticeDestino);
//verificamos si hay una adyacencia entre esos vertices para lanzar una excepcion
  if(super.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
    throw new AristaYaExisteException();   
  }
  // saca la lista de adyacencia con peso del origen
  List<AdyacenteConPeso>adyacenciaDelOrigen=
                                 super.listaDeAdayacencia.get(posVerticeOrigen);
  //y insertamos en la lista pero creamos una adyacencia con peso con el destino y su peso 
  adyacenciaDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,peso));
  //y lo ordenamos
  Collections.sort(adyacenciaDelOrigen);
 }
 
 //este metodo devuelve la cantidad de aristas que entran al vertice
 public int gradoDeEntrada(int posDeVertice){
   //validamos si el vertice esta en rango  
  super.validarVertice(posDeVertice);
  //inicializamos un contador
  int contador=0;
  //hacemos un for de 0 hasta la cantidad de vertices 
  for(int posVerticeDeTurno=0;posVerticeDeTurno<super.listaDeAdayacencia.size();
                                                           posVerticeDeTurno++){
   //sacamos la lista de adyacencia con peso de cada vertice    
   List<AdyacenteConPeso> adyaceciaDeVerticeEnTurno=
                                super.listaDeAdayacencia.get(posVerticeDeTurno);
   //y verificamos si hay el vertice en la lista usando el contains
   if(adyaceciaDeVerticeEnTurno.contains(new AdyacenteConPeso(posDeVertice))){
     contador++;  //contador se incrementa
   }
  }
 return contador; //devuelve el contador
 }

 //este metodo devuelve la cantidad de aristas en todo el grafo
 @Override
 public int cantidadDeAristas() {
  int contadorDeAristas=0; //inicializamos el contador
  //hacemos un for de 0 hasta la cantidad de vertices
 for(int i=0;i<super.listaDeAdayacencia.size();i++){
  //y sacamos la lista de adyacencia con peso de cada vertice   
  List<AdyacenteConPeso>adyacenciaDelVerticeEnTurno=
                                                super.listaDeAdayacencia.get(i);
  //y sumamos el tamaño de las listas de adyacencia con peso
  contadorDeAristas+=adyacenciaDelVerticeEnTurno.size();
 }
 return contadorDeAristas;//retornamos el contador
 }
 
 // este metodo devuelve la cantidad de aristas que salen de un vertice
 public int gradoDeSalida(int posDeVertice){
  //usamos el grado de vertice del grafo no dirigido   
  return super.gradoDeVertice(posDeVertice);
 }
}