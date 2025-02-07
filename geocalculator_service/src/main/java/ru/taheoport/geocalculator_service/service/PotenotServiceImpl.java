package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.mapper.PotenotTaskMapper;

import java.util.List;

/**
 * This class implements interface PotenotService
 */
@Component
@RequiredArgsConstructor
public class PotenotServiceImpl implements PotenotService{

    private final PotenotTaskMapper potenotTaskMapper;

    @Override
    public PotenotTaskResponse resolvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList) {
        if (potenotTaskRequestList.size() != 3) {
            throw new RuntimeException("There is not enough data to calculate");
        } else {

            return potenotTaskMapper.solvePotenotTask(potenotTaskRequestList);

        }
    }

    /**
     * Solves geodetic Potenot Problem
     * @param potenotStringRequests List of landmark points with known coordinates
     * @return Instance of PotenotTaskDto with coordinates calculated point and it's accuracy
     */
    @Override
    public PotenotStringResponse getPotenotStringResponse(List<PotenotStringRequest> potenotStringRequests) {
        List<PotenotTaskRequest> potenotTaskRequests = potenotTaskMapper.toPotenotTaskRequests(potenotStringRequests);
        PotenotTaskResponse potenotTaskResponse = potenotTaskMapper.solvePotenotTask(potenotTaskRequests);
        return potenotTaskMapper.toPotenotStringResponse(potenotTaskResponse);
    }

    /**
     * Gives response if raw data is not valid
     * @return PotenotStringResponse
     */
    @Override
    public PotenotStringResponse getPotenotStringErrorResponse() {
        PotenotStringResponse potenotStringResponse = new PotenotStringResponse();
        potenotStringResponse.setPointX("0.000");
        potenotStringResponse.setPointY("0.000");
        return potenotStringResponse;
    }
}
