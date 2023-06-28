package it.freelogix.gejson.parser.exception;

public class GeParserTokenInfoNotFoundException extends RuntimeException {
  public GeParserTokenInfoNotFoundException(String msg) {
    super(msg);
  }
  
  public GeParserTokenInfoNotFoundException(GeParserTokenInfoNotFoundException e) {
	    super(e);
	  }
}