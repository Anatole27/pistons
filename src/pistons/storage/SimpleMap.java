package pistons.storage;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

import pistons.solfege.Note;

public class SimpleMap{

	private Vector<Note> keys;
	private Vector<BufferedImage> values;
	
	public SimpleMap() {
		keys = new Vector<Note>();
		values = new Vector<BufferedImage>();
	}


	public int size() {
		return keys.size();
	}

	
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	
	public boolean containsKey(Note key) {
		Iterator<Note> keyIt = keys.iterator();
		while(keyIt.hasNext()) {
			if(keyIt.next().equals(key))
				return true;
		}
		return false;
	}

	
	public boolean containsValue(Object value) {
		Iterator<BufferedImage> valueIt = values.iterator();
		while(valueIt.hasNext()) {
			if(valueIt.next().equals(value))
				return true;
		}
		return false;
	}

	
	public BufferedImage get(Note key) {
		Iterator<Note> keyIt = keys.iterator();
		int idx = 0;
		while(keyIt.hasNext()) {
			if(keyIt.next().equals(key)) {
				return values.get(idx);
			}
			idx++;
		}
		return null;
	}

	
	public Object put(Note key, BufferedImage value) {
		keys.add(key);
		values.add(value);
		return null;
	}

	
	public BufferedImage remove(Object key) {
		Iterator<Note> keyIt = keys.iterator();
		int idx = 0;
		while(keyIt.hasNext()) {
			if(keyIt.next().equals(key)) {
				keys.remove(idx);
				values.remove(idx);
			}
			idx++;
		}
		return null;
	}

	
	public void clear() {
		keys.clear();
		values.clear();
		
	}

}
