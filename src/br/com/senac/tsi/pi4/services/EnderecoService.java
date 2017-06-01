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
	public Response createEndereco(Endereco endereco) {
 
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Endereco (idCliente, nomeEndereco, logradouroEndereco, "
					+ "numeroEndereco, CEPEndereco, complementoEndereco, cidadeEndereco, paisEndereco, UFEndereco) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, endereco.getCliente().getIdCliente());
			ps.setString(2, endereco.getNomeEndereco());
			ps.setString(3, endereco.getLogradouroEndereco());
			ps.setString(4, endereco.getNumeroEndereco());
			ps.setString(5, endereco.getCepEndereco());
			ps.setString(6, endereco.getComplementoEndereco());
			ps.setString(7, endereco.getCidadeEndereco());
			ps.setString(8, endereco.getPaisEndereco());
			ps.setString(9, endereco.getUfEndereco());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0){
				return Response.status(500).entity("Erro na query").build();
			}else{
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next())
					return Response.status(201).entity(""+rs.getLong(1)).build();
				else
					return Response.status(500).entity("Erro no result set").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Erro: "+e.getMessage()).build();
		}
 
	}	
}
