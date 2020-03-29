package com.jatinkheradiya.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

  private static ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  public static synchronized <T> T createObjectFromString(String jsonString, Class<T> clazz)
      throws Exception {
    try {
      return mapper.readValue(jsonString, clazz);

    } catch (Exception e) {
//      String incorrectFieldMessage = "The request string is invalid.";
//      if (e.getMessage() != null) {
//        final Matcher matcher =
//            Pattern.compile("Unrecognized field \"(.*?)\"").matcher(e.getMessage());
//        if (matcher.find()) {
//          incorrectFieldMessage = "";
//        }
//      }
      throw e;
    }
  }

  public static synchronized String createJsonFromObject(Object obj)
      throws Exception {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
     throw e;
    }
  }

}
