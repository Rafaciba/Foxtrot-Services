package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.tsi.pi4.Cliente;
import br.com.senac.tsi.pi4.Database;

@Path ("/login")
public class LoginService {
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ClienteLogin(String email, String senha) {
		
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente WHERE emailCliente = ? AND senhaCliente = ?");
			ps.setString(1, email);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Cliente cliente = new Cliente(rs.getInt("idCliente"),rs.getString("nomeCompletoCliente"),rs.getString("emailCliente"),
											rs.getString("senhaCliente"),rs.getString("CPFCliente"),rs.getString("celularCliente"),
											rs.getString("telComercialCliente"),rs.getString("telResindencialCliente"),
											rs.getString("dtNascCliente"),rs.getInt("recebeNewsLetter"));
				return Response.status(200).entity(cliente).build();
			}else{
				return Response.status(500).entity("Deu merda na query").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
 
	}
	
}
