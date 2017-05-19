package br.com.senac.tsi.pi4;

import java.math.BigDecimal;

public class Produto {

	private int idProduto;
    private String nomeProduto;
    private String descProduto;
    private BigDecimal precProduto;
    private BigDecimal descontoPromocao;
    private int idCategoria;
    private boolean ativoProduto;
    private int idUsuario;
    private int qtdMinEstoque;
    private String imagem;
	
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescProduto() {
		return descProduto;
	}
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	public BigDecimal getPrecProduto() {
		return precProduto;
	}
	public void setPrecProduto(BigDecimal precProduto) {
		this.precProduto = precProduto;
	}
	
	
}
