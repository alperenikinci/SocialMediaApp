package com.alperenikinci.constants;

public class RestApi {

    public static final String VERSION="api/v1";
    public static final String AUTH=VERSION+"/auth";

    public static final String REGISTER="/register";
    public static final String LOGIN="/login";
    public static final String UPDATE="/update";
    public static final String FINDALL="/findall";
    public static final String FINDBYID="/findbyid";
    public static final String DELETEBYID="/deletebyid";
    public static final String DELETEBYAUTHID="/deletebyauthid";
    public static final String ACTIVATESTATUS="/activatestatus";
    public static final String CREATE="/create";
    public static final String UPDATEBYUSERNAMEOREMAIL="/updateusernameoremail";
    public static final String ACTIVATESTATUSBYID="/activatestatus/{authId}";
    public static final String GETALLACTIVATESTATUS="/getallactivatestatus";
    public static final String GETALLBYSTATUS="/getallbystatus";
    public static final String GETALLBYSTRINGSTATUS="/getallbystringstatus";
    public static final String GETBYROLE="/getbyrole/{role}";


}
