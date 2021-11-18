package com.activeedge.activeedgeinventoryapp.service.serviceImplementation;

import com.activeedge.activeedgeinventoryapp.dto.input.StockInputDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.enums.Status;
import com.activeedge.activeedgeinventoryapp.model.Stock;
import com.activeedge.activeedgeinventoryapp.repository.StockRepository;
import com.activeedge.activeedgeinventoryapp.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
            return new BasicResponseDTO(Status.CREATED, stock);

        } catch (Exception ex) {
            log.error("There was an error while creating stock: {}", ex.getMessage());
            return new BasicResponseDTO(Status.FAILED_VALIDATION, ex.getMessage());
        }

    }
}
