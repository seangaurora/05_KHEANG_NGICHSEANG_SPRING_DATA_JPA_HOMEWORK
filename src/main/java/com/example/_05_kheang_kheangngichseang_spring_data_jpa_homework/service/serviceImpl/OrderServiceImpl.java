package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.serviceImpl;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.OrderStatusEnum;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Customer;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Order;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Product;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.ProductOrder;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.OrderRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository.CustomerRepository;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository.OrderRepository;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository.ProductOrderRepository;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository.ProductRepository;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;

    @Override
    public OrderResponse createOrder(Long customerId, List<OrderRequest> orderRequests) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        Order order = new Order();
//        order.setOrderId(null);
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatusEnum.PENDING);
        order = orderRepository.save(order);

        List<ProductOrder> productOrders = new ArrayList<>();
        double totalAmount = 0.0;
        for (OrderRequest orderRequest : orderRequests) {
            Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow();

            int productQty = orderRequest.getQuantity();
            double price = product.getPrice();
            double subTotal = price * productQty;
            totalAmount += subTotal;

            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrder.setQuantity(productQty);
            productOrders.add(productOrder);
//            productOrderRepository.save(productOrder);
        }

        order.setTotalAmount(totalAmount);
        order.setProducts(productOrders);

        productOrderRepository.saveAll(productOrders);

        Order savedOrder = orderRepository.save(order);
        return savedOrder.toResponse();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public List<OrderResponse> getOrderByCustomerId(Long customerId) {
        List<Order> orderList = orderRepository.findOrderByCustomer_CustomerId(customerId);
        return orderList.stream().map(Order::toResponse).toList();
    }

    @Override
    public OrderResponse updateOrderStatusById(Long id, OrderStatusEnum status) {
        Order orderToUpdate = orderRepository.findById(id).orElseThrow();
        orderToUpdate.setStatus(status);
        return orderRepository.save(orderToUpdate).toResponse();
    }
}
