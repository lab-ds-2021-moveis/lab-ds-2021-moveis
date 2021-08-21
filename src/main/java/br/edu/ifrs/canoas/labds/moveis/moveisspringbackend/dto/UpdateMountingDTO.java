package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class UpdateMountingDTO implements UpdateEntityDTO <Mounting>{

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Long id;
    public String dateRequest;
    public String place;
    public String status;

    @Override
    public Mounting toEntity(){
        Mounting mounting = new Mounting();

        try{
            mounting.setDateRequest(formatter.parse(dateRequest).toInstant());
        }catch(ParseException e){
            //sem nada
        }

        mounting.setId(id);
        mounting.setPlace(place);
        mounting.setStatus(MountingStatus.valueOf(status));

        return mounting;
    }

    @Override
    public UpdateMountingDTO from(Mounting mounting) {
        id = mounting.getId();
        dateRequest = mounting.getDateRequest().toString();
        place = mounting.getPlace();
        status = mounting.getStatus().toString();
        return this;
    }
}
