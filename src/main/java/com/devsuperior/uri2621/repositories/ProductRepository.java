package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.DTO.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //SQL query
    @Query(nativeQuery = true, value = "SELECT products.name " +
            "FROM products " +
            "INNER JOIN providers ON products.id_providers = providers.id " +
            "WHERE products.amount BETWEEN :min AND :max " +
            "AND providers.name LIKE CONCAT(:beginName, '%')")
    List<ProductMinProjection> search1(Integer min, Integer max, String beginName);

    //JPQL query
    //relationship for one with the Class Provider, so don't need writer INNER JOIN here on JPQL query, because navigate directly
    @Query("SELECT new com.devsuperior.uri2621.DTO.ProductMinDTO(obj.name) " +
            "FROM Product obj " +
            "WHERE obj.amount BETWEEN :min AND :max " +
            "AND obj.provider.name LIKE CONCAT(:beginName, '%')")
    List<ProductMinDTO> search2(Integer min, Integer max, String beginName);


}
