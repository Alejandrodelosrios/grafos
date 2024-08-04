/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import Pesados.GrafoPesado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author EQUIPO
 */
public class Kruskal {
 private final GrafoPesado elGrafo;//copia del grafo original
 private final GrafoPesado grafoAuxiliar;//grafo resultante o arbol de expansion
 private final List<CostoMinimo> listaDePesosOrdenados;//lista con los costos minimos
 private final ControlMarcado marcados;//para controlar los vertices marcados

 //constructor
 public Kruskal(GrafoPesado elGrafo) {
  this.elGrafo = elGrafo;//copia la referencia de memoria
  this.grafoAuxiliar= new GrafoPesado(elGrafo.cantidadVertices());//instancia el grafo auxiliar 
  this.listaDePesosOrdenados= new ArrayList<>();//instancia la lista de costos 
  this.marcados=new ControlMarcado(elGrafo.cantidadVertices());//instanciamos para controlar los marcados
  this.cargarLista();//llamamos aun metodo privado
  this.ejecutarKruskal();//llamamos un metodo publico
 }
 
 //este metodo privado carga la lista de costo minimos
 private void cargarLista(){   
  for(int i=0;i<this.elGrafo.cantidadVertices();i++){//hacemos un for de todos los vertices 
   Iterable<Integer> adyacentesDelVertice=this.elGrafo.adyacentesDelVertice(i);//sacamos sus adyacentes
   for(Integer adyacentes:adyacentesDelVertice){//hacemos un for each para ver los adyacentes   
    if(!this.marcados.estaMarcado(adyacentes)){ //revisamos si no estan marcados 
        double pesoDeLaArista=this.elGrafo.peso(i,adyacentes); //sacamos su peso
        CostoMinimo arista=new CostoMinimo(i,adyacentes,pesoDeLaArista); //instanciamos una arista  
        this.listaDePesosOrdenados.add(arista);//la metemos a la lista  
      }
    }//fin del for each   
  this.marcados.marcarVertice(i);//marcamos el vertice actual 
  }//fin del for
  Collections.sort(listaDePesosOrdenados);//ordenamos la lista de costos con el collections sort
 }
 
 //este metodo publico ejecuta todo el algortimo de kruskal
 public void ejecutarKruskal(){
  CicloPesado verificador= new CicloPesado(this.grafoAuxiliar);//instanciamos un verificador de ciclos
   for(CostoMinimo aristas:this.listaDePesosOrdenados){//hacemos un for each con la lista de costos
    //sacamos el origen el destino y el peso
    int posVerticeOrigen=aristas.getIndiceOrigen();
    int posVerticeDestino=aristas.getIndiceDestino();
    double peso=aristas.getPeso();
    try { //hacemos un try catch para atrapar las excepciones
     //insertamos en el grafo auxiliar    
     this.grafoAuxiliar.insertarArista(posVerticeOrigen,posVerticeDestino,peso);
    } catch (AristaYaExisteException ex) {/*no pasa nada*/}
    if(verificador.hayCiclosEnGrafo()){//verifica si el grafo auxiliar tiene ciclos
      try{//hacemos un try catch para atrapar las excepciones
     //borramos la arista recien insertada en el grafo auxiliar      
      this.grafoAuxiliar.eliminarArista(posVerticeOrigen,posVerticeDestino);        
      }catch(AristaNoExisteException exe){/*no hacemos nada*/}
    }
   }//fin del for each
 }
 
 //este metodo publico devuelve el arbol de expansion
 public GrafoPesado getArbolDeExpansion(){
 return this.grafoAuxiliar;    
 }
 
 //este metodo publico (prueba) deberia imprimir el arbol de expansion 
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