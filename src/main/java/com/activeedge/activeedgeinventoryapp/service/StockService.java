package com.activeedge.activeedgeinventoryapp.service;

import com.activeedge.activeedgeinventoryapp.dto.input.StockInputDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;

public interface StockService {

    BasicResponseDTO createStock(StockInputDTO newStock);

    BasicResponseDTO updateStock(StockInputDTO stockUpdates, Long stockId);

    BasicResponseDTO getAllStocks();

    BasicResponseDTO getStockById(Long stockId);

    BasicResponseDTO deleteStock(Long stockId);

}
