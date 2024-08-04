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
public class AristaYaExisteException extends Exception{
    
 public AristaYaExisteException(){
  super("la arista que quiere insertar ya existe");   
 }    
 public AristaYaExisteException(String message){
   super(message);  
 }
}
