/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import No_Pesados.Grafo;
import Pesados.GrafoPesado;

/**
 *
 * @author EQUIPO
 */
public class Warshall {
  private final boolean[][] matrizDeAdyacencia;
  private final boolean[][] matrizDeCaminos;
  
 // costructor para un digrafo no pesado 
 public Warshall(Grafo unDiGrafo){
  //sacamos el tamaño de la matriz
  int tamañoDeLaMatriz=unDiGrafo.cantidadVertices();
  //instanciamos la matriz con el tamaño que tendra este
  this.matrizDeAdyacencia=new boolean[tamañoDeLaMatriz][tamañoDeLaMatriz];
  this.matrizDeCaminos=new boolean[tamañoDeLaMatriz][tamañoDeLaMatriz];
   //hacemos un for para poner true en los lugares correspondientes
      for(int i=0;i<tamañoDeLaMatriz;i++){
      //sacamos los adyacentes de cada vertice    
    Iterable<Integer> adyacentesDelVerticeEnTurno=unDiGrafo.
                                                        adyacentesDelVertice(i);
    // y hacemos un for each y ponemos true en esas posiciones
    for(Integer adyacente:adyacentesDelVerticeEnTurno){
      this.matrizDeAdyacencia[i][adyacente]=true;   
    }
   }
   this.ejecutarAlgoritmo();
 }
 
 // costructor para un digrafo no pesado 
 public Warshall(GrafoPesado unDiGrafo){
  //sacamos el tamaño de la matriz
  int tamañoDeLaMatriz=unDiGrafo.cantidadVertices();
  //instanciamos la matriz con el tamaño que tendra este
  this.matrizDeAdyacencia=new boolean[tamañoDeLaMatriz][tamañoDeLaMatriz];
  this.matrizDeCaminos=new boolean[tamañoDeLaMatriz][tamañoDeLaMatriz];
   //hacemos un for para poner true en los lugares correspondientes
      for(int i=0;i<tamañoDeLaMatriz;i++){
      //sacamos los adyacentes de cada vertice    
    Iterable<Integer> adyacentesDelVerticeEnTurno=unDiGrafo.
                                                        adyacentesDelVertice(i);
    // y hacemos un for each y ponemos true en esas posiciones
    for(Integer adyacente:adyacentesDelVerticeEnTurno){
      this.matrizDeAdyacencia[i][adyacente]=true;   
    }
   }
   this.ejecutarAlgoritmo();
 } 
 
 //este metodo ejecuta el algortimo de warhall y devuelve la matriz de caminos
  public void ejecutarAlgoritmo(){
  //calculamos el tamaño de la matriz    
  int tamañoDeLaMatrizDeAdyacencia=this.matrizDeAdyacencia.length;
  this.copiarDeLaMatrizDeAdyacencia();
  //hacemos este for ya que sera las iteraciones que hara el algortimo
   for(int k=0;k<tamañoDeLaMatrizDeAdyacencia;k++){
    //este for sera para las filas de la matriz de caminos    
    for(int i=0;i<tamañoDeLaMatrizDeAdyacencia;i++){
     //y este for sera para las columnas de la matriz de camino   
     for(int j=0;j<tamañoDeLaMatrizDeAdyacencia;j++){
      //esto es para no ejectuar la fila y columna que se bloquean en cada iteracion   
      if(i!=k && j!=k){
       //esto revisa si es falso para ver si cambia de valor    
       if(!this.matrizDeCaminos[i][j]){      
         this.matrizDeCaminos[i][j]=this.matrizDeCaminos[i][j] ||
                (this.matrizDeCaminos[i][k]&&this.matrizDeCaminos[k][j]);
        }   
      }    
     }  
    }
   }
 }
 
 // este metodo copia lo de la matriz de adyacencia y lo pone en la matriz de 
 //camino 
 private void copiarDeLaMatrizDeAdyacencia(){
   int tamañoDeLaMatrizDeCaminos=this.matrizDeAdyacencia.length;  
   for(int fila=0;fila<tamañoDeLaMatrizDeCaminos;fila++){
    for(int columna=0;columna<tamañoDeLaMatrizDeCaminos;columna++){
     this.matrizDeCaminos[fila][columna]=this.matrizDeAdyacencia[fila][columna];    
    }   
   }     
 }
 
 //este metodo devuelve la matriz de camino despues de ejeecutar el algortimo
 public boolean[][] getMatrizDeCaminos(){
  return this.matrizDeCaminos;   
 } 
 
 //este metodo devuelve la matriz de adyacencia 
 public boolean[][] getMatrizDeAdyacencia(){
  return this.matrizDeAdyacencia;   
 }
 
  // este metodo imprime el algortimo
  public String imprimir(boolean[][] matrizDeCaminos){
   String cadena="";
    for(int i=0;i<matrizDeCaminos.length;i++){
      cadena+="[";  
     for(int j=0;j<matrizDeCaminos.length;j++){
      if(matrizDeCaminos[i][j]){
        cadena+=1;  
      }else{
       cadena+=0;   
      }   
     }
      cadena+="]\n";
    }
   return cadena; 
  } 
}