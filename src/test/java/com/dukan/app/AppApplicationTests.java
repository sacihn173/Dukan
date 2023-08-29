package com.dukan.app;

import com.dukan.app.Order.Order;
import com.dukan.app.Order.OrderRepository;
import com.dukan.app.Order.OrderService;
import com.dukan.app.Product.Product;
import com.dukan.app.Product.ProductRepository;
import com.dukan.app.Product.ProductService;
import com.dukan.app.Tags.Tag;
import com.dukan.app.Tags.TagService;
import com.dukan.app.User.User;
import com.dukan.app.User.UserRepository;
import com.dukan.app.User.UserService;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

}
