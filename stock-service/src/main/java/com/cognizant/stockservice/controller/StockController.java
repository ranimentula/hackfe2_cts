package com.cognizant.stockservice.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.stockservice.VO.ResponseTemplateVO;
import com.cognizant.stockservice.model.Stock;
import com.cognizant.stockservice.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@PostMapping("/add/{companyCode}")
	public Stock saveStock(@PathVariable int companyCode,@RequestBody Stock stock) {
		return stockService.saveStock(companyCode,stock);
	}
	
//	@GetMapping("/get/{companyCode}/{date1}/{date2}")
//	public ResponseTemplateVO getStocksByDate(@PathVariable int companyCode,@PathVariable String date1,@PathVariable String date2) {
//		return stockService.getStocksByDate(companyCode, date1, date2);
//		
//	}
	
//	@GetMapping("/get/{companyCode}/{date1}/{date2}")
//	public ResponseTemplateVO getStocksByDate(@PathVariable int companyCode,
//			@PathVariable("date1") @DateTimeFormat(pattern="yyyy-DD-mm") LocalDate date1,
//			@PathVariable("date2") @DateTimeFormat(pattern="yyyy-DD-mm") LocalDate date2) {
//		return stockService.getStocksByDate(companyCode, date1, date2);
//	
//	}
	
	@GetMapping("/get/{companyCode}/{date1}/{date2}")
	public ResponseTemplateVO getStocksByDate(@PathVariable int companyCode,
			@PathVariable("date1") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date1,
			@PathVariable("date2") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date2) {
		return stockService.getStocksByDate(companyCode, date1, date2);
	
	}
	
	@DeleteMapping("delete/{stockId}")
	public String deleteStock(@PathVariable int stockId) {
		stockService.deleteStock(stockId);
		return "Stock deleted with Id : " + stockId;
	}
	
	@DeleteMapping("deleteall/{companyCode}")
	public String deleteAllStocksForcompanyCode(@PathVariable int companyCode) {
		stockService.deleteAllStocksForcompanyCode(companyCode);
		return "All stocks deleted for company with code : " + companyCode;
	}
	
	@GetMapping("/lateststock/{companyCode}")
	public Stock latestStock(@PathVariable int companyCode){
		return stockService.latestStock(companyCode);
		
	}
	
	@PutMapping("/update/{stockId}")
	public void updateStock(@PathVariable int stockId,@RequestBody Stock stock) {
		
		stockService.updateStock(stockId,stock);
	}

}
