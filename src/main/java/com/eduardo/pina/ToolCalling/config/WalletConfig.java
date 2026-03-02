package com.eduardo.pina.ToolCalling.config;

import com.eduardo.pina.ToolCalling.api.StockRequest;
import com.eduardo.pina.ToolCalling.api.StockResponse;
import com.eduardo.pina.ToolCalling.api.WalletResponse;
import com.eduardo.pina.ToolCalling.repositories.WalletRepository;
import com.eduardo.pina.ToolCalling.service.StockService;
import com.eduardo.pina.ToolCalling.service.WalletService;
import com.eduardo.pina.ToolCalling.tools.StockTools;
import com.eduardo.pina.ToolCalling.tools.WalletTools;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class WalletConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Description("Number of shares for each company in my portfolio")
    public Supplier<WalletResponse> numberOffShares(WalletRepository repository){
        return new WalletService(repository);
    }

    @Bean
    @Description("Latest Stock Prices")
    public Function<StockRequest, StockResponse> latestStockPrice(){
        return new StockService(restTemplate());
    }

    @Bean
    public WalletTools walletTools(WalletRepository repository){
        return new WalletTools(repository);
    }

    @Bean
    public StockTools StockTools(){
        return new StockTools(restTemplate());
    }
}
