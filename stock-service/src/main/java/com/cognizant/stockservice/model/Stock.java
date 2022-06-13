package com.cognizant.stockservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Stock")
public class Stock {
	@Id
	private int stockId;
	private double stockPrice;
//	@JsonFormat(pattern = "YYYY-MM-DD",shape = Shape.ANY)
//	private String date;
//	private LocalDate date;
//	@NotNull
//	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
//	private LocalTime time;
	private LocalDate date=LocalDate.now();
	private LocalTime time=LocalTime.now();
	private int companyCode;
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockPrice=" + stockPrice + ", date=" + date + ", time=" + time
				+ ", companyCode=" + companyCode + "]";
	}
	public Stock(int stockId, double stockPrice, LocalDate date, LocalTime time, int companyCode) {
		super();
		this.stockId = stockId;
		this.stockPrice = stockPrice;
		this.date = date;
		this.time = time;
		this.companyCode = companyCode;
	}
	public Stock() {
		super();
		
	}
	
	

}
