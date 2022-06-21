package com.example.demo.repository;


import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    @Query(value = "SELECT SUM(total_amount) FROM recipe",nativeQuery = true)
    Long getTotalIncome();

    @Query(value = "SELECT SUM(amount) FROM recipe",nativeQuery = true)
    Long getTotalPaid();

    @Query(value = "SELECT SUM(amount_to_pay) FROM recipe",nativeQuery = true)
    Long getTotalUnpaid();

    @Query(value = "SELECT SUM(amount) FROM recipe r where r.payment_method = 'Cash' ",nativeQuery = true)
    Long getTotalCash();

    @Query(value = "SELECT SUM(amount) FROM recipe r where r.payment_method = 'Cheque' ",nativeQuery = true)
    Long getTotalCheque();
}
