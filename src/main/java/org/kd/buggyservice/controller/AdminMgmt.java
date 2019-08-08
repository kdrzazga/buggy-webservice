package org.kd.buggyservice.controller;

import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminMgmt {

    @PostMapping(path = "/stop")
    public void stop() {
        BuggyWebservice.stop();
    }
}
