package com.jatinkheradiya.app.repo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface GenericRepo {

  public JSONArray getCarManufacturers();

  public JSONObject getCarModelMap();

  JSONArray getCarMakeModelMap();
}
