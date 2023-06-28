package it.freelogix.gejson.parser.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import it.freelogix.gejson.GeJSONArray;
import it.freelogix.gejson.GeJSONObject;
import it.freelogix.gejson.parser.GeParserAction;
import it.freelogix.gejson.parser.Tokenizer;
import it.freelogix.gejson.parser.Tokenizer.Token;
import it.freelogix.gejson.parser.exception.ParserException;
import it.freelogix.gejson.parser.exception.ParserActionException;

public class GeJSONParser{
	String jsonString;
	Tokenizer tokenizer;

	// ---------------------------------------------------------------------------------
	private boolean debugMode = true;
	
	public static final int TRACE = 0;
	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int WARNING = 3;
	public static final int ERROR = 4;
	
	/**
	 * Internal log, it's for debug, write message to stdout with INFO level
	 */
	private void log(String message) {
		if(!debugMode) {return;}
		log(message,INFO);
	}
	
	
	/**
	 * Internal log, it's for debug, write message to stdout with level specified by second parameter
	 */
	private void log(String message, int level) {
		if(!debugMode) {return;}
		String lvlStr="";
		switch(level) {
		case 0:
			lvlStr="TRACE";
			break;
		case 1:
			lvlStr="DEBUG";
			break;
		case 2:
			lvlStr="INFO";
			break;
		case 3:
			lvlStr="WARNING";
			break;
		case 4:
			lvlStr="ERROR";
			break;
		}
		System.out.println("[GeJSONParser]["+lvlStr+"] "+message);
	}
	// ---------------------------------------------------------------------------------

//	private boolean convertScientificNotationInObjectEnable=false;

	/**
	 * By default, when the pase encounters a number with scientific notation (with uppercase or lowercase e), 
	 * this is converted into a string. By enabling this function, the number is broken down into the numeric 
	 * part and the exponential part and organized into a JSON object similar to this:
	 * 
	 * {
	 *   "number" : 10,
	 *   "exp" : 2,
	 *   "string" : "10E2"
	 * }
	 * 
	 * @param _b boolean
	 */
//	public void convertScientificNotationInObject(boolean _b) {
//		convertScientificNotationInObjectEnable=_b;
//	}

	/**
	 * Returns true if conversion of scientific notation, into JSON object, is enabled
	 * @return boolean
	 */
//	public boolean convertScientificNotationInObjectEnableIsEnabled() {
//		return convertScientificNotationInObjectEnable;
//	}

	// ------------------

//	private boolean recognizeScientificNotationInObjectEnable=true;

	/**
	 * This method enables or disables the recognition of scientific notation 
	 * in numbers (a number between double quotes is not recognized).
	 * 
	 * If strict mode is enabled and scientific notation recognition is disabled, an error will be returned if found.
	 * @param _b boolean
	 */
//	public void recognizeScientificNotation(boolean _b) {
//		recognizeScientificNotationInObjectEnable=_b;
//	}

	/**
	 * Returns true if the recognition of scientific notation is enabled
	 * @return boolean
	 */
//	public boolean recognizeScientificNotationInObjectIsEnabled() {
//		return recognizeScientificNotationInObjectEnable;
//	}

	// ---------------------------------------------------------------------------------

	public static final int TOKEN_NONE = 0;
	public static final int TOKEN_OPEN_BRACKET = 1;
	public static final int TOKEN_CLOSE_BRACKET = 2;
	public static final int TOKEN_OPEN_SQUARE_BRACKET = 3;
	public static final int TOKEN_CLOSE_SQUARE_BRACKET = 4;
	public static final int TOKEN_TEXT_BETWEEN_DOUBLE_QUOTAS = 5;
	public static final int TOKEN_DOUBLE_DOT = 6;
	public static final int TOKEN_COMMA = 7;
	public static final int TOKEN_INTEGER = 8;
	public static final int TOKEN_FLOAT = 9;
	public static final int TOKEN_NUMBER_SCIENTIFIC_NOTATION = 10;
	public static final int TOKEN_BOOLEAN = 11;

	public GeJSONParser(String _jsonString) {
		jsonString=_jsonString;
		tokenizer=new Tokenizer();

	}
	
	/**
	 * Filtri del parser
	 */
	Map<Integer,ArrayList<GeParserAction>> filters = new HashMap<Integer,ArrayList<GeParserAction>>();
	
