package it.freelogix.gejson;

abstract public class GeJSONEntity {
	public final static int TYPE_NO=0;
	public final static int TYPE_OBJECT=10;
	public final static int TYPE_ARRAY=20;
	public final static int TYPE_DATA=30;
	
	protected int entityType=TYPE_NO;
	
	public GeJSONEntity(int _entityType) {
		entityType=_entityType;
	}
	
	public int getEntityType() {
		return entityType;
	}
	
	// -------------------------------------------------------------------------
	
	public String getEntityTypeToString() {
		switch(entityType) {
		case TYPE_OBJECT:
			return "JSON Object";
		case TYPE_ARRAY:
			return "JSON Array";
		case TYPE_DATA:
			return "JSON Data";
		default:
			return "No type";
		}
	}
	
	/**
	 * Returns a string containing as many spaces as specified by the passed parameter(_indent)
	 * @param Integer _indent 
	 * @return String
	 */
	protected String printIndent(int _indent) {
		String out="";
		for(int a=0; a<_indent;a++) {
			out+="    ";
		}
		return out;
	}
	
	protected Object parent=null;
	protected int parentType=PARENT_NONE;

	public static final int PARENT_NONE=0;
	public static final int PARENT_TYPE_OBJECT=100;
	public static final int PARENT_TYPE_ARRAY=200;

	public Object getParent() {
		return parent;
	}

	public void setParent(Object _parent, int _parentType) {
		parent=_parent;
		parentType=_parentType;
	}

	public int getParentType() {
		return parentType;
	}

	public boolean hasParent() {
		if(parent!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Ritorna l'entità radice di tutta la struttura, risale tutti i parenti dell'entità corrente fino a quella iniziale 
	 * che non possiede parenti
	 * @return GeJSONObject
	 */
	public GeJSONObject getParentRoot() {
		Object parent =null;
		int currentParentType=0;

		boolean next=true;

		while(next) {

			if(currentParentType==0) {
				currentParentType=getParentType();

				switch(currentParentType) {
				case PARENT_TYPE_OBJECT:
					parent=((GeJSONObject)getParent());
					currentParentType=PARENT_TYPE_OBJECT;
					break;
				case PARENT_TYPE_ARRAY:
					parent=((GeJSONArray)getParent());
					currentParentType=PARENT_TYPE_ARRAY;
					break;
				}
			}else {
				switch(currentParentType) {
				case PARENT_TYPE_OBJECT:
					if(!((GeJSONObject)parent).hasParent()) next=false;
					currentParentType=((GeJSONObject)parent).getParentType();
					parent=((GeJSONObject)parent).getParent();
					break;
				case PARENT_TYPE_ARRAY:
					if(!((GeJSONArray)parent).hasParent()) next=false;
					currentParentType=((GeJSONArray)parent).getParentType();
					parent=((GeJSONArray)parent).getParent();
					break;
				}
			}
		}

		return ((GeJSONObject)parent);
	}
	
	/**
	 * Fa diventare l'entità corrente come root della sua struttura. ma solo per l'entità corrente, le altre entita della struttura manterranno il root
	 * che hanno. è pensato per quando si estrae una parte della struttura.
	 * se su questa entità verra chiamato il metodo getParentRoot questo retituirà null, se chiamato hasParent verrà restituito false
	 */
	public void setAsParentRoot() {
		parent=null;
		parentType=PARENT_NONE;
	}
	
}
