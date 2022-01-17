package com.activeedge.activeedgeinventoryapp.service.serviceImplementation;

import com.activeedge.activeedgeinventoryapp.dto.input.StockInputDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.enums.Status;
import com.activeedge.activeedgeinventoryapp.exception.ResourceNotFoundException;
import com.activeedge.activeedgeinventoryapp.model.Amount;
import com.activeedge.activeedgeinventoryapp.model.Stock;
import com.activeedge.activeedgeinventoryapp.repository.StockRepository;
import com.activeedge.activeedgeinventoryapp.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class StockServiceImplementation implements StockService {

    private final StockRepository stockRepository;

    private final ModelMapper modelMapper;

    @Override
    public BasicResponseDTO createStock(StockInputDTO newStock) {

        try {

            Stock stock = modelMapper.map(newStock, Stock.class);
            stockRepository.save(stock);
            log.info("New stock {} successfully added", stock.getName());
            log.info("Stock current price: {}", stock.getCurrentPrice().getCurrency());
            log.info("Stock current price: {}", stock.getCurrentPrice().getValue());
            return new BasicResponseDTO(Status.CREATED, stock);

        } catch (Exception ex) {
            log.error("There was an error while creating stock: {}", ex.getMessage());
            return new BasicResponseDTO(Status.ERROR, ex.getMessage());
        }

    }

    @Override
    public BasicResponseDTO updateStock(StockInputDTO stockUpdates, Long stockId) {
        try {

            Stock currentStock = getStock(stockId);
            Stock stock = updateCurrentStock(stockUpdates, currentStock);

            stockRepository.save(stock);

            return new BasicResponseDTO(Status.SUCCESS, stock);

        } catch (Exception ex) {
            log.error("There was an error while updating stock: {}", ex.getMessage());
            return new BasicResponseDTO(Status.ERROR, ex.getMessage());
        }
    }

    private Stock updateCurrentStock(StockInputDTO stockUpdates, Stock currentStock) {
        currentStock.setName(stockUpdates.getName());
        currentStock.setCurrentPrice(modelMapper.map(stockUpdates.getCurrentPrice(), Amount.class));
        currentStock.setQuantityInStock(stockUpdates.getQuantityInStock());
        currentStock.setReorderLevel(stockUpdates.getReorderLevel());
        currentStock.setDiscontinued(stockUpdates.isDiscontinued());
        return currentStock;
    }

    @Override
    public BasicResponseDTO getAllStocks() {
        try {

            return new BasicResponseDTO(Status.SUCCESS, stockRepository.findAll());

        } catch (Exception ex) {
            log.error("There was an error while getting all stocks: {}", ex.getMessage());
            return new BasicResponseDTO(Status.ERROR, ex.getMessage());
        }
    }

    @Override
    public BasicResponseDTO getStockById(Long stockId) {
        try {
            Stock stock = getStock(stockId);

            if (!Objects.nonNull(stock)) {
                return new BasicResponseDTO(Status.NOT_FOUND, "Error: Stock not found");
            }

            return new BasicResponseDTO(Status.SUCCESS, stock);

        } catch (Exception ex) {
            log.error("There was an error while getting stock: {}", ex.getMessage());
            return new BasicResponseDTO(Status.ERROR, ex.getMessage());
        }
    }

    @Override
    public BasicResponseDTO deleteStock(Long stockId) {
        try {

            Stock stock = getStock(stockId);

            if (!Objects.nonNull(stock)) {
                return new BasicResponseDTO(Status.NOT_FOUND, "Error: Stock not found");
            }

            stockRepository.deleteById(stockId);

            return new BasicResponseDTO(Status.SUCCESS, "Stock was successfully deleted");

        } catch (Exception ex) {
            log.error("There was an error while deleting stock: {}", ex.getMessage());
            return new BasicResponseDTO(Status.ERROR, ex.getMessage());
        }
    }

    private Stock getStock(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Stock not found"));
    }
}