	/**
	 * Add a action to parser
	 * @param _tokenId Ineger
	 * @param _filterAction GeParserFilterAction
	 */
	public void addAction(int _tokenId,GeParserAction _action) {
		if(filters.get(_tokenId)!=null) {
			filters.get(_tokenId).add(_action);
		}else {
			ArrayList<GeParserAction> l = new ArrayList<GeParserAction>();
			l.add(_action);
			filters.put(_tokenId, l);
		}
		
	}
	
	/**
	 * Return the current entity,can be:
	 * GeJSONObject or GeJSONArray
	 * @see getCurrentEntityType()
	 * @return int
	 */
	public Object getCurrentEntity() {
		return o;
	}
	
	/**
	 * Return an integer the represent the current entity type,can be:
	 * GeJSONParser.OTYPE_OBJ or GeJSONParser.OTYPE_ARRAY.
	 * 
	 * The current entity can be JSON object or JSON array and is the object thet
	 * was processd from parser.
	 * @return int
	 */
	public int getCurrentEntityType() {
		return oType;
	}
	
	/**
	 * Return the root entity
	 * @return Object
	 */
	public Object getRootEntity() {
		return ret;
	}
	
	/**
	 * Return an integer the represent the root entity object type,can be:
	 * GeJSONParser.OTYPE_OBJ or GeJSONParser.OTYPE_ARRAY
	 * @return int
	 */
	public int getRootEntityType() {
		return retType;
	}
	
	/**
	 * Return the original input string that to be processed by parser
	 * @return String
	 */
	public String getRawInput() {
		return jsonString;
	}
	// ----------------------------------------------------------------------------------------------
	
	boolean tokensJSONIsAdded=false;
	
	/**
	 * Reeturn true if tokensJSONIsAdded() is called so the
	 * JSON tokens are loaded
	 * @return boolan
	 */
	public boolean JSONTokensIsAdded() {
		return tokensJSONIsAdded;
	}
	
	/**
	 * Add all JSON tokens in the parser:
	 * 
	 * priority 	string       token id								RegExp
	 * ------------------------------------------------------------------------------------------------------------------------------
	 * 1			{			TOKEN_OPEN_BRACKET						\\{
	 * 2			}			TOKEN_CLOSE_BRACKET						\\}
	 * 3			[			TOKEN_OPEN_SQUARE_BRACKET				\\[
	 * 4			]			TOKEN_CLOSE_SQUARE_BRACKET				\\]
	 * 5			,			TOKEN_COMMA								,
	 * 6			:			TOKEN_DOUBLE_DOT						:
	 * 7			"string"	TOKEN_TEXT_BETWEEN_DOUBLE_QUOTAS		\\\"[^\\\"]*\\\"
	 * 8			y, n		TOKEN_BOOLEAN							^(?:(y(?:es)?|t(?:rue)?|on)|(n(?:o)?|f(?:alse)?|off))$
	 * 8			true, false	TOKEN_BOOLEAN							^(?:(y(?:es)?|t(?:rue)?|on)|(n(?:o)?|f(?:alse)?|off))$
	 * 8			yes, no		TOKEN_BOOLEAN							^(?:(y(?:es)?|t(?:rue)?|on)|(n(?:o)?|f(?:alse)?|off))$
	 * 9			[+-]n.[n]	TOKEN_FLOAT								^([+-]?\\d*\\.?\\d*)$
	 * 9			[+-][n].n	TOKEN_FLOAT								^([+-]?(\\d+\\.)?\\d+)$
	 * 9			n.n			TOKEN_FLOAT								\\d+\\.\\d+
	 * 10			inter												[0-9]+
	 */
	public void addJSONTokens() {
//		tokenizer.add("sin|cos|exp|ln|sqrt", 1); // function
		tokenizer.add("\\{", TOKEN_OPEN_BRACKET); // open bracket
		tokenizer.add("\\}", TOKEN_CLOSE_BRACKET); // close bracket
		tokenizer.add("\\[", TOKEN_OPEN_SQUARE_BRACKET); // open bracket
		tokenizer.add("\\]", TOKEN_CLOSE_SQUARE_BRACKET); // close bracket
		tokenizer.add("\\\"[^\\\"]*\\\"", TOKEN_TEXT_BETWEEN_DOUBLE_QUOTAS); // text between double quotas
		tokenizer.add(":", TOKEN_DOUBLE_DOT); // double dot
		tokenizer.add(",", TOKEN_COMMA); // commas
		
		tokenizer.add("^([Yy]es|[Nn]o|[Tt]rue|[Ff]alse)", TOKEN_BOOLEAN);

		tokenizer.add("^([+-]?\\d*\\.?\\d*)$",TOKEN_FLOAT);
		tokenizer.add("^([+-]?(\\d+\\.)?\\d+)$",TOKEN_FLOAT);
		tokenizer.add("\\d+\\.\\d+",TOKEN_FLOAT);

		tokenizer.add("[0-9]+",TOKEN_INTEGER); // integer number

		//		tokenizer.add("[+-]", 9); // plus or minus
		//		tokenizer.add("[*/]", 10); // mult or divide
		//		tokenizer.add("\\^", 11); // raised
		//		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 13); // variable

		tokensJSONIsAdded=true;
	}
	
