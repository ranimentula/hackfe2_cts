package com.cognizant.stockservice.VO;

import java.util.List;

import com.cognizant.stockservice.model.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	
	private List<Stock> stocks;
	private Company company;
	private double maxStockPrice;
	private double minStockPrice;
	private double avgStockPrice;
	private double latestStockPrice;
	
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public double getMaxStockPrice() {
		return maxStockPrice;
	}
	public void setMaxStockPrice(double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}
	public double getMinStockPrice() {
		return minStockPrice;
	}
	public void setMinStockPrice(double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}
	
	
	public double getAvgStockPrice() {
		return avgStockPrice;
	}
	public void setAvgStockPrice(double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
	
	public double getLatestStockPrice() {
		return latestStockPrice;
	}
	public void setLatestStockPrice(double latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}
	@Override
	public String toString() {
		return "ResponseTemplateVO [stocks=" + stocks + ", company=" + company + ", maxStockPrice=" + maxStockPrice
				+ ", minStockPrice=" + minStockPrice + ", avgStockPrice=" + avgStockPrice + ", latestStockPrice="
				+ latestStockPrice + "]";
	}
	public ResponseTemplateVO(List<Stock> stocks, Company company, double maxStockPrice, double minStockPrice,
			double avgStockPrice, double latestStockPrice) {
		super();
		this.stocks = stocks;
		this.company = company;
		this.maxStockPrice = maxStockPrice;
		this.minStockPrice = minStockPrice;
		this.avgStockPrice = avgStockPrice;
		this.latestStockPrice = latestStockPrice;
	}
	public ResponseTemplateVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
