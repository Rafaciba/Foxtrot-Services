package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.tsi.pi4.Database;
import br.com.senac.tsi.pi4.Produto;

import com.google.gson.Gson;



@Path ("/produto")
public class ProductService {
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduto(@PathParam("param") String produtoId) {
		
		String id = produtoId;
		Produto produto = null;
		
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("select * from produto where idProduto = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto = new Produto();
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setDescProduto(rs.getString("descProduto"));
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setPrecProduto(rs.getBigDecimal("precProduto"));
				produto.setDescontoPromocao(rs.getBigDecimal("descontoPromocao"));
				produto.setIdCategoria(rs.getInt("idCategoria"));
				produto.setAtivoProduto(rs.getBoolean("ativoProduto"));
				produto.setIdUsuario(rs.getInt("idUsuario"));
				produto.setQtdMinEstoque(rs.getInt("qtdMinEstoque"));
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (produto == null)
			return Response.status(404).entity(produtoId).build();
		else
			return Response.status(200).entity(produto).build();
		
	}
	
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdutos() {
		
		Produto produto = null;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("select * from produto");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto = new Produto();
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setDescProduto(rs.getString("descProduto"));
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setPrecProduto(rs.getBigDecimal("precProduto"));
				produto.setDescontoPromocao(rs.getBigDecimal("descontoPromocao"));
				produto.setIdCategoria(rs.getInt("idCategoria"));
				produto.setAtivoProduto(rs.getBoolean("ativoProduto"));
				produto.setIdUsuario(rs.getInt("idUsuario"));
				produto.setQtdMinEstoque(rs.getInt("qtdMinEstoque"));
				produtos.add(produto);
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (produto == null)
			return Response.status(404).entity(produtos).build();
		else
			return Response.status(200).entity(produtos).build();
		
	}
	
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveProduto(List<Produto> produtos) {
 
		Gson gson = new Gson ();
		String jsonProduto = gson.toJson(produtos);
		System.out.println("salvando usuario "+jsonProduto);
		
		
		return Response.status(200).entity("DEU CERTO").build();
 
	}
	
	
	@GET
	@Path("/categoria/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdutosFiltrado(@PathParam("param") String idCategoria) {
		
		String id = idCategoria;
		Produto produto = null;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("select * from produto where idCategoria = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto = new Produto();
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setDescProduto(rs.getString("descProduto"));
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setPrecProduto(rs.getBigDecimal("precProduto"));
				produto.setDescontoPromocao(rs.getBigDecimal("descontoPromocao"));
				produto.setIdCategoria(rs.getInt("idCategoria"));
				produto.setAtivoProduto(rs.getBoolean("ativoProduto"));
				produto.setIdUsuario(rs.getInt("idUsuario"));
				produto.setQtdMinEstoque(rs.getInt("qtdMinEstoque"));
				produtos.add(produto);
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (produto == null)
			return Response.status(404).entity(produtos).build();
		else
			return Response.status(200).entity(produtos).build();
		
	}
	
	
	@GET
	@Path("/busca/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdutosBusca(@PathParam("param") String keyword) {
		
		String id = keyword;
		Produto produto = null;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("select * from produto where idCategoria = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				produto = new Produto();
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setDescProduto(rs.getString("descProduto"));
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setPrecProduto(rs.getBigDecimal("precProduto"));
				produto.setDescontoPromocao(rs.getBigDecimal("descontoPromocao"));
				produto.setIdCategoria(rs.getInt("idCategoria"));
				produto.setAtivoProduto(rs.getBoolean("ativoProduto"));
				produto.setIdUsuario(rs.getInt("idUsuario"));
				produto.setQtdMinEstoque(rs.getInt("qtdMinEstoque"));
				produtos.add(produto);
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (produto == null)
			return Response.status(404).entity(produtos).build();
		else
			return Response.status(200).entity(produtos).build();
		
	}
	
	
	
	
	
}
