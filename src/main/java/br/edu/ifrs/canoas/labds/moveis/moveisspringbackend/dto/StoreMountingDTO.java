package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class StoreMountingDTO implements EntityDTO <Mounting>{

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public String dateRequest;
    public String place;

    @Override
    public Mounting toEntity(){
        Mounting mounting = new Mounting();

        try{
            mounting.setDateRequest(formatter.parse(dateRequest).toInstant());
        }catch(ParseException e){
            //sem nada
        }

        mounting.setPlace(place);
        mounting.setStatus(MountingStatus.WAITING);

        return mounting;
    }
}
