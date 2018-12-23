package GIS;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;



public class myGIS_layer implements GIS_layer {

	HashSet<GIS_element> elSet;
	Meta_data data;
	long time;

	public myGIS_layer() {
		// TODO Auto-generated constructor stub
		
		elSet = new HashSet<GIS_element>();
		this.data=new layerMeta_data();
		this.time=data.getUTC();
	}

	@Override
	public boolean add(GIS_element arg0) {
		// TODO Auto-generated method stub

		return elSet.add(arg0);

	}

	@Override
	public boolean addAll(Collection <?extends GIS_element> arg0) {
		// TODO Auto-generated method stub
		return elSet.addAll(arg0);

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		elSet.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return elSet.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return elSet.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return elSet.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return elSet.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub

		return elSet.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub

		return elSet.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return elSet.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elSet.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return elSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {	
		// TODO Auto-generated method stub
		return elSet.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		return this.data;
	}

}
