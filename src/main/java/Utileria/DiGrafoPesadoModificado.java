/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

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
public  class DiGrafoPesadoModificado extends DiGrafoPesado{
  private Comparator<AdyacenteConPeso> comparadorAdyacentePorPeso=new Comparator<AdyacenteConPeso>(){
      @Override
      public int compare(AdyacenteConPeso primerAdyacenteConPeso, AdyacenteConPeso segundoAdyacenteConPeso) {
       if(primerAdyacenteConPeso==null){
          return -1; 
       }
       if(segundoAdyacenteConPeso==null){
         return 1;  
       }
       if(primerAdyacenteConPeso.getPeso()>segundoAdyacenteConPeso.getPeso()){
        return -1;   
       }
       if(primerAdyacenteConPeso.getPeso()<segundoAdyacenteConPeso.getPeso()){
        return 1;   
       }
       return 0;
      }   
  };
    public DiGrafoPesadoModificado(int nroInicialDeVertices) {
     super(nroInicialDeVertices);
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double peso) throws AristaYaExisteException {
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
   Collections.sort(adyacenciaDelOrigen,comparadorAdyacentePorPeso);
  }
    
  public void setPesoArista
                       (int posVerticeOrigen,int posVerticeDestino,double peso){
   List<AdyacenteConPeso> adyacentesDelOrigen=
                                 super.listaDeAdayacencia.get(posVerticeOrigen);
   AdyacenteConPeso adyacenteDelOrigen= new AdyacenteConPeso(posVerticeDestino,peso);
   int posicioAdyacente=adyacentesDelOrigen.indexOf(adyacenteDelOrigen);
   adyacentesDelOrigen.set(posicioAdyacente,adyacenteDelOrigen);
   Collections.sort(adyacentesDelOrigen,comparadorAdyacentePorPeso);
  }  
 }   
