package com.jatinkheradiya.app.repo.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.repo.GenericRepo;

public class GenericRepoImpl implements GenericRepo {

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getCarManufacturers() {
    return databaseLayer.getCarManufacturers();
  }

  @Override
  public JSONObject getCarModelMap() {
    return null;
  }

  @Override
  public JSONArray getCarMakeModelMap() {
    return databaseLayer.getCarMakeModelMap();
  }
}
