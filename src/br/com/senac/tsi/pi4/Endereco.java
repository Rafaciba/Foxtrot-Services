package br.com.senac.tsi.pi4;

public class Endereco {
	private int idEndereco;
    private Cliente cliente;
    private String nomeEndereco;
    private String logradouroEndereco;
    private String numeroEndereco;
    private String CEPEndereco;
    private String complementoEndereco;
    private String cidadeEndereco;
    private String paisEndereco;
    private String UFEndereco;
    
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNomeEndereco() {
		return nomeEndereco;
	}
	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}
	public String getLogradouroEndereco() {
		return logradouroEndereco;
	}
	public void setLogradouroEndereco(String logradouroEndereco) {
		this.logradouroEndereco = logradouroEndereco;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getCEPEndereco() {
		return CEPEndereco;
	}
	public void setCEPEndereco(String cEPEndereco) {
		CEPEndereco = cEPEndereco;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getCidadeEndereco() {
		return cidadeEndereco;
	}
	public void setCidadeEndereco(String cidadeEndereco) {
		this.cidadeEndereco = cidadeEndereco;
	}
	public String getPaisEndereco() {
		return paisEndereco;
	}
	public void setPaisEndereco(String paisEndereco) {
		this.paisEndereco = paisEndereco;
	}
	public String getUFEndereco() {
		return UFEndereco;
	}
	public void setUFEndereco(String uFEndereco) {
		UFEndereco = uFEndereco;
	}
    
    
}
