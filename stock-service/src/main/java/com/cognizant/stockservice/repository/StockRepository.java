package com.cognizant.stockservice.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.cognizant.stockservice.model.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, Integer>{

	@Query
	List<Stock> findBycompanyCode(int companyCode);

	@Query
	List<Stock> findAllByDateBetween(LocalDate date1, LocalDate date2);

	@Query
	List<Stock> findBycompanyCodeAndDateBetween(int companyCode, LocalDate date1, LocalDate date2);

	@Query
	List<Stock> findBycompanyCodeOrderByDateAsc(int companyCode);

	@Query
	void deleteAllBycompanyCode(int companyCode);

//	@Transactional
//	@Query("update Stock set stockPrice= :stockPrice, date =: date, time =: time where id- =: stockId")
//	void updateStock(int stockId, double stockPrice,LocalDate date, LocalTime time);
	
//	@Query
//	List<Stock> findBydateBetween(String date1,String date2);

	
}
