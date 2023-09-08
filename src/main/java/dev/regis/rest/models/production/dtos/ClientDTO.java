package dev.regis.rest.models.production.dtos;

import java.io.Serializable;

public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public ClientDTO() {
    }

    public ClientDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
