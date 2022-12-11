package com.example.salessummary;

import com.example.salessummary.entities.Sales;
import com.example.salessummary.repositories.SalesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalesSummaryApplication {
    private final SalesRepository salesRepository;

    public SalesSummaryApplication(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesSummaryApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SalesRepository salesRepository){
        return args -> {
            salesRepository.save(new Sales(null, 2.3, new Date(), "book", "John"));
            salesRepository.save(new Sales(null, 3.4, new Date(), "phone", "Mary"));
            salesRepository.save(new Sales(null, 5.6, new Date(), "pen", "Karen"));
            salesRepository.save(new Sales(null, 7.8, new Date(), "car", "Mike"));
            salesRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });
        };
    }

}
