package com.lawencon.bookstore.services;

import java.util.List;
import java.util.Scanner;

import com.lawencon.bookstore.model.Book;

public interface BookService {
	List<Book> setListBook() throws Exception;

	void showMenu(List<Book> list) throws Exception;

	Book selectBook(int pilih, Book book) throws Exception;

	boolean showBelanja(List<Book> list) throws Exception;

	void addBelanja(List<Book> list, Book book) throws Exception;

	void deleteAllBook(List<Book> list) throws Exception;

	void deleteBook(int noBuku, List<Book> list) throws Exception;

	void updateBook(int noBuku, List<Book> belanja, Book book) throws Exception;

	Book cekUpdateBook(int noBuku, List<Book> belanja) throws Exception;

	void transaksiBook(List<Book> listBook, int menu, Scanner sc, Book buku, boolean kondisi, List<Book> listBuy)
			throws Exception;

	void updateBookTransaksi(int qty, Scanner sc, int menu, List<Book> listBuy, boolean kondisi) throws Exception;

	void deleteBookTransaksi(int menu, List<Book> listBuy, Scanner sc) throws Exception;

	void deleteAllBookTransaksi(List<Book> listBuy) throws Exception;

	void showMenuUtama() throws Exception;

	void showMenuKeranjang() throws Exception;
}
