package br.com.senac.tsi.pi4;

import java.util.ArrayList;

public class Pedido {
    
    private int idPedido;
    private int idCliente;
    private int idStatus;
    private String dataPedido;
    private int idTipoPagto;
    private int idEndereco;
    private int idAplicacao;
    private ArrayList<ItemCarrinho> carrinho;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCliente() {
        return idCliente;
    }

    public void setCliente(int cliente) {
        this.idCliente = cliente;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getIdTipoPagto() {
        return idTipoPagto;
    }

    public void setIdTipoPagto(int idTipoPagto) {
        this.idTipoPagto = idTipoPagto;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(int idAplicacao) {
        this.idAplicacao = idAplicacao;
    }
    
    public ArrayList<ItemCarrinho> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<ItemCarrinho> carrinho) {
        this.carrinho = carrinho;
    }
}
