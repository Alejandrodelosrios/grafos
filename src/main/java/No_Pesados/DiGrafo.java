/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package No_Pesados;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class DiGrafo extends Grafo{
 
 public DiGrafo(){
  super();   
 }   
 public DiGrafo(int nroDeVertice){
  super(nroDeVertice);   
 }

 @Override
 //este metodo se reescribio por que un digrafo solo borra en un sentido
 public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)
                                                throws AristaNoExisteException {
 //valida los vertices y verifica que ambos vertices tengan una adyacencia    
 super.validarVertice(posVerticeOrigen);
 super.validarVertice(posVerticeDestino);
 if(!super.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
   //si no tienen adyacencia este manda una excepcion  
   throw new AristaNoExisteException();  
 }
 //en caso que si tenga saca la lista de adyacencia del vertice origen
 List<Integer> adyacenciaDelOrigen =
                                 super.listaDeAdayacencia.get(posVerticeOrigen);
 //y borramos el vertice destino de la lista (usando un casting)
 adyacenciaDelOrigen.remove((Integer)posVerticeDestino);
 //y usamos el collection sort para ordenar (opcional)
 Collections.sort(adyacenciaDelOrigen);
 }

 @Override
 //estem metodo se reescribio y se lo anulo debido a que un digrafo tiene 
 //grado de entrada y grado de salida
 public int gradoDeVertice(int posVertice) {
   //manda esta excepcion  
  throw new UnsupportedOperationException("este metodo no soporta grafos "
                                                                 + "dirigidos");
 }

 @Override
 //este metodo se reescribio por que un digrafo solo inserta en un sentido
 public void insertarArista(int posVerticeOrigen,int posVerticeDestino) 
                                                throws AristaYaExisteException{
  //validamos ambos vertices y verificamos si existe una adyacencia entre ambos
  //vertces
  super.validarVertice(posVerticeOrigen);
  super.validarVertice(posVerticeDestino);
  if(super.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
    //si existe una adyacencia manda una excepcion   
    throw new AristaYaExisteException();   
  }
  //caso contrario saca la lista de adyacencias del vertice origen
  List<Integer>adyacenciaDelOrigen=
                                 super.listaDeAdayacencia.get(posVerticeOrigen);
  //y añade el vertice destino en la lista de adyacencia del origen
  adyacenciaDelOrigen.add(posVerticeDestino);
  //y usamos el collection sort para ordenar
  Collections.sort(adyacenciaDelOrigen);
 }
 //este metodo devuelve la cantidad de arsitas que entran en el verice
 public int gradoDeEntrada(int posDeVertice){
  //validamos el vertice   
  super.validarVertice(posDeVertice);
  //creamos y instanciamos el contador
  int contador=0;
  //y hacemos un for de 0 hasta el tamaño de la lista de adyacencia
  for(int posVerticeDeTurno=0;posVerticeDeTurno<super.listaDeAdayacencia.size();
                                                           posVerticeDeTurno++){
   //sacamos la lista de adyacencia de cada vertice   
   List<Integer> adyaceciaDeVerticeEnTurno=
                                super.listaDeAdayacencia.get(posVerticeDeTurno);
   //y verificamos si se encunetra ese vertice en cada lista de adyacencia
   if(adyaceciaDeVerticeEnTurno.contains(posDeVertice)){
     //si se encuentra ira creciendo  
     contador++;  
   }
  }
  //y retornamos la cantidad 
 return contador; 
 }

 @Override
 //este metodo devuelve la cantidad de aristas del grafo
 public int cantidadDeAristas() {
  //creamos y instanciamos el contador de aristas   
  int contadorDeAristas=0;   
 // y hacemos un for de 0 hasta e tamaño de la lista de adyacencia 
 for(int i=0;i<super.listaDeAdayacencia.size();i++){
  //sacamos la lista de adyacentes de cada vertice    
  List<Integer> adyacenciaDelVerticeEnTurno=super.listaDeAdayacencia.get(i);
  //y vamos sumando el tamaño que tiene cada lista de adyacencia
  contadorDeAristas+=adyacenciaDelVerticeEnTurno.size();
 }
 //y retoramos dicha cantidad
 return contadorDeAristas;
 }
 //este metodo devuelve el grado que sale de un vertice
 public int gradoDeSalida(int posDeVertice){
   // llama al metodo de grado de vertice del grafo no dirigido  
  return super.gradoDeVertice(posDeVertice);
 }
}