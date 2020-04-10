package com.jatinkheradiya.app.entities;


/**
 * The Class ApiResponseVo.
 */
public class ApiResponseVo {

  /** The code. */
  private Integer code;

  /** The message. */
  private Object message;

  /** The message type. */
  private String moreInfo;

  /**
   * Getter method to get the code.
   * 
   * @return code.
   */
  public Integer getCode() {
    return code;
  }

  /**
   * Setter method to set the code.
   *
   * @param code the new code
   */
  public void setCode(Integer code) {
    this.code = (code < 0) ? 0 : code;
  }

  /**
   * Getter method to get the message.
   * 
   * @return message.
   */
  public Object getMessage() {
    return message;
  }

  /**
   * Setter method to set the message.
   *
   * @param message the new message
   */
  public void setMessage(Object message) {
    this.message = message;
  }

  /**
   * Getter method to get the more_info.
   * 
   * @return more_info.
   */
  public String getMoreInfo() {
    return moreInfo;
  }

  /**
   * Setter method to set the more_info.
   *
   * @param more_info the new more_info
   */

  public void setMoreInfo(String more_info) {
    this.moreInfo = more_info;
  }

  /**
   * Custom implementation of hashcode method.
   *
   * @return the int
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + ((moreInfo == null) ? 0 : moreInfo.hashCode());
    return result;
  }

  /**
   * Custom implementation of equals method.
   *
   * @param obj the obj
   * @return true, if successful
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ApiResponseVo other = (ApiResponseVo) obj;
    if (code == null) {
      if (other.code != null) {
        return false;
      }
    } else if (!code.equals(other.code)) {
      return false;
    }
    if (message == null) {
      if (other.message != null) {
        return false;
      }
    } else if (!message.equals(other.message)) {
      return false;
    }
    if (moreInfo == null) {
      if (other.moreInfo != null) {
        return false;
      }
    } else if (!moreInfo.equals(other.moreInfo)) {
      return false;
    }
    return true;
  }
}
