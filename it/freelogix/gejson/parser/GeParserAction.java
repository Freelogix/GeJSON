package it.freelogix.gejson.parser;

import it.freelogix.gejson.parser.Tokenizer.Token;
import it.freelogix.gejson.parser.exception.ParserActionException;

abstract public class GeParserAction {
	protected Token token;
	
	public abstract void run(Token _token) throws ParserActionException;

}
