package edu.aydin.insurance.Service;

import edu.aydin.insurance.Dtos.VehiclePartDto;
import edu.aydin.insurance.Dtos.WorkmanshipDto;
import edu.aydin.insurance.Dtos.WorkmanshipPartDto;
import edu.aydin.insurance.Entites.VehiclePart;
import edu.aydin.insurance.Entites.Workmanship;
import edu.aydin.insurance.Entites.WorkmanshipPart;
import edu.aydin.insurance.Repository.WorkmanshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkmanshipService {

    @Autowired
    private WorkmanshipRepository workmanshipRepository;

    @Autowired
    private WorkmanshipPartService workmanshipPartService;

    @Autowired
    private VehiclePartService vehiclePartService;

    public Workmanship addWorkmanship(WorkmanshipDto workmanshipDto){
        Workmanship workmanship = fromDto(workmanshipDto);
        workmanshipRepository.save(workmanship);
        return workmanship;
    }

    public List<WorkmanshipDto> getWorkmanshipByWorkOrderId(Long id){
        Optional<List<Workmanship>> workmanships = workmanshipRepository.findAllByWorkOrder(id);
        List<WorkmanshipDto> workmanshipDtos = new ArrayList<>();
        for (Workmanship workmanship:workmanships.get()){
            workmanshipDtos.add(toDto(workmanship));
        }
        return workmanshipDtos;
    }

    public WorkmanshipDto toDto(Workmanship workmanship){

        List<WorkmanshipPart> workmanshipParts = workmanship.getWorkmanshipPart();
        List<WorkmanshipPartDto> workmanshipPartDtos = new ArrayList<>();

        for (WorkmanshipPart part:workmanshipParts){
            workmanshipPartDtos.add(workmanshipPartService.toDto(part));
        }

        List<VehiclePart> vehicleParts = workmanship.getVehiclePart();
        List<VehiclePartDto> vehiclePartDtos = new ArrayList<>();

        for (VehiclePart part:vehicleParts){
            vehiclePartDtos.add(vehiclePartService.toDto(part));
        }

        return new WorkmanshipDto(workmanship.getId(),
                workmanship.getCost(),
                workmanship.isDone(),
                workmanshipPartDtos,
                vehiclePartDtos
        );
    }

    public Workmanship fromDto(WorkmanshipDto workmanshipDto){
        List<WorkmanshipPartDto> workmanshipPartDtos = workmanshipDto.getWorkmanshipPartDto();
        List<WorkmanshipPart> workmanshipParts = new ArrayList<>();
        for (WorkmanshipPartDto partDto:workmanshipPartDtos){
            workmanshipParts.add(workmanshipPartService.fromDto(partDto));
        }

        List<VehiclePartDto> vehiclePartDtos = workmanshipDto.getVehiclePartDto();
        List<VehiclePart> vehicleParts = new ArrayList<>();
        for (VehiclePartDto vehiclePartDto:vehiclePartDtos){
            vehicleParts.add(vehiclePartService.fromDto(vehiclePartDto));
        }

        Workmanship workmanship = new Workmanship();
        workmanship.setCost(workmanshipDto.getCost());
        workmanship.setDone(workmanshipDto.isDone());
        workmanship.setId(workmanshipDto.getId());
        workmanship.setVehiclePart(vehicleParts);
        workmanship.setWorkmanshipPart(workmanshipParts);
        return workmanship;
    }

}
