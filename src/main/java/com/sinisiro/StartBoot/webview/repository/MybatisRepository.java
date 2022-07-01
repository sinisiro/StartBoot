package com.sinisiro.StartBoot.webview.repository;

import com.sinisiro.StartBoot.webview.domain.CaptureLog;
import com.sinisiro.StartBoot.webview.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface MybatisRepository {
    List<Map<String, Object>> getMember();
    void insertCaptureLog(CaptureLog captureLog);
    Member findById(Long memberId);
}