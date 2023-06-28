package it.freelogix.gejson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.freelogix.gejson.exception.GeJSONKeyNotFoundException;

public class GeJSONObject extends GeJSONEntity{	
	/**
	 * Create an empty GeJSONObject
	 */
	public GeJSONObject() {
		super(GeJSONEntity.TYPE_OBJECT);
	}
	
	/**
	 * Create a json object with a key and a float as value,
	 * @param _key		String
	 * @param _value	Float
	 */
//	public GeJSONObject(String _key,float _value) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_value);
//	}
	
	/**
	 * Create a json object with a key and a double as value,
	 * @param _key		String
	 * @param _value	double
	 */
//	public GeJSONObject(String _key,double _value) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_value);
//	}
	
	/**
	 * Create a json object with a key and a byte as value,
	 * @param _key		String
	 * @param _value	Byte
	 */
//	public GeJSONObject(String _key,byte _value) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_value);
//	}
	
	/**
	 * Create a json object with a key and a boolean as value,
	 * @param _key		String
	 * @param _value	boolean
	 */
//	public GeJSONObject(String _key,boolean _value) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_value);
//	}

	/**
	 * Create a json object with a key and a integer as value,
	 * @param _key		String
	 * @param _value	integer
	 */
//	public GeJSONObject(String _key,int _value) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_value);
//	}
	
	/**
	 * Create a json object with a key and null as value,
	 * @param _key		String
	 */
//	public GeJSONObject(String _key) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key);
//	}

	/**
	 * Create a json object with a key and a String as value,
	 * @param _key		String
	 * @param _value	String
	 */
//	public GeJSONObject(String _key,String _value) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_value);
//	}

	/**
	 * Create a json object with a key and a json object(GeJSONObject) as value,
	 * @param _key		String
	 * @param _value	GeJSONObject
	 */
//	public GeJSONObject(String _key,GeJSONObject _obj) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_obj);
//	}

	/**
	 * Create a json object with a key and an array(GeJSONArray) as value,
	 * @param _key		String
	 * @param _value	GeJSONArray
	 */
//	public GeJSONObject(String _key,GeJSONArray _Array) {
//		super(GeJSONEntity.TYPE_OBJECT);
//		put(_key,_Array);
//	}
	
	/**
	 * Create a json object with a key and an java generic object as value,
	 * @param _key		String
	 * @param _value	Object
	 */
	public GeJSONObject(String _key,Object _Obj) {
		super(GeJSONEntity.TYPE_OBJECT);
		put(_key,_Obj);
	}
	
	// --------------------------------------------------------------------------------------------------------

	List<GeJSONData> elements = new ArrayList<GeJSONData>();
	
	/**
	 * Return number of pair key/value in the object
	 * @return int
	 */
	public int size() {
		return elements.size();
	}
	
