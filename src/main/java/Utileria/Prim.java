/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaYaExisteException;
import Pesados.GrafoPesado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author EQUIPO
 */
public class Prim {
 private final GrafoPesado elGrafo; //grafo original
 private final GrafoPesado grafoAuxiliar; //grafo auxiliar (sub arbol)
 private final ControlMarcado marcados; //para controlar los vertices
 private final List<Integer>listaDelGrafoOriginal; //lista de vertices
 
 //constructor
 public Prim(GrafoPesado elGrafo) {
  this.elGrafo = elGrafo;//le damos la referencia de memoria del grafo original
  this.grafoAuxiliar=new GrafoPesado(elGrafo.cantidadVertices());//instanciamos el grafoAuxiliar
  this.marcados=new ControlMarcado(elGrafo.cantidadVertices());//creamos  el control de marcado
  listaDelGrafoOriginal=new ArrayList<>(); //creamos una lista para los vertices originales 
  this.cargarLista();//llamamos aun metodo que cargue la lista con los vertices
 }
 
 //este metodo privado carga la lista con las posiciones de los vertice
 private void cargarLista(){
  for(int i=0;i<this.elGrafo.cantidadVertices();i++){
    this.listaDelGrafoOriginal.add(i);
  }
 }
 
 //este metodo publico hace el algortimo de prim recibiendo una posicion valida
 //Nota: se puede empezar desde cualquier posicion por recoendacion mejor empezar por el 0
 public void ejecutarPrim(int posVerticeInicial){ //(opcional este parametro)
  this.elGrafo.validarVertice(posVerticeInicial);//validamos el vertice si es correcto
  List<Integer> listaDelGrafoAuxiliar=new ArrayList<>();//creamos una lista para el grafo auxiliar
  //buscamos la posicion del vertice que nos dieron en la lista del grafo original
  int posDelVerticeABorrar=this.listaDelGrafoOriginal.indexOf(posVerticeInicial);
  //una vez que lo encontraos lo borramos de dicha lista y lo añadimos en la lista del grafo auxiliar
  this.listaDelGrafoOriginal.remove(posDelVerticeABorrar);  
  listaDelGrafoAuxiliar.add(posVerticeInicial);
  //marcamos ese vertice 
  this.marcados.marcarVertice(posVerticeInicial);
  do{//iteramos hasta que la lista del grafo original este vacia o no se encuentre mas caminos
   this.insertarAristaAlGrafoAuxiliar(listaDelGrafoAuxiliar);//llamamos aun metodo privado 
   }while(!this.listaDelGrafoOriginal.isEmpty()&& this.hayCaminos()); //evaluamos
 }

  //este metodo privado obtiene la arista con el costo menor costo que pase por 
 //los vertices que hay en la lista del grafo auxiliar  
  private CostoMinimo getAristaDeCostoMenor
                                          (List<Integer> listaDelGrafoAuxiliar){
   List<CostoMinimo> listaOrdenada=new ArrayList<>();//creamos una lista para ordenar los costos
   for(Integer vertices:listaDelGrafoAuxiliar){//hacemos un for each de la lista del grafo auxiliar
    //hacemos un segundo for each que sacar los adyacentes del primer for each    
    for(Integer adyacentes :this.elGrafo.adyacentesDelVertice(vertices)){
     if(!this.marcados.estaMarcado(adyacentes)){//revisamos que no este marcado el adyacente   
     double peso = this.elGrafo.peso(vertices,adyacentes);//sacamos su peso
     //creamos una arista(tabla) para comparar los pesos
     CostoMinimo arista=new CostoMinimo(vertices,adyacentes,peso);
     listaOrdenada.add(arista);//añadimos esa arista a la lista de CostoMinimo
    }
   }//fin del segundo for each
  }//fin del primer for each
  Collections.sort(listaOrdenada);//usamos el collections sort para ordenar la lista
  return listaOrdenada.get(0);//devolmemos el primero por que es el menor
  }
  
 //este metodo privado inserta una arista al grafo auxiliar                                         
  private void insertarAristaAlGrafoAuxiliar
                                          (List<Integer> listaDelGrafoAuxiliar){
   //usamos el metodo anterior para encontrar la arista menor del grafo original                                           
   CostoMinimo aristaMenosPesada=
                              this.getAristaDeCostoMenor(listaDelGrafoAuxiliar);
   //sacamos el origen el destino y el peso
   int origen=aristaMenosPesada.getIndiceOrigen();
   int destino=aristaMenosPesada.getIndiceDestino();
   double peso=aristaMenosPesada.getPeso();
    try {//hacemos un try catch para atrapar alguna excepcion
     this.grafoAuxiliar.insertarArista(origen,destino,peso);//insertamos la arista
     //buscamos el destino en la lista original y lo borramos
     int posDelVerticeABorrar=this.listaDelGrafoOriginal.indexOf(destino);
     this.listaDelGrafoOriginal.remove(posDelVerticeABorrar);
     listaDelGrafoAuxiliar.add(destino);//añadimos el vertice destino a la lista auxiliar
     this.marcados.marcarVertice(destino);// marcamos el vertice destino
    } catch (AristaYaExisteException ex) {/*no se encontrara excepcions*/}
  }                                        
  
 // este metodo privado busca si hay camino para pasar                                         
 private boolean hayCaminos(){
  //hacemos un for   
  for(int i=0;i<this.elGrafo.cantidadVertices();i++){
    if(!this.marcados.estaMarcado(i)){//revisamos si no esta marcado
     //hacemos un for each con los adyacentes de ese vertice no marcado  
     for(Integer adyacentes:this.elGrafo.adyacentesDelVertice(i)){
      //buscamos si entre sus adyacentes si hay uno marcado   
      if(this.marcados.estaMarcado(adyacentes)){
         return true; 
      }   
     }   
    }    
  }
  return false;//en caso de no haber camino
 }          
 
 // este metodo publico devuelve el grafo auxiliar 
 public GrafoPesado getArbolDeExpansion(){
 return this.grafoAuxiliar;    
 }
 //este metodo publico (prueba) debe imprimir el grafo auxiliar 
 public String imprimirArbolDeExpansion(){
  String mostrar="";  
  for(int i=0;i<this.grafoAuxiliar.cantidadVertices();i++){
    mostrar+="("+i+")->";  
   Iterable<Integer>adyacentesDelVertice=
                                     this.grafoAuxiliar.adyacentesDelVertice(i);
   for(Integer adyacentes:adyacentesDelVertice){
    mostrar+="("+adyacentes+")->";
    double peso=this.grafoAuxiliar.peso(i,adyacentes);
    mostrar+=peso+"\n";
  }
 }
 return mostrar; 
}
}