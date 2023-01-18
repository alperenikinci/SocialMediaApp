package com.alperenikinci.constant;

public class ApiUrls {

    public static final String VERSION="api/v1";
    public static final String ELASTIC=VERSION+"/elastic";


    public static final String CREATE="/create";
    public static final String UPDATE="/update";
    public static final String FINDALL="/findall";
    public static final String FINDBYID="/findbyid";
    public static final String DELETEBYID="/deletebyid";
    public static final String DELETEBYAUTHID="/deletebyauthid";
    public static final String ACTIVATESTATUS="/activatestatus";
    public static final String ACTIVATESTATUSBYID="/activatestatus/{authId}";
    public static final String UPDATEBYUSERNAMEOREMAIL="/updateusernameoremail";
    public static final String GETBYROLE="/getbyrole/{role}";
}
