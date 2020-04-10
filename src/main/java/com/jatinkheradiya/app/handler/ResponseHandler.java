package com.jatinkheradiya.app.handler;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jatinkheradiya.app.entities.ApiResponseVo;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.vertx.ext.web.RoutingContext;


/**
 * This class manages all the response for the vertx.
 */

public class ResponseHandler {


  /**
   * Error Message for Serialization .
   */
  private static final String IO_EXCEPTION_MESSAGE =
      "IO Exception while serializing the error message: {}";

  /**
   * Instantiates a new response handler.
   */
  private ResponseHandler() {
  }

  /**
   * The Constant APPLICATION_JSON_CHARSET_UTF_8.
   */
  private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";

  /**
   * The Constant CONTENT_TYPE.
   */
  private static final String CONTENT_TYPE = "content-type";

  //  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(ResponseHandler.class);

  /**
   * Send error response.
   *
   * @param routingCtx     the routing ctx
   * @param appErrorCode   the app error code
   * @param errorMsg       the error msg
   * @param moreInfo       the more info
   * @param httpStatusCode the http status code
   */
  public static void sendErrorResponse(RoutingContext routingCtx, int appErrorCode, Object errorMsg,
      String moreInfo, int httpStatusCode) {
    try {
      ApiResponseVo responseVo = new ApiResponseVo();
      responseVo.setCode(appErrorCode);
      responseVo.setMessage(errorMsg);
      responseVo.setMoreInfo(moreInfo);
      routingCtx.response().putHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
          .setStatusCode(httpStatusCode).end(JsonUtil.createJsonFromObject(responseVo));
    } catch (final Exception e) {
      LOG.error(IO_EXCEPTION_MESSAGE + errorMsg + e);
    }
  }

  /**
   * Send error response.
   *
   * @param routingCtx the routing ctx
   * @param ex         the ex
   */
  public static void sendErrorResponse(RoutingContext routingCtx, VertxAppException ex) {
    try {
      ApiResponseVo responseVo = new ApiResponseVo();
      responseVo.setCode(ex.getErrorCode());
      responseVo.setMessage(ex.getMessage());
      responseVo.setMoreInfo(ex.getMoreInfo());
      routingCtx.response().putHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
          .setStatusCode(ex.getErrorCode()).end(JsonUtil.createJsonFromObject(responseVo));
    } catch (final VertxAppException e) {
      LOG.error(IO_EXCEPTION_MESSAGE, e);
    }
  }

  /**
   * Send error response.
   *
   * @param routingCtx the routing ctx
   * @param ex         the ex
   */
  public static void sendErrorResponse(RoutingContext routingCtx, Exception ex) {
    try {
      JSONObject responseVo = new JSONObject();
      //      responseVo.setCode(AppError.UNKNOWN_INTERNAL_ERROR.getCode());
      responseVo.put("Message", ex.getMessage());
      //      responseVo.setMoreInfo(ErrorMessageDetails.INVALID_REQUEST);
      routingCtx.response().putHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
          .setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
          .end(JsonUtil.createJsonFromObject(responseVo.toString()));
    } catch (final VertxAppException e) {
      String errorMsg = "Error while sending error response with exception";
      LOG.error(IO_EXCEPTION_MESSAGE, errorMsg, e);
    }
  }

  /**
   * This method sends the success response for the vertx by setting Success code and message in the
   * routing context.
   *
   * @param routingContext contains body, header and other http information
   * @param result         Response result in the form of Object
   * @param httpStatusCode the http status code
   */
  public static void sendSuccessResponse(RoutingContext routingContext, Object result,
      int httpStatusCode) {
    try {
      final String resultStr = JsonUtil.createJsonFromObject(result);

      routingContext.response().putHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
          .setStatusCode(httpStatusCode).end(resultStr);
    } catch (final VertxAppException e) {
      LOG.error("{}, Exception occured while serializing json response from object., {}",
          IO_EXCEPTION_MESSAGE, e);
      ResponseHandler
          .sendErrorResponse(routingContext, 101, "Error in parsing the request", "Invalid Request",
              HttpStatus.SC_BAD_REQUEST);
    }
  }

  /**
   * This method sends the success response for the vertx by setting Success code and message in the
   * routing context.
   * <p>
   * Overriding the method to ensure we always pass string.
   * </p>
   *
   * @param routingContext contains body, header and other http information
   * @param resultStr      Response result in the form of String
   * @param httpStatusCode the http status code
   */
  public static void sendSuccessResponse(RoutingContext routingContext, String resultStr,
      int httpStatusCode) {
    routingContext.response().putHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
        .setStatusCode(httpStatusCode).end(resultStr);
  }
}
