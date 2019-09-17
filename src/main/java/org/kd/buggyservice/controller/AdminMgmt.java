package org.kd.buggyservice.controller;

import org.kd.buggyservice.common.GlobalInfo;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminMgmt {

    @RequestMapping(path = "/")
    public String printInfo() {
        var header = "<!DOCTYPE html>\n<html>\n<body>";
        var info = GlobalInfo.getDbInfo(); /*TODO: Security error - Credentials to login to db showed to client*/
        info += GlobalInfo.getSampleRequestsInfo() + GlobalInfo.getSampleJsonInfo();
        info = info.replaceAll("\n", "<br/>");
        var footer = "\n</body>\n</html>";

        return header + info + footer;
    }

    @PostMapping(path = "/stop")
    public void stop() {
        BuggyWebservice.stop();
    }

}
