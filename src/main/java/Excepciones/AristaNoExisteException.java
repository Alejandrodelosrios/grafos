/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author EQUIPO
 */
public class AristaNoExisteException extends Exception{
public AristaNoExisteException(){
  super("la arista que quiere eliminar no existe");   
 }    
 public AristaNoExisteException(String message){
   super(message);  
 }    
}
