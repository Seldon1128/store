package com.product.api.repository;

import java.util.List;
import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{
	
	@Query(value ="SELECT * FROM category ORDER BY category", nativeQuery = true)
	List<Category> findAll();
	
	@Query(value ="SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
	List<Category> findActive();
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO category(category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
	void create(@Param("category") String category, @Param("tag") String tag);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value ="UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id",
	nativeQuery = true)
		void update(@Param("category_id") Integer category_id, @Param("category") String category,
	@Param("tag") String tag);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value ="UPDATE category SET status = :status WHERE category_id = :category_id", nativeQuery = true)
	void updateStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);

	
	/*Nuevo: verificacion de duplicaciones*/
	@Query(value = "SELECT COUNT(*) FROM category WHERE LOWER(category) = LOWER(:category)", nativeQuery = true)
	int countByCategoryIgnoreCase(@Param("category") String category);

	@Query(value = "SELECT COUNT(*) FROM category WHERE LOWER(tag) = LOWER(:tag)", nativeQuery = true)
	int countByTagIgnoreCase(@Param("tag") String tag);

	@Query(value = "SELECT COUNT(*) FROM category WHERE category_id <> :id AND LOWER(category) = LOWER(:category)", nativeQuery = true)
	int countByCategoryIgnoreCaseAndIdNot(@Param("id") Integer id, @Param("category") String category);

	@Query(value = "SELECT COUNT(*) FROM category WHERE category_id <> :id AND LOWER(tag) = LOWER(:tag)", nativeQuery = true)
	int countByTagIgnoreCaseAndIdNot(@Param("id") Integer id, @Param("tag") String tag);

}
