package com.lawencon.bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lawencon.bookstore.config.AppConfig;
import com.lawencon.bookstore.model.Book;
import com.lawencon.bookstore.services.BookService;

/**
 * MUHAMMAD NUR ALI
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		BookService bookService = context.getBean("bookService", BookService.class);

		List<Book> listBook = bookService.setListBook();
		List<Book> listBuy = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		boolean kondisi = true;
		int menu = 0, qty = 0;

		do {
			bookService.showMenuUtama();
			try {
				menu = sc.nextInt();
				switch (menu) {
				case 1:
					Book buku = new Book();
					bookService.transaksiBook(listBook, menu, sc, buku, kondisi, listBuy);
					break;
				case 2:
					while (bookService.showBelanja(listBuy)) {
						bookService.showMenuKeranjang();
						menu = sc.nextInt();
						switch (menu) {
						case 1:
							bookService.updateBookTransaksi(qty, sc, menu, listBuy, kondisi);
							break;
						case 2:
							bookService.deleteBookTransaksi(menu, listBuy, sc);
							break;
						case 3:
							bookService.deleteAllBookTransaksi(listBuy);
							break;
						case 0:
							kondisi = true;
							break;
						default:
							System.out.println("Menu Tidak Ditemukan");
							kondisi = false;
							sc.nextLine();
							break;
						}
					}
					break;
				case 3:
					kondisi = false;
					System.out.println("=== Terimakasih ===");
					break;
				default:
					System.out.println("Menu Tidak Ditemukan");
					break;
				}
			} catch (Exception e) {
				System.out.println("Inputan Salah, Hanya Angka Yang Diperbolehkan");
				sc.nextLine();
			}
		} while (kondisi == true);
		sc.close();
	}
}
