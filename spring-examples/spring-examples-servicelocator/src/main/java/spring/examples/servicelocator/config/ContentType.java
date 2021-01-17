package spring.examples.servicelocator.config;

import spring.examples.servicelocator.config.TypeConstants;

public enum ContentType {
  JSON(TypeConstants.JSON_PARSER, TypeConstants.JSON_SUFFIX) ,
  CSV(TypeConstants.CSV_PARSER, TypeConstants.CSV_SUFFIX) ,
  XML(TypeConstants.XML_PARSER, TypeConstants.XML_SUFFIX ) ;
  private final String parserName;
  public  String fileName;

  ContentType(String parserName, String suffix) {
    this.parserName = parserName;
    this.fileName = "workers." + suffix;
  }

  @Override//By the string the right parser is matched
  public String toString() {
    return parserName;
  }


}
