package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;


@Service
public class SvcProductImageImp implements SvcProductImage{
	
	@Autowired
	RepoProductImage repo;
	
	//Guardamos la ruta en una variable
	@Value("${app.upload.dir}")
	private String uploadDir;


	@Override
	public ResponseEntity<ApiResponse> deleteProductImage(Integer product_image_id) {
		try {
		    // validateProductId(id);
			repo.disableProductImage(product_image_id);
			return new ResponseEntity<>(new ApiResponse("La imagen ha sido eliminada"), HttpStatus.OK);
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}
	}
	
	@Override
	public ResponseEntity<ProductImage[]> getProductImages(Integer id){
		try {
		      ProductImage[] productImgs = repo.findByProductId(id);
		      return new ResponseEntity<>(productImgs, HttpStatus.OK);
		} catch (DataAccessException e) {
		      throw new DBAccessException(e);
		}
	}
	
	@Override
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in){
		try {
			// Eliminar el prefijo "data:image/png;base64," si existe
			if (in.getImage().startsWith("data:image")) {
				int commaIndex = in.getImage().indexOf(",");
				if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}
			// 1 - Decodificar imagen
			
			// Decodifica la cadena Base64 a bytes
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
			
			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "product", fileName);

			
			// 2 - Guardar el File en el sistema de archivos
			
			// Asegurarse de que el directorio exista
			Files.createDirectories(imagePath.getParent());


			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);
			
			// 3 - Guardar la ruta en la base de datos
			
			// Crear la entidad CustomerImage y guardar la URL en la base de datos
			ProductImage productImage = new ProductImage();
			productImage.setProductId(in.getProductId());
			productImage.setImage("/img/product/" + fileName);
			productImage.setStatus(1); 
			
			// Guardar la ruta de la imagen
			repo.save(productImage);
			
			return new ResponseEntity<>(new ApiResponse("La imagen ha sido registrada"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		} catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}

	}

	

}
