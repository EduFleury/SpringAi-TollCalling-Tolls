package com.eduardo.pina.ToolCalling.api;

import java.util.List;

public class StockData {

    private List<DailyStockData> values;

    public StockData(List<DailyStockData> values) {
        this.values = values;
    }

    public List<DailyStockData> getValues() {
        return values;
    }

    public void setValues(List<DailyStockData> values) {
        this.values = values;
    }
}
