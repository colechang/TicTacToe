import java.util.LinkedList;

public class Dictionary implements DictionaryADT{
	Dictionary h;
	int size;
	int amount = 0;
	private LinkedList<Record>[] hashtable;
	
	public Dictionary(int size) {
		this.size = size;
		hashtable = new LinkedList[size];
		for(int i=0; i<hashtable.length; i++) {
			hashtable[i] = new LinkedList<Record>();
		}
	}
	
	private int hashfunction(String config) {
		int value = (int)config.charAt(config.length()-1);
		int prime = 11;
		for(int i = config.length() -2; i>=0; i--) {
			value = ((value * prime) + (int)config.charAt(i)) % size;
 		}
		return value%size;
	}
	public int insert(Record pair) throws DictionaryException {
		int index = hashfunction(pair.getConfig());
		Record record = recordgetter(pair.getConfig());
		if(record == null) {
			hashtable[index].add(pair);
			return 0;
		}
		else if(record.equals(pair)) {
			hashtable[index].add(pair);
			return 1;
		}
		else {
			throw new DictionaryException();
		}
	}

	public void remove(String config) throws DictionaryException {
		int index = hashfunction(config);
		Record record = recordgetter(config);
		if(record== null){
			throw new DictionaryException();
		}
		else {
			int key = hashfunction(config);
			hashtable[key].remove(record);
			record = null;
	}
	}
	public int get(String config) {
		int key = hashfunction(config);
		
		if (hashtable[key] != null) {
			for (Record record : hashtable[key]) {
				if ((record.getConfig()).equals(config)) {
					return record.getScore();
				}
			}
		}
		return -1;
	}

	
	public int numElements() {
		return hashtable.length + 1;
	}
	private Record recordgetter(String config) {

		int key = hashfunction(config);
		
		if (hashtable[key] != null) { 			//if key is not null
			for (Record record : hashtable[key]) {		//iterate through the table
				if ((record.getConfig()).equals(config)) {		//return the config that matches
					return record;
				}
			}
		}
		return null;		//return null if config isnt found
	}
	

}
