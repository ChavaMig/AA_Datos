package com.example.aa1.service;

import com.example.aa1.domain.SparePart;
import com.example.aa1.exception.SparePartNotFoundException;
import com.example.aa1.repository.SparePartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparePartService {

    private final SparePartRepository sparePartRepository;

    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    public List<SparePart> findAll() {
        return sparePartRepository.findAll();
    }

    public SparePart findById(Long id) throws SparePartNotFoundException {
        return sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);
    }

    public SparePart add(SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }

    public SparePart modify(Long id, SparePart sparePart) throws SparePartNotFoundException {
        SparePart existingSparePart = sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);

        sparePart.setId(existingSparePart.getId());
        return sparePartRepository.save(sparePart);
    }

    public void delete(Long id) throws SparePartNotFoundException {
        SparePart sparePart = sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);

        sparePartRepository.delete(sparePart);
    }
}
