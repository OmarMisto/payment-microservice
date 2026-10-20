package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Dues;
import com.example.payment_service.repository.DuesRepository;
import com.example.payment_service.service.IDuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuesService implements IDuesService {
    private final DuesRepository duesRepository;
    @Override
    public void saveDues(Dues dues) {
        duesRepository.save(dues);
    }
}
