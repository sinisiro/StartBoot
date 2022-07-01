package com.sinisiro.StartBoot.webview.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//JPA 사용을 위한 entity 추가
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity     //테이블 생성됨
public class CaptureLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logDate;
    private String logTime;
    private String screenId;

}
