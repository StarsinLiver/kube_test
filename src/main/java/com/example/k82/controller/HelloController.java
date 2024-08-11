package com.example.k82.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : com.example.k82.controller
 * fileName : HelloController
 * author : san26
 * date : 2024-08-07
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2024-08-07         san26          최초 생성
 */

@RestController
@RequestMapping("/api")
public class HelloController {


//    @GetMapping("/hello1")
//    public String hello() {
//        return "안녕하세요 이것은 1번입니다.";
//    }

//    @GetMapping("/hello2")
//    public String hello2() {
//        System.out.println("2번이다~~~~");
//        return "안녕하세요 이것은 2번입니다.";
//    }
//    @GetMapping("/hello3")
//    public String hello3() {
//        return "안녕하세요 이것은 3번입니다.";
//    }
    @GetMapping("/hello4")
    public String hello4() {
        return "안녕하세요 이것은 4번입니다.";
    }
}
