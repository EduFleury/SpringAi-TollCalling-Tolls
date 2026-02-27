package com.eduardo.pina.ToolCalling.service;

import com.eduardo.pina.ToolCalling.api.DailyStockData;
import com.eduardo.pina.ToolCalling.api.StockData;
import com.eduardo.pina.ToolCalling.api.StockRequest;
import com.eduardo.pina.ToolCalling.api.StockResponse;
import com.eduardo.pina.ToolCalling.settings.ApiSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

public class StockService implements Function<StockRequest, StockResponse> {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);
    private RestTemplate restTemplate;

    public StockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${TWELVE_DATA_API_KEY}")
    String apiKey;

    @Override
    public StockResponse apply(StockRequest stockRequest) {
        StockData data = restTemplate.getForObject(
                ApiSettings.TWELVE_DATA_BASE_URL
                        + "?apikey={0}&symbol={1}&interval=1day&outputsize=1",
                StockData.class,
                apiKey,
                stockRequest.company());

        DailyStockData latestData = data.getValues().get(0);
        return new StockResponse(Float.parseFloat(latestData.getClose()));
    }

    @Override
    public <V> Function<V, StockResponse> compose(Function<? super V, ? extends StockRequest> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<StockRequest, V> andThen(Function<? super StockResponse, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
