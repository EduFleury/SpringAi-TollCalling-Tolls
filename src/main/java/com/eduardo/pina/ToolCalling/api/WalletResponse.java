package com.eduardo.pina.ToolCalling.api;

import com.eduardo.pina.ToolCalling.entities.Share;

import java.util.List;

public record WalletResponse(List<Share> shares) {
}
