/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.Serializable;

/**
 *
 * @author soy-y
 */
public class Image implements Serializable {
    
    private String name;
    private String url;
    private String identificador;
    
    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }

  public Image() {
  }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public String getIdentificador() {
    return identificador;
  }

}
