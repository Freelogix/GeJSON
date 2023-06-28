package it.freelogix.gejson;

import it.freelogix.gejson.parser.json.GeJSONParser;

public class Test {

	public static void main(String[] args) {

		// get all to string with space, indent and new line
		//		System.out.println(books.toString());

//				String jsonStr="{\"books\":[{\"title\":\"Harry potter and the Philosopher's stone\",\"author\":\"K. J. Rowling\",\"pages\":\"100\",},{\"title\":\"Harry potter and the Secrects chamber\",\"author\":\"K. J. Rowling\",\"pages\":\"121\",\"characters\":[\"Harry Potter\",\"Hermione Granger\",\"Ron Wisley\",\"Albus Silente\"],}],}";
//				String jsonStr="{\"books\":[\"book1\",\"book2\",{\"sub-obj\":\"val\"}]}";
//				String jsonStr="{\"books\":{\"titolo\":\"Il nome della rosa\"}";
//				String jsonStr=""
//						+ "{"
//						+ "    \"pages\":100,"
//						+ "    \"author\":\"K. J. Rowling\","
//						+ "    \"title\":\"Harry potter and the Chamber of secrects\","
//						+ "    \"year\":\"2000\","
//						+ "    \"characters\":["
//						+ "        \"Harry Potter\","
//						+ "        \"Hermione Granger\","
//						+ "        \"Ron Wisley\","
//						+ "        \"Albus Silente\""
//						+ "    ],"
//						+ "    \"shops\":["
//						+ "        {"
//						+ "            \"available\":true,"
//						+ "            \"copies\":10,"
//						+ "            \"price\":25,"
//						+ "            \"price_unit\":\"€\","
//						+ "            \"name\":\"shop1\""
//						+ "        },"
//						+ "        {"
//						+ "            \"available\":false,"
//						+ "            \"copies\":0,"
//						+ "            \"price\":19,"
//						+ "            \"price_unit\":\"€\","
//						+ "            \"name\":\"shop2\""
//						+ "        }"
//						+ "    ]"
//						+ "}";
//		String  jsonStr="{"
//				+ "  	\"test\": \"ciao\""
//				+ "}";
//		String  jsonStr="{"
//				+ "  	\"logic-groups\":["
//				+ "  		{"
//				+ "  			\"name\":\"test1\","
//				+ "  			\"logic\":["
//				+ "	            {"
//				+ "	              \"class\":\"it.gexy.restbrain.server.core.blocks.LBLog\","
//				+ "	              \"data\":\"test1\""
//				+ "	            }"
//				+ "          	]"
//				+ "  		},"
//				+ "			{"
//				+ "  			\"name\":\"test2\","
//				+ "  			\"logic\":["
//				+ "	            {"
//				+ "	              \"class\":\"it.gexy.restbrain.server.core.blocks.LBLog\","
//				+ "	              \"data\":\"test2\""
//				+ "	            }"
//				+ "          	]"
//				+ "  		}"
//				+ "  	],"
//				+ "    \"services\":["
//				+ "      {\"service\":{"
//				+ "          \"name\":\"Test\","
//				+ "          \"class\":\"it.gexy.restbrain.server.services.EmptyService\","
//				+ "          \"logic\":["
//				+ "            {"
//				+ "              \"class\":\"it.gexy.restbrain.server.core.blocks.LBLog\","
//				+ "              \"data\":\"test\""
//				+ "            }"
//				+ "          ]"
//				+ "        }"
//				+ "      }      "
//				+ "    ]"
//				+ "}";
		

String jsonStr="{\"test\":[\"book1\",\"book2\",{\"sub-obj\":\"val\"}]}";
				GeJSONParser parser = new GeJSONParser(jsonStr);
		 		
				GeJSONObject o = (GeJSONObject) parser.parse();
				
				System.out.println(o.toString());
				
//				JSONPath x = new JSONPath();
//				
//				try {
//					x.parse(o,"pages");
//				}catch (JSONPathException e) {
//					System.out.println(e.getMessage());
//				}
//				
//				for(Token token : x.getParser().getTokens()) {
//					System.out.println(token.getSequence());
//				}
//				parser.addAction(GeJSONParser.TOKEN_NONE, new GeParserAction() {
//					
//					@Override
//					public void run(Token token) throws ParserActionException {
//						System.out.println(token.sequence);
//					}
//				});
//				
//				parser.addAction(GeJSONParser.TOKEN_BOOLEAN, new GeParserAction() {
//					
//					@Override
//					public void run(Token token) throws ParserActionException {
//						System.out.println("flota");
//					}
//				});
//				
//				
//////				
//				System.out.println("\n\n"+o.toString());
//
//				
//				ArrayList<GeJSONData> elements = o.getElements();
//				for(int a=0;a<elements.size();a++) {
//					System.out.println(elements.get(a).getKey());
//				}

//				System.out.println(book.toString());

		// {"books":[{"pages":"100","author":"K. J. Rowling","title":"Harry potter and the Philosopher's stone"},{"characters":["Harry Potter","Hermione Granger","Ron Wisley","Albus Silente"],"pages":"121","author":"K. J. Rowling","title":"Harry potter and the Secrects chamber"}]}


		// get all serialized and print value data type flag
		//		books.printValueDataTypeFlag(true);
		//		System.out.println("\n\n"+books.serialize());

		//{"books":[OBJ|{"pages":INT|"100","author":STR|"K. J. Rowling","title":STR|"Harry potter and the Philosopher's stone"},OBJ|{"characters":[STR|"Harry Potter",STR|"Hermione Granger",STR|"Ron Wisley",STR|"Albus Silente"],"pages":INT|"121","author":STR|"K. J. Rowling","title":STR|"Harry potter and the Secrects chamber"}]}
	
				
		
		
	}

}

