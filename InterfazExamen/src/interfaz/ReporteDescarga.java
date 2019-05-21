/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Alberto Sánchez
 */
public class ReporteDescarga implements Serializable{
 
  private int id;
  private String nombreHost;
  private Date fechaDescargas;
  private String URL;

  public ReporteDescarga(int id, String nombreHost, Date fechaDescargas, String URL) {
    this.id = id;
    this.nombreHost = nombreHost;
    this.fechaDescargas = fechaDescargas;
    this.URL = URL;
  }

    public ReporteDescarga() {
    }

  public int getId() {
    return id;
  }

  public String getNombreHost() {
    return nombreHost;
  }

  public Date getFechaDescargas() {
    return fechaDescargas;
  }

  public String getURL() {
    return URL;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombreHost(String nombreHost) {
    this.nombreHost = nombreHost;
  }

  public void setFechaDescargas(Date fechaDescargas) {
    this.fechaDescargas = fechaDescargas;
  }

  public void setURL(String URL) {
    this.URL = URL;
  }

}
