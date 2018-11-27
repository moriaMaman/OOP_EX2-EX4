package Geom;


public class myGeom_element implements Geom_element {

	Point3D coordinate;
	String [] line;
	
	public myGeom_element(String[]l)
	{
		this.line=new String [l.length];
		for(int i=0;i<l.length;i++) {
			this.line[i]=l[i];
		}
		double x=Double.parseDouble(this.line[6]);
		double y=Double.parseDouble(this.line[7]);
		double z=Double.parseDouble(this.line[8]);
		this.coordinate=new Point3D(x,y,z);
	}
	@Override
	public double distance3D(Point3D p) {
		// TODO Auto-generated method stub
		return (coordinate.distance3D(p));
	}

	@Override
	public double distance2D(Point3D p) {
		// TODO Auto-generated method stub
		return (coordinate.distance2D(p));
	}

}
