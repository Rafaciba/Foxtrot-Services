package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.tsi.pi4.Database;
import br.com.senac.tsi.pi4.Categoria;


@Path("/categoria")
public class CategoriaService {
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoria(@PathParam("param") String categoriaId) {
		
		String id = categoriaId;
		Categoria categoria = null; 
		
		try { 
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNomeCategoria(rs.getString("nomeCategoria"));
				categoria.setDescCategoria(rs.getString("descCategoria"));
			}
		} catch (Exception e) {
			return Response.status(500).entity("ERRO: "+e.getMessage()).build();
		}
		if (categoria == null)
			return Response.status(404).entity(categoria).build();
		else
			return Response.status(200).entity(categoria).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorias() {
				
		Categoria categoria = null;
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		try { 
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM categoria");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNomeCategoria(rs.getString("nomeCategoria"));
				categoria.setDescCategoria(rs.getString("descCategoria"));
				categorias.add(categoria);
			}
		} catch (Exception e) {
			return Response.status(500).entity("ERRO: "+e.getMessage()).build();
		}
		if (categoria == null)
			return Response.status(404).entity(categorias).build();
		else
			return Response.status(200).entity(categorias).build();
		
	}
}
