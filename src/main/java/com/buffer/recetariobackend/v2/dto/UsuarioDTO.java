package com.buffer.recetariobackend.v2.dto;

public class UsuarioDTO {

    private String idUsuarioDTO;
    private String username;
    private String password;
    private String name;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdUsuarioDTO() {
        return idUsuarioDTO;
    }

    public void setIdUsuarioDTO(String idUsuarioDTO) {
        this.idUsuarioDTO = idUsuarioDTO;
    }
}
