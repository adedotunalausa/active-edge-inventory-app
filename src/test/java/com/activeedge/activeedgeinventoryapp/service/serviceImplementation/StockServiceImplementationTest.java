package com.activeedge.activeedgeinventoryapp.service.serviceImplementation;

import com.activeedge.activeedgeinventoryapp.dto.input.AmountInputDTO;
import com.activeedge.activeedgeinventoryapp.dto.input.StockInputDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.enums.Status;
import com.activeedge.activeedgeinventoryapp.model.Amount;
import com.activeedge.activeedgeinventoryapp.model.Stock;
import com.activeedge.activeedgeinventoryapp.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplementationTest {

    @Mock private StockRepository stockRepository;

    @Mock private ModelMapper modelMapper;

    private StockServiceImplementation stockServiceImplementationUnderTest;
    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        stockServiceImplementationUnderTest = new StockServiceImplementation(stockRepository, modelMapper);
    }

    @Test
    void canCreateStock() {

        // when
        BasicResponseDTO response = stockServiceImplementationUnderTest.createStock(getStockDTO());

        // then
        assertThat(response.getResponseData()).isNotNull();

    }

    @Test
    void cnaUpdateStock() {
        Stock stock = getStock();

        StockInputDTO stockInputDTO = getStockDTO();

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        BasicResponseDTO response = stockServiceImplementationUnderTest.updateStock(stockInputDTO, 1L);

        assertThat(response.getResponseData()).isNotNull();

        assertEquals(Status.SUCCESS, response.getStatus());
    }

    @Test
    void canGetAllStocks() {
        // given
        List<Stock> stocks = getStocks();

        // when
        when(stockRepository.findAll()).thenReturn(stocks);
        BasicResponseDTO response = stockServiceImplementationUnderTest.getAllStocks();

        assertThat(response.getResponseData()).isEqualTo(stocks);
    }

    @Test
    void canGetStockById() {
        Stock stock = getStock();

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        BasicResponseDTO response = stockServiceImplementationUnderTest.getStockById(1L);

        assertThat(response.getResponseData()).isEqualTo(stock);
    }

    @Test
    void canDeleteStock() {
        Stock stock = getStock();

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        stockServiceImplementationUnderTest.deleteStock(1L);

        verify(stockRepository).deleteById(1L);
    }

    private StockInputDTO getStockDTO() {
        return new StockInputDTO("Denim Belt", getAmountDTO(), 2343L, 343L, false);
    }

    private AmountInputDTO getAmountDTO() {
        return new AmountInputDTO("USD", 34.00);
    }

    private List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();
        Stock stock1 = getStock();
        Stock stock2 = getStock();
        Stock stock3 = getStock();
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);
        return stocks;
    }

    private Stock getStock() {
        return new Stock("Rebook Sneakers", getAmount(), 2344L, 233L, false);
    }

    private Amount getAmount() {
        return new Amount("USD", 23.32);
    }
}