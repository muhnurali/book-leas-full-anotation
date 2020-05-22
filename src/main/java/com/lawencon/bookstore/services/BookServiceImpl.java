package com.lawencon.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import com.lawencon.bookstore.model.Book;
import com.lawencon.bookstore.model.Constants;

@Service
public class BookServiceImpl implements BookService {

	private static int count = 1;

	@Override
	public List<Book> setListBook() throws Exception {
		List<Book> list = new ArrayList<Book>();
		list.add(new Book(Constants.JAVA.name(), Constants.JAVA.harga));
		list.add(new Book(Constants.KOTLIN.name(), Constants.KOTLIN.harga));
		list.add(new Book(Constants.SPRING.name(), Constants.SPRING.harga));
		list.add(new Book(Constants.ANGULAR.name(), Constants.ANGULAR.harga));
		return list;
	}

	@Override
	public void showMenu(List<Book> list) throws Exception {
		System.out.println("\n=== List Book ===");
		list.forEach(x -> {
			System.out.println(count + ". " + x.getName() + ", Rp. " + x.getPrice());
			count++;
		});
		count = 1;
		System.out.print("Pilih Buku : ");
	}

	@Override
	public Book selectBook(int id, Book book) throws Exception {
		switch (id) {
		case 1:
			book.setName(Constants.JAVA.nama);
			book.setPrice(Constants.JAVA.harga);
			return book;
		case 2:
			book.setName(Constants.KOTLIN.nama);
			book.setPrice(Constants.KOTLIN.harga);
			return book;
		case 3:
			book.setName(Constants.SPRING.nama);
			book.setPrice(Constants.SPRING.harga);
			return book;
		case 4:
			book.setName(Constants.ANGULAR.nama);
			book.setPrice(Constants.ANGULAR.harga);
			return book;
		default:
			throw new Exception();
		}
	}

	@Override
	public boolean showBelanja(List<Book> list) throws Exception {
		System.out.println("\n=== Keranjang Belanja ===");
		if (list.isEmpty()) {
			System.out.println("Keranjang Belanja Masih Kosong");
			return false;
		} else {
			System.out.println("----------------------------");
			list.forEach(x -> {
				System.out
						.println(count + ". " + x.getName() + ", Rp. " + x.getPrice() + ", Banyaknya : " + x.getQty());
				count++;
			});
			int total = list.stream().mapToInt(x -> x.getPrice() * x.getQty()).sum();
			System.out.println("----------------------------");
			System.out.println("Total : Rp. " + total);
			System.out.println();
		}
		count = 1;
		return true;
	}

	@Override
	public void addBelanja(List<Book> list, Book book) throws Exception {
		list.add(book);
		System.out.println("Berhasil Menambahka Buku Ke Dalam Keranjang");
	}

	@Override
	public void deleteAllBook(List<Book> list) throws Exception {
		if (list.isEmpty()) {
			throw new Exception();
		} else {
			list.clear();
			System.out.println("Berhasil Hapus Semua Buku Dalam Keranjang");
		}
	}

	@Override
	public void deleteBook(int noBuku, List<Book> list) throws Exception {
		if (list.get(noBuku - 1) == null) {
			throw new Exception();
		} else {
			list.remove(noBuku - 1);
			System.out.println("Berhasil Hapus Buku");
		}
	}

	@Override
	public void updateBook(int noBuku, List<Book> belanja, Book book) throws Exception {
		belanja.set(noBuku - 1, book);
		System.out.println("Berhasil Update Buku");

	}

	@Override
	public Book cekUpdateBook(int noBuku, List<Book> belanja) throws Exception {
		if (belanja.get(noBuku - 1) == null) {
			throw new Exception();
		} else {
			System.out.println("Update " + belanja.get(noBuku - 1).getName() + ", Banyaknya : "
					+ belanja.get(noBuku - 1).getQty());
			return belanja.get(noBuku - 1);

		}
	}

	@Override
	public void transaksiBook(List<Book> listBook, int menu, Scanner sc, Book buku, boolean kondisi, List<Book> listBuy)
			throws Exception {
		do {
			this.showMenu(listBook);
			try {
				menu = sc.nextInt();
				if (menu > 0 && menu < 5) {
					buku = this.selectBook(menu, buku);
					kondisi = true;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Buku Tidak Ditemukan");
				kondisi = false;
				sc.nextLine();
			}
		} while (kondisi == false);
		do {
			System.out.print("Berapa banyak : ");
			try {
				menu = sc.nextInt();
				if (menu == 0) {
					System.out.println("Minimal Membeli 1 Buku");
					kondisi = false;
					sc.nextLine();
				} else {
					buku.setQty(menu);
					kondisi = true;
				}
			} catch (Exception e) {
				System.out.println("Hanya angka yang diperbolehkan");
				kondisi = false;
				sc.nextLine();
			}
		} while (kondisi == false);
		this.addBelanja(listBuy, buku);
	}

	@Override
	public void updateBookTransaksi(int qty, Scanner sc, int menu, List<Book> listBuy, boolean kondisi) {
		try {
			System.out.print("Update Buku : ");
			qty = sc.nextInt();
			Book update = this.cekUpdateBook(qty, listBuy);
			System.out.print("Ubah Berapa Banyak : ");
			try {
				menu = sc.nextInt();
				if (menu == 0) {
					System.out.println("Minimal Membeli 1 Buku");
					kondisi = false;
					sc.nextLine();
				} else {
					update.setQty(menu);
					kondisi = true;
				}
			} catch (Exception e) {
				System.out.println("Hanya angka yang diperbolehkan");
				kondisi = false;
				sc.nextLine();
			}
			this.updateBook(qty, listBuy, update);
		} catch (Exception e) {
			System.out.println("Buku Tidak Ditemukan");
		}
	}

	@Override
	public void deleteBookTransaksi(int menu, List<Book> listBuy, Scanner sc) throws Exception {
		try {
			System.out.print("Delete Buku : ");
			menu = sc.nextInt();
			this.deleteBook(menu, listBuy);
		} catch (Exception e) {
			System.out.println("Buku Tidak Ditemukan");
		}
	}

	public void deleteAllBookTransaksi(List<Book> listBuy) throws Exception {
		try {
			this.deleteAllBook(listBuy);
		} catch (Exception e) {
			System.out.println("Keranjang Belanja Masih Kosong");
		}

	}

}
