package com.eduardo.pina.ToolCalling.tools;

import com.eduardo.pina.ToolCalling.entities.Share;
import com.eduardo.pina.ToolCalling.repositories.WalletRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class WalletTools {

    private WalletRepository repository;

    public WalletTools(WalletRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Number of shares for each company in my wallet")
    public List<Share> getNumberOfShares(){
        return repository.findAll();
    }
}
