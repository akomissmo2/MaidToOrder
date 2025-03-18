package com.maidservice.maidtoorder.service;

import com.maidservice.maidtoorder.entity.Maid;
import com.maidservice.maidtoorder.repository.MaidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaidService {
    private final MaidRepository maidRepository;

    public MaidService(MaidRepository maidRepository) {
        this.maidRepository = maidRepository;
    }

    // Fetch all maids
    public List<Maid> getAllMaids() {
        return maidRepository.findAll();
    }
}