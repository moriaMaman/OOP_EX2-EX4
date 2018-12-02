package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class myGIS_project implements GIS_project{

	HashSet<GIS_layer> elproject; 
	Meta_data data;
	long time;
	
	public myGIS_project() {
		elproject=new HashSet<GIS_layer>();
		data=new projectMeta_data();
		this.time=data.getUTC();
	}
	@Override
	public boolean add(GIS_layer arg0) {
		// TODO Auto-generated method stub
		return elproject.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		// TODO Auto-generated method stub
		return elproject.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		elproject.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return elproject.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return elproject.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return elproject.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return elproject.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return elproject.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return elproject.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return elproject.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elproject.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return elproject.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return elproject.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		return this.data;
	}
	public String toString() {
		Iterator <GIS_layer> it = this.elproject.iterator();
		String s="";
		int i=1;
		while(it.hasNext())
		{
			GIS_layer lay=it.next();
			s=s+it.toString();
			s=s+"\n"+"number "+i+" file:";
			i++;
		}
		return s;
	}

}
