package com.activeedge.activeedgeinventoryapp.controller;

import com.activeedge.activeedgeinventoryapp.dto.input.StockInputDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StockController extends Controller {

    private final StockService stockService;

    @PostMapping()
    public BasicResponseDTO createStock(@RequestBody @Valid StockInputDTO dto) {
        return responseWithUpdatedHttpStatus(stockService.createStock(dto));
    }

    @PutMapping("/{stockId}")
    public BasicResponseDTO updateStock(@PathVariable(name = "stockId") Long stockId, @RequestBody @Valid StockInputDTO dto) {
        return responseWithUpdatedHttpStatus(stockService.updateStock(dto, stockId));
    }

    @GetMapping()
    public BasicResponseDTO getAllStocks() {
        return responseWithUpdatedHttpStatus(stockService.getAllStocks());
    }

    @GetMapping("/{stockId}")
    public BasicResponseDTO getStockById(@PathVariable(name = "stockId") Long stockId) {
        return responseWithUpdatedHttpStatus(stockService.getStockById(stockId));
    }

    @DeleteMapping("/{stockId}")
    public BasicResponseDTO deleteStock(@PathVariable(name = "stockId") Long stockId) {
        return responseWithUpdatedHttpStatus(stockService.deleteStock(stockId));
    }

}
