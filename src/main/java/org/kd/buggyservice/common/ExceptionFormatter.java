package org.kd.buggyservice.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionFormatter {

    public static String fetchStacktrace(Throwable e){
        var writer = new StringWriter();
        var printWriter= new PrintWriter(writer);
        e.printStackTrace(printWriter);
        return writer.toString();
    }
}
