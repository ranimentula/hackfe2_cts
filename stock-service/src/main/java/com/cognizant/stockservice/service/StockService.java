package com.cognizant.stockservice.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.stockservice.VO.Company;
import com.cognizant.stockservice.VO.ResponseTemplateVO;
import com.cognizant.stockservice.exception.CompanyNotFoundException;
import com.cognizant.stockservice.kafka.KafkaSender;
import com.cognizant.stockservice.model.Stock;
import com.cognizant.stockservice.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	KafkaSender kafkaSender;

	public Stock saveStock(int companyCode, Stock stock) {
		try {
			Company company = restTemplate.getForObject("http://COMPANY-SERVICE/company/info/" + companyCode,
					Company.class);

		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}

		stock.setCompanyCode(companyCode);
		Stock stock_saved = stockRepository.save(stock);
		kafkaSender.send(stock_saved.toString());
		return stock_saved;
	}

	public ResponseTemplateVO getCompanyWithStock(int companyCode) {

		List<Stock> stocks_sorted = null;
		ResponseTemplateVO vo = new ResponseTemplateVO();

		Company company = restTemplate.getForObject("http://COMPANY-SERVICE/company/info/" + companyCode,
				Company.class);

//		if (company.getCompanyCode() == companyCode) {
//			stocks = stockRepository.findBycompanyCode(companyCode);
//		} else {
//
//		}
		if ((company != null) && (company.getCompanyCode() == companyCode)) {

			stocks_sorted = stockRepository.findBycompanyCodeOrderByDateAsc(companyCode);
			vo.setCompany(company);
			vo.setLatestStockPrice(stocks_sorted.get(stocks_sorted.size() - 1).getStockPrice());
		} else {

		}

		return vo;
	}

	public ResponseTemplateVO getStocksByDate(int companyCode, LocalDate date1, LocalDate date2) {

		List<Stock> stocks = null;
		Stock stock_maxprice = null;
		Stock stock_minprice = null;
		double sum = 0;
		double avg = 0;
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Company company;
		// vo = null;

		try {
			company = restTemplate.getForObject("http://COMPANY-SERVICE/company/info/" + companyCode, Company.class);
		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}

		/*
		 * if (company.getCompanyCode() == companyCode) { stocks =
		 * stockRepository.findAllByDateBetween(date1, date2);
		 * System.out.println("inside if: size of the stocks========" + stocks.size());
		 * }
		 */
		if ((company != null) && (company.getCompanyCode() == companyCode)) {
			System.out.println("inside if of getStocksByDate: latest stock price of company with code: " + companyCode
					+ " is: " + company.getLatestStockPrice());
			stocks = stockRepository.findBycompanyCodeAndDateBetween(companyCode, date1, date2);
			System.out.println("inside if of getStocksByDate: size of the stocks========" + stocks.size());
		} else {

		}
		if (!stocks.isEmpty()) {
			stock_maxprice = stocks.stream().max(Comparator.comparingDouble(Stock::getStockPrice)).get();
			System.out.println(
					"maximum stock price for company code " + companyCode + " is " + stock_maxprice.getStockPrice());

			stock_minprice = stocks.stream().min(Comparator.comparingDouble(Stock::getStockPrice)).get();
			System.out.println(
					"minimum stock price for company code " + companyCode + " is " + stock_minprice.getStockPrice());

			for (Stock stock : stocks) {

				sum += stock.getStockPrice();
			}
			avg = sum / stocks.size();

			System.out.println("Average stock price for company code " + companyCode + " is " + avg);
			vo.setCompany(company);
			vo.setStocks(stocks);
			vo.setMaxStockPrice(stock_maxprice.getStockPrice());
			vo.setMinStockPrice(stock_minprice.getStockPrice());
			vo.setAvgStockPrice(avg);
			vo.setLatestStockPrice(company.getLatestStockPrice());
		} else {

		}

		return vo;
	}

	public void deleteStock(int stockId) {
		stockRepository.deleteById(stockId);

	}

	public Stock latestStock(int companyCode) {

		List<Stock> stocks;
		Stock stock = null;
		stocks = stockRepository.findBycompanyCodeOrderByDateAsc(companyCode);

		if (!stocks.isEmpty()) {
			stock = stocks.get(stocks.size() - 1);
		} else {

		}
		return stock;

	}

	public void deleteAllStocksForcompanyCode(int companyCode) {
		stockRepository.deleteAllBycompanyCode(companyCode);

	}

	public void updateStock(int stockId, Stock stock) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(stockId));
		Stock stock_db = mongoTemplate.findOne(query, Stock.class);

		stock_db.setStockPrice(stock.getStockPrice());
		stock_db.setDate(stock.getDate());
		stock_db.setTime(stock_db.getTime());

		mongoTemplate.save(stock_db);

	}

//	public ResponseTemplateVO getStocksByDate(int companyCode,String date1,String date2) {
//		
//		List<Stock> stocks = null;
//		ResponseTemplateVO vo= new ResponseTemplateVO();
//		
//		Company company=restTemplate.getForObject("http://COMPANY-SERVICE/company/info/"+companyCode,  Company.class);
//		
//		if (company.getCompanyCode()==companyCode) {
//			stocks=stockRepository.findBydateBetween(date1,date2);
//		}
//		else {
//		
//		}
//		
//		vo.setCompany(company);
//		vo.setStocks(stocks);
//		return vo;
//	}

}
