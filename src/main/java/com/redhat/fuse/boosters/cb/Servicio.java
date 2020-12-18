package com.redhat.fuse.boosters.cb;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.springframework.beans.propertyeditors.ReaderEditor;

@CsvRecord(separator = ",")
//@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.CLASS)
@JsonTypeInfo(use=Id.NAME, include=As.WRAPPER_OBJECT)

public class Servicio {

    @DataField(pos = 1)
    private Boolean esOnline;

    @DataField(pos = 2)
    private Boolean esVigente;
    
    @DataField(pos = 3)
	private String equipo;
	
	@DataField(pos  = 4)
	private String ms;

	@DataField(pos = 5)
	private String ds;

	@DataField(pos = 6)
	private String ciudad;

	@DataField(pos = 7)
	private String direccion;

	@DataField(pos = 8)
	private Boolean esUnAlta;

	@DataField(pos = 9)
	private Double banwidth;

	public Boolean getEsOnline() {
		return esOnline;
	}

	public void setEsOnline(Boolean esOnline) {
		this.esOnline = esOnline;
	}

	public Boolean getEsVigente(){
		return esVigente;
	}

	public void setEsVigente(Boolean esVigente){
		this.esVigente = esVigente;
	}

	public String getEquipo(){
		return equipo;
	}

	public void setEquipo(String equipo){
		this.equipo = equipo;
	}

	public String getMs(){
		return ms;
	}

	public void setMs(String ms){
		this.ms = ms;
	}

	public String getDs (){
		return ds;
	}

	public void setDs(String ds){
		this.ds = ds;
	}

	public String getCiudad(){
		return ciudad;
	}

	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}

	public String getDireccion(){
		return direccion;
	}

	public void setDireccion(String direccion){
		this.direccion = direccion;
	}

	public Boolean getEsUnAlta(){
		return esUnAlta;
	}

	public void setEsUnAlta(Boolean esUnAlta){
		this.esUnAlta = esUnAlta;
	}

	public Double getBanwidth(){
		return banwidth;
	}

	public void setBandwidth(Double banwidth){
		this.banwidth= banwidth;
	}
    
}
