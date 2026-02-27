package com.eduardo.pina.ToolCalling.service;

import com.eduardo.pina.ToolCalling.api.WalletResponse;
import com.eduardo.pina.ToolCalling.repositories.WalletRepository;

import java.util.function.Supplier;

public class WalletService implements Supplier<WalletResponse> {

    private WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public WalletResponse get() {
        var shares = repository.findAll();
        return new WalletResponse(shares);
    }

    
}
