package com.doran.processed_voice.service;

import org.springframework.stereotype.Service;

import com.doran.processed_voice.repository.ProcessedVoiceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessedVoiceService {
    private final ProcessedVoiceRepository processedVoiceRepository;
}
