/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class ModeloDrone {
    private int id;
    private String codigo;
    private String pesquisa;

    public String getPesquisa() {
        return pesquisa;
    }
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    } 
}
