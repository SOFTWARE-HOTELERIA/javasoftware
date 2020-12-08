/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa3_dominio.entidades;

/**
 *
 * @author USER
 */
public class TipoDocumento {
    private int documentoId;
 private String descripcion;
  public TipoDocumento(){}
  public TipoDocumento(int documentoId){
      this.documentoId=documentoId;
  }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
