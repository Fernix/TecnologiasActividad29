/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaz.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author Alberto Sánchez
 */
public class Conection {

  private String nombre;
  private String url;
  private static Connection conection;
  private static PreparedStatement preparedStatement = null;
  private static Statement statement = null;
  private String consultaSql;
  private ArrayList<Image> listaImagenes = null;
  private ResultSet result;
  
//  public boolean registraDescarga(){
//    consultaSql = "INSERT INTO IMAGEN(NOMBRE, URL) " + "VALUES(?,?)";
//  }

  public void añadeImagen(Image imagen) {
    consultaSql = "INSERT INTO IMAGEN(NOMBRE, URL) " + "VALUES(?,?)";
    try {
      nombre = imagen.getName();
      url = imagen.getUrl();
      conection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + "C:\\temp\\Ejercicio29\\Derby;");
      preparedStatement = conection.prepareStatement(consultaSql);
      preparedStatement.setString(1, nombre);
      preparedStatement.setString(2, url);
      preparedStatement.execute();
      preparedStatement.close();
    } catch (SQLException ex) {
      Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        conection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public ArrayList<Image> obtieneListaImagenes() {
    consultaSql = "SELECT * FROM IMAGEN";
    try {
      conection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + "C:\\temp\\Ejercicio29\\Derby;");
      listaImagenes = new ArrayList<>();
      statement = conection.createStatement();
      result = statement.executeQuery(consultaSql);
      while (result.next()) {
        Image imagen = new Image();
        imagen.setIdentificador(result.getString(1));
        imagen.setName(result.getString(2));
        imagen.setUrl(result.getString(3));
        listaImagenes.add(imagen);
      }
      result.close();
    } catch (SQLException ex) {
      Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        conection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (listaImagenes == null || listaImagenes.isEmpty()) {
      return null;
    }
    return listaImagenes;
  }
}