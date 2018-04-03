package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.giovannicarmo.projetocurso.carmoeletro.domain.*;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.PaymentState;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.Profile;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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

    public void instantiateTestDatabase() throws ParseException {

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
        Product p4 = new Product(null, "Mesa de escritorio", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV True Color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 80.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 9.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        State est1 = new State(null, "Minas Gerais");
        State est2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlândia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Arrays.asList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.save(Arrays.asList(est1, est2));
        cityRepository.save(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA, passwordEncoder.encode("batata"));

        cli1.getTelephones().addAll(Arrays.asList("27363323", "93838393"));

        Client cli2 = new Client(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", ClientType.PESSOAFISICA, passwordEncoder.encode("123"));

        cli2.getTelephones().addAll(Arrays.asList("93883321", "34252625"));

        cli2.addProfile(Profile.ADIMIN);

        Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
        Address e3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

        cli1.getAddresses().addAll(Arrays.asList(e1, e2,e3));

        clientRepository.save(Arrays.asList(cli1, cli2));
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

        OrderItem item1 = new OrderItem(ped1, p1, 50.00, 2000.00, 1.0);
        OrderItem item2 = new OrderItem(ped1, p3, 0.00, 80.00, 1.0);
        OrderItem item3 = new OrderItem(ped2, p2, 100.00, 800.00, 1.0);

        ped1.getItems().addAll(Arrays.asList(item1, item2));
        ped2.getItems().addAll(Arrays.asList(item3));

        p1.getItems().addAll(Arrays.asList(item1));
        p2.getItems().addAll(Arrays.asList(item3));
        p3.getItems().addAll(Arrays.asList(item2));

        orderItemRepository.save(Arrays.asList(item1, item2, item3));
    }
}
