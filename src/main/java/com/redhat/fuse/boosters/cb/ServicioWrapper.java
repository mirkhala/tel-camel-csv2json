package com.redhat.fuse.boosters.cb;

public class ServicioWrapper {

    public ServicioWrapper(Servicio servicio){
        this.servicio = servicio;
    }

    public ServicioWrapper(){}

    private Servicio servicio;

    public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
    
}
