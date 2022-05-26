package com.apex.technicaltest.constants;

public class LogConstants {

    private LogConstants() {
    }

    //Constact Error log message
    public static final String ERROR_LOG_FAILED_CALL_EXCEPTION_ACCOUNTS_SERVICE_RETRIEVE_RECORDS = "Failed call exception as a result of external service call might be unavailble. Available accounts not retrieved.";
    public static final String ERROR_LOG_FAILED_CALL_EXCEPTION_CHAT_SERVICE_UPDATE_CHAT = "Failed call exception as a result of external service call might be unavailble. Failed to update chat tag.";
    public static final String ERROR_LOG_FAILED_CALL_EXCEPTION_CHAT_SERVICE_LATEST_CHAT = "Failed call exception as a result of external service call might be unavailble. Failed to retrieve latest chat.";
    public static final String ERROR_LOG_FAILED_LOGIN_EXCEPTION = "Failed to authenticate. Please login to establish a new session";
}
