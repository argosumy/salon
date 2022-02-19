package spdu2022.java.project.beutysalon.salons_registration.controllers;

import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.salons_registration.dto.SalonsDTO;
import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalonMapper {

    protected Salon convertSalonsDtoToSalonForSalonUpdate(SalonsDTO salonsDto, long id) {
        return new Salon(id, salonsDto.getSalonName(), salonsDto.getCityLocation(), salonsDto.getPhone());
    }

    protected Salon convertSalonsDtoToSalonForSalonCreate(SalonsDTO salonsDto) {
        Salon newSalon = new Salon();
        newSalon.setSalonName(salonsDto.getSalonName());
        newSalon.setCityLocation(salonsDto.getCityLocation());
        newSalon.setPhone(salonsDto.getPhone());
        return newSalon;
    }

    protected SalonsDTO convertSalonToSalonsDto(Salon salon) {
        return new SalonsDTO(salon.getId(), salon.getSalonName(), salon.getCityLocation(), salon.getPhone());
    }

    protected List<SalonsDTO> convertSalonsToListSalonsDto(List<Salon> salon) {
        return salon.stream().map(this::convertSalonToSalonsDto).collect(Collectors.toList());
    }
}
