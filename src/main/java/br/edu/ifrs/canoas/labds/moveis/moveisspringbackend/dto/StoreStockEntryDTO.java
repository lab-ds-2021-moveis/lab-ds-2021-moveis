package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockEntry;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class StoreStockEntryDTO implements EntityDTO <StockEntry>{

    //private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    //a data de entrada é feita automaticamente quando o produto vai ser inserido no banco, que nem o id do produto
    //public String createdAt;
    public Integer amount;
    public Long idProduct;

    @Override
    public StockEntry toEntity(){
        StockEntry stockEntry = new StockEntry();

        /*
        try{
            stockEntry.setCreatedAt(formatter.parse(createdAt).toInstant());
        }catch(ParseException e){
            //sem nada
        }
         */
        stockEntry.setAmount(amount);

        return stockEntry;
    }
}