	/**
	 * Return true id this object contain a specified key
	 * @param _key	String
	 * @return boolean
	 */
	public boolean containsKey(String _key) {
		for(int i=0;i<elements.size();i++) {
			if(elements.get(i).getKey().equals(_key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return position of key
	 * @param _key	String
	 * @return	Integer
	 * @throws GeJSONKeyNotFoundException
	 */
	public int getKeyIndex(String _key) throws GeJSONKeyNotFoundException {
		for(int i=0;i<elements.size();i++) {
			if(elements.get(i).getKey().equals(_key)) {
				return i;
			}
		}
		throw new GeJSONKeyNotFoundException("Key "+_key+" not found in the object");
	}

	/**
	 * Return the GeJSONData object, that contains key and value, by it's key string
	 * @param _key String
	 * @return	GeJSONData
	 * @throws GeJSONKeyNotFoundException
	 */
	public GeJSONData getDataByKey(String _key) throws GeJSONKeyNotFoundException {
		for(int i=0;i<elements.size();i++) {
			if(elements.get(i).getKey().equals(_key)) {
				return elements.get(i);
			}
		}
		throw new GeJSONKeyNotFoundException("Key "+_key+" not found in the object");
	}

	/**
	 * Return the GeJSONData from the it's position in the object
	 * @param _index	Integer
	 * @return	GeJSONData
	 * @throws IndexOutOfBoundsException
	 */
	public GeJSONData getDataByIndex(int _index) throws IndexOutOfBoundsException {
		try{
			return elements.get(_index);
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Index "+_index+" out of bounds of elements");
		}

	}
	
	/**
	 * Return last GeJSONData in the json object
	 * @return GeJSONData
	 */
	public GeJSONData getLastData() {
		return elements.get(elements.size()-1);
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Remove a key/value pair by it's key string
	 * @param _key String
	 * @return GeJSONObject
	 * @throws GeJSONKeyNotFoundException
	 */
	public GeJSONObject remove(String _key) throws GeJSONKeyNotFoundException {
		for(int i=0;i<elements.size();i++) {
			if(elements.get(i).getKey().equals(_key)) {
				elements.remove(i);
				return this;
			}
		}
		throw new GeJSONKeyNotFoundException("Key "+_key+" not found in the object");
	}
	
	// --------------------------------------------------------------------------------------------------------
	
	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	Object
	 */
	public void put(String _key, Object _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}
	
	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	Integer
	 */
	public void put(String _key, int _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}

	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	Float
	 */
	public void put(String _key,float _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}
	
	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	long
	 */
	public void put(String _key,long _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}

	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	Double
	 */
	public void put(String _key,double _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}

	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	String
	 */
	public void put(String _key,String _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}

	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	Byte
	 */
	public void put(String _key,byte _value) {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}

	/**
	 * Set value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	Boolean
	 */
	public void put(String _key,boolean _value)  {
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_value);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_value));
		}
	}

	/**
	 * Set null value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 */
	public void put(String _key)  {
		try {
			getDataByKey(_key).setValue();
		} catch (GeJSONKeyNotFoundException e) {
			GeJSONData d = new GeJSONData();
			d.setKey(_key);
			d.setValue();
			elements.add(d);
		}
	}

	/**
	 * Set json object(GeJSONObject) as a value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	GeJSONObject
	 */
	public GeJSONObject put(String _key,GeJSONObject _obj) {
		_obj.setParent(this,PARENT_TYPE_OBJECT);
		try {
			getDataByKey(_key).setValue(_obj);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_obj));
		}
		return _obj;
	}

	/**
	 * Set json array(GeJSONArray) as a value of a pair key/value, if the pair not exist put new pair key/value to the object 
	 * @param _key	String
	 * @param _value	GeJSONObject
	 */
	public GeJSONArray put(String _key,GeJSONArray _array)  {
		_array.setParent(this,PARENT_TYPE_OBJECT);
		try {
			GeJSONData d=getDataByKey(_key);
			d.setValue(_array);
		} catch (GeJSONKeyNotFoundException e) {
			elements.add(new GeJSONData(_key,_array));
		}
		return _array;
	}
	
	// --------------------------------------------------------------------------------------------------------
	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	Integer
	 * @throws GeJSONKeyNotFoundException
	 */
	public int getInt(String _key) throws GeJSONKeyNotFoundException {
		try {
			return elements.get(getKeyIndex(_key)).getInt();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _default	int
	 * @return	Integer
	 * @throws GeJSONKeyNotFoundException
	 */
	public int getInt(String _key, int _default)  {
		try {
			return elements.get(getKeyIndex(_key)).getInt();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}
	
	
	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	Float
	 * @throws GeJSONKeyNotFoundException
	 */
	public float getFloat(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException{
		try {
			return elements.get(getKeyIndex(_key)).getFloat();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _default	float
	 * @return	Float
	 */
	public float getFloat(String _key,float _default) {
		try {
			return elements.get(getKeyIndex(_key)).getFloat();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}

	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	_default	Double
	 * @throws GeJSONKeyNotFoundException
	 */
	public double getDouble(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException {
		try {
			return elements.get(getKeyIndex(_key)).getDouble();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _default	long
	 * @return	long
	 */
	public long getLong(String _key,long _default) {
		try {
			return elements.get(getKeyIndex(_key)).getLong();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}

	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	_default	Double
	 * @throws GeJSONKeyNotFoundException
	 */
	public long getLong(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException {
		try {
			return elements.get(getKeyIndex(_key)).getLong();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _default	double
	 * @return	Double
	 */
	public double getDouble(String _key,double _default)  {
		try {
			return elements.get(getKeyIndex(_key)).getDouble();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}
	 
	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	String
	 * @throws GeJSONKeyNotFoundException
	 */
	public String getString(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException {
		try {
			return elements.get(getKeyIndex(_key)).getString();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _default	String
	 * @return	String
	 */
	public String getString(String _key, String _default) {
		try {
			return elements.get(getKeyIndex(_key)).getString();
		}
		catch(GeJSONKeyNotFoundException | ClassCastException | NullPointerException e) {
			return _default;
		}
	}

	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	Byte
	 * @throws GeJSONKeyNotFoundException
	 */
	public byte getByte(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException {
		try {
			return elements.get(getKeyIndex(_key)).getByte();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _key	Byte
	 * @return	Byte
	 */
	public byte getByte(String _key, byte _default) {
		try {
			return elements.get(getKeyIndex(_key)).getByte();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}

	/**
	 * get value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	Boolean
	 * @throws GeJSONKeyNotFoundException
	 */
	public boolean getBoolean(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException{
		try {
			return elements.get(getKeyIndex(_key)).getBoolean();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get value of pair key/value from it's key string, if not present this return the _default parameter
	 * @param _key	String
	 * @param _default	boolean
	 * @return	Boolean
	 */
	public boolean getBoolean(String _key,boolean _default) {
		try {
			return elements.get(getKeyIndex(_key)).getBoolean();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}
	
	
	/**
	 * get json object(GeJSONObject) as value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	GeJSONObject
	 * @throws GeJSONKeyNotFoundException
	 */
	public GeJSONObject getObject(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException{
		try {
			return elements.get(getKeyIndex(_key)).getObject();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get json object(GeJSONObject) as value of pair key/value from it's key string, if not present this return the _default parameter 
	 * @param _key	String
	 * @param _key	Object
	 * @return	GeJSONObject
	 */
	public GeJSONObject getObject(String _key,GeJSONObject _default) {
		try {
			return elements.get(getKeyIndex(_key)).getObject();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}

	/**
	 * get json array(GeJSONArray) as value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	Integer
	 * @throws GeJSONKeyNotFoundException
	 */
	public GeJSONArray getArray(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException{
		try {
			return elements.get(getKeyIndex(_key)).getArray();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get json array(GeJSONArray) as value of pair key/value from it's key string, if not present this return the _default parameter
	 * @param _key	String
	 * @param _default	GeJSONArray
	 * @return	Integer
	 */
	public GeJSONArray getArray(String _key,GeJSONArray _default) {
		try {
			return elements.get(getKeyIndex(_key)).getArray();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}
	
	
	/**
	 * get java object as value of pair key/value from it's key string 
	 * @param _key	String
	 * @return	Object
	 * @throws GeJSONKeyNotFoundException
	 */
	public Object get(String _key) throws ClassCastException, NullPointerException, GeJSONKeyNotFoundException{
		try {
			return elements.get(getKeyIndex(_key)).get();
		}
		catch(GeJSONKeyNotFoundException e) {
			throw new GeJSONKeyNotFoundException("Key not found: "+_key);
		}
	}
	
	/**
	 * get java object as value of pair key/value from it's key string, if not present this return the _default parameter
	 * @param _key	String
	 * @param _default	Object
	 * @return	Object
	 */
	public Object get(String _key,Object _default){
		try {
			return elements.get(getKeyIndex(_key)).get();
		}
		catch(ClassCastException | NullPointerException | GeJSONKeyNotFoundException e) {
			return _default;
		}
	}
	
	// --------------------------------------------------------------------------------------------------------

	boolean valueDataTypePrintEnabled=false;

	/**
	 * Enables writing of the abbreviation of the data type when toString() or Serialize() is called
	 * @param _b boolean
	 */
	public void printValueDataTypeFlagEnable(boolean _b) {
		valueDataTypePrintEnabled=_b;

		for(int i=0;i<elements.size();i++) {
			elements.get(i).printValueDataTypeFlagEnable(_b);
		}
	}

	public boolean isEnabledPrintValueDataTypeFlag(boolean _b) {
		return valueDataTypePrintEnabled;
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Return a string represent the JSON structure of data without new line and with double quotas for keys and values, null value represent without quotas.
	 *  for the array and objects call serialize() of these classes.
	 * @return String
	 */
	public String serialize() {
		String out="";

		out+="{";

		for(int i=0;i<elements.size();i++) {
			out+=elements.get(i).serialize();
			if(i<elements.size()-1) { out+=","; }
		}

		out+="}";

		return out;
	}

	/**
	 * Return a string represent the JSON structure of data with newline, tab and spaces and with double quotas for keys and values, null value represent without quotas.
	 * for the array and objects call toString() of these classes.
	 * @return String
	 */
	public String toString() {
		return toString(0);
	}

	/**
	 * Return a string represent the JSON structure of data with newline, tabs(with spaces) and spaces and with double quotas for keys and values, null value represent without quotas.
	 * for the array and objects call toString() of these classes.
	 * @return String
	 */
	public String toString(int _indent) {
		String out="";

		out+=printIndent(_indent)+"{\n";

		for(int i=0;i<elements.size();i++) {
			out+=elements.get(i).toString(_indent);
			if(i<elements.size()-1) { out+=",\n"; }
		}

		out+="\n"+printIndent(_indent)+"}";

		return out;
	}
}
