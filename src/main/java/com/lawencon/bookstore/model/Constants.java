package com.lawencon.bookstore.model;

public enum Constants {
	JAVA("Java", 10000), 
	KOTLIN("Kotlin", 11000), 
	SPRING("Spring", 12000), 
	ANGULAR("Angular", 13000);

	public int harga = 0;
	public String nama = "";

	private Constants(String nama, int harga) {
		this.nama = nama;
		this.harga = harga;
	}
}
