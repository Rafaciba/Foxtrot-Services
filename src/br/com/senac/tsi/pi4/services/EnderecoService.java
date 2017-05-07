package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.tsi.pi4.Database;
import br.com.senac.tsi.pi4.Endereco;

@Path ("/endereco")
public class EnderecoService {
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCliente(Endereco endereco) {
 
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Endereco (idCliente, nomeEndereco, logradouroEndereco, numeroEndereco, CEPEndereco, complementoEndereco, cidadeEndereco, paisEndereco, UFEndereco) VALUES ('"+
					endereco.getCliente().getIdCliente()+"', '"+endereco.getNomeEndereco()+"', '"+endereco.getLogradouroEndereco()+
					"', '"+((endereco.getNumeroEndereco()!=null)?endereco.getNumeroEndereco():"")+
					"', '"+((endereco.getCEPEndereco()!=null)?endereco.getCEPEndereco():"")+
					"', '"+((endereco.getComplementoEndereco()!=null)?endereco.getComplementoEndereco():"")+
					"', '"+((endereco.getCidadeEndereco()!=null)?endereco.getCidadeEndereco():"")+
					"', '"+((endereco.getPaisEndereco()!=null)?endereco.getPaisEndereco():"")+
					"', "+endereco.getUFEndereco()+") ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0){
				return Response.status(500).entity("Erro na query").build();
			}else{
				ResultSet rs = ps.getGeneratedKeys();
				return Response.status(200).entity(""+rs.getLong(1)).build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
 
	}
}
