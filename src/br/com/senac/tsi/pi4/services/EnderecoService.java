package br.com.senac.tsi.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.senac.tsi.pi4.Database;
import br.com.senac.tsi.pi4.Endereco;

@Path ("/endereco")
public class EnderecoService {
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEndereco(Endereco endereco) {
 
		try {
			
			Connection conn = Database.get().conn();
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT nomeEndereco FROM Endereco WHERE nomeEndereco = ?");
			preparedStatement.setString(1, endereco.getNomeEndereco());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()){
				PreparedStatement ps = conn.prepareStatement("INSERT INTO Endereco (idCliente, nomeEndereco, logradouroEndereco, "
						+ "numeroEndereco, CEPEndereco, complementoEndereco, cidadeEndereco, paisEndereco, UFEndereco) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(1, endereco.getIdCliente());
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
			}else{
				return Response.status(200).entity("Nome de endereço já existe").build();
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Erro: "+e.getMessage()).build();
		}
 
	}
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnderecosCliente(@PathParam("param") String clienteId){
		
		String id = clienteId;
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		
		try{
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Endereco WHERE idCliente = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				do{
					
					Endereco endereco = new Endereco();
					endereco.setIdEndereco(rs.getInt("idEndereco"));
					endereco.setNomeEndereco(rs.getString("nomeEndereco"));
					endereco.setLogradouroEndereco(rs.getString("logradouroEndereco"));
					endereco.setNumeroEndereco(rs.getString("numeroEndereco"));
					endereco.setCepEndereco(rs.getString("cepEndereco"));
					endereco.setComplementoEndereco(rs.getString("complementoEndereco"));
					endereco.setCidadeEndereco(rs.getString("cidadeEndereco"));
					endereco.setPaisEndereco(rs.getString("paisEndereco"));
					endereco.setUfEndereco(rs.getString("ufEndereco"));
					
					enderecos.add(endereco);
					
				}while(rs.next());
				
				Gson gson = new Gson();
				return Response.status(200).entity(gson.toJson(enderecos)).build();
			}else{
				return Response.status(404).entity("").build();
			}
			
		}catch(Exception e){
			return Response.status(500).entity("").build();
		}
	}
	
}
