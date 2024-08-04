/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Excepciones.AristaNoExisteException;
import Excepciones.AristaYaExisteException;
import Pesados.AdyacenteConPeso;
import Pesados.DiGrafoPesado;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class FordFulKerson {
 private DiGrafoPesadoModificado grafoPesadoModificado;
 private final DiGrafoPesado digrafoPesadoOrginal;
 private double flujoMaximo;
 private int posFuente;
 private int posSumidero;
 private ControlMarcado marcados;

 public FordFulKerson(DiGrafoPesado diGrafoBase) {
  this.digrafoPesadoOrginal = diGrafoBase;
  this.grafoPesadoModificado=
                    new DiGrafoPesadoModificado(diGrafoBase.cantidadVertices());
  this.armarGrafoPesadoModificado();
  this.marcados=new ControlMarcado(diGrafoBase.cantidadVertices());
  this.ejecutarFordFulKerson();
 }
 
 private void armarGrafoPesadoModificado(){
  int fuentes=0;
  int sumidero=0;
  for(int i=0;i<this.digrafoPesadoOrginal.cantidadVertices();i++){
   Iterable<Integer> adyacentesDelVertice=
                              this.digrafoPesadoOrginal.adyacentesDelVertice(i);
   for(Integer adyacentes:adyacentesDelVertice){ 
        try{
           double peso=this.digrafoPesadoOrginal.peso(i,adyacentes);
           if(this.grafoPesadoModificado.existeAdyacencia(i,adyacentes)){
               this.grafoPesadoModificado.setPesoArista(i,adyacentes,peso);
           }else{
               this.grafoPesadoModificado.insertarArista(i,adyacentes,peso);
           }
         }catch(AristaYaExisteException ex){}
           if(this.grafoPesadoModificado.existeAdyacencia(adyacentes,i)){
             if(this.digrafoPesadoOrginal.existeAdyacencia(adyacentes,i)){  
               double peso=this.digrafoPesadoOrginal.peso(adyacentes,i);
               this.grafoPesadoModificado.setPesoArista(adyacentes,i,peso);
             }else{
              this.grafoPesadoModificado.setPesoArista(adyacentes,i,0.0);
             } 
            }
     }
    if (this.digrafoPesadoOrginal.gradoDeEntrada(i)==0){
      fuentes++;  
    }
    if(this.digrafoPesadoOrginal.gradoDeSalida(i)==0){
      sumidero++;   
    }
    if(fuentes>1){
     throw new RuntimeException("el DiGrafo tiene mas de una fuente");   
    }
    if(sumidero>1){
     throw new RuntimeException("el DiGrafo tiene mas de una fuente");   
    }
  }
  this.fijarFuenteYSumidero();
 }
 
  private void fijarFuenteYSumidero(){
   for(int i=0;i<this.digrafoPesadoOrginal.cantidadVertices();i++){
    if(this.digrafoPesadoOrginal.gradoDeEntrada(i)==0){
     this.posFuente=i;   
    }
    if(this.digrafoPesadoOrginal.gradoDeSalida(i)==0){
     this.posSumidero=i;   
    }
   }    
  } 
  public Iterable<Integer>
  
    public double getFlujoMaximo() {
        return flujoMaximo;
    }

    public int getPosFuente() {
        return posFuente;
    }

    public int getPosSumidero() {
        return posSumidero;
    }
    
}