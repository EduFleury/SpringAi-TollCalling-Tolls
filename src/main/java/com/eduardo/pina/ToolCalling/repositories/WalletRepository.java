package com.eduardo.pina.ToolCalling.repositories;

import com.eduardo.pina.ToolCalling.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Share, Long> {
}
