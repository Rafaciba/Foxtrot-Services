package br.com.senac.tsi.pi4;

public class ItemCarrinho {
	private Produto produto;
    private int quantidade;


    public ItemCarrinho(Produto produto) {
        this.produto = produto;
        this.quantidade = 1;
    }


    public int getQuantidade(){
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void incQuantidade(){
        this.quantidade++;
    }
}
