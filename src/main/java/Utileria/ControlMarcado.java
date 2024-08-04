/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class ControlMarcado {
 private final List<Boolean> marcados;
 
  public ControlMarcado(int nroVertice){
   this.marcados=new LinkedList<>();   
   for(int i=0;i<nroVertice;i++){
    this.marcados.add(Boolean.FALSE);
   }
  }

 public void marcarVertice(int posDeVertice){
  this.marcados.set(posDeVertice, Boolean.TRUE);
 }

 public void desmarcarVertice(int posVertice){
  this.marcados.set(posVertice, Boolean.FALSE);
 }

 public void desmarcarTodos(){
  for(int i=0;i<this.marcados.size();i++){
   this.marcados.set(i, Boolean.FALSE);
  }   
 }

 public boolean estanTodosMarcados(){
  for(Boolean marcado:this.marcados){
    if(!marcado){
     return false; 
    }   
   }   
  return true;
 }
 public boolean estaMarcado(int posDeVertice){
  return this.marcados.get(posDeVertice);
 }
}