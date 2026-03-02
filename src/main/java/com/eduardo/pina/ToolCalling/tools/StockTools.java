package com.eduardo.pina.ToolCalling.tools;

import com.eduardo.pina.ToolCalling.api.DailyShareQuote;
import com.eduardo.pina.ToolCalling.api.DailyStockData;
import com.eduardo.pina.ToolCalling.api.StockData;
import com.eduardo.pina.ToolCalling.api.StockResponse;
import com.eduardo.pina.ToolCalling.service.StockService;
import com.eduardo.pina.ToolCalling.settings.ApiSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StockTools {

    private static final Logger logger = LoggerFactory.getLogger(StockTools.class);

    @Value("${TWELVE_DATA_API_KEY}")
    String apiKey;

    private RestTemplate restTemplate;

    public StockTools(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Tool(description = "Latest Stock Price")
    public StockResponse getLatestStockPrice(@ToolParam(description = "Name of Company") String company){
        logger.info("Get Stock price for: {}", company);

        StockData data = restTemplate.getForObject(
                ApiSettings.TWELVE_DATA_BASE_URL
                        + "?apikey={0}&symbol={1}&interval=1day&outputsize=1",
                StockData.class,
                apiKey,
                company);

        DailyStockData latestData = data.getValues().get(0);

        logger.info("Get Stock prices ({}) -> {}", company, latestData);

        return new StockResponse(Float.parseFloat(latestData.getClose()));

    }

    @Tool(description = "Historical daily Stock Price")
    public List<DailyShareQuote> getHistoricalStockPrice(@ToolParam(description = "Search period in days") int days,
                                                         @ToolParam(description = "Name of Company") String company){
        logger.info("Get Historical Stock price: {} for: {}", company, days);

        StockData data = restTemplate.getForObject(
                ApiSettings.TWELVE_DATA_BASE_URL
                        + "?apikey={0}&symbol={1}&interval=1day&outputsize={2}",
                StockData.class,
                apiKey,
                company,
                days);

        return data.getValues().stream()
                .map(d -> new DailyShareQuote(company,
                                            Float.parseFloat(d.getClose()),
                                            d.getDatetime()))
                .toList();

    }
}
