/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jhonatan
 */
public class Momento {
    String ativo;
    int quantidade;
    float preco_medio;

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_medio() {
        return preco_medio;
    }

    public void setPreco_medio(float preco_medio) {
        this.preco_medio = preco_medio;
    }
}
