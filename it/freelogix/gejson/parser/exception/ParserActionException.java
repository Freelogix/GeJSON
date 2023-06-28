package it.freelogix.gejson.parser.exception;

public class ParserActionException extends RuntimeException {
  public ParserActionException(String msg) {
    super(msg);
  }
  
  public ParserActionException(ParserActionException e) {
	    super(e);
	  }
}