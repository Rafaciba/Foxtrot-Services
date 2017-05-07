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
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Cliente (nomeCompletoCliente, emailCliente, senhaCliente, CPFCliente, celularCliente, telComercialCliente, telResidencialCliente, dtNascCliente, recebeNewsLetter) VALUES ('"+
					cliente.getNomeCompletoCliente()+"', '"+cliente.getEmailCliente()+"', '"+cliente.getSenhaCliente()+
					"', '"+((cliente.getCPFCliente()!=null)?cliente.getCPFCliente():"")+
					"', '"+((cliente.getCelularCliente()!=null)?cliente.getCelularCliente():"")+
					"', '"+((cliente.getTelComercialCliente()!=null)?cliente.getTelComercialCliente():"")+
					"', '"+((cliente.getTelResidencialCliente()!=null)?cliente.getTelResidencialCliente():"")+
					"', '"+((cliente.getDtNascCliente()!=null)?cliente.getDtNascCliente():"")+
					"', "+cliente.getRecebeNewsLetter()+") ");
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
