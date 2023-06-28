package it.freelogix.gejson;

import java.util.ArrayList;
import java.util.List;

public class GeJSONArray extends GeJSONEntity{
	/**
	 * Create an empty array
	 */
	public GeJSONArray() {
		super(GeJSONEntity.TYPE_ARRAY);
	}

	/**
	 * Create an GeJSONArray from an array of integers
	 * @param _vals	Integer[]
	 */
	public GeJSONArray(int[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}

	/**
	 * Create an GeJSONArray from an array of floats
	 * @param _vals	float[]
	 */
	public GeJSONArray(float[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}
	
	/**
	 * Create an GeJSONArray from an array of longs
	 * @param _vals	long[]
	 */
	public GeJSONArray(long[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}

	/**
	 * Create an GeJSONArray from an array of double
	 * @param _vals	double[]
	 */
	public GeJSONArray(double[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}

	/**
	 * Create an GeJSONArray from an array of strings
	 * @param _vals	String[]
	 */
	public GeJSONArray(String[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}

	/**
	 * Create an GeJSONArray from an array of bytes
	 * @param _vals	Bytes[]
	 */
	public GeJSONArray(byte[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}

	/**
	 * Create an GeJSONArray from an array of booleans
	 * @param _vals	boolea[]
	 */
	public GeJSONArray(boolean[] _vals) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_vals.length;i++) {
			elements.add(new GeJSONData(_vals[i]));
		}
	}

	/**
	 * Create an GeJSONArray from an array of GeJSONObjects
	 * @param _vals	GeJSONObject[]
	 */
	public GeJSONArray(GeJSONObject[] _objs) {
		super(GeJSONEntity.TYPE_ARRAY);
		for(int i=0;i<_objs.length;i++) {
			elements.add(new GeJSONData(_objs[i]));
		}
	}

	// --------------------------------------------------------------------------------------------------------

	List<GeJSONData> elements = new ArrayList<GeJSONData>();

	boolean valueDataTypePrintEnabled=false;

	/**
	 * Enables writing of the abbreviation of the data type when toString() or Serialize() is called
	 * @param _b boolean
	 */
	public void printValueDataTypeFlagEnable(boolean _b) {
		valueDataTypePrintEnabled=_b;

		for(int a=0;a<size();a++) {
			elements.get(a).printValueDataTypeFlagEnable(_b);
		}
	}

	/**
	 * Return true if data abbreviation is enable
	 * @see printValueDataTypeFlagEnable(boolean _b)
	 * @return boolean
	 */
	public boolean isEnabledPrintValueDataTypeFlag() {
		return valueDataTypePrintEnabled;
	}

	/**
	 * Return number of elements in json array
	 * @return int
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Remove all elements in json array
	 */
	public void clear() {
		elements.clear();
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Return string represent structure of JSON data without newline
	 * @return String
	 */
	public String serialize() {
		String out="";
		out+="[";

		int size=elements.size();

		for(int a=0;a<size;a++) {
			out+=elements.get(a).serialize();
			if(a<size-1){out+=",";}
		}

		out+="]";

		return out;
	}

	// --------------------------------------------------------------------------------------------------------

	public String toString() {
		return toString(0);
	}

	/**
	 * equal to toString method but with spaces before print the strings, the _indent paramete 
	 * set the number of spaces to add before.
	 * @param _indent
	 * @return String
	 */
	public String toString(int _indent) {
		String out="";
		out+="[\n";
		int size=elements.size();

		for(int a=0;a<size;a++) {
			out+=elements.get(a).toString(_indent);
			if(a<size-1){out+=",";}
			out+="\n";
		}

		out+=printIndent(_indent)+"]";

		return out;
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Add a GeJSONObject to array, return object has been added
	 * @param _obj	GeJSONObject
	 * @return	GeJSONObject
	 */
	public GeJSONObject add(GeJSONObject _obj) {
		GeJSONData d = new GeJSONData(_obj);
		_obj.setParent(this,PARENT_TYPE_ARRAY);
		elements.add(d);
		return _obj;
	}

	/**
	 * Add a generic java object to array, return object has been added
	 * @param _obj	Object(java object)
	 * @return	Object
	 */
	public Object add(Object _obj) {
		elements.add(new GeJSONData(_obj));
		return _obj;
	}

	/**
	 * Add an integer value to array, return the array
	 * @param _value	Integer
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(int _value) {
		elements.add(new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a float value to the array, return the array
	 * @param _value	Float
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(float _value) {
		elements.add(new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a double value to the array, return the array
	 * @param _value	Double
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(double _value) {
		elements.add(new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a byte value to the array, return the array
	 * @param _value	byte
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(byte _value) {
		elements.add(new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a boolean value to the array, return the array
	 * @param _value	boolean
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(boolean _value) {
		elements.add(new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a string value to the array, return the array
	 * @param _value	String
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(String _value) {
		elements.add(new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a null to the array, return the current array
	 * @return	GeJSONArray
	 */
	public GeJSONArray add() {
		elements.add(new GeJSONData());
		return this;
	}

	/**
	 * Add a GeJSONArray to the array, return the current array
	 * @param _value	GeJSONArray
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(GeJSONArray _array) {
		GeJSONData d = new GeJSONData(_array);
		_array.setParent(this,PARENT_TYPE_ARRAY);
		elements.add(d);
		return _array;
	}

	/**
	 * Add a GeJSONData object to the array, return the current array
	 * @param _value	GeJSONData
	 * @return	GeJSONArray
	 */
	public GeJSONArray add(GeJSONData _data) {
		elements.add(_data);
		return this;
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Add a GeJSONObject to the array at the specified index, return the object that has been added
	 * @param _index	Integer
	 * @param _obj		GeJSONObject
	 * @return	GeJSONObject
	 */
	public GeJSONObject addAt(int _index,GeJSONObject _obj) {
		GeJSONData d = new GeJSONData(_obj);
		_obj.setParent(this,PARENT_TYPE_ARRAY);
		elements.add(_index,d);
		return _obj;
	}

	public GeJSONArray addAt(int _index,GeJSONArray _array) {
		GeJSONData d = new GeJSONData(_array);
		_array.setParent(this,PARENT_TYPE_ARRAY);
		elements.add(_index,d);
		return _array;
	}

	/**
	 * Add an integer to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	Integer
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,int _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a float to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	Float
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,float _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}
	
	/**
	 * Add a float to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	Long
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,long _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a double to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	Double
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,double _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a byte to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	Byte
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,byte _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a boolean to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	boolean
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,boolean _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a string to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	String
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,String _value) {
		elements.add(_index,new GeJSONData(_value));
		return this;
	}

	/**
	 * Add a null to the array at the specified index, return the array
	 * @param _index	Integer
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index) {
		elements.add(_index,new GeJSONData());
		return this;
	}

	/**
	 * Add a GeJSONData object to the array at the specified index, return the array
	 * @param _index	Integer
	 * @param _value	GeJSONData
	 * @return	GeJSONArray
	 */
	public GeJSONArray addAt(int _index,GeJSONData _data) {
		elements.add(_index,_data);
		return this;
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Set value at the specified index with an GeJSONObject, return the GeJSONObject that has been seted
	 * @param _index	Integer
	 * @param _value	String
	 * @return	GeJSONObject
	 */
	public void set(int _index, GeJSONObject _obj) {
		GeJSONData d = new GeJSONData(_obj);
		_obj.setParent(this,PARENT_TYPE_ARRAY);
		elements.set(_index, d);
	}

	/**
	 * Set value at the specified index with an integer, return the array
	 * @param _index	Integer
	 * @param _value	Integer
	 * @return	GeJSONArray
	 */
	public void set(int _index,int _value) {
		elements.set(_index,new GeJSONData(_value));
	}

	/**
	 * Set value at the specified index with a float, return the array
	 * @param _index	Integer
	 * @param _value	Float
	 * @return	GeJSONArray
	 */
	public void set(int _index,float _value) {
		elements.set(_index,new GeJSONData(_value));
	}
	
	/**
	 * Set value at the specified index with a long, return the array
	 * @param _index	Integer
	 * @param _value	Long
	 * @return	GeJSONArray
	 */
	public void set(int _index,long _value) {
		elements.set(_index,new GeJSONData(_value));
	}

	/**
	 * Set value at the specified index with a double, return the array
	 * @param _index	Integer
	 * @param _value	Double
	 * @return	GeJSONArray
	 */
	public void set(int _index,double _value) {
		elements.set(_index,new GeJSONData(_value));
	}

	/**
	 * Set value at the specified index with a byte, return the array
	 * @param _index	Integer
	 * @param _value	Byte
	 * @return	GeJSONArray
	 */
	public void set(int _index,byte _value) {
		elements.set(_index,new GeJSONData(_value));
	}

	/**
	 * Set value at the specified index with a string, return the array
	 * @param _index	Integer
	 * @param _value	String
	 * @return	GeJSONArray
	 */
	public void set(int _index,String _value) {
		elements.set(_index,new GeJSONData(_value));
	}

	/**
	 * Set value at the specified index with a object, return the array
	 * @param _index	Integer
	 * @param _value	Object
	 * @return	GeJSONArray
	 */
	public void set(int _index,Object _value) {
		elements.set(_index,new GeJSONData(_value));
	}

	/**
	 * Set value at the specified index with a null, return the array
	 * @param _index	Integer
	 * @return	GeJSONArray
	 */
	public void set(int _index) {
		elements.set(_index,new GeJSONData());
	}

	/**
	 * Set value at the specified index with a GeJSONArray, return the original array
	 * @param _index	Integer
	 * @param _value	GeJSONArray
	 * @return	GeJSONArray
	 */
	public void set(int _index,GeJSONArray _array) {
		GeJSONData d = new GeJSONData(_array);
		_array.setParent(this,PARENT_TYPE_ARRAY);
		elements.set(_index, d);
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Remove element from the array by index
	 * @param _index Integer
	 */
	public void remove(int _index) {
		elements.remove(_index);
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Return a element of the array from it's index, as a GeJSONObject
	 * @param _index int
	 * @return GeJSONObject
	 */
	public GeJSONObject getObject(int _index) {
		return elements.get(_index).getObject();
	}

	/**
	 * Return a element of the array from it's index, as Integer
	 * @param _index Integer
	 * @return Integer
	 */
	public int getInt(int _index) {
		return elements.get(_index).getInt();
	}

	/**
	 * Return a element of the array from it's index, as float
	 * @param _index Integer
	 * @return Float
	 */
	public float getFloat(int _index) {
		return elements.get(_index).getFloat();
	}
	
	/**
	 * Return a element of the array from it's index, as long
	 * @param _index Integer
	 * @return Long
	 */
	public long getLong(int _index) {
		return elements.get(_index).getLong();
	}

	/**
	 * Return a element of the array from it's index, as double
	 * @param _index Integer
	 * @return Double
	 */
	public double getDouble(int _index) {
		return elements.get(_index).getDouble();
	}

	/**
	 * Return a element of the array from it's index, as Byte
	 * @param _index Integer
	 * @return Byte
	 */
	public byte getByte(int _index) {
		return elements.get(_index).getByte();
	}

	/**
	 * Return a element of the array from it's index, as String
	 * @param _index Integer
	 * @return String
	 */
	public String getString(int _index) {
		return elements.get(_index).getString();
	}

	/**
	 * Return a element of the array from it's index, as array
	 * @param _index Integer
	 * @return GeJSONArray
	 */
	public GeJSONArray getArray(int _index) {
		return elements.get(_index).getArray();
	}

	/**
	 * Return a element of the array from it's index, as GeJSONData
	 * @param _index Integer
	 * @return GeJSONData
	 */
	public GeJSONData getData(int _index) {
		return elements.get(_index);
	}

	/**
	 * Return a element of the array from it's index, as Java Object
	 * @param _index Integer
	 * @return Object
	 */
	public Object get(int _index) {
		return elements.get(_index).get();
	}

	// --------------------------------------------------------------------------------------------------------

	/**
	 * Return index of GeJSONData element
	 * @param _obj	GeJSONData
	 * @return	Integer
	 */
	public int indexOf(GeJSONData _obj) {
		return elements.indexOf(_obj);
	}

	/**
	 * Return last index of GeJSONData element
	 * @param _obj	GeJSONData
	 * @return	Integer
	 */
	public int lastIndexOf(GeJSONData _obj) {
		return elements.lastIndexOf(_obj);
	}

	/**
	 * Return true if array is empty
	 * @return	boolean
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * Return an array with the elements get between start index and end index
	 * @param _indexStart	Integer
	 * @param _indexEnd		Integer
	 * @return	GeJSONArray
	 */
	public GeJSONArray getSubList(int _indexStart, int _indexEnd) {
		GeJSONArray a = new GeJSONArray();
		for(int i=_indexStart;i<=_indexEnd;i++) {
			a.add(elements.get(i));
		}
		return a;
	}
}
