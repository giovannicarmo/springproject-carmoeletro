package com.giovannicarmo.projetocurso.carmoeletro;

import com.giovannicarmo.projetocurso.carmoeletro.domain.*;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CarmoeletroApplication implements CommandLineRunner{

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

	public static void main(String[] args) {
		SpringApplication.run(CarmoeletroApplication.class, args);
	}


    @Override
    public void run(String... strings) throws Exception {

        Category category1 = new Category(null, "Informatica");
        Category category2 = new Category(null,"Escritorio");

        Product product1 = new Product(null, "Computador", 2000.00);
        Product product2 = new Product(null, "Impressora", 800.00);
        Product product3 = new Product(null, "Mouse", 80.00);

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "Sao Paulo");

        City city1 = new City(null, "Uberlandia", state1);
        City city2 = new City(null, "Sao Paulo", state2);
        City city3 = new City(null, "Campinas", state2);

        Client client = new Client(null, "maria@gmail.com", "Maria Silva", "12256592390",
                                    ClientType.PESSOAFISICA);

        client.getTelephones().addAll(Arrays.asList("21011877", "21011865"));

        Address address1 = new Address(null, "Rua Flores", "300", "Apto 203",
                                        "Jardim", "38220750", client, city1);

        Address address2 = new Address(null, "Avenida Matos", "105", "Sala 600",
                                        "Centro", "38777091", client, city2);

        client.getAddresses().addAll(Arrays.asList(address1, address2));

        category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        category2.getProducts().addAll(Arrays.asList(product2));

        product1.getCategories().addAll(Arrays.asList(category1));
        product2.getCategories().addAll(Arrays.asList(category1, category2));
        product3.getCategories().addAll(Arrays.asList(category1));

        state1.getCities().addAll(Arrays.asList(city1));
        state2.getCities().addAll(Arrays.asList(city2, city3));

        categoryRepository.save(Arrays.asList(category1, category2));
        productRepository.save(Arrays.asList(product1, product2, product3));
        stateRepository.save(Arrays.asList(state1, state2));
        cityRepository.save(Arrays.asList(city1, city2, city3));
        clientRepository.save(Arrays.asList(client));
        addressRepository.save(Arrays.asList(address1, address2));
    }
}
