package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.ProductEnvironment;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Data
public class UpdateStockRequestDTO implements UpdateEntityDTO <StockRequest>{

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Long id;
    public String dateRequest;
    public Integer amount;
    public Long idProduct;
    public String status;

    @Override
    public StockRequest toEntity(){
        StockRequest stockRequest = new StockRequest();
       try{
            stockRequest.setDateRequest(formatter.parse(dateRequest).toInstant());
        }catch(ParseException e){
            //sem nada
        }
        stockRequest.setId(id);
        stockRequest.setAmount(amount);
        stockRequest.setStatus(RequestStatus.valueOf(status));
        return stockRequest;
    }

    @Override
    public UpdateStockRequestDTO from(StockRequest stockRequest) {
        id = stockRequest.getId();
        dateRequest = stockRequest.getDateRequest().toString();
        amount = stockRequest.getAmount();
        idProduct = stockRequest.getProduct().getId();
        status = stockRequest.getStatus().toString();
        return this;
    }
}
