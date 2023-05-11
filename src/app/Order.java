package app;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Order {
	private UUID id = UUID.randomUUID();
	private String status;
	private LocalDate orderDate;
	private LocalDate deliveryDAte;
	private List<Product> products;
	private Customer customer;

	public Order(String status, LocalDate orderDate, LocalDate deliveryDate,List<Product> products, Customer costumer) {
        setStatus(status);
        setOrderDate(orderDate);
        setDeliveryDAte(deliveryDate);
        setProducts(products);
        setCustomer(costumer);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDAte() {
		return deliveryDAte;
	}

	public void setDeliveryDAte(LocalDate deliveryDAte) {
		this.deliveryDAte = deliveryDAte;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
