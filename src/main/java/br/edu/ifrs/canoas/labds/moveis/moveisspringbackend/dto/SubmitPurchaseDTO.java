package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class SubmitPurchaseDTO {
    public Long customerId;
    public Boolean includeMount = false;
    public String dateRequest;
    public String place;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Mounting buildMounting() {
        Mounting mounting = new Mounting();

        try {
            mounting.setDateRequest(formatter.parse(dateRequest).toInstant());
        } catch(ParseException e) {
            // Não faz nada
        }

        mounting.setPlace(place);
        mounting.setStatus(MountingStatus.WAITING);

        return mounting;
    }
}
