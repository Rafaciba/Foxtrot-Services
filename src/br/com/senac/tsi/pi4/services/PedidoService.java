package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.tsi.pi4.Database;
import br.com.senac.tsi.pi4.Pedido;

@Path ("/pedido")
public class PedidoService {
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPedido(Pedido pedido) {
 
		try {
			Connection conn = Database.get().conn();
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Pedido (idCliente, idStatus, dataPedido, idTipoPedido, idEndereco, idAplicacao) "
					+ "VALUES (?, ?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pedido.getCliente());
			ps.setInt(2, pedido.getIdStatus());
			ps.setString(3, pedido.getDataPedido());
			ps.setInt(4, pedido.getIdTipoPagto());
			ps.setInt(5, pedido.getIdEndereco());
			ps.setInt(6, pedido.getIdAplicacao());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0){
				return Response.status(500).entity("Erro ao cadastrar o pedido").build();
			}else{
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next())
					return Response.status(201).entity(""+rs.getLong(1)).build();
				else
					return Response.status(500).entity("Erro no result set").build();
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPedido(@PathParam("param") String idPedido) {
		
		String id = idPedido;
		Pedido pedido = null;
		
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pedido WHERE idPedido = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setCliente(rs.getInt("idCliente"));
				pedido.setIdStatus(rs.getInt("idStatus"));
				pedido.setDataPedido(rs.getString("dataPedido"));
				pedido.setIdTipoPagto(rs.getInt("idTipoPagto"));
				pedido.setIdEndereco(rs.getInt("idEndereco"));
				pedido.setIdAplicacao(rs.getInt("idAplicacao"));
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (pedido == null)
			return Response.status(404).entity("Pedido não encontrado").build();
		else
			return Response.status(200).entity(pedido).build();
	}	
}
