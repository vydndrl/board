package com.beyond.board.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// slf4j를 어노테이션을 통한 로거 선언 방법
@Slf4j
public class LogController {
//    Slf4j 어노테이션 또는 Logger 직접 선언
//    로거 직접선언방법
//    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("log/test1")
    public String logTest1() {
//        기존의 로그 방식 System.out.println
//        문제점1. 성능이 좋지 않음 2. 로그 분류 작업 불가
        System.out.println("println 로그 입니다.");
//        logger.debug("debug 로그입니다.");
//        logger.info("info 로그입니다.");
//        logger.error("error 로그입니다.");
        log.info("slf4를 통한 info 로그 입니다.");
        log.error("slf4를 통한 error 로그 입니다.");

        return "ok";
    }
}
