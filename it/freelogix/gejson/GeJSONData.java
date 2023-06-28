package it.freelogix.gejson;


public class GeJSONData extends GeJSONEntity{
	/**
	 * Create null value
	 */
	public GeJSONData() {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue();
	}
	
	/**
	 * Create Integer value
	 * @param _value Integer
	 */
	public GeJSONData(int _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}
	
	/**
	 * Create Java object value
	 * @param _value Object
	 */
	public GeJSONData(Object _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}

	/**
	 * Create float value
	 * @param _value float
	 */
	public GeJSONData(float _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}

	/**
	 * Create double value
	 * @param _value double
	 */
	public GeJSONData(double _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}

	/**
	 * Create byte value
	 * @param _value byte
	 */
	public GeJSONData(byte _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}

	/**
	 * Create String value
	 * @param _value String
	 */
	public GeJSONData(String _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}

	/**
	 * Create Boolean value
	 * @param _value Boolean
	 */
	public GeJSONData(boolean _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_value);
	}

	/**
	 * Create json object as value
	 * @param _value GeJSONObject
	 */
	public GeJSONData(GeJSONObject _obj) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_obj);
	}

	/**
	 * Create json array as value
	 * @param _value GeJSONArray
	 */
	public GeJSONData(GeJSONArray _array) {
		super(GeJSONEntity.TYPE_DATA);
		this.setValue(_array);
	}

	/**
	 * Create pair key/value with a integer value
	 * @param _key		String
	 * @param _value	Integer
	 */
	public GeJSONData(String _key,int _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}

	/**
	 * Create pair key/value with a float value
	 * @param _key		String
	 * @param _value	Float
	 */
	public GeJSONData(String _key,float _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}
	
	/**
	 * Create pair key/value with a long value
	 * @param _key		String
	 * @param _value	Long
	 */
	public GeJSONData(String _key,long _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}

	/**
	 * Create pair key/value with a double value
	 * @param _key		String
	 * @param _value	Double
	 */
	public GeJSONData(String _key,double _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}

	/**
	 * Create pair key/value with a byte value
	 * @param _key		String
	 * @param _value	Byte
	 */
	public GeJSONData(String _key,byte _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}

	/**
	 * Create pair key/value with a String value
	 * @param _key		String
	 * @param _value	String
	 */
	public GeJSONData(String _key,String _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}

	/**
	 * Create pair key/value with a boolean value
	 * @param _key		String
	 * @param _value	boolean
	 */
	public GeJSONData(String _key,boolean _value) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_value);
	}

	/**
	 * Create pair key/value with a json object(GeJSONObject) as value
	 * @param _key		String
	 * @param _value	GeJSONObject
	 */
	public GeJSONData(String _key,GeJSONObject _obj) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_obj);
	}

	/**
	 * Create pair key/value with a json object(GeJSONArray) as value
	 * @param _key		String
	 * @param _value	GeJSONArray
	 */
	public GeJSONData(String _key,GeJSONArray _array) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_array);
	}
	
	/**
	 * Create pair key/value with a java object as value
	 * @param _key		String
	 * @param _value	Object
	 */
	public GeJSONData(String _key,Object _rawObj) {
		super(GeJSONEntity.TYPE_DATA);
		this.setKey(_key);
		this.setValue(_rawObj);
	}

	// --------------------------------------------------------------------------------------------------------
	
	 String key;
	
	/**
	 * Set key of this key/data pair
	 * @param _key String 
	 */
	public void setKey(String _key) {
		key=_key;
	}
	
	/**
	 * Return key string f this key/data pair
	 * @return String
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Return true if this key/data pair has setted key
	 * @return boolean
	 */
	public boolean hasKey() {
		if(key!=null) {
			return true;
		}else {
			return false;
		}
	}

	// --------------------------------------------------------------------------------------------------------

	public static final int DATA_TYPE_BYTE = 0;
	public static final int DATA_TYPE_INT = 10;
	public static final int DATA_TYPE_FLOAT = 20;
	public static final int DATA_TYPE_DOUBLE = 30;
	public static final int DATA_TYPE_BOOLEAN = 40;
	public static final int DATA_TYPE_STRING = 50;
	public static final int DATA_TYPE_OBJECT = 60;
	public static final int DATA_TYPE_NULL = 70;
	public static final int DATA_TYPE_ARRAY = 80;
	public static final int DATA_TYPE_LONG = 90;
	public static final int DATA_TYPE_OBJECT_RAW = 100;

	int dataType=DATA_TYPE_NULL;

	/**
	 * Return an integer represent the type of value in this key/value pair, see DATA_TYPE_ constats
	 * @return integer
	 */
	public int getValueType() {
		return dataType;
	}
	
	// --------------------------------------------------------------------------------------------------------
	
	boolean valueDataTypePrintEnabled=false;
	
	/**
	 * Enables writing of the abbreviation of the data type when toString() or Serialize() is called
	 * @param _b boolean
	 */
	public void printValueDataTypeFlagEnable(boolean _b) {
		valueDataTypePrintEnabled=_b;
		
		if(valueIsJSONObject()) {
			getObject().printValueDataTypeFlagEnable(_b);
		} else if(valueIsJSONArray()) {
			getArray().printValueDataTypeFlagEnable(_b);
		}
	}
	
	public boolean isEnabledPrintValueDataTypeFlag(boolean _b) {
		return valueDataTypePrintEnabled;
	}
	
	// --------------------------------------------------------------------------------------------------------

	/**
	 * Return true if value of key/value pair is integer
	 * @return boolean
	 */
	public boolean valueIsInt() {
		if(dataType==DATA_TYPE_INT) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is float
	 * @return boolean
	 */
	public boolean valueIsFloat() {
		if(dataType==DATA_TYPE_FLOAT) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is double
	 * @return boolean
	 */
	public boolean valueIsDouble() {
		if(dataType==DATA_TYPE_DOUBLE) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is string
	 * @return boolean
	 */
	public boolean valueIsString() {
		if(dataType==DATA_TYPE_STRING) {
			System.out.println(dataType);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is boolean
	 * @return boolean
	 */
	public boolean valueIsBoolean() {
		if(dataType==DATA_TYPE_BOOLEAN) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is null
	 * @return boolean
	 */
	public boolean valueIsNull() {
		if(dataType==DATA_TYPE_NULL) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is json object(GeJSONObject)
	 * @return boolean
	 */
	public boolean valueIsJSONObject() {
		if(dataType==DATA_TYPE_OBJECT) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Return true if value of key/value pair is json array(GeJSONArray)
	 * @return boolean
	 */
	public boolean valueIsJSONArray() {
		if(dataType==DATA_TYPE_ARRAY) {
			return true;
		}else {
			return false;
		}
	}

	// --------------------------------------------------------------------------------------------------------

	Object value=null;

	public void setValue(int _value) {
		value=_value;
		dataType=DATA_TYPE_INT;
	}

	public void setValue(float _value) {
		value=_value;
		dataType=DATA_TYPE_FLOAT;
	}
	
	public void setValue(long _value) {
		value=_value;
		dataType=DATA_TYPE_LONG;
	}

	public void setValue(double _value) {
		value=_value;
		dataType=DATA_TYPE_DOUBLE;
	}

	public void setValue(String _value) {
		value=_value;
		dataType=DATA_TYPE_STRING;
	}

	public void setValue(byte _value) {
		value=_value;
		dataType=DATA_TYPE_BYTE;
	}

	public void setValue(boolean _value) {
		value=_value;
		dataType=DATA_TYPE_BOOLEAN;
	}

	public void setValue() {
		value=null;
		dataType=DATA_TYPE_NULL;
	}

	public void setValue(GeJSONObject _value) {
		value=_value;
		dataType=DATA_TYPE_OBJECT;
	}

	public void setValue(GeJSONArray _array) {
		value=_array;
		dataType=DATA_TYPE_ARRAY;
	}
	
	public void setValue(Object _rawObj) {
		value=_rawObj;
		dataType=DATA_TYPE_OBJECT_RAW;
	}

	// --------------------------------------------------------------------------------------------------------

	public int getInt() {
		return (Integer)value;
	}

	public float getFloat() {
		return (Float)value;
	}
	
	public long getLong() {
		return (Long)value;
	}

	public double getDouble() {
		return (Double)value;
	}

	public String getString() {
		return (String)value;
	}

	public byte getByte() {
		return (Byte)value;
	}

	public boolean getBoolean() {
		return (Boolean) value;
	}

	public GeJSONObject getObject() {
		return (GeJSONObject)value;
	}

	public GeJSONArray getArray() {
		return (GeJSONArray) value;
	}
	
	public Object get() {
		return value;
	}
	

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Return a string of value, for the array and objects call serialize() of these classes. The null value returned without quotas.
	 * @return String
	 */
	public String serialize() {
		String out="";
		
		String key="";
		if(hasKey()) {
			key="\""+getKey()+"\":";
		}

		switch(this.getValueType()) {
		case DATA_TYPE_INT:
			out+=key+printValueDataTypeFlag(DATA_TYPE_INT)+getInt();
			break;
		case DATA_TYPE_FLOAT:
			out+=key+printValueDataTypeFlag(DATA_TYPE_FLOAT)+getFloat();
			break;
		case DATA_TYPE_DOUBLE:
			out+=key+printValueDataTypeFlag(DATA_TYPE_DOUBLE)+getDouble();
			break;
		case DATA_TYPE_BYTE:
			out+=key+printValueDataTypeFlag(DATA_TYPE_BYTE)+"\""+getByte()+"\"";
			break;
		case DATA_TYPE_STRING:
			out+=key+printValueDataTypeFlag(DATA_TYPE_STRING)+"\""+getString()+"\"";
			break;
		case DATA_TYPE_BOOLEAN:
			out+=key+printValueDataTypeFlag(DATA_TYPE_BOOLEAN)+((getBoolean())?"true":"false");
			break;
		case DATA_TYPE_NULL:
			out+=key+printValueDataTypeFlag(DATA_TYPE_NULL)+"null";
			break;
		case DATA_TYPE_OBJECT:
			out+=key+getObject().serialize();
			break;
		case DATA_TYPE_OBJECT_RAW:
			out+=key+get().toString();
			break;
		case DATA_TYPE_ARRAY:
			out+=key+getArray().serialize();
			break;
		}

		return out;
	}

	// --------------------------------------------------------------------------------------------------------
	
	/**
	 * Return a string of value, for the array and object call toString() of these classes. The null value returned without quotas.
	 * @return String
	 */
	public String toString() {
		return toString(0);
	}
	
	/**
	 * Return a string of value, for the array and object call toString() of these classes. The null value returned without quotas.
	 * @return String
	 */
	public String toString(int _indent) {
		String out="";
		
		String key="";
		if(hasKey()) {
			key="\""+getKey()+"\":";
		}

		switch(this.getValueType()) {
		case DATA_TYPE_INT:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_INT)+getInt();
			break;
		case DATA_TYPE_FLOAT:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_FLOAT)+getFloat();
			break;
		case DATA_TYPE_DOUBLE:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_DOUBLE)+getDouble();
			break;
		case DATA_TYPE_BYTE:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_BYTE)+"\""+getByte()+"\"";
			break;
		case DATA_TYPE_STRING:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_STRING)+"\""+getString()+"\"";
			break;
		case DATA_TYPE_BOOLEAN:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_BOOLEAN)+((getBoolean())?"true":"false");
			break;
		case DATA_TYPE_NULL:
			out+=printIndent(_indent+1)+key+printValueDataTypeFlag(DATA_TYPE_NULL)+"null";
			break;
		case DATA_TYPE_OBJECT:
			out+=key+getObject().toString(_indent+1);
			break;
		case DATA_TYPE_OBJECT_RAW:
			out+=key+get().toString();
			break;
		case DATA_TYPE_ARRAY:
			out+=printIndent(_indent+1)+key+getArray().toString(_indent+1);
			break;
		}

		return out;
	}
	
	/**
	 * Return data type chars if enabled
	 * @param _indent
	 * @return
	 */
	private String printValueDataTypeFlag(int _type) {
		String out="";
		if(valueDataTypePrintEnabled) {
			switch(_type) {
			case DATA_TYPE_INT:
				out="INT|";
				break;
			case DATA_TYPE_FLOAT:
				out="FLT|";
				break;
			case DATA_TYPE_DOUBLE:
				out="DBL|";
				break;
			case DATA_TYPE_BYTE:
				out="BYT|";
				break;
			case DATA_TYPE_STRING:
				out="STR|";
				break;
			case DATA_TYPE_NULL:
				out="NUL|";
				break;
			case DATA_TYPE_OBJECT:
				out="OBJ|";
				break;
			case DATA_TYPE_ARRAY:
				out="ARY|";
				break;
			default:
				out="UNK|";
			}
		}
		return out;
	}
}
