package br.com.fiap.tech_challenge.infra.gateway.database.entities;

import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_order")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(insertable = false)
	private Integer sequence;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum status;

	@Column(nullable = false)
	private boolean isPaid;

	@Column(nullable = false)
	private String paymentId;

	@Column(nullable = false)
	private String qr;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private CustomerEntity customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderProductEntity> products = new ArrayList<>();

	public OrderEntity() {
	}

	public OrderEntity(Order order) {
		this.id = order.getId();
		this.amount = order.getAmount();
		this.sequence = order.getSequence();
		this.status = order.getStatus();
		this.isPaid = order.isPaid();
		this.customer = order.getCustomer() != null ? new CustomerEntity(order.getCustomer()) : null;
		this.paymentId = order.getPaymentId();
		this.qr = order.getQr();
		this.createdAt = order.getCreatedAt();
		this.updatedAt = order.getUpdatedAt();
	}

	public void addOrderProductEntity(OrderProductEntity orderProductEntity) {
		orderProductEntity.setOrder(this);
		this.products.add(orderProductEntity);
	}

	public Order toOrder() {
		List<OrderProduct> orderProducts = products.stream()
			.map(orderProductEntity -> orderProductEntity.toOrderProduct(id))
			.toList();

		return new Order(id, amount, sequence, status, isPaid, orderProducts,
				customer != null ? customer.toCustomer() : null, paymentId, qr, createdAt, updatedAt);
	}

	public UUID getId() {
		return this.id;
	}

}
