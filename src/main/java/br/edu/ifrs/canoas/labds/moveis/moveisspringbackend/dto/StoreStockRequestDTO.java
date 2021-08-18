package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import lombok.Data;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Data
public class StoreStockRequestDTO implements EntityDTO <StockRequest>{

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public String dateRequest;
    public Integer amount = 1;
    public Long idProduct;

    @Override
    public StockRequest toEntity(){
        StockRequest stockRequest = new StockRequest();
        try{
            stockRequest.setDateRequest(formatter.parse(dateRequest).toInstant());
        }catch(ParseException e){
            //sem nada
        }
        stockRequest.setAmount(amount);
        stockRequest.setStatus(RequestStatus.WAITING);
        return stockRequest;
    }
}
