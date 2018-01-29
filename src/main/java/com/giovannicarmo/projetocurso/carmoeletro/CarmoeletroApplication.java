package com.giovannicarmo.projetocurso.carmoeletro;

import com.giovannicarmo.projetocurso.carmoeletro.domain.*;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.PaymentState;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CarmoeletroApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(CarmoeletroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletronicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoracao");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.save(Arrays.asList(p1, p2, p3));

        State est1 = new State(null, "Minas Gerais");
        State est2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlândia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Arrays.asList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.save(Arrays.asList(est1, est2));
        cityRepository.save(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);

        cli1.getTelephones().addAll(Arrays.asList("27363323", "93838393"));

        Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getAddresses().addAll(Arrays.asList(e1, e2));

        clientRepository.save(Arrays.asList(cli1));
        addressRepository.save(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Payment pagto1 = new CardPayment(null, PaymentState.QUITADO, ped1, 6);
        ped1.setPayment(pagto1);

        Payment pagto2 = new TicketPayment(null, PaymentState.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPayment(pagto2);

        cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

        orderRepository.save(Arrays.asList(ped1, ped2));
        paymentRepository.save(Arrays.asList(pagto1, pagto2));

        OrderItem item1 = new OrderItem(ped1, p1, 0.00, 2000.00, 1);
        OrderItem item2 = new OrderItem(ped1, p3, 100.00, 80.00, 1);
        OrderItem item3 = new OrderItem(ped2, p2, 100.00, 800.00, 1);

        ped1.getItems().addAll(Arrays.asList(item1, item2));
        ped2.getItems().addAll(Arrays.asList(item3));

        p1.getItems().addAll(Arrays.asList(item1));
        p2.getItems().addAll(Arrays.asList(item3));
        p3.getItems().addAll(Arrays.asList(item2));

        orderItemRepository.save(Arrays.asList(item1, item2, item3));
    }
}
