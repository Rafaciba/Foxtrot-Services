package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

import br.com.senac.tsi.pi4.Cliente;
import br.com.senac.tsi.pi4.Database;

@Path ("/login")
public class LoginService {
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ClienteLogin(JSONObject data) {
		
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente WHERE emailCliente = ? AND senhaCliente = ?");
			ps.setString(1, data.getString("email"));
			ps.setString(2, data.getString("senha"));
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Cliente clienteRetorno = new Cliente(rs.getInt("idCliente"),rs.getString("nomeCompletoCliente"),rs.getString("emailCliente"),
											rs.getString("senhaCliente"),rs.getString("CPFCliente"),rs.getString("celularCliente"),
											rs.getString("telComercialCliente"),rs.getString("telResidencialCliente"),
											rs.getString("dtNascCliente"),rs.getInt("recebeNewsLetter"));
				Gson gson = new Gson();
				return Response.status(200).entity(gson.toJson(clienteRetorno)).build();
			}else{
				return Response.status(500).entity("").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity("").build();
		}
 
	}
	
}
