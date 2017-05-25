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
	public BigDecimal getDescontoPromocao() {
		return descontoPromocao;
	}
	public void setDescontoPromocao(BigDecimal descontoPromocao) {
		this.descontoPromocao = descontoPromocao;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public boolean isAtivoProduto() {
		return ativoProduto;
	}
	public void setAtivoProduto(boolean ativoProduto) {
		this.ativoProduto = ativoProduto;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getQtdMinEstoque() {
		return qtdMinEstoque;
	}
	public void setQtdMinEstoque(int qtdMinEstoque) {
		this.qtdMinEstoque = qtdMinEstoque;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}	
	
}