	/**
	 * Add a token that to be processed by parser, _regexp parameter must be passed a regura expression string 
	 * to find in the origina text passe to parser, as parameter _tokeId an integer must be passed which must 
	 * uniquely identify the entity
	 * @param _regExp String
	 * @param _numericId int
	 */
	public void addToken(String _regExp,int _tokenId) {
		tokenizer.add(_regExp, _tokenId);
	}
	
	/**
	 * Clare all tokens info
	 */
	public void clearTokens() {
		tokenizer.tokenInfosClear();
	}
	
	// ----------------------------------------------------------------------------------------------


	//	  tokenizer.tokenize(" sin(x) * (1 + var_12) ");

	private final static int OP_INIT=0;
	private final static int OP_NOP=1;
	private final static int OP_PARENT=2;
	private final static int OP_CREATE_OBJECT_PAIR_KEY=50;
	private final static int OP_SET_ARRAY_VALUE=60;
	private final static int OP_SET_OBJECT_PAIR_VALUE=70;

	
	/**
	 * Used to identify the type of entity, in this case JSON Object
	 * represented by GeJSONObject class
	 */
	public final static int OTYPE_OBJ=100;
	
	/**
	 * Used to identify the type of entity, in this case JSON Array
	 * represented by GeJSONArray class
	 */
	public final static int OTYPE_ARRAY=200;

	// lista dei token dopo il parse
	private LinkedList<Token> toks;
	// indice durante lo scorrimento dei token dopo il parse
	private int tokIndex=0;
	// tipo di oggetto corrente
	private int oType=OTYPE_OBJ;
	// operazione da eseguire
	private int op = OP_INIT;
	// tipo di oggetto dell'entità root
	private int retType=OTYPE_OBJ;

	/**
	 * Returns the next token after the current one
	 * @return Token
	 */
	private Token getNextToken() {
		return toks.get(tokIndex+1);
	}

	/**
	 * Returns the previous token to the current one
	 * @return Token
	 */
	private Token getBackToken() {
		return toks.get(tokIndex-1);
	}

	/**
	 * If the next token is a : then you are perforce in a text tolekn representing the key
	 * @return boolean
	 */
	private boolean nextIsDdot() {
		return getNextToken().token==TOKEN_DOUBLE_DOT;
	}


	Object ret;
	Object o = new GeJSONObject();


