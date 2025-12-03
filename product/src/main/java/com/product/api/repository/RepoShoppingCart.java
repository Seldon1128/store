package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;
import com.product.api.entity.ShoppingCart;

import jakarta.transaction.Transactional;

@Repository
public interface RepoShoppingCart extends JpaRepository<ShoppingCart, Integer>{
	
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO cart_item(user_id, gtin, quantity, status) VALUES (:user_id, :gtin, :quantity, 1)", nativeQuery = true)
	void create(@Param("user_id") Integer user_id, @Param("gtin") String gtin, @Param("quantity") Integer quantity);
	
	
	@Query(value = "SELECT * FROM cart_item WHERE user_id = :userId AND gtin = :gtin AND status = 1 LIMIT 1", nativeQuery = true)
	ShoppingCart findItem(@Param("userId") Integer userId, @Param("gtin") String gtin);
	
	@Modifying (clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE cart_item SET quantity = :qty WHERE cart_item_id = :id", nativeQuery = true)
    void updateQty(@Param("id") Integer cartItemId, @Param("qty") Integer qty);
	
	@Query(value = "SELECT c.cart_item_id, c.quantity, p.product, p.gtin, p.price "
            + "FROM cart_item c "
            + "INNER JOIN product p ON p.gtin = c.gtin "
            + "WHERE c.user_id = :userId AND c.status = 1", nativeQuery = true)
	List<Object[]> getCartItems(@Param("userId") Integer userId);
	
	@Query(value = "SELECT * FROM cart_item WHERE cart_item_id = :id LIMIT 1", nativeQuery = true)
	ShoppingCart findByIdCart(@Param("id") Integer id);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "DELETE FROM cart_item WHERE cart_item_id = :id", nativeQuery = true)
	void deleteItem(@Param("id") Integer id);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "DELETE FROM cart_item WHERE user_id = :userId", nativeQuery = true)
	void deleteAllByUser(@Param("userId") Integer userId);

	@Query(value = "SELECT COUNT(*) FROM cart_item WHERE user_id = :userId", nativeQuery = true)
	int countItems(@Param("userId") Integer userId);


}
