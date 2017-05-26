package br.com.senac.tsi.pi4.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.senac.tsi.pi4.Database;

@Path ("/imagem")
public class ProdutoImagemService {
		
	@GET
	@Path("/{param}/{width}/{height}")
	@Produces("image/jpg")
	public Response getImagem(@PathParam("param") String imagemId, @PathParam("width") int IMG_WIDTH, @PathParam("height") int IMG_HEIGHT) {
		
		String id = imagemId;
		int WIDTH = IMG_WIDTH;
		int HEIGHT = IMG_HEIGHT;
		String imagemString = "";
		byte[] imageResizedBytes = null;
		
		try {
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("select imagem from produto where idProduto = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				
				byte[] imagemBytes = rs.getBytes("imagem");
				InputStream in = new ByteArrayInputStream(imagemBytes);
				BufferedImage imageOriginal = ImageIO.read(in);				
				
				BufferedImage imagemResized = resizeImage(imageOriginal,WIDTH,HEIGHT,imageOriginal.getType());
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write (imagemResized, "jpg", baos);
				baos.flush();
				imageResizedBytes = baos.toByteArray();
				baos.close();
				
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (imagemString == "")
			return Response.status(404).entity(imageResizedBytes).build();
		else
			return Response.status(200).entity(imageResizedBytes).build();
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage,int IMG_WIDTH, int IMG_HEIGHT,  int type){
		
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		
		return resizedImage;
	}
} 

/*
public class ProdutoImagemService {
		
	@GET
	@Path("/{param}/{width}/{height}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImagem(@PathParam("param") String imagemId, @PathParam("width") int IMG_WIDTH, @PathParam("height") int IMG_HEIGHT) {
		
		String id = imagemId;
		int WIDTH = IMG_WIDTH;
		int HEIGHT = IMG_HEIGHT;
		String imagemString = "";
		
		try {
			Connection conn = Database.get().conn();
			PreparedStatement ps = conn.prepareStatement("select imagem from produto where idProduto = ?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				
				byte[] imagemBytes = rs.getBytes("imagem");
				InputStream in = new ByteArrayInputStream(imagemBytes);
				BufferedImage imageOriginal = ImageIO.read(in);				
				
				BufferedImage imagemResized = resizeImage(imageOriginal,WIDTH,HEIGHT,imageOriginal.getType());
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write (imagemResized, "jpg", baos);
				baos.flush();
				byte[] imageResizedBytes = baos.toByteArray();
				baos.close();				
				
				imagemString = java.util.Base64.getEncoder().encodeToString(imageResizedBytes);
				
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if (imagemString == "")
			return Response.status(404).entity(imagemString).build();
		else
			return Response.status(200).entity(imagemString).build();
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage,int IMG_WIDTH, int IMG_HEIGHT,  int type){
		
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		
		return resizedImage;
	}
}
*/