	/**
	 * It executes the tokens created from the JSON string and composes the object structure that represents it.
	 * Returns the root object containing all the structure which is a GeJSONObject
	 * @return GeJSONObject
	 * @throws ParserException
	 */
	public Object parse() throws ParserException,ParserActionException{
		addJSONTokens();
		ret=((GeJSONObject)o);
		try {

			tokenizer.tokenize(jsonString);

			toks=tokenizer.getTokens();

			for (Tokenizer.Token tok : toks) {
				
				// actions
				ArrayList<GeParserAction> parserActions = filters.get(tok.token);
				
				if(parserActions!=null) {
					for (GeParserAction act : parserActions) {
						act.run(tok);
					}
				}else {
					parserActions = filters.get(TOKEN_NONE);
					if(parserActions!=null) {
						for (GeParserAction act : parserActions) {
							act.run(tok);
						}
					}
				}
				
				// -------------------

				switch(tok.token) {
				case TOKEN_OPEN_BRACKET: // {
					if(op==OP_SET_OBJECT_PAIR_VALUE) {
						// se ho settato la key e devo settare il valore 
						// ma trovo {, allora devo creare un nuovo oggetto come valore
						GeJSONObject obj = ((GeJSONObject)o);
						o=obj.put(obj.getLastData().getKey(),new GeJSONObject());
						oType=OTYPE_OBJ;
						op=OP_CREATE_OBJECT_PAIR_KEY;
					}else if(op==OP_SET_ARRAY_VALUE){
						o=((GeJSONArray)o).add(new GeJSONObject());
						oType=OTYPE_OBJ;
						op=OP_CREATE_OBJECT_PAIR_KEY;
					}else {
						if(oType==OTYPE_ARRAY) {
							o=((GeJSONArray)o).add(new GeJSONObject());
							oType=OTYPE_OBJ;
						}
						op=OP_CREATE_OBJECT_PAIR_KEY;
					}
					break;
				case TOKEN_CLOSE_BRACKET: // }
					if(oType==OTYPE_OBJ) {
						if(((GeJSONObject)o).hasParent()) {
							if(((GeJSONObject)o).getParentType()==OTYPE_OBJ) {
								o=((GeJSONObject)o).getParent();
								oType=OTYPE_OBJ;
							}else if (((GeJSONObject)o).getParentType()==OTYPE_ARRAY){
								o=((GeJSONObject)o).getParent();
								oType=OTYPE_ARRAY;
							}

						}
					}else if(oType==OTYPE_ARRAY){
						if(((GeJSONArray)o).hasParent()) {

							if(((GeJSONArray)o).getParentType()==OTYPE_OBJ) {
								o=((GeJSONArray)o).getParent();
								oType=OTYPE_OBJ;
							}else if (((GeJSONArray)o).getParentType()==OTYPE_ARRAY){
								o=((GeJSONArray)o).getParent();
								oType=OTYPE_ARRAY;
							}

						}
					}
					op=OP_PARENT;
					break;
				case TOKEN_OPEN_SQUARE_BRACKET: // [
					log("Found: TOKEN_OPEN_SQUARE_BRACKET");
					if(oType==OTYPE_OBJ) {
						log("Current entity type: OTYPE_OBJ");
						log("Get current working entity");
						GeJSONObject obj = (GeJSONObject)o;
						log("Creating new array as current entity");
						try {
							o=((GeJSONObject)o).put(obj.getLastData().getKey(),new GeJSONArray());
						}catch(IndexOutOfBoundsException e) {
							o=new GeJSONArray();
							ret=o;
						}
						
						log("Set current type: OTYPE_ARRAY");
						oType=OTYPE_ARRAY;
						log("Set op: OP_SET_ARRAY_VALUE");
						op=OP_SET_ARRAY_VALUE;
					}else if(oType==OTYPE_ARRAY) {
						log("Current entity type: OTYPE_ARRAY");
						log("Create new array and add to current entity(array in array)");
						o=((GeJSONArray)o).add(new GeJSONArray());
						log("Set current type: OTYPE_ARRAY");
						oType=OTYPE_ARRAY;
						log("Set op: OP_SET_ARRAY_VALUE");
						op=OP_SET_ARRAY_VALUE;
					}
					break;
				case TOKEN_CLOSE_SQUARE_BRACKET: // ]
					log("Found: TOKEN_CLOSE_SQUARE_BRACKET");
					if(oType==OTYPE_OBJ) {
						log("Current entity type: OTYPE_OBJ");
						if(((GeJSONObject)o).hasParent()) {
							log("Current entity has parent");
							if(((GeJSONObject)o).getParentType()==OTYPE_OBJ) {
								log("Current entity parent type: OTYPE_OBJ");
								o=((GeJSONObject)o).getParent();
								log("Get parent of current entity");
								oType=OTYPE_OBJ;
								log("Set current entity type: OTYPE_OBJ");
							}else if (((GeJSONObject)o).getParentType()==OTYPE_ARRAY){
								log("Current entity type: OTYPE_ARRAY");
								if(((GeJSONObject)o).hasParent()) {
									log("Current entity has parent");
									log("Set current entity to parent");
									o=((GeJSONObject)o).getParent();
								}
								log("Set current entity type: OTYPE_ARRAY");
								oType=OTYPE_ARRAY;
							}
						}
					}else if(oType==OTYPE_ARRAY){
						log("Current entity type: OTYPE_ARRAY");
						if(((GeJSONArray)o).hasParent()) {
							log("Current entity has parent");
							if(((GeJSONArray)o).getParentType()==OTYPE_OBJ) {
								log("Current entity type: OTYPE_OBJ");
								log("Set current entity to parent");
								o=((GeJSONArray)o).getParent();
								log("Set current entity type: OTYPE_OBJ");
								oType=OTYPE_OBJ;
							}else if (((GeJSONArray)o).getParentType()==OTYPE_ARRAY){
								log("Current entity type: OTYPE_ARRAY");
								log("Current entity to parent");
								o=((GeJSONArray)o).getParent();
								log("Set current entity type: OTYPE_ARRAY");
								oType=OTYPE_ARRAY;
							}
						}
					}
					op=OP_PARENT;
					break;
				case TOKEN_TEXT_BETWEEN_DOUBLE_QUOTAS: // " "
					String content=tok.sequence.substring(1, tok.sequence.length()-1);

					if(oType==OTYPE_OBJ && op==OP_CREATE_OBJECT_PAIR_KEY) {
						// l'entità corrente è un oggetto
						// è stato appena creato un oggeto, ho incontrato una stringa
						// va creato un nuovo pair(key/value) che ha come key la stringa corrente
						((GeJSONObject)o).put(content);
						op=OP_SET_OBJECT_PAIR_VALUE;
					} else if(oType==OTYPE_OBJ && op==OP_SET_OBJECT_PAIR_VALUE) {
						// l'entità corrente è un oggetto
						// è stata settata la key(quindi sei passato per uno degli stati precedenti)
						// devi settare il value(in questo case è testo)
						((GeJSONObject)o).getLastData().setValue(content);
					} else if(oType==OTYPE_ARRAY && op==OP_SET_ARRAY_VALUE) {
						// l'entità corrente è un array
						// è stata settata la key(quindi sei passato per uno degli stati precedenti)
						// devi settare il il record(in questo case è testo)
						((GeJSONArray)o).add(content);
					}
					break;
				case TOKEN_DOUBLE_DOT: // :
					break;
				case TOKEN_COMMA: // ,
					if(op==OP_SET_OBJECT_PAIR_VALUE||op==OP_PARENT) {
						op=OP_CREATE_OBJECT_PAIR_KEY;
					}else if(op==OP_SET_ARRAY_VALUE) {
						op=OP_SET_ARRAY_VALUE;
					}

					break;
				case TOKEN_FLOAT:
					// è stata settata la key(quindi sei passato per uno degli stati precedenti)

					if(oType==OTYPE_OBJ && op==OP_SET_OBJECT_PAIR_VALUE) {
//						if(converNumberToStringEnable) {
//							// devi settare il value(in questo case è testo)
//							((GeJSONObject)o).getLastData().setValue(tok.sequence);
//						}else {
							float number_float=Float.parseFloat(tok.sequence);
							// devi settare il value(in questo case è numero)
							((GeJSONObject)o).getLastData().setValue(number_float);
//						}
					} else if(oType==OTYPE_ARRAY && op==OP_SET_ARRAY_VALUE) {
//						if(converNumberToStringEnable) {
//							// devi settare il value(in questo case è testo)
//							((GeJSONArray)o).add(tok.sequence);
//						}else {
							float number_float=Float.parseFloat(tok.sequence);
							// devi settare il il record(in questo case è numero)
							((GeJSONArray)o).add(number_float);
//						}
						
					}
					break;
				case TOKEN_INTEGER:
					// è stata settata la key(quindi sei passato per uno degli stati precedenti)
					if(oType==OTYPE_OBJ && op==OP_SET_OBJECT_PAIR_VALUE) {
//						if(converNumberToStringEnable) {
//							// devi settare il value(in questo case è testo)
//							((GeJSONObject)o).getLastData().setValue(tok.sequence);
//						}else {
							int number_integer=Integer.valueOf(tok.sequence);
							// devi settare il value(in questo case è numero)
							((GeJSONObject)o).getLastData().setValue(number_integer);
//						}
					} else if(oType==OTYPE_ARRAY && op==OP_SET_ARRAY_VALUE) {
//						if(converNumberToStringEnable) {
//							// devi settare il value(in questo case è testo)
//							((GeJSONArray)o).add(tok.sequence);
//						}else {
							int number_integer=Integer.valueOf(tok.sequence);
							// devi settare il il record(in questo case è numero)
							((GeJSONArray)o).add(number_integer);
//						}
					}
					break;
				case TOKEN_BOOLEAN:
					// è stata settata la key(quindi sei passato per uno degli stati precedenti)
					String content_bool = tok.sequence.toLowerCase().trim();
					
//					if(converBooleanToNumberEnable) {
//						int value=0;
//						if(content.equals("true")||content.equals("on")) {
//							value=1;
//						}else if(content.equals("false")||content.equals("off")){
//							value=0;
//						}else {
//							throw new ParserException("Boolean value has been recognized by tokenizer but is not true,false,on or off string, the value is:\""+tok.sequence+"\"");
//						}
//					}else {
						boolean value=false;
						if(content_bool.equals("true")||content_bool.equals("on")||content_bool.equals("yes")) {
							value=true;
						}else if(content_bool.equals("false")||content_bool.equals("off")||content_bool.equals("no")){
							value=false;
						}else {
							throw new ParserException("Boolean value has been recognized by tokenizer but is not true, false, on, off, y or n string, the value is:\""+tok.sequence+"\"");
						}
//					}
					
					if(oType==OTYPE_OBJ && op==OP_SET_OBJECT_PAIR_VALUE) {
//						if(converBooleanToStringEnable) {
//							// devi settare il value(in questo case è testo)
//							
//							((GeJSONObject)o).getLastData().setValue(tok.sequence);
//						}else {
							// devi settare il value(in questo case è numero)
							((GeJSONObject)o).getLastData().setValue(value);
//						}
					} else if(oType==OTYPE_ARRAY && op==OP_SET_ARRAY_VALUE) {
//						if(converBooleanToStringEnable) {
//							// devi settare il value(in questo case è testo)
//							((GeJSONArray)o).add(tok.sequence);
//						}else {
							// devi settare il il record(in questo case è numero)
							((GeJSONArray)o).add(value);
//						}
					}
					break;
//				case TOKEN_NUMBER_SCIENTIFIC_NOTATION:
//
//					// l'entità corrente è un oggetto
//					// è stata settata la key(quindi sei passato per uno degli stati precedenti)
//					if(oType==OTYPE_OBJ && op==OP_SET_OBJECT_PAIR_VALUE) {
//
//						if(convertScientificNotationInObjectEnable) {
//							GeJSONObject sno = new GeJSONObject();
//							int ePos = tok.sequence.indexOf("e");
//							if(ePos==-1) {ePos=tok.sequence.indexOf("E");}
//							
//							sno.put("number",Float.parseFloat(tok.sequence.substring(0, ePos)));
//							sno.put("exp",Float.parseFloat(tok.sequence.substring(ePos+1, tok.sequence.length())));
//							sno.put("string",tok.sequence);
//							
//							((GeJSONObject)o).getLastData().setValue(sno);
//						}else {
//							// devi settare il value(in questo case è testo)
//							String number_sn=tok.sequence.substring(0, tok.sequence.length());
//							((GeJSONObject)o).getLastData().setValue(number_sn);
//						}
//					} else if(oType==OTYPE_ARRAY && op==OP_SET_ARRAY_VALUE) {
//						if(convertScientificNotationInObjectEnable) {
//							GeJSONObject sno = new GeJSONObject();
//							
//							int ePos = tok.sequence.indexOf("e");
//							if(ePos==-1) {ePos=tok.sequence.indexOf("E");}
//							
//							sno.put("number",Float.parseFloat(tok.sequence.substring(0, ePos)));
//							sno.put("exp",Float.parseFloat(tok.sequence.substring(ePos+1, tok.sequence.length())));
//							
//							sno.put("string",tok.sequence);
//							((GeJSONArray)o).add(sno);
//						}else {
//							// devi settare il il record(in questo case è testo)
//							String number_sn=tok.sequence.substring(0, tok.sequence.length());
//							((GeJSONArray)o).add(number_sn);
//						}
//					}
//					break;
				}
				tokIndex++;
			}

			return ret;
		}
		catch (ParserException e) {
			throw new ParserException(e);
		}
	}
}


