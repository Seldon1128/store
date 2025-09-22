package com.product.api.repository;

import java.util.List;
import com.product.api.entity.Category;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{
	
	@Query(value="SELECT * FROM category ORDER BY category", nativeQuery=true)
	List<Category> getCategories();
}
