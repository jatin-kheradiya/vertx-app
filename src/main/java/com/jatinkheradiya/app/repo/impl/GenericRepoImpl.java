package com.jatinkheradiya.app.repo.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.GenericRepo;

public class GenericRepoImpl implements GenericRepo {

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getCarManufacturers() {
    JSONArray jsonArray = new JSONArray();
    try {
      List<String> carManufacturers = databaseLayer.getCarManufacturers();
      for (String carMake : carManufacturers) {
        JSONObject car = new JSONObject();
        car.put("make", carMake);
        jsonArray.add(car);
      }
    } catch (VertxAppException e) {
      e.printStackTrace();
    }
    return jsonArray;
  }

  @Override
  public JSONObject getCarModelMap() {
    return null;
  }

  @Override
  public JSONArray getCarMakeModelMap() {
    JSONArray makeModelMap = new JSONArray();
    try {
      List results = databaseLayer.getCarMakeModelMap();
      for (Object resultElement: results) {

        JSONObject makeModel = new JSONObject();
        Object[] resultArray = (Object[]) resultElement;
        for (int i = 1; i <= resultArray.length; i++) {
          makeModel.put("make", resultArray[0].toString());
          String modelsList = resultArray[1].toString().replaceAll("\"", "");
          String models = modelsList.substring(1, modelsList.length() - 1);
          makeModel.put("models", models);
        }
        makeModelMap.add(makeModel);
      }
    } catch (VertxAppException e) {
      e.printStackTrace();
    }
    return makeModelMap;
  }
}
