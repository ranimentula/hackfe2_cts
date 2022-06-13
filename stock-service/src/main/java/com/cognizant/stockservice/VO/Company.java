package com.cognizant.stockservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
	
	private int companyCode;
	private String companyName;
	private String companyCeo;
	private long companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	private double latestStockPrice;
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public long getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(long companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	public double getLatestStockPrice() {
		return latestStockPrice;
	}
	public void setLatestStockPrice(double latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}
	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", companyName=" + companyName + ", companyCeo=" + companyCeo
				+ ", companyTurnover=" + companyTurnover + ", companyWebsite=" + companyWebsite + ", stockExchange="
				+ stockExchange + ", latestStockPrice=" + latestStockPrice + "]";
	}
	public Company(int companyCode, String companyName, String companyCeo, long companyTurnover,
			String companyWebsite, String stockExchange, double latestStockPrice) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.latestStockPrice = latestStockPrice;
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
