package it.freelogix.gejson.parser;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.freelogix.gejson.parser.exception.GeParserTokenInfoNotFoundException;
import it.freelogix.gejson.parser.exception.ParserException;

public class Tokenizer
{
	private class TokenInfo
	{
		public final Pattern regex;
		public final int token;
		public String regexString;

		public TokenInfo(String regex, int token)
		{
			super();
			this.regexString=regex;
			this.regex = Pattern.compile("^("+regex+")");
			this.token = token;
		}
	}
	
	public class Token {
		public final int token;
		public final String sequence;
		
		public Token(int _token, String _sequence)
		{
			super();
			token = _token;
			sequence = _sequence;
		}
	}

	private LinkedList<TokenInfo> tokenInfos;
	private LinkedList<Token> tokens;
	
	// ------------------------------------------------------------------
	
	/**
	 * Return token info by token id
	 * @param _tokenId int
	 * @return TokenInfo
	 */
	public TokenInfo getTokenInfo(int _tokenId) {
		for(TokenInfo ti :tokenInfos) {
			if(ti.token==_tokenId) {
				return ti;
			}
		}
		throw new GeParserTokenInfoNotFoundException("Token not found with ID: "+_tokenId);
	}
	
	/**
	 * Find and return token info by regex string
	 * @param _regex String
	 * @return TokenInfo
	 */
	public TokenInfo getTokenInfo(String _regex) {
		for(TokenInfo ti :tokenInfos) {
			if(ti.regexString.equals(_regex)) {
				return ti;
			}
		}
		throw new GeParserTokenInfoNotFoundException("Token not found with regex string: "+_regex);
	}

	// ------------------------------------------------------------------
	
	/**
	 * Remove all token info
	 */
	public void tokenInfosClear() {
		tokenInfos.clear();
	}
	
	// ------------------------------------------------------------------
	
	public Tokenizer()
	{
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
	}

	/**
	 * Add new token info
	 * @param regex String
	 * @param token int
	 */
	public void add(String regex, int token)
	{
		tokenInfos.add(new TokenInfo(regex, token));
	}

	public void tokenize(String str)
	{
		String s = str.trim();
		tokens.clear();
		while (!s.equals(""))
		{
			boolean match = false;
			for (TokenInfo info : tokenInfos)
			{
				Matcher m = info.regex.matcher(s);
				if (m.find())
				{
					match = true;
					String tok = m.group().trim();
					s = m.replaceFirst("").trim();
					tokens.add(new Token(info.token, tok));
					break;
				}
			}
			if (!match) {
				throw new ParserException("Unexpected character in input: "+s.charAt(0));
			}
		}
	}

	/**
	 * Return all token after tokenizin
	 * @return LinkedList<Token>
	 */
	public LinkedList<Token> getTokens()
	{
		return tokens;
	}
	
	/**
	 * Return all tokens info
	 * @return LinkedList<TokenInfo> 
	 */
	public LinkedList<TokenInfo> getTokensInfo()
	{
		return tokenInfos;
	}

}
