package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	public static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		List<Order> orders = new ArrayList<>();
		List<Product> products = new ArrayList<>();
		products = createProducts();
		orders = createOrders();

		filter_1(products);
		filter_2(orders);
		filter_3(products, 10);
		filter_4(orders, "2023-05-04", "2023-05-08");
	}

	public static List<Product> createProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("bicicletta", "boys", 2000));
		products.add(new Product("macchina", "boys", 20000));
		products.add(new Product("rasoio", "boys", 2));
		products.add(new Product("pallone", "boys", 10));
		products.add(new Product("Lo strigo", "books", 110));
		products.add(new Product("Millenum", "books", 150));
		products.add(new Product("Le due torri", "books", 80));
		products.add(new Product("Java Mattone", "books", 30));
		products.add(new Product("peluche", "baby", 10));
		products.add(new Product("ciuccio", "baby", 1));
		products.add(new Product("latte in polvere", "baby", 7));
		products.add(new Product("passeggino", "baby", 150));

		return products;
	}

	public static List<Customer> createCustomers() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("mauro", 1));
		customers.add(new Customer("gabriele", 2));
		customers.add(new Customer("simone", 1));
		customers.add(new Customer("monica", 2));
		return customers;
	}

	public static List<Order> createOrders() {
		List<Order> orders = new ArrayList<>();
		List<Product> products = createProducts();
		List<Customer> customers = createCustomers();
		Random randNum = new Random();
		LocalDate today = LocalDate.now();

		for (Customer c : customers) {
			int n = randNum.nextInt(products.size() - 4);
			int nd = randNum.nextInt(10);
			Collections.shuffle(products);
			ArrayList<Product> random_items = new ArrayList<Product>(products.subList(0, n));
			orders.add(new Order("test", today.minusDays(nd), today.plusDays(nd), random_items, c));
		}
		return orders;
	}

	public static void filter_1(List<Product> products) {
		List<Product> filterProducts = products.stream()
				.filter(product -> product.getCategory().equals("books") && product.getPrice() > 100).toList();
		logger.info(filterProducts.toString());
	}

	public static void filter_2(List<Order> orders) {
		List<Order> filterdOrders = orders.stream()
				.filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("baby")))
				.toList();
		logger.info(filterdOrders.toString());
	}

	public static void filter_3(List<Product> products, int discount) {
		List<Product> Products = products.stream().filter(product -> product.getCategory().equals("baby"))
				.map(product -> {
					product.setPrice(product.getPrice() * (100 - discount) / 100);
					return product;
				}).toList();
		logger.info(Products.toString());
	}

	public static void filter_4(List<Order> orders, String dateStart, String dateEnd) {
		LocalDate startDate = LocalDate.parse(dateStart);
		LocalDate endDate = LocalDate.parse(dateEnd);
		List<Product> products = orders.stream()
				.filter(order -> order.getCustomer().getTier() == 2 && order.getOrderDate().isAfter(startDate)
						&& order.getOrderDate().isBefore(endDate))
				.flatMap(order -> order.getProducts().stream()).distinct().toList();
		logger.info(products.toString());
	}
}
/*
 * Esercizio #1 Ottenere una lista di prodotti che appartengono alla categoria
 * «Books» ed hanno un prezzo > 100
 * 
 * Esercizio #2 Ottenere una lista di ordini con prodotti che appartengono alla
 * categoria «Baby»
 * 
 * Esercizio #3 Ottenere una lista di prodotti che appartengono alla categoria
 * «Boys» ed applicare 10% di sconto al loro prezzo
 * 
 * Esercizio #4 Ottenere una lista di prodotti ordinati da clienti di livello
 * (tier) 2 tra I'01-Feb-2021 e t'01-Apr-2021
 */
