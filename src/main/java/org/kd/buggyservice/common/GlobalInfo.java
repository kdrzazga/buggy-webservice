package org.kd.buggyservice.common;

import org.kd.buggyservice.main.BuggyWebservice;

public final class GlobalInfo {

    public static String getDbInfo(){
        return "\nH2 database link: http://localhost:" + BuggyWebservice.getPort() + "/h2-console\n"
                + "Credentials: user sa, (no password)\n";
    }

    public static String getSampleRequestsInfo(){
        var port = BuggyWebservice.getPort();

        return "\n\nSample requests:\n"
                + "\nPOST http://localhost:" + port + "/createAuthor/John/Kochanovsky"
                + "\nPOST http://localhost:" + port + "/createLibrary/Opole/internal"
                + "\nGET http://localhost:" + port + "/readBooks"
                + "\nGET http://localhost:" + port + "/readAuthor/1001"
                + "\nGET http://localhost:" + port + "/readLibrary/3004"
                + "\nGET http://localhost:" + port + "/readLibraries"
                + "\nPOST http://localhost:" + port + "/updateAuthor" + "\t{author json}"
                + "\nDELETE http://localhost:" + port + "/deleteBook/2002\n"
                + "\nPOST http://localhost:" + port + "/stop\n";

    }

    public static String getSampleJsonInfo(){
        return "\nSample JSONs for update:"
                + "\nauthor json={\"id\": 1002, \"name\": \"CK\", \"lastname\": \"Norwid\"}"
                + "\nbook json={\"id\": 2002, \"title\": \"Ania z Zielonego Wzgorza\", \"published\": \"2019\", \"author\": \"1003\"}"
                + "\nlibrary json={\"id\": 3002, \"address\": \"Radom\"}";
    }
}
