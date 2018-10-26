package einmaleins;

public class Tuple<X, Y> { 
	public X x; 
	public Y y; 
	public X getX() {
		return x;
	}
	public void setX(X x) {
		this.x = x;
	}
	public Y getY() {
		return y;
	}
	public void setY(Y y) {
		this.y = y;
	}
	public Tuple(X x, Y y) { 
		this.x = x; 
		this.y = y; 
	} 
	
	@Override
	public boolean equals (Object object) {
	    boolean result = false;
	    if (object == null || object.getClass() != getClass()) {
	        result = false;
	    } else {
	    	Tuple<X, Y> tuple = (Tuple<X, Y>) object;
	        if (this.x.equals(tuple.getX()) && this.y.equals(tuple.getY())) {
	            result = true;
	        }
	    }
	    return result;
	}
} 