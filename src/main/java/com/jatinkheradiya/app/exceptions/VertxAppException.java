package com.jatinkheradiya.app.exceptions;

public class VertxAppException extends Exception  {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1394483839589428637L;

  /** The error code. */
  private final int errorCode;

  /** The exception type. */
  private final String exceptionType;

  /** The message. */
  private final String message;

  /** The more info. */
  private final String moreInfo;

  /**
   * Default Constructor.
   */
  public VertxAppException() {
    super();
    this.errorCode = 0;
    this.exceptionType = "";
    this.message = "";
    this.moreInfo = "";
  }

  /**
   * Constructor to send error message and code.
   *
   * @param message       is the error message.
   * @param exceptionType is the type of the application exception thrown.
   * @param errorCode     is the code of the application exception thrown.
   * @param moreInfo      the more info
   */
  public VertxAppException(final String message, final String exceptionType, int errorCode,
      final String moreInfo) {
    this.message = message;
    this.exceptionType = exceptionType;
    this.errorCode = errorCode;
    this.moreInfo = moreInfo;
  }

  /**
   * Constructor with 2 arguments.
   *
   * @param message is the error message
   * @param t       is exception to be thrown.
   */
  public VertxAppException(String message, Throwable t) {
    super(message, t);
    this.errorCode = 0;
    this.message = message;
    this.exceptionType = "";
    this.moreInfo = "";
  }

  /**
   * Method to get the code of application error.
   *
   * @return code of application error
   */
  public int getErrorCode() {
    return errorCode;
  }

  /**
   * Method to get the type of application error.
   *
   * @return type of application error
   */
  public String getExceptionType() {
    return exceptionType;
  }

  /**
   * Method to get the message of exception.
   *
   * @return message is message of exception.
   */
  @Override
  public String getMessage() {
    return message;
  }

  /**
   * Gets the more info.
   *
   * @return the more info
   */
  public String getMoreInfo() {
    return moreInfo;
  }

}
