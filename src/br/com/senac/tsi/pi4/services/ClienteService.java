package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.tsi.pi4.Cliente;
import br.com.senac.tsi.pi4.Database;

@Path ("/cliente")
public class ClienteService {
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCliente(Cliente cliente) {
 
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Cliente (nomeCompletoCliente, emailCliente, senhaCliente, CPFCliente,"
					+ " celularCliente, telComercialCliente, telResidencialCliente, dtNascCliente, recebeNewsLetter) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, cliente.getNomeCompletoCliente());
			ps.setString(2, cliente.getEmailCliente());
			ps.setString(3, cliente.getSenhaCliente());
			ps.setString(4, cliente.getCpfCliente());
			ps.setString(5, ((cliente.getCelularCliente()!=null)?cliente.getCelularCliente():""));
			ps.setString(6, ((cliente.getTelComercialCliente()!=null)?cliente.getTelComercialCliente():""));
			ps.setString(7, ((cliente.getTelResidencialCliente()!=null)?cliente.getTelResidencialCliente():""));
			ps.setString(8, ((cliente.getDtNascCliente()!=null)?cliente.getDtNascCliente():""));
			ps.setInt(9, cliente.getRecebeNewsLetter());
			int rs = ps.executeUpdate();
			if(rs == 0){
				return Response.status(500).entity("Erro na query").build();
			}else{
				return Response.status(200).entity("DEU CERTO").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
 
	}

}
