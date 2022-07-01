package com.sinisiro.StartBoot.webview.service;

import com.sinisiro.StartBoot.webview.domain.CaptureLog;
import com.sinisiro.StartBoot.webview.repository.MybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NativeService {

    private final MybatisRepository mybatisRepository;

    public List<Map<String, Object>> getMember() {
        return mybatisRepository.getMember();
    }

    public void insertCaptureLog(CaptureLog captureLog) {
        mybatisRepository.insertCaptureLog(captureLog);
    }
}
